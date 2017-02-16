package win.sinno.simgp3.common.util;

import org.junit.Assert;
import org.junit.Test;
import win.sinno.smgp3.common.util.LongMsgContentSplitUtil;

import java.util.List;

/**
 * TODO
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午5:53
 */
public class LongMsgContentSplitUtilTest {

    @Test
    public void testSplit() {

        String longMsg = "亲爱的朋友，很抱歉的告知您因为断货这次未能给您发货，所以只能选择给您退款了。再次跟您说声对不起，很理解您的心情。下次到货要月底了，如果可以等的话可以月底拍哦，到时候我们可以给您小优惠哒。再次感谢您对我们的信任和光顾，祝您生活愉快。退订回T";

        List<String> msg = LongMsgContentSplitUtil.split(longMsg);

        for (int i = 0; i < msg.size(); i++) {
            System.out.println(msg.get(i));
        }
        Assert.assertEquals(msg.size(), 2);

        System.out.println("---------");

        longMsg = longMsg + longMsg;

        msg = LongMsgContentSplitUtil.split(longMsg);

        for (int i = 0; i < msg.size(); i++) {
            System.out.println(msg.get(i));
        }
        Assert.assertEquals(msg.size(), 4);

    }
}
