package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.protocol.message.SmgpLoginResp;

/**
 * smgp login resp message decoder
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午3:45
 */
public class SmgpLoginRespDecoder implements ISmgpMessageDecoder<SmgpLoginResp> {

    private SmgpLoginRespDecoder() {
    }

    private static class SmgpLoginRespDecoderHolder {
        private static final SmgpLoginRespDecoder INSTANCE = new SmgpLoginRespDecoder();
    }

    public static SmgpLoginRespDecoder getInstance() {
        return SmgpLoginRespDecoderHolder.INSTANCE;
    }

    /**
     * @param bytes
     * @return
     */
    @Override
    public SmgpLoginResp decoder(byte[] bytes) {
        return null;
    }
}
