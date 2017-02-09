package win.sinno.smgp3.protocol.body;

/**
 * login resp message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午3:28
 */
public class SmgpLoginRespBody implements ISmgpBody {

    private Integer status;

    private String authenticatorServer;

    private Integer serverVersion;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthenticatorServer() {
        return authenticatorServer;
    }

    public void setAuthenticatorServer(String authenticatorServer) {
        this.authenticatorServer = authenticatorServer;
    }

    public Integer getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(Integer serverVersion) {
        this.serverVersion = serverVersion;
    }
}
