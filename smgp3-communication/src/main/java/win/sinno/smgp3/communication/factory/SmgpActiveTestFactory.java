package win.sinno.smgp3.communication.factory;

import win.sinno.smgp3.common.util.SequenceIdGenerator;
import win.sinno.smgp3.protocol.constant.SmgpRequestEnum;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpActiveTest;

/**
 * ActiveTest
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/13 下午2:53
 */
public class SmgpActiveTestFactory {

    public static Builder builder() {
        return Builder.INSTANCE;
    }

    public static class Builder {
        static final Builder INSTANCE = new Builder();

        private int sequenceId;

        private Builder() {
        }

        private void sequenceId(int sequenceId) {
            this.sequenceId = sequenceId;
        }

        public SmgpActiveTest build() {
            SmgpHeader smgpHeader = new SmgpHeader();
            smgpHeader.setRequestId(SmgpRequestEnum.ACTIVE_TEST.getId());

            if (sequenceId <= 0) {
                sequenceId = SequenceIdGenerator.nextSeqId();
            }

            smgpHeader.setSequenceId(sequenceId);

            SmgpActiveTest smgpActiveTest = new SmgpActiveTest(smgpHeader);
            return smgpActiveTest;
        }
    }
}
