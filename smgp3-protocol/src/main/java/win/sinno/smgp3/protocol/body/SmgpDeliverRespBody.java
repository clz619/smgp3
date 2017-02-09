package win.sinno.smgp3.protocol.body;

/**
 * smgp deliver resp message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:41
 */
public class SmgpDeliverRespBody implements ISmgpBody {

    /**
     * 短消息流水号
     */
    private String msgId;

    /**
     * 请求返回结果
     */
    private Integer status;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
