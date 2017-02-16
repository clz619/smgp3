package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.header.SmgpHeader;

/**
 * smgp header encode 编码器
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午4:03
 */
public class SmgpHeaderEncoder implements ISmgpMessageEncoder<SmgpHeader> {

    private SmgpHeaderEncoder() {

    }

    private static class SmgpHeaderEncoderHolder {
        private static final SmgpHeaderEncoder HOLDER = new SmgpHeaderEncoder();
    }

    public static SmgpHeaderEncoder getInstance() {
        return SmgpHeaderEncoderHolder.HOLDER;
    }

    /**
     * 编码
     *
     * @param smgpHeader
     * @return
     */
    @Override
    public byte[] encode(SmgpHeader smgpHeader) {

        byte[] bytes = new byte[12];

        int packetLength = smgpHeader.getPacketLength();
        int requestId = smgpHeader.getRequestId();
        int sequenceId = smgpHeader.getSequenceId();

        ByteUtil.int2byte(packetLength, bytes, 0);
        ByteUtil.int2byte(requestId, bytes, 4);
        ByteUtil.int2byte(sequenceId, bytes, 8);

        return bytes;
    }

//    /**
//     * 将smgp header 编码储存至 bytes offset+12 字节区间段
//     *
//     * @param smgpHeader
//     * @param bytes
//     * @param offset
//     */
//    public static void encode(SmgpHeader smgpHeader, byte[] bytes, int offset) {
//
//        int packetLength = smgpHeader.getPacketLength();
//        int requestId = smgpHeader.getRequestId();
//        int sequenceId = smgpHeader.getSequenceId();
//
//        ByteUtil.int2byte(packetLength, bytes, offset);
//        ByteUtil.int2byte(requestId, bytes, offset + 4);
//        ByteUtil.int2byte(sequenceId, bytes, offset + 8);
//    }
}
