package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.tlv.TpUdhiMessage;

/**
 * smgp tp udhi message decoder
 * <p>
 * support 6 bit udhi
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午3:33
 */
public class SmgpTpUdhiMessageDecoder implements ISmgpMessageDecoder<TpUdhiMessage> {

    private SmgpTpUdhiMessageDecoder() {
    }

    private static class SmgpTpUdhiMessageDecoderHolder {
        private static SmgpTpUdhiMessageDecoder HOLDER = new SmgpTpUdhiMessageDecoder();
    }

    public static SmgpTpUdhiMessageDecoder getInstance() {
        return SmgpTpUdhiMessageDecoderHolder.HOLDER;
    }

    /**
     * @param bytes
     * @return
     */
    @Override
    public TpUdhiMessage decode(byte[] bytes) {

        TpUdhiMessage msg = new TpUdhiMessage();

        msg.setSign(ByteUtil.byte2int(bytes[3]));
        msg.setTn(ByteUtil.byte2int(bytes[4]));
        msg.setIdx(ByteUtil.byte2int(bytes[5]));

        return msg;
    }
}
