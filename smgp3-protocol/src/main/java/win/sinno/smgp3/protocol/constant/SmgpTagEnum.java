package win.sinno.smgp3.protocol.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * smgp tag
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午2:41
 */
public enum SmgpTagEnum {

    TP_PID("tpPid", 0x0001),

    TP_UDHI("tpUdhi", 0x0002),

    LINK_ID("linkId", 0x0003),

    CHARGE_USER_TYPE("chargeUserType", 0x0004),

    CHARGE_TERM_TYPE("chargeTermType", 0x0005),

    CHARGE_TERM_PSEUDO("chargeTermPseudo", 0x0006),

    DEST_TERM_TYPE("destTermType", 0x0007),

    DEST_TERM_PSEUDO("destTermPseudo", 0x0008),

    PK_TOTAL("pkTotal", 0x0009),

    PK_NUMBER("pkNumber", 0x000a),

    SUBMIT_MSG_TYPE("submitMsgType", 0x000b),

    SP_DEAL_RESLT("spDealReslt", 0x000c),

    SRC_TERM_TYPE("srcTermType", 0x000d),

    SRC_TERM_PSEUDO("srcTermPseudo", 0x000e),

    NODES_COUNT("nodesCount", 0x000f),

    MSG_SRC("msgSrc", 0x0010),

    SRC_TYPE("srcType", 0x0011),

    MSERVICE_ID("mserviceId", 0x0012);

    SmgpTagEnum(String name, Integer val) {
        this.name = name;
        this.val = val;
    }

    private String name;

    private Integer val;

    public String getName() {
        return name;
    }

    public Integer getVal() {
        return val;
    }


    private static Map<Integer, SmgpTagEnum> smgpTagMap = new HashMap<Integer, SmgpTagEnum>();

    public static SmgpTagEnum getById(int id) {

        SmgpTagEnum tagEnum = smgpTagMap.get(id);

        if (tagEnum == null) {

            synchronized (SmgpStatusEnum.class) {

                tagEnum = smgpTagMap.get(id);

                if (tagEnum == null) {
                    SmgpTagEnum[] tagEnums = SmgpTagEnum.values();

                    for (SmgpTagEnum tag : tagEnums) {

                        if (tag.getVal() == id) {
                            tagEnum = tag;
                            break;
                        }
                    }

                    smgpTagMap.put(id, tagEnum);
                }

            }
        }

        return tagEnum;
    }
}
