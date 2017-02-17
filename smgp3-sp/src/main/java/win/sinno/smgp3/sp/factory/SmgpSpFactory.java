package win.sinno.smgp3.sp.factory;

import win.sinno.smgp3.sp.SmgpSp;

/**
 * smgp sp factory
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/17 上午10:35
 */
public class SmgpSpFactory {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;

        private String host;

        private int port;

        private String spId;

        private String spPwd;

        private String spSrcTermId;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder spId(String spId) {
            this.spId = spId;
            return this;
        }

        public Builder spPwd(String spPwd) {
            this.spPwd = spPwd;
            return this;
        }

        public Builder spSrcTermId(String spSrcTermId) {
            this.spSrcTermId = spSrcTermId;
            return this;
        }


        public SmgpSp build() {

            return new SmgpSp(
                    name,
                    host,
                    port,
                    spId,
                    spPwd,
                    spSrcTermId);

        }

    }
}
