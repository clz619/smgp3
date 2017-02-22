package win.sinno.smgp3.communication.factory;

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

    public static Builder builder(Integer sequenceId) {
        return new Builder(sequenceId);
    }

    public static class Builder {

        private Integer sequenceId;

        private Builder(Integer sequenceId) {
            this.sequenceId = sequenceId;
        }

        public SmgpActiveTest build() {
            SmgpHeader smgpHeader = new SmgpHeader();
            smgpHeader.setRequestId(SmgpRequestEnum.ACTIVE_TEST.getId());
            smgpHeader.setSequenceId(sequenceId);

            SmgpActiveTest smgpActiveTest = new SmgpActiveTest(smgpHeader);
            return smgpActiveTest;
        }
    }
}
