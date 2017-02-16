package win.sinno.smgp3.protocol.model;

/**
 * “状态报告格式”采用SMPP V3.4中的规定，即“id:IIIIIIIIII sub:SSS dlvrd:DDD Submit date:YYMMDDhhmm done date: YYMMDDhhmm stat:DDDDDDD err:E Text:……”
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 上午11:51
 */
public class SmgpReportMessage {

    /**
     * 状态报告对应原短消息的MsgID
     * <p>
     * 短消息流水号，用来唯一标识一条短消息。
     * 该字段在短消息的转发处理流程中保持唯一。
     * MsgId字段包含以下三部分内容：
     * SMGW代码：3字节（BCD码）
     * 编码规则如下：
     * 3位区号（不足前添0）+2位设备类别+1位序号
     * 区号：所在省长途区号
     * 设备类别：SMGW取06
     * 序号：所在省的设备编码，例如第一个网关编号为1
     * 时间：4字节（BCD码），格式为MMDDHHMM（月日时分）
     * 序列号：3字节（BCD码），取值范围为000000～999999，从0开始，顺序累加，步长为1，循环使用。
     * 例如某SMGW的代码为010061，在2003年1月16日下午5时0分收到一条短消息，这条短消息的MsgID为：0x01006101161700012345，其中010061表示SMGW代码，01161700表示接收时间，012345表示消息序列号。
     * <p>
     * 字符串长度 为 20
     */
    private String id;

    /**
     * 取缺省值001
     */
    private String sub;

    /**
     * 取缺省值001
     */
    private String dlvrd;

    /**
     * 短消息提交时间（格式：年年月月日日时时分分，例如010331200000）
     */
    private String submitDate;

    /**
     * 短消息下发时间（格式：年年月月日日时时分分，例如010331200000）
     */
    private String doneDate;

    /**
     * 短消息的最终状态
     * <p>
     * {@link win.sinno.smgp3.protocol.constant.SmgpReportStatEnum}
     */
    private String stat;

    private String err;

    /**
     * FIXME 17个字节怎么表示短消息的内容？
     * <p>
     * 前3个字节，表示短消息长度（用ASCII码表示），
     * 后17个字节表示短消息的内容（保证内容不出现乱码）
     */
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDlvrd() {
        return dlvrd;
    }

    public void setDlvrd(String dlvrd) {
        this.dlvrd = dlvrd;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SmgpReportMessage{" +
                "id='" + id + '\'' +
                ", sub='" + sub + '\'' +
                ", dlvrd='" + dlvrd + '\'' +
                ", submitDate='" + submitDate + '\'' +
                ", doneDate='" + doneDate + '\'' +
                ", stat='" + stat + '\'' +
                ", err='" + err + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
