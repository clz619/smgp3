package win.sinno.smgp3.communication.factory;

import win.sinno.smgp3.protocol.body.SmgpSubmitBody;
import win.sinno.smgp3.protocol.constant.SmgpRequestEnum;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpSubmit;

/**
 * smgp submit factory
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/14 上午11:11
 */
public class SmgpSubmitFactory {

    public static Builder builder(Integer sequenceId) {
        return new Builder(sequenceId);
    }


    public static class Builder {

        private Integer sequenceId;

        private String spId;

        private String msgContent;

        private String mobile;

        private String serviceId;

        private String validTime;

        private String atTime;

        private String srcTermId;

        private String chargeTermId = "";

        private Builder(Integer sequenceId) {
            this.sequenceId = sequenceId;
        }

        public Builder spId(Integer sequenceId) {
            this.sequenceId = sequenceId;
            return this;
        }

        public Builder spId(String spId) {
            this.spId = spId;
            return this;
        }

        public Builder msgContent(String msgContent) {
            this.msgContent = msgContent;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder validTime(String validTime) {
            this.validTime = validTime;
            return this;
        }

        public Builder atTime(String atTime) {
            this.atTime = atTime;
            return this;
        }

        public Builder srcTermId(String srcTermId) {
            this.srcTermId = srcTermId;
            return this;
        }

        public Builder chargeTermId(String chargeTermId) {
            this.chargeTermId = chargeTermId;
            return this;
        }

        public SmgpSubmit build() {

            SmgpHeader header = new SmgpHeader();
            header.setRequestId(SmgpRequestEnum.SUBMIT.getId());
            header.setSequenceId(sequenceId);

            SmgpSubmitBody body = new SmgpSubmitBody();
            body.setDestTermId(mobile);
            body.setMsgContent(msgContent);
            if (serviceId != null) {
                body.setServiceId(serviceId);
            }
            if (validTime != null) {
                body.setValidTime(validTime);
            }

            if (atTime != null) {
                body.setAtTime(atTime);
            }

            if (srcTermId != null) {
                body.setSrcTermId(srcTermId);
            }
            if (chargeTermId != null) {
                body.setChargeTermId(chargeTermId);
            }
            //提交
            SmgpSubmit smgpSubmit = new SmgpSubmit();
            smgpSubmit.setHeader(header);
            smgpSubmit.setBody(body);

            return smgpSubmit;
        }

    }
}
