package win.sinno.smgp3.protocol.body;

import win.sinno.smgp3.protocol.constant.Tlv;

/**
 * submit message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午3:30
 */
public class SmgpSubmitBody implements ISmgpBody {

    //-------必选参数

    /**
     * 短消息类型
     */
    private int msgType;

    /**
     * SP是否要求返回状态报告
     */
    private int needReport;

    /**
     * 短消息发送优先级
     */
    private int priority;

    /**
     * 业务代码
     */
    private String serviceId;

    /**
     * 收费类型
     */
    private String feeType;

    /**
     * 资费代码
     */
    private String feeCode;

    /**
     * 包月费／封顶费
     */
    private String fixedFee;

    /**
     * 短消息格式
     */
    private int msgFormat;

    /**
     * 短消息有效时间
     */
    private String validTime;

    /**
     * 短消息定时发送时间
     */
    private String atTime;

    /**
     * 短信息发送方号码
     */
    private String srcTermId;

    /**
     * 计费用户号码
     */
    private String chargeTermId;

    /**
     * 短消息接收号码总数
     */
    private int destTermIdCount;

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

    //------------可选参数

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
     * 信息内容的来源
     */
    private Tlv msgSrc;

    /**
     * 计费用户类型
     */
    private Tlv chargeUserType;

    /**
     * 计费用户的号码类型
     */
    private Tlv chargeTermType;

    /**
     * 计费用户的伪码
     */
    private Tlv changeTermPseudo;

    /**
     * 短消息接收方号码的类型
     */
    private Tlv destTermType;

    /**
     * 短消息接收方的伪码
     */
    private Tlv destTermPseudo;

    /**
     * 相同MsgID的消息总条数
     */
    private Tlv pkTotal;

    /**
     * 相同MsgID的消息序号
     */
    private Tlv pkNumber;

    /**
     * SP发送的消息类型
     */
    private Tlv submitMsgType;

    /**
     * sp对消息的处理结果
     */
    private Tlv spDealResult;

    /**
     * 业务代码(用于移动网业务)
     */
    private Tlv mServiceId;

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getNeedReport() {
        return needReport;
    }

    public void setNeedReport(int needReport) {
        this.needReport = needReport;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(String fixedFee) {
        this.fixedFee = fixedFee;
    }

    public int getMsgFormat() {
        return msgFormat;
    }

    public void setMsgFormat(int msgFormat) {
        this.msgFormat = msgFormat;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }

    public String getSrcTermId() {
        return srcTermId;
    }

    public void setSrcTermId(String srcTermId) {
        this.srcTermId = srcTermId;
    }

    public String getChargeTermId() {
        return chargeTermId;
    }

    public void setChargeTermId(String chargeTermId) {
        this.chargeTermId = chargeTermId;
    }

    public int getDestTermIdCount() {
        return destTermIdCount;
    }

    public void setDestTermIdCount(int destTermIdCount) {
        this.destTermIdCount = destTermIdCount;
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

    public Tlv getMsgSrc() {
        return msgSrc;
    }

    public void setMsgSrc(Tlv msgSrc) {
        this.msgSrc = msgSrc;
    }

    public Tlv getChargeUserType() {
        return chargeUserType;
    }

    public void setChargeUserType(Tlv chargeUserType) {
        this.chargeUserType = chargeUserType;
    }

    public Tlv getChargeTermType() {
        return chargeTermType;
    }

    public void setChargeTermType(Tlv chargeTermType) {
        this.chargeTermType = chargeTermType;
    }

    public Tlv getChangeTermPseudo() {
        return changeTermPseudo;
    }

    public void setChangeTermPseudo(Tlv changeTermPseudo) {
        this.changeTermPseudo = changeTermPseudo;
    }

    public Tlv getDestTermType() {
        return destTermType;
    }

    public void setDestTermType(Tlv destTermType) {
        this.destTermType = destTermType;
    }

    public Tlv getDestTermPseudo() {
        return destTermPseudo;
    }

    public void setDestTermPseudo(Tlv destTermPseudo) {
        this.destTermPseudo = destTermPseudo;
    }

    public Tlv getPkTotal() {
        return pkTotal;
    }

    public void setPkTotal(Tlv pkTotal) {
        this.pkTotal = pkTotal;
    }

    public Tlv getPkNumber() {
        return pkNumber;
    }

    public void setPkNumber(Tlv pkNumber) {
        this.pkNumber = pkNumber;
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

    public Tlv getmServiceId() {
        return mServiceId;
    }

    public void setmServiceId(Tlv mServiceId) {
        this.mServiceId = mServiceId;
    }
}
