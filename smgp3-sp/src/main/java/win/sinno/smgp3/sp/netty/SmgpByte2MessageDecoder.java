package win.sinno.smgp3.sp.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import win.sinno.smgp3.common.config.LoggerConfigs;
import win.sinno.smgp3.protocol.header.SmgpHeader;

import java.util.List;

/**
 * smgp 粘包 处理
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/13 上午11:31
 */
public class SmgpByte2MessageDecoder extends ByteToMessageDecoder {

    private static final Logger LOG = LoggerConfigs.SMGP3_LOG;

    /**
     * Decode the from one {@link ByteBuf} to an other. This method will be called till either the input
     * {@link ByteBuf} has nothing to read when return from this method or till nothing was read from the input
     * {@link ByteBuf}.
     *
     * @param channelHandlerContext the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param byteBuf               the {@link ByteBuf} from which to read data
     * @param list                  the {@link List} to which decoded messages should be added
     * @throws Exception is thrown if an error accour
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        int byteBufLen = byteBuf.readableBytes();

        if (byteBufLen < SmgpHeader.HEADER_LENGTH) {
            //小于协议头长度的，不做处理
            LOG.warn("smgp byte 2 message decoder readable byte buf len:{} < smgp header len:12.wait transport.undo", byteBufLen);
            return;
        }

        byteBuf.markReaderIndex();
        //协议包长度
        int packetLen = byteBuf.readInt();
        byteBuf.resetReaderIndex();

        if (packetLen <= 0) {
            //协议 长度负数
            LOG.warn("smgp byte 2 message decoder get header packet len:{} is negative.undo", packetLen);
            return;
        }

        if (byteBufLen < packetLen) {
            //未传输完的，不做处理
            LOG.warn("smgp byte 2 message decoder readable byte buf len:{} < smgp packet len:{}.wait transport.undo", new Object[]{byteBufLen, packetLen});
            return;
        }

        list.add(byteBuf.readRetainedSlice(packetLen));
    }

}
