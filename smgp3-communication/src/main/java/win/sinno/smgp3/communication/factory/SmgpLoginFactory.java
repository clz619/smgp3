package win.sinno.smgp3.communication.factory;

import win.sinno.common.util.DateUtil;
import win.sinno.smgp3.protocol.constant.SmgpConfigs;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        //        private
        private String spId;

        private String spPwd;

        private int loginMode;


        private int clientVersion = SmgpConfigs.CLIENT_VERSION;

        //当前时间戳
        private long nowTimestamp;

        private String timestampStr;

        private int timestamp;

        private Builder() {
            nowTimestamp = System.currentTimeMillis();
            timestampStr = DateUtil.format(nowTimestamp, TIMESTAMP_FORMAT);
            timestamp = Integer.valueOf(timestampStr);
        }

        public SmgpLogin build() {
            SmgpLogin login = new SmgpLogin();

            return login;
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
    }
}
