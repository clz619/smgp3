package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.body.SmgpLoginRespBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;
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
        SmgpLoginResp resp = new SmgpLoginResp(bytes);

        SmgpHeader header = SmgpHeaderDecoder.decoder(bytes);

        SmgpLoginRespBody body = new SmgpLoginRespBody();

        //status
        int offset = 12;
        int status = ByteUtil.byte2int(bytes, offset);

        offset += 4;

        //authServer TODO paser
        offset += 16;

        //serverVersion
        int serverVersion = bytes[offset];

        body.setStatus(status);
        body.setServerVersion(serverVersion);

        resp.setHeader(header);
        resp.setBody(body);

        return resp;
    }
}
