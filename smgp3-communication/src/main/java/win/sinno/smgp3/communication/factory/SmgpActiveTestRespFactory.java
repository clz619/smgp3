package win.sinno.smgp3.communication.factory;

import win.sinno.smgp3.common.util.SequenceIdGenerator;
import win.sinno.smgp3.protocol.constant.SmgpRequestEnum;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpActiveTestResp;

/**
 * ActiveTest
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/13 下午2:53
 */
public class SmgpActiveTestRespFactory {

    public static Builder builder() {
        return Builder.INSTANCE;
    }

    public static class Builder {
        static final Builder INSTANCE = new Builder();

        private int sequenceId;

        private Builder() {
        }

        public Builder sequenceId(int sequenceId) {
            this.sequenceId = sequenceId;
            return this;
        }

        public SmgpActiveTestResp build() {
            SmgpHeader smgpHeader = new SmgpHeader();
            smgpHeader.setRequestId(SmgpRequestEnum.ACTIVE_TEST.getId());

            if (sequenceId <= 0) {
                sequenceId = SequenceIdGenerator.nextSeqId();
            }

            smgpHeader.setSequenceId(sequenceId);

            SmgpActiveTestResp smgpActiveTest = new SmgpActiveTestResp(smgpHeader);
            return smgpActiveTest;
        }
    }
}
