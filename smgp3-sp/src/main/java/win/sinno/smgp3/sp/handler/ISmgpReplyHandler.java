package win.sinno.smgp3.sp.handler;

import win.sinno.smgp3.protocol.model.SmgpReplyMessage;

/**
 * smgp reply handler
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午2:08
 */
public interface ISmgpReplyHandler {

    /**
     * handler reply msg
     *
     * @param smgpReplyMessage
     */
    void handler(SmgpReplyMessage smgpReplyMessage);
}
