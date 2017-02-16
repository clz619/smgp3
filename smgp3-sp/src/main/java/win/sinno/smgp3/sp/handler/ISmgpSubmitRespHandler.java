package win.sinno.smgp3.sp.handler;

import win.sinno.smgp3.protocol.message.SmgpSubmitResp;

/**
 * smgp submit resp message handler
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午2:12
 */
public interface ISmgpSubmitRespHandler {

    void handler(SmgpSubmitResp smgpSubmitResp);
}
