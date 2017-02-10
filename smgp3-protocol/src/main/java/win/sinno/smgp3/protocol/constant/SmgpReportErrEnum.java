package win.sinno.smgp3.protocol.constant;

/**
 * smgp 短信 回执 错误代码表
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午2:10
 */
public enum SmgpReportErrEnum {

    SUCCESS("000", SmgpReportStatEnum.DELIVRD, "成功"),

    MOBILE_CANNOT_COMMUNICATION("001", SmgpReportStatEnum.EXPIRED, "用户不能通信"),

    MOBILE_BUSY("002", SmgpReportStatEnum.EXPIRED, "用户忙"),

    TERMINAL_NO_THIS("003", SmgpReportStatEnum.UNDELIV, "终端无此部件号"),

    ILLEGAL_MOBILE("004", SmgpReportStatEnum.UNDELIV, "非法用户"),

    MOBILE_IN_BLACK("005", SmgpReportStatEnum.UNDELIV, "用户在黑名单内"),

    SYSTEM_ERR("006", SmgpReportStatEnum.UNDELIV, "系统错误"),

    MOBILE_MEMORY("007", SmgpReportStatEnum.EXPIRED, "用户内存满"),

    NOT_INFO_TERMINAL("008", SmgpReportStatEnum.UNDELIV, "非信息终端"),

    DATA_ERR("009", SmgpReportStatEnum.UNDELIV, "数据错误"),

    DATA_LOSE("010", SmgpReportStatEnum.UNDELIV, "数据丢失"),

    UNKNOWN("999", SmgpReportStatEnum.UNKNOWN, "未知错误");

    SmgpReportErrEnum(String val, SmgpReportStatEnum stat, String descr) {
        this.val = val;
        this.stat = stat;
        this.descr = descr;
    }

    /**
     * 状态值（字符串）
     */
    private String val;

    /**
     * 对应状态
     */
    private SmgpReportStatEnum stat;

    /**
     * 说明
     */
    private String descr;

    public String getVal() {
        return val;
    }

    public SmgpReportStatEnum getStat() {
        return stat;
    }

    public String getDescr() {
        return descr;
    }
}
