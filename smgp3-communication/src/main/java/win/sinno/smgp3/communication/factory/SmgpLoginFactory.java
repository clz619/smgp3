package win.sinno.smgp3.communication.factory;

import win.sinno.common.util.DateUtil;
import win.sinno.smgp3.protocol.body.SmgpLoginBody;
import win.sinno.smgp3.protocol.constant.SmgpConfigs;
import win.sinno.smgp3.protocol.constant.SmgpLoginModeEnum;
import win.sinno.smgp3.protocol.constant.SmgpRequestEnum;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpLogin;

/**
 * smgp login factory
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午4:57
 */
public class SmgpLoginFactory {

    private static final String TIMESTAMP_FORMAT = "MMddHHmmss";

    //固定长度为42
    private static final int PACKAGE_LEN = 42;

    public static Builder builder(Integer sequenceId) {
        return new Builder(sequenceId);
    }

    public static class Builder {

        private String spId;

        private String spPwd;

        private int loginMode = SmgpLoginModeEnum.TRANSMIT_MODE.getId();

        private int clientVersion = SmgpConfigs.CLIENT_VERSION;

        //当前时间戳
        private long nowTimestamp;

        private String timestampStr;

        private int timestamp;

        private Integer sequenceId;

        private Builder(Integer sequenceId) {
            nowTimestamp = System.currentTimeMillis();
            timestampStr = DateUtil.format(nowTimestamp, TIMESTAMP_FORMAT);
            timestamp = Integer.valueOf(timestampStr);
            this.sequenceId = sequenceId;
        }


        public Builder spId(String spId) {
            this.spId = spId;
            return this;
        }

        public Builder spPwd(String spPwd) {
            this.spPwd = spPwd;
            return this;
        }

        public Builder loginMode(int loginMode) {
            this.loginMode = loginMode;
            return this;
        }

        public Builder clientVersion(int clientVersion) {
            this.clientVersion = clientVersion;
            return this;
        }

        public SmgpLogin build() {

            SmgpHeader header = new SmgpHeader();
            header.setPacketLength(PACKAGE_LEN);
            header.setRequestId(SmgpRequestEnum.LOGIN.getId());
            header.setSequenceId(sequenceId);

            SmgpLoginBody body = new SmgpLoginBody();
            body.setClientId(spId);
            body.setLoginMode(loginMode);
            body.setTimeStamp(timestamp);
            body.setTimeStampyyMMddmmss(timestampStr);
            body.setClientVersion(clientVersion);

            SmgpLogin login = new SmgpLogin();

            login.setSpPwd(spPwd);
            login.setHeader(header);
            login.setBody(body);

            return login;
        }
    }
}
