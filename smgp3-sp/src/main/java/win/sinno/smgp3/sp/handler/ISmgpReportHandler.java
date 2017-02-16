package win.sinno.smgp3.sp.handler;

import win.sinno.smgp3.protocol.model.SmgpReportMessage;

/**
 * smgp report handler
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午2:07
 */
public interface ISmgpReportHandler {

    /**
     * handler smgp report message
     *
     * @param smgpReportMessage
     */
    void handler(SmgpReportMessage smgpReportMessage);
}
