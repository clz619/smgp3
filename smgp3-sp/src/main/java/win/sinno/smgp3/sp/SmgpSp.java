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
import win.sinno.smgp3.communication.decoder.SmgpLoginRespDecoder;
import win.sinno.smgp3.communication.encoder.SmgpLoginEncoder;
import win.sinno.smgp3.communication.factory.SmgpLoginFactory;
import win.sinno.smgp3.protocol.body.SmgpLoginRespBody;
import win.sinno.smgp3.protocol.constant.SmgpRequestEnum;
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

    private static final SmgpLoginEncoder SMGP_LOGIN_ENCODER = SmgpLoginEncoder.getInstance();

    private static final SmgpLoginRespDecoder SMGP_LOGIN_RESP_DECODER = SmgpLoginRespDecoder.getInstance();

    private static final LengthFieldBasedFrameDecoder LENGTH_FIELD_BASED_FRAME_DECODER = new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0);

    private static final SmgpByte2MessageDecoder SMGP_BYTE_2_MESSAGE_DECODER = new SmgpByte2MessageDecoder();


    private String name;
    private String host;
    private int port;
    private String spId;
    private String spPwd;

    private ChannelFuture channelFuture;

    public SmgpSp() {
    }

    public SmgpSp(String name, String host, int port, String spId, String spPwd) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.spId = spId;
        this.spPwd = spPwd;
    }


    public synchronized void startConnect() throws InterruptedException {
        final SmgpMessageHandler smgpMessageHandler = new SmgpMessageHandler(this);

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

                ch.pipeline().addFirst(LENGTH_FIELD_BASED_FRAME_DECODER)
                        .addLast(SMGP_BYTE_2_MESSAGE_DECODER)
                        .addLast(smgpMessageHandler);
            }
        });

        try {

            channelFuture = bootstrap.connect(host, port).sync();

            connect();

            channelFuture.channel().closeFuture().sync();
        } finally {

            LOG.info("sp shutdown host:{},port:{}...", new Object[]{host, port});

            eventLoopGroup.shutdownGracefully();
        }


    }

    //是否连接
    private boolean isConnect() {
        return channelFuture != null
                && channelFuture.channel() != null
                && channelFuture.channel().isOpen();
    }

    private void connect() {
        SmgpLogin smgpLogin = SmgpLoginFactory.builder()
                .spId(spId)
                .spPwd(spPwd)
                .build();

        sendLogin(smgpLogin);
    }

    /**
     * send smgp message
     *
     * @param message
     */
    @Override
    public void send(byte[] message) {

        if (isConnect()) {
            LOG.info("sp:{} send:{}", new Object[]{name, message});

            ByteBuf byteBuf = Unpooled.wrappedBuffer(message);

            channelFuture.channel().writeAndFlush(byteBuf);
        } else {
            LOG.info("unconnect.can not send..");
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

        LOG.info("login resp status:{}", status);
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
}
