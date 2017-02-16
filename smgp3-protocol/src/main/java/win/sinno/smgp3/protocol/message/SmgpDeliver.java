package win.sinno.smgp3.protocol.message;

import win.sinno.smgp3.protocol.body.SmgpDeliverBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.model.SmgpReportMessage;

/**
 * smgp Deliver message
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:44
 */
public class SmgpDeliver extends SmgpMessage<SmgpHeader, SmgpDeliverBody> {

    private SmgpReportMessage smgpReportMessage;

    public SmgpReportMessage getSmgpReportMessage() {
        return smgpReportMessage;
    }

    public void setSmgpReportMessage(SmgpReportMessage smgpReportMessage) {
        this.smgpReportMessage = smgpReportMessage;
    }

    @Override
    public String toString() {
        return "SmgpDeliver{" +
                "smgpReportMessage=" + smgpReportMessage +
                "} " + super.toString();
    }
}
