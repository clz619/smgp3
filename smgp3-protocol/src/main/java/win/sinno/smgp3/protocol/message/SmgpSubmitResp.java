package win.sinno.smgp3.protocol.message;

import win.sinno.smgp3.protocol.body.SmgpSubmitRespBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;

/**
 * smgp SubmitResp message
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:28
 */
public class SmgpSubmitResp extends SmgpMessage<SmgpHeader, SmgpSubmitRespBody> {

    public SmgpSubmitResp(SmgpHeader header, SmgpSubmitRespBody body) {
        super(header, body);
    }

    public SmgpSubmitResp(byte[] bytes, SmgpHeader header, SmgpSubmitRespBody body) {
        super(bytes, header, body);
    }

}
