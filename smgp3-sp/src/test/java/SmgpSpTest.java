import org.junit.Test;
import win.sinno.smgp3.sp.SmgpSp;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/13 下午1:59
 */
public class SmgpSpTest {

    private static String host = "";
    private static int port = 8890;
    private static String spId = "";
    private static String spPwd = "";

    @Test
    public void loginTest() {
        SmgpSp smgpSp = new SmgpSp("test", host, port, spId, spPwd);
        try {
            smgpSp.connect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(200000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
