package win.sinno.smgp3.protocol.body;

/**
 * login message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午3:15
 */
public class SmgpLoginBody implements ISmgpBody {

    /**
     * 客户端用来登录服务器端的用户账号。
     * <p>
     * 当客户端为SP时，用户帐号为SP服务代码；
     * 当客户端为SMGW时，用户帐号为SMGW代码；
     * 当客户端为GNS时，用户帐号为GNS代码。
     *
     * @return
     */
    private String clientId;
    /**
     * 客户端认证码，用来鉴别客户端的合法性。
     * 其值通过单向MD5 hash计算得出，表示如下：
     * AuthenticatorClient =MD5（ClientID+7 字节的二进制0（0x00） + Shared secret+Timestamp）
     * Shared secret 由服务器端与客户端事先商定，最长15字节。
     * 此处Timestamp格式为：MMDDHHMMSS（月日时分秒），经TimeStamp字段值转换成字符串，转换后右对齐，左补0x30得到。
     * 例如3月1日0时0分0秒，TimeStamp字段值为0x11F0E540，此处为0301000000。
     *
     * @return
     */
    private String authenticatorClient;
    /**
     * 客户端用来登录服务器端的登录模式。
     * 0＝发送短消息（send mode）；
     * 1＝接收短消息（receive mode）；
     * 2＝收发短消息（transmit mode）；
     * 其它保留。
     *
     * @return
     */
    private int loginMode;
    /**
     * 时间戳
     *
     * @return
     */
    private int timeStamp;

    /**
     * 客户端支持的协议版本号
     *
     * @return
     */
    private int clientVersion;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public String getAuthenticatorClient() {
        return authenticatorClient;
    }

    public void setAuthenticatorClient(String authenticatorClient) {
        this.authenticatorClient = authenticatorClient;
    }


    public int getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(int loginMode) {
        this.loginMode = loginMode;
    }


    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }


    public int getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(int clientVersion) {
        this.clientVersion = clientVersion;
    }
}
