package win.sinno.smgp3.protocol.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 短消息状态
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午1:24
 */
public enum SmgpReportStatEnum {

    DELIVRD("DELIVERED", "DELIVRD", "短消息转发成功"),

    EXPIRED("EXPIRED", "EXPIRED", "短消息超过有效期"),

    DELETED("DELETED", "DELETED", "短消息已经被删除"),

    UNDELIV("UNDELIVERABLE", "UNDELIV", "短消息是不可转发的"),

    ACCEPTD("ACCEPTED", "ACCEPTD", "短消息已经被最终用户接收（为保持与SMPP兼容，保留）"),

    UNKNOWN("UNKNOWN", "UNKNOWN", "未知短消息状态"),

    DTBLACK("DTBLACK", "DTBLACK", "目的号码是黑名单号码"),

    REJECTD("REJECTED", "REJECTD", "短消息被拒绝（为保持与SMPP兼容，保留）"),

    VMFLUCTL("VMFLUCTL", "VMFLUCTL", "虚拟号码超流速（系统生成）"),

    OTHER("OTHER", "OTHER", "网关内部状态"),;

    SmgpReportStatEnum(String name, String stat, String descr) {
        this.name = name;
        this.stat = stat;
        this.descr = descr;
    }

    /**
     * 状态名
     */
    private String name;

    /**
     * 状态值
     */
    private String stat;

    /**
     * 说明
     */
    private String descr;

    public String getName() {
        return name;
    }

    public String getStat() {
        return stat;
    }

    public String getDescr() {
        return descr;
    }

    private static Map<String, SmgpReportStatEnum> smgpReportStatEnumMap = new HashMap<String, SmgpReportStatEnum>();

    public static SmgpReportStatEnum getByStat(String stat) {

        SmgpReportStatEnum smgpReportStatEnum = smgpReportStatEnumMap.get(stat);

        if (smgpReportStatEnum == null) {

            synchronized (SmgpReportStatEnum.class) {

                smgpReportStatEnum = smgpReportStatEnumMap.get(stat);

                if (smgpReportStatEnum == null) {

                    SmgpReportStatEnum[] smgpReportStatEnums = SmgpReportStatEnum.values();

                    for (SmgpReportStatEnum statEnum : smgpReportStatEnums) {

                        if (statEnum.getStat().equals(stat)) {
                            smgpReportStatEnum = statEnum;

                            break;
                        }
                    }

                    if (smgpReportStatEnum == null) {
                        smgpReportStatEnum = OTHER;
                    }

                    smgpReportStatEnumMap.put(stat, smgpReportStatEnum);

                }
            }

        }

        return smgpReportStatEnum;
    }
}
