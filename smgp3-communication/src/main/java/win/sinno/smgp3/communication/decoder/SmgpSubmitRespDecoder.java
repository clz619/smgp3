package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.body.SmgpSubmitRespBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpSubmitResp;

/**
 * smgp submit resp decode
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/14 上午10:59
 */
public class SmgpSubmitRespDecoder implements ISmgpMessageDecoder<SmgpSubmitResp> {

    private SmgpSubmitRespDecoder() {
    }

    private static class SmgpSubmitRespDecoderHolder {
        private static final SmgpSubmitRespDecoder INSTANCE = new SmgpSubmitRespDecoder();
    }

    public static SmgpSubmitRespDecoder getInstance() {
        return SmgpSubmitRespDecoderHolder.INSTANCE;
    }

    /**
     * @param bytes
     * @return
     */
    @Override
    public SmgpSubmitResp decode(byte[] bytes) {
        SmgpHeader header = SmgpHeaderDecoder.getInstance().decode(bytes);

        SmgpSubmitRespBody body = new SmgpSubmitRespBody();

        byte[] msgIdBytes = new byte[10];
        System.arraycopy(bytes, 12, msgIdBytes, 0, 10);

        body.setMsgId(ByteUtil.byteArray2HexString(msgIdBytes));

        body.setStatus(ByteUtil.byte2int(bytes, 22));

        SmgpSubmitResp smgpSubmitResp = new SmgpSubmitResp(bytes, header, body);

        return smgpSubmitResp;
    }
}
