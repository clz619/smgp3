package win.sinno.smgp3.communication.decoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * smgp msg decoder test
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 上午11:50
 */
public class SmgpMsgIdDecoderTest {

    @Test
    public void testMsgId() {
        byte[] bytes = {2, 3, 113, 2, 20, 21, 87, 65, 88, 121};

        Assert.assertEquals("02037102141557415879", SmgpMsgIdDecoder.decode(bytes));

    }
}
