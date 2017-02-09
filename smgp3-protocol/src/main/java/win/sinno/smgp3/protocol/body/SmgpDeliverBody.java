package win.sinno.smgp3.protocol.body;

import win.sinno.smgp3.protocol.define.Tlv;

/**
 * smgp deliver message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:29
 */
public class SmgpDeliverBody implements ISmgpBody {

    //--------必选参数
    /**
     * 短消息流水号
     */
    private String msgId;

    /**
     * 是否为状态报告
     */
    private int isReport;

    /**
     * 短消息格式
     */
    private int msgFormat;

    /**
     * 短消息接收时间
     */
    private String recvTime;

    /**
     * 短消息发送号码
     */
    private String srcTermId;

    /**
     * 短消息接收号码
     */
    private String destTermId;

    /**
     * 短消息长度
     */
    private int msgLength;

    /**
     * 短消息内容
     */
    private String msgContent;

    /**
     * 保留
     */
    private String reserve;

    //--------可选参数

    /**
     * GSM协议类型
     */
    private Tlv tpPid;

    /**
     * GSM协议类型
     */
    private Tlv tpUdhi;

    /**
     * 交易标识
     */
    private Tlv linkId;

    /**
     * 短消息发送方的号码类型
     */
    private Tlv srcTermType;

    /**
     * 短消息发送方的伪码
     */
    private Tlv srcTermPseudo;

    /**
     * sp发送的短信类型
     */
    private Tlv submitMsgType;

    /**
     * sp对消息的处理结果
     */
    private Tlv spDealResult;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getIsReport() {
        return isReport;
    }

    public void setIsReport(int isReport) {
        this.isReport = isReport;
    }

    public int getMsgFormat() {
        return msgFormat;
    }

    public void setMsgFormat(int msgFormat) {
        this.msgFormat = msgFormat;
    }

    public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
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

    public int getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(int msgLength) {
        this.msgLength = msgLength;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public Tlv getTpPid() {
        return tpPid;
    }

    public void setTpPid(Tlv tpPid) {
        this.tpPid = tpPid;
    }

    public Tlv getTpUdhi() {
        return tpUdhi;
    }

    public void setTpUdhi(Tlv tpUdhi) {
        this.tpUdhi = tpUdhi;
    }

    public Tlv getLinkId() {
        return linkId;
    }

    public void setLinkId(Tlv linkId) {
        this.linkId = linkId;
    }

    public Tlv getSrcTermType() {
        return srcTermType;
    }

    public void setSrcTermType(Tlv srcTermType) {
        this.srcTermType = srcTermType;
    }

    public Tlv getSrcTermPseudo() {
        return srcTermPseudo;
    }

    public void setSrcTermPseudo(Tlv srcTermPseudo) {
        this.srcTermPseudo = srcTermPseudo;
    }

    public Tlv getSubmitMsgType() {
        return submitMsgType;
    }

    public void setSubmitMsgType(Tlv submitMsgType) {
        this.submitMsgType = submitMsgType;
    }

    public Tlv getSpDealResult() {
        return spDealResult;
    }

    public void setSpDealResult(Tlv spDealResult) {
        this.spDealResult = spDealResult;
    }
}
