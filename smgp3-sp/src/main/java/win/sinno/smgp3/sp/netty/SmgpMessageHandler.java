package win.sinno.smgp3.sp.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import win.sinno.smgp3.common.config.LoggerConfigs;
import win.sinno.smgp3.sp.SmgpSp;

/**
 * smgp message handler
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/13 下午12:00
 */
public class SmgpMessageHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerConfigs.SMGP3_LOG;

    private SmgpSp smgpSp;


    public SmgpMessageHandler(SmgpSp smgpSp) {
        this.smgpSp = smgpSp;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        //可读字节数
        byte[] bytes = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);

        smgpSp.receive(bytes);
    }

    /**
     * 异常捕获，关闭连接，并进行重连
     * <p>
     * TODO 进行重连
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        LOG.error(cause.getMessage(), cause);

        ctx.close();

    }
}
