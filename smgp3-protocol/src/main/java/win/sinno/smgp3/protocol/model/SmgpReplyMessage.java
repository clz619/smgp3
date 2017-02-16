package win.sinno.smgp3.protocol.model;

/**
 * smgp reply
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午1:58
 */
public class SmgpReplyMessage {
    /**
     * 短消息流水号，用来唯一标识一条短消息。
     */
    private String msgId;

    /**
     * 短消息接收时间
     * 格式为YYYYMMDDHHMMSS（年年年年月月日日时时分分秒秒）。
     */
    private String recvDate;

    /**
     * 短信息发送方号码
     */
    private String srcTermId;

    /**
     * 短消息接收号码
     */
    private String destTermId;

    private String msgContent;

    public SmgpReplyMessage() {
    }

    public SmgpReplyMessage(String msgId, String recvDate, String srcTermId, String destTermId, String msgContent) {
        this.msgId = msgId;
        this.recvDate = recvDate;
        this.srcTermId = srcTermId;
        this.destTermId = destTermId;
        this.msgContent = msgContent;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getRecvDate() {
        return recvDate;
    }

    public void setRecvDate(String recvDate) {
        this.recvDate = recvDate;
    }

    public String getSrcTermId() {
        return srcTermId;
    }

    public void setSrcTermId(String srcTermId) {
        this.srcTermId = srcTermId;
    }

    public String getDestTermId() {
        return destTermId;
    }

    public void setDestTermId(String destTermId) {
        this.destTermId = destTermId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public String toString() {
        return "SmgpReplyMessage{" +
                "msgId='" + msgId + '\'' +
                ", recvDate='" + recvDate + '\'' +
                ", srcTermId='" + srcTermId + '\'' +
                ", destTermId='" + destTermId + '\'' +
                ", msgContent='" + msgContent + '\'' +
                '}';
    }
}
