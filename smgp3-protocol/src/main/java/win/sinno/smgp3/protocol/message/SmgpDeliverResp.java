package win.sinno.smgp3.protocol.message;

import win.sinno.smgp3.protocol.body.SmgpDeliverRespBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;

/**
 * smgp DeliverResp message
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:48
 */
public class SmgpDeliverResp extends SmgpMessage<SmgpHeader, SmgpDeliverRespBody> {

    public SmgpDeliverResp() {
    }

    public SmgpDeliverResp(SmgpHeader header, SmgpDeliverRespBody body) {
        super(header, body);
    }

}
