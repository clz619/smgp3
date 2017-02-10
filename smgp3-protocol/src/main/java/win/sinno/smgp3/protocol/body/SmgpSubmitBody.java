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
     * <p>
     * 短消息类型。
     * 对于回执消息该字段无效；对于文本短消息，该字段表示短消息的消息流向：
     * 0＝MO消息（终端发给SP）；
     * 6＝MT消息（SP发给终端，包括WEB上发送的点对点短消息）；
     * 7＝点对点短消息；
     * 其它保留。
     */
    private int msgType = 6;

    /**
     * 是否要求返回状态报告。
     * <p>
     * 0＝不要求返回状态报告；
     * 1＝要求返回状态报告；
     * 其它保留。
     */
    private int needReport = 1;

    /**
     * 短消息发送优先级。
     * <p>
     * 0＝低优先级；
     * 1＝普通优先级；
     * 2＝较高优先级；
     * 3＝高优先级；
     * 其它保留。
     */
    private int priority = 3;

    /**
     * 业务代码，用于固定网业务。
     * <p>
     * 对于MO消息或点对点短消息，该字段无效；
     * 对于MT消息，该字段表示业务代码，是该条短消息所属的业务类别，由数字、字母和符号组合而成。
     * 对于从WEB上发送的点对点短消息，要求业务代码为 “PC2P”，
     * 其它业务代码由SP自定义。
     */
    private String serviceId;

    /**
     * 对计费用户采取的收费类型。
     * <p>
     * 对于MO消息或点对点短消息，该字段无效。对于MT消息，该字段用法如下：
     * <p>
     * 00＝免费，此时FixedFee和FeeCode无效；
     * <p>
     * 01＝按条计信息费，此时FeeCode表示每条费用，FixedFee无效；
     * <p>
     * 02＝按包月收取信息费，此时FeeCode无效，FixedFee表示包月费用；
     * <p>
     * 03＝按封顶收取信息费，若按条收费的费用总和达到或超过封顶费后，则按照封顶费用收取信息费；
     * 若按条收费的费用总和没有达到封顶费用，则按照每条费用总和收取信息费。
     * FeeCode表示每条费用，FixedFee表示封顶费用。
     * <p>
     * 其它保留。
     */
    private String feeType;

    /**
     * FIXME
     * <p>
     * 资费代码
     * <p>
     * 每条短消息费率，单位为“分”。
     * 对于MO消息或点对点短消息，该字段无效；对于MT消息
     */
    private String feeCode;

    /**
     * 短消息的包月费/封顶费，单位为“分”。
     */
    private String fixedFee;

    /**
     * 短消息内容体的编码格式。
     * <p>
     * 0＝ASCII编码；
     * 3＝短消息写卡操作；
     * 4＝二进制短消息；
     * 8＝UCS2编码；
     * 15＝GB18030编码；
     * 246（F6）＝(U)SIM相关消息；
     * 其它保留。
     * <p>
     * 对于文字短消息，要求MsgFormat＝0、8、15。对于回执消息，要求MsgFormat＝0。
     */
    private int msgFormat = 15;

    /**
     * 短消息有效时间，格式遵循SMPP3.3以上版本协议。 FIXME
     * 短消息有效时间在转发过程中保持不变。
     */
    private String validTime;

    /**
     * FIXME
     * 短消息定时发送时间，格式遵循SMPP3.3以上版本协议。
     * 短消息定时发送时间在转发过程中保持不变。
     */
    private String atTime;

    /**
     * 短信息发送方号码
     * <p>
     * 短消息发送方号码。
     * 对于MT消息，SrcTermID格式为“118＋SP服务代码＋其它（可选）”，
     * 例如SP服务代码为1234时，SrcTermID可以为1181234或118123456等。
     * <p>
     * <p>
     * 对于MO消息，固定网中SrcTermID格式为“区号+号码（区号前添零）”，
     * 例如02087310323，07558780808，移动网中SrcTermID格式为MSISDN号码格式。
     * <p>
     * 对于固定网点对点消息，主叫号码为普通终端时，SrcTermID格式为“区号+号码（区号前添零）”；
     * 主叫号码为爱因平台时，SrcTermID格式为“10631＋区号+号码（区号前添零）”。
     */
    private String srcTermId;

    /**
     * 计费用户号码。
     * <p>
     * ChargeTermID为空时，
     * 如果是MT消息，则表示对被叫用户号码计费；
     * 如果是MO或点对点消息，则表示对主叫用户号码计费。
     * <p>
     * ChargeTermID为非空时，表示对计费用户号码计费。
     */
    private String chargeTermId;

    /**
     * 短消息接收号码总数
     * <p>
     * 短消息接收号码总数（≤100），用于SP实现群发短消息。
     */
    private int destTermIdCount;

    /**
     * 短消息接收号码
     * <p>
     * 对于MT消息，DestTermID连续存储DestTermIDCount个号码，每一个接收方号码为21位，
     * 固定网中DestTermID格式为“区号+号码（区号前添零）”，
     * 移动网中DestTermID格式为MSISDN号码格式，不足21位时应左对齐，右补0x00。
     * <p>
     * 对于MO消息，DestTermID格式为“118＋SP服务代码＋其它（可选）”。
     * 对于点对点短消息，DestTermID格式为“区号+号码（区号前添零）” ，不足21位时应左对齐，右补0x00。
     * <p>
     * 对于固定网点对点消息，被叫号码为普通终端时，SrcTermID格式为“区号+号码（区号前添零）”；被叫号码为爱因平台时，SrcTermID格式为“10631＋区号+号码（区号前添零）”。
     */
    private String destTermId;

    /**
     * 短消息长度
     * 指MsgContent域的长度（字节数），取值大于或等于0。
     * 对于MT消息，取值应小于或等于140。
     * 对于文字短消息，msgFormat=0时，一个字符长度是一个字节，其他格式一个字符长度是两个字节
     */
    private int msgLength;

    /**
     * 短消息内容
     * <p>
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
