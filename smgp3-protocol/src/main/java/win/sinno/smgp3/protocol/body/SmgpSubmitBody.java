package win.sinno.smgp3.protocol.body;

/**
 * submit message body
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午3:30
 */
public class SmgpSubmitBody {

    //必选参数

    /**
     * 短消息类型
     */
    private int msgType;

    /**
     * SP是否要求返回状态报告
     */
    private int needReport;

    /**
     *
     */
    private int priority;


    //可选参数

}
