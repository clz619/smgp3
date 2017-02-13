package win.sinno.smgp3.protocol.body;

import win.sinno.smgp3.protocol.constant.SmgpStatusEnum;

/**
 * login resp message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午3:28
 */
public class SmgpLoginRespBody implements ISmgpBody {

    /**
     * 请求返回结果。响应包用来向请求包返回成功信息或者失败原因。
     * <p>
     * {@link SmgpStatusEnum}
     */
    private Integer status;

    /**
     * Login服务器端返回给客户端的认证码，当客户端认证出错时，此项为空。
     * <p>
     * 其值通过单向MD5 hash计算得出，表示如下：
     * AuthenticatorServer =MD5（Status+AuthenticatorClient + Shared secret）
     * Shared secret 由服务器端与客户端事先商定,最长15字节AuthenticatorClient为客户端发送给服务器端的Login中的值。
     */
    private String authenticatorServer;

    /**
     * 服务器端支持的最高版本号。
     * 高4bit表示主版本号，低4bit表示次版本号。
     * 例如0x13，表示协议版本号1.3。
     */
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

    @Override
    public String toString() {
        return "SmgpLoginRespBody{" +
                "status=" + status +
                ", authenticatorServer='" + authenticatorServer + '\'' +
                ", serverVersion=" + serverVersion +
                '}';
    }
}
