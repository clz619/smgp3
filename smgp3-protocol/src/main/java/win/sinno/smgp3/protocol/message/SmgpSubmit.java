package win.sinno.smgp3.protocol.message;

import win.sinno.smgp3.protocol.body.SmgpSubmitBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;

/**
 * smgp Submit message
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:27
 */
public class SmgpSubmit extends SmgpMessage<SmgpHeader, SmgpSubmitBody> {
    public SmgpSubmit() {
    }

    public SmgpSubmit(SmgpHeader header, SmgpSubmitBody body) {
        super(header, body);
    }

    public boolean hasTlv() {
        return getBody().getSmgpTlvCollection() != null && getBody().getSmgpTlvCollection().size() > 0;
    }

}
