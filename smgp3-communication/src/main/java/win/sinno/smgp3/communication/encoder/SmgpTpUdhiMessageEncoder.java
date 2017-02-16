package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.protocol.tlv.TpUdhiMessage;

/**
 * tpudhi message encoder
 * <p>
 * when tlv tpudhi val = 1 ,
 * this msg will add before msgContent byte .
 * <p>
 * use 6 bit tp udhi
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午2:54
 */
public class SmgpTpUdhiMessageEncoder implements ISmgpMessageEncoder<TpUdhiMessage> {

    private SmgpTpUdhiMessageEncoder() {
    }

    private static class SmgpTpUdhiMessageEncoderHolder {
        private static final SmgpTpUdhiMessageEncoder HOLDER = new SmgpTpUdhiMessageEncoder();
    }

    public static SmgpTpUdhiMessageEncoder getInstance() {
        return SmgpTpUdhiMessageEncoderHolder.HOLDER;
    }

    /**
     * 消息编码
     *
     * @param tpUdhiMessage
     * @return
     */
    @Override
    public byte[] encode(TpUdhiMessage tpUdhiMessage) {

        byte[] bytes = new byte[6];

        bytes[0] = 0x05;
        bytes[1] = 0x00;
        bytes[2] = 0x03;
        bytes[3] = (byte) tpUdhiMessage.getSign();
        bytes[4] = (byte) tpUdhiMessage.getTn();
        bytes[5] = (byte) tpUdhiMessage.getIdx();

        return bytes;
    }
}
