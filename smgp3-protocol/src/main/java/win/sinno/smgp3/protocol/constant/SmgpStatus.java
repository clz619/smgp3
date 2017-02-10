package win.sinno.smgp3.protocol.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 登陆请求返回结果
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 上午9:52
 */
public enum SmgpStatus {

    //-----

    SUCCESS("success", 0, "成功"),

    SYSTEM_BUSY("systemBusy", 1, "系统忙"),

    OUT_MAX_CONN_SIZE("outMaxConnSize", 2, "超过最大连接数"),

    //----- 3-9保留

    MESSAGE_STRUCTURE_ERR("messageStructureErr", 10, "消息结构错"),

    REQUEST_ID_ERR("requestIdErr", 11, "命令字错"),

    SEQUENCE_ID_DUPLICATION_ERR("sequenceIdErr", 12, "序列号重复"),

    //----- 13-19保留

    IP_ADDR_ERR("idAddrErr", 20, "ip地址错"),

    AUTH_ERR("authErr", 21, "认证错"),

    VERSION_TOO_HIGH("versionTooHigh", 22, "版本太高"),

    //----- 23-29保留

    ILLEGAL_MSG_TYPE("illegalMsgType", 30, "非法消息类型"),

    ILLEGAL_PRIORITY("illegalPriority", 31, "非法优先级"),

    ILLEGAL_FEE_TYPE("illegalFeeType", 32, "非法资费类型"),

    ILLEGAL_FEE_CODE("illegalFeeCode", 33, "非法资费代码"),

    ILLEGAL_MSG_FORMAT("illegalMsgFormat", 34, "非法短消息格式"),

    ILLEGAL_DATE_FORMAT("illegalDateFormat", 35, "非法时间格式"),

    ILLEGAL_MSG_LENGTH("illegalMsgLength", 36, "非法短消息长度"),

    OUT_OF_VALID_TIME("outOfValidTime", 37, "有效期过期"),

    ILLEGAL_QUERY_TYPE("illegalQueryType", 38, "非法查询类别"),

    ROUTE_ERR("routeErr", 39, "路由错误"),

    ILLEGAL_FIXED_FEE("illegalFixedFee", 40, "非法包月费/封顶费"),

    ILLEGAL_UPDATE_TYPE("illegalUpdateType", 41, "非法更新类型"),

    ILLEGAL_ROUTE_ID("illegalRouteId", 42, "非法路由id"),

    ILLEGAL_SERVICE_ID("illegalServiceId", 43, "非法服务代码"),

    ILLEGAL_VALID_TIME("illegalValidTime", 44, "非法有效时间"),

    ILLEGAL_AT_TIME("illegalAtTime", 45, "非法定时发送时间"),

    ILLEGAL_SRC_TERM_ID("illegalSrcTermId", 46, "非法发送用户号码"),

    ILLEGAL_DEST_TERM_ID("illegalDestTermId", 47, "非法接收用户号码"),

    ILLEGAL_CHARGE_TERM_ID("illegalChargeTermId", 48, "非法计费用户号码"),

    ILLEGAL_SP_CODE("illegalSpCode", 49, "非法sp服务代码"),

    //----- 50-55其它用途

    ILLEGAL_SRC_GATEWAY_ID("illegalSrcGatewayId", 56, "非法源网关代码"),

    ILLEGAL_QUERY_TERM_ID("illegalQueryTermId", 57, "非法查询号码"),

    ROUTE_NOT_MATCH("routeNotMatch", 58, "没有匹配路由"),

    ILLEGAL_SP_TYPE("illegalSpType", 59, "非法sp类型"),

    ILLEGAL_LAST_ROUTE_ID("illegalLastRouteId", 60, "非法上一条路由编号"),

    ILLEGAL_ROUTE_TYPE("illegalRouteType", 61, "非法路由类型"),

    ILLEGAL_DEST_GATEWAY_ID("illegalDestGatewayId", 62, "非法目标网关代码"),

    ILLEGAL_DEST_GATEWAY_IP("illegalDestGatewayIp", 63, "非法目标网关ip"),

    ILLEGAL_DEST_GATEWAY_PORT("illegalDestGatewayPort", 64, "非法目标网关端口"),

    ILLEGAL_ROUTE_TERM_RANGE_ID("illegalRouteTermRangeId", 65, "非法路由号码段"),

    ILLGEAL_PROVINCE_CODE("illegalProvinceCode", 66, "非法终端所属省代码"),

    ILLEGAL_USER_TYPE("illegalUserType", 67, "非法用户类型"),

    NODE_UNSUPPORT_ROUTE_UPDATE("nodeUnsupportRouteUpdate", 68, "本节点不支持路由更新"),

    ILLEGAL_SP_ID("illegalSpId", 69, "非法sp企业代码"),

    ILLEGAL_SP_ACCESS_TYPE("illegalSpAccessType", 70, "非法sp接入类型"),

    ROUTE_UPDATE_FAIL("routeUpdateFail", 71, "路由信息更新失败"),

    ILLEGAL_TIMESTAMP("illegalTimestamp", 72, "非法时间戳"),

    ILLEAGL_MSERVICE_ID("illegalMServiceId", 73, "非法业务代码"),

    SP_FORBID_SEND_TIME_BUCKET("spForbidSendTimeBucket", 74, "sp禁止下发时段"),

    SP_OUT_OF_DAILY_FLOW("spOutOfDailyFlow", 75, "sp发送超过日流量"),

    SP_ACCOUNT_EXPRIE("spAccountExprie", 76, "sp账号过有效期"),


    //---------以下为企业信使自定义代码

    E_ACCOUNT_ERR("eAccountErr", 101, "无此账号或被禁用"),

    E_PWD_ERR("ePwdErr", 102, "密码错"),

    E_SUBMIT_TOO_FAST("eSubmitTooFast", 103, "提交过快（每秒钟提交手机号码个数超过流速限制）"),

    E_SYSTEM_BUSY("eSystemBusy", 104, "系统忙（所有用户提交速率之和超过系统处理能力）"),

    E_ILLEGAL_SRC_ID("eIllegalSrcId", 105, "非法发送号（源号码错误）"),

    E_SENSITIVE_MSG("eSensiticeMsg", 106, "敏感短信"),

    E_MSG_LENGTH_ERR("eMsgLengthErr", 107, "消息长度错，MsgLength = 0，或MsgLength > 140（msgFormat=0时，一个字符是一个字节，其他格式一个字符是两个字节）"),

    E_MOBILE_COUNT_ERR("eMobileCountErr", 108, "手机号个数错（每个submit消息中包含的手机号码个数超过20个）"),

    E_MOBILE_FORMAT_ERR("eMobileFormatErr", 109, "错误手机号"),

    E_IP_ADDR_ERR("eIpAddrErr", 110, "ip错"),

    E_NO_SEND_PERMISSION("eNoSendPermission", 114, "无发送权限"),

    E_NO_FREE_SMS("eNoFreeSms", 115, "没有免费短信"),

    E_BLACK("eBlack", 116, "黑名单企业"),

    E_STOP_SUBMIT("eStopSubmit", 117, "企业被暂停提交"),

    E_VIRTUAL_MOBILE_OUT_OF_FLOW("eVirtualMobileOutOfFlow", 119, "虚拟号码超流控"),


    //------unknow
    UNKNOW("unknow", 999, "未知错");

    SmgpStatus(String name, int id, String descr) {
        this.name = name;
        this.id = id;
        this.descr = descr;
    }

    private String name;

    private int id;

    private String descr;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescr() {
        return descr;
    }

    private static Map<Integer, SmgpStatus> smgpStatusMap = new HashMap<Integer, SmgpStatus>();

    public static SmgpStatus getById(int id) {

        SmgpStatus smgpStatus = smgpStatusMap.get(id);

        if (smgpStatus == null) {

            synchronized (SmgpStatus.class) {

                smgpStatus = smgpStatusMap.get(id);

                if (smgpStatus == null) {
                    SmgpStatus[] smgpStatuses = SmgpStatus.values();

                    for (SmgpStatus status : smgpStatuses) {

                        if (status.getId() == id) {
                            smgpStatus = status;
                            break;
                        }
                    }
                }

                if (smgpStatus == null) {
                    smgpStatus = UNKNOW;
                }

                smgpStatusMap.put(id, smgpStatus);
            }
        }

        return smgpStatus;
    }


}
