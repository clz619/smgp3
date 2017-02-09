package win.sinno.smgp3.protocol.body;

/**
 * smgp submit resp message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:25
 */
public class SmgpSubmitRespBody implements ISmgpBody {

    /**
     * 短消息流水号
     */
    private String msgId;

    /**
     * 请求返回结果
     */
    private int status;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
