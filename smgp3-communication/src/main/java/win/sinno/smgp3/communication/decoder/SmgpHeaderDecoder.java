package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.util.SmgpHeaderUtil;
import win.sinno.smgp3.protocol.header.SmgpHeader;

/**
 * header decode
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午3:55
 */
public class SmgpHeaderDecoder implements ISmgpMessageDecoder<SmgpHeader> {

    private SmgpHeaderDecoder() {
    }

    private static class SmgpHeaderDecoderHolder {
        private static final SmgpHeaderDecoder HOLDER = new SmgpHeaderDecoder();
    }

    public static SmgpHeaderDecoder getInstance() {
        return SmgpHeaderDecoderHolder.HOLDER;
    }

    /**
     * smgp header decode
     *
     * @param bytes
     * @return
     */
    @Override
    public SmgpHeader decode(byte[] bytes) {
        SmgpHeader header = new SmgpHeader();

        int packgeLength = SmgpHeaderUtil.getMessagePacketLength(bytes);
        int requestId = SmgpHeaderUtil.getMessageRequestId(bytes);
        int sequenceId = SmgpHeaderUtil.getMessageSequenceId(bytes);

        header.setPacketLength(packgeLength);
        header.setRequestId(requestId);
        header.setSequenceId(sequenceId);

        return header;
    }

}
