package win.sinno.smgp3.protocol.constant;

/**
 * 客户端用来登录服务器端的登录模式
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 上午9:41
 */
public enum SmgpLoginModeEnum {

    SEND_MODE("sendMode", 0, "发送短消息"),

    RECEIVE_MODE("receiveMode", 1, "接收短消息"),

    TRANSMIT_MODE("transmitMode", 2, "收发短消息"),;

    SmgpLoginModeEnum(String name, int id, String descr) {
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
