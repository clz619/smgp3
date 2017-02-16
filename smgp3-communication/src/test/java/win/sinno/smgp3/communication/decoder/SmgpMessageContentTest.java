package win.sinno.smgp3.communication.decoder;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午3:17
 */
public class SmgpMessageContentTest {

    @Test
    public void testTxt() {
        byte[] bytes = {84, -56, 84, -56};

        try {
            String str = new String(bytes, "iso-10646-ucs-2");

            System.out.println(str);

            System.out.println("--------");


            String haha = "哈哈";

            //gb18030
            byte[] bs = haha.getBytes("GB18030");
            for (byte b : bs) {
                System.out.println(b);
            }

//            UnicodeBigUnmarked
            bs = haha.getBytes("UnicodeBigUnmarked");
            for (byte b : bs) {
                System.out.println(b);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
