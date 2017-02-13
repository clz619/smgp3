package win.sinno.smgp3.sp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import win.sinno.smgp3.common.config.LoggerConfigs;
import win.sinno.smgp3.common.util.SmgpHeaderUtil;
import win.sinno.smgp3.communication.ISmgpCommunication;
import win.sinno.smgp3.communication.decoder.SmgpHeaderDecoder;
import win.sinno.smgp3.communication.decoder.SmgpLoginRespDecoder;
import win.sinno.smgp3.communication.encoder.SmgpHeaderEncoder;
import win.sinno.smgp3.communication.encoder.SmgpLoginEncoder;
import win.sinno.smgp3.communication.factory.SmgpActiveTestFactory;
import win.sinno.smgp3.communication.factory.SmgpActiveTestRespFactory;
import win.sinno.smgp3.communication.factory.SmgpLoginFactory;
import win.sinno.smgp3.protocol.body.SmgpLoginRespBody;
import win.sinno.smgp3.protocol.constant.SmgpRequestEnum;
import win.sinno.smgp3.protocol.constant.SmgpStatusEnum;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpActiveTest;
import win.sinno.smgp3.protocol.message.SmgpActiveTestResp;
import win.sinno.smgp3.protocol.message.SmgpLogin;
import win.sinno.smgp3.protocol.message.SmgpLoginResp;
import win.sinno.smgp3.sp.netty.SmgpByte2MessageDecoder;
import win.sinno.smgp3.sp.netty.SmgpMessageHandler;

/**
 * sp
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/13 上午11:15
 */
public class SmgpSp implements ISmgpCommunication {

    private static final Logger LOG = LoggerConfigs.SMGP3_LOG;


    private String name;
    private String host;
    private int port;
    private String spId;
    private String spPwd;

    private ChannelFuture channelFuture;

    private boolean connectFlag;

    private long lastConnTs;

    private long lastRespTs;

    private long lastActiveTestTs;

    public static final int SEND_TRY_COUNT = 3;

    private static final SmgpLoginEncoder SMGP_LOGIN_ENCODER = SmgpLoginEncoder.getInstance();

    private static final SmgpLoginRespDecoder SMGP_LOGIN_RESP_DECODER = SmgpLoginRespDecoder.getInstance();


    private static final long ACTIVE_TEST_TS = 30000l;

    private static final long ACTIVE_TEST_DEAD_TS = 180000;

    public SmgpSp() {
    }

    public SmgpSp(String name, String host, int port, String spId, String spPwd) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.spId = spId;
        this.spPwd = spPwd;
    }


    public synchronized void connect() throws InterruptedException {


        final LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0);

        final SmgpByte2MessageDecoder smgpByte2MessageDecoder = new SmgpByte2MessageDecoder();

        final SmgpMessageHandler smgpMessageHandler = new SmgpMessageHandler(this);

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

                ch.pipeline().addFirst(lengthFieldBasedFrameDecoder)
                        .addLast(smgpByte2MessageDecoder)
                        .addLast(smgpMessageHandler);
            }
        });

        Thread activeTestThread = new Thread() {

            @Override
            public void run() {

                while (true) {
                    if (connectFlag) {
                        //心跳时间步骤
                        long now = System.currentTimeMillis();
                        long activeTsStep = now - lastActiveTestTs;

                        LOG.info(" active ts step :{}", activeTsStep);

                        if (activeTsStep > ACTIVE_TEST_DEAD_TS
                                && (now - lastConnTs) > ACTIVE_TEST_DEAD_TS) {

                            close();

                            reconnect();
                        } else if (activeTsStep > ACTIVE_TEST_TS) {


                            sendActiveTest(SmgpActiveTestFactory.builder().build());

                            try {
                                Thread.sleep(30000l);
                            } catch (InterruptedException e) {
                                LOG.error(e.getMessage(), e);
                            }
                        } else {
                            try {
                                Thread.sleep(2000l);
                            } catch (InterruptedException e) {
                                LOG.error(e.getMessage(), e);
                            }
                        }
                    } else {
                        try {
                            Thread.sleep(5000l);
                        } catch (InterruptedException e) {
                            LOG.error(e.getMessage(), e);
                        }
                    }
                }

            }
        };

        try {

            channelFuture = bootstrap.connect(host, port).sync();

            sendConnectReq();

            activeTestThread.start();

            channelFuture.channel().closeFuture().sync();
        } finally {

            LOG.info("sp shutdown host:{},port:{}...", new Object[]{host, port});

            eventLoopGroup.shutdownGracefully();

            activeTestThread.interrupt();
        }


    }

    //是否连接
    private boolean isConnect() {
        return channelFuture != null
                && channelFuture.channel() != null
                && channelFuture.channel().isOpen();
    }

    private synchronized void sendConnectReq() {
        SmgpLogin smgpLogin = SmgpLoginFactory.builder()
                .spId(spId)
                .spPwd(spPwd)
                .build();

        sendLogin(smgpLogin);
    }

    public synchronized void reconnect() {
        try {
            if (!isConnect()) {
                setConnectFlag(false);
                connect();
            }
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * send smgp message
     * <p>
     * if fail default try 3 send
     *
     * @param message
     */
    @Override
    public void send(byte[] message) {

        send(message, 0);
    }

    /**
     * send smgp message, if send fail try count
     *
     * @param message
     * @param trycount
     */
    @Override
    public void send(byte[] message, int trycount) {
        try {

            if (trycount >= SEND_TRY_COUNT) {
                LOG.warn("try count >max try count,send fail:{}", message);
                return;
            }

            if (isConnect()) {
                LOG.info("sp:{} send:{}", new Object[]{name, message});

                ByteBuf byteBuf = Unpooled.wrappedBuffer(message);

                channelFuture.channel().writeAndFlush(byteBuf);
            } else {
                LOG.info("unconnect.can not send..");

                connect();

                send(message, ++trycount);
            }
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * receive smgp message
     *
     * @param message
     * @return
     */
    @Override
    public void receive(byte[] message) {
        handlerMessage(message);
    }

    /**
     * send smgp Login message
     *
     * @param smgpLogin
     */
    @Override
    public void sendLogin(SmgpLogin smgpLogin) {
        send(SMGP_LOGIN_ENCODER.encode(smgpLogin));
    }

    /**
     * send smgp ActiveTest message
     *
     * @param smgpActiveTest
     */
    @Override
    public void sendActiveTest(SmgpActiveTest smgpActiveTest) {
        send(SmgpHeaderEncoder.encoder(smgpActiveTest.getHeader()));
    }

    /**
     * send smgp ActiveTestResp message
     *
     * @param smgpActiveTestResp
     */
    @Override
    public void sendActiveTestResp(SmgpActiveTestResp smgpActiveTestResp) {
        send(SmgpHeaderEncoder.encoder(smgpActiveTestResp.getHeader()));
    }

    /**
     * receive -> hander
     *
     * @param bytes
     */
    @Override
    public void handlerMessage(byte[] bytes) {
        int requestId = SmgpHeaderUtil.getMessageRequestId(bytes);

        LOG.info("receive smgp message:{}", bytes);
        SmgpRequestEnum smgpRequestEnum = SmgpRequestEnum.getById(requestId);

        switch (smgpRequestEnum) {

            case LOGIN_RESP:

                handlerLoginResp(SMGP_LOGIN_RESP_DECODER.decoder(bytes));
                break;

            case ACTIVE_TEST:
                handlerActiveTest(new SmgpActiveTest(SmgpHeaderDecoder.decoder(bytes)));
                break;

            case ACTIVE_TEST_RESP:

                handlerActiveTestResp(new SmgpActiveTestResp(SmgpHeaderDecoder.decoder(bytes)));
                break;

            default:
                LOG.info("unkonw smgp message:{}", bytes);
        }

    }

    /**
     * handler smgp LoginResp message
     *
     * @param smgpLoginResp
     */
    @Override
    public void handlerLoginResp(SmgpLoginResp smgpLoginResp) {
        SmgpLoginRespBody body = smgpLoginResp.getBody();
        int status = body.getStatus();

        SmgpStatusEnum smgpStatusEnum = SmgpStatusEnum.getById(status);
        switch (smgpStatusEnum) {
            case SUCCESS:
                setConnectFlag(true);
                lastConnTs = System.currentTimeMillis();
                LOG.info("login success");
                break;

            default:
                LOG.warn("login fail status:{}", status);
        }

        LOG.info("login resp status:{}", status);
    }

    /**
     * handler smgp ActiveTest message
     *
     * @param smgpActiveTest
     */
    @Override
    public void handlerActiveTest(SmgpActiveTest smgpActiveTest) {
        SmgpHeader header = smgpActiveTest.getHeader();

        SmgpActiveTestResp activeTestResp = SmgpActiveTestRespFactory.builder()
                .sequenceId(header.getSequenceId()).build();

        send(SmgpHeaderEncoder.encoder(activeTestResp.getHeader()));
    }

    /**
     * handler smgp ActiveTestResp message
     *
     * @param smgpActiveTestResp
     */
    @Override
    public void handlerActiveTestResp(SmgpActiveTestResp smgpActiveTestResp) {
        lastActiveTestTs = System.currentTimeMillis();
    }

    private void close() {
        LOG.info("sp:{} close now ", name);

        if (channelFuture != null
                && channelFuture.channel() != null
                && channelFuture.channel().isOpen()) {
            channelFuture.channel().close();
        }

        setConnectFlag(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSpPwd() {
        return spPwd;
    }

    public void setSpPwd(String spPwd) {
        this.spPwd = spPwd;
    }

    public boolean isConnectFlag() {
        return connectFlag;
    }

    public void setConnectFlag(boolean connectFlag) {
        this.connectFlag = connectFlag;
    }

    public long getLastConnTs() {
        return lastConnTs;
    }

    public void setLastConnTs(long lastConnTs) {
        this.lastConnTs = lastConnTs;
    }

    public long getLastRespTs() {
        return lastRespTs;
    }

    public void setLastRespTs(long lastRespTs) {
        this.lastRespTs = lastRespTs;
    }

    public long getLastActiveTestTs() {
        return lastActiveTestTs;
    }

    public void setLastActiveTestTs(long lastActiveTestTs) {
        this.lastActiveTestTs = lastActiveTestTs;
    }
}
