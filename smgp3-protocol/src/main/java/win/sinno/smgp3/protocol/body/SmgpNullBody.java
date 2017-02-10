package win.sinno.smgp3.protocol.body;

/**
 * smgp null message 无消息题 的消息使用
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午5:50
 */
public class SmgpNullBody implements ISmgpNullBody {

    private SmgpNullBody() {
    }

    //实例
    public static final SmgpNullBody INSTANCE = new SmgpNullBody();
}
