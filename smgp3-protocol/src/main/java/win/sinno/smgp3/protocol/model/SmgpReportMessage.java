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
    private String txt;

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

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
