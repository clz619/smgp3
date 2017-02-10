package win.sinno.smgp3.protocol.constant;

/**
 * smgp3 request
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午2:32
 */
public enum SmgpRequestEnum {

    LOGIN("Login", 0x00000001, "客户端登陆"),

    LOGIN_RESP("LoginResp", 0x80000001, "客户端登陆应答"),

    SUBMIT("Submit", 0x00000002, "提交短消息"),

    SUBMIT_RESP("SubmitResp", 0x80000002, "提交短消息应答"),

    DELIVER("Deliver", 0x00000003, "下发短消息"),

    DELIVER_RESP("DeliverResp", 0x80000003, "下发短消息应答"),

    ACTIVE_TEST("ActiveTest", 0x00000004, "链路检测"),

    ACTIVE_TEST_RESP("ActiveTestResp", 0x80000004, "链路检测应答"),

    EXIT("Exit", 0x00000006, "退出请求"),

    EXIT_RESP("ExitResp", 0x80000006, "退出应答"),

    QUERY("Query", 0x00000007, "SP统计查询"),

    QUERY_RESP("QueryResp", 0x80000007, "SP统计查询应答"),;

    SmgpRequestEnum(String name, int id, String descr) {
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
}
