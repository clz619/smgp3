package win.sinno.smgp3.communication.decoder;

import org.junit.Assert;
import org.junit.Test;
import win.sinno.smgp3.communication.encoder.SmgpMsgIdEncoder;

/**
 * smgp msgId encoder
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午2:58
 */
public class SmgpMsgIdEncoderTest {

    @Test
    public void testMsgId() {
        byte[] bs = {2, 3, 113, 2, 20, 21, 87, 65, 88, 121};
        String msgId = "02037102141557415879";

        byte[] bytes = SmgpMsgIdEncoder.encode(msgId);
        for (byte b : bytes) {
            System.out.println(b);
        }

        Assert.assertArrayEquals(bs, bytes);

    }
}
