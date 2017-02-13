package win.sinno.smgp3.protocol.body;

import win.sinno.smgp3.protocol.constant.Tlv;

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
     * 短消息流水号，用来唯一标识一条短消息。
     * 该字段在短消息的转发处理流程中保持唯一。
     * <p>
     * MsgId字段包含以下三部分内容：
     * SMGW代码：3字节（BCD码）
     * <p>
     * 编码规则如下：
     * 3位区号（不足前添0）+2位设备类别+1位序号
     * 区号：所在省长途区号
     * <p>
     * 设备类别：SMGW取06
     * 序号：所在省的设备编码，例如第一个网关编号为1
     * 时间：4字节（BCD码），格式为MMDDHHMM（月日时分）
     * 序列号：3字节（BCD码），取值范围为000000～999999，从0开始，顺序累加，步长为1，循环使用。
     * <p>
     * 例如某SMGW的代码为010061，在2003年1月16日下午5时0分收到一条短消息，
     * 这条短消息的MsgID为：0x01006101161700012345，
     * 其中010061表示SMGW代码，
     * 01161700表示接收时间，
     * 012345表示消息序列号。
     */
    private String msgId;

    /**
     * 是否为状态报告
     * <p>
     * 是否为状态报告。
     * 0＝不是状态报告；
     * 1＝是状态报告；
     */
    private int isReport;

    /**
     * 短消息格式
     * <p>
     * 短消息内容体的编码格式。
     * 0＝ASCII编码；
     * 3＝短消息写卡操作；
     * 4＝二进制短消息；
     * 8＝UCS2编码；
     * 15＝GB18030编码；
     * 246（F6）＝(U)SIM相关消息；
     * 其它保留。
     * 对于文字短消息，要求MsgFormat＝0、8、15。对于回执消息，要求MsgFormat＝0。
     */
    private int msgFormat;

    /**
     * 短消息接收时间
     * <p>
     * SMGW接收到短消息的时间。格式为YYYYMMDDHHMMSS（年年年年月月日日时时分分秒秒）。
     */
    private String recvTime;

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
     */
    private int msgLength;

    /**
     * 短消息内容
     * <p>
     * 当IsReport＝1时，MsgContent中内容为状态报告
     * {@link win.sinno.smgp3.protocol.model.SmgpReportMessage}
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

    @Override
    public String toString() {
        return "SmgpDeliverBody{" +
                "msgId='" + msgId + '\'' +
                ", isReport=" + isReport +
                ", msgFormat=" + msgFormat +
                ", recvTime='" + recvTime + '\'' +
                ", srcTermId='" + srcTermId + '\'' +
                ", destTermId='" + destTermId + '\'' +
                ", msgLength=" + msgLength +
                ", msgContent='" + msgContent + '\'' +
                ", reserve='" + reserve + '\'' +
                ", tpPid=" + tpPid +
                ", tpUdhi=" + tpUdhi +
                ", linkId=" + linkId +
                ", srcTermType=" + srcTermType +
                ", srcTermPseudo=" + srcTermPseudo +
                ", submitMsgType=" + submitMsgType +
                ", spDealResult=" + spDealResult +
                '}';
    }
}
