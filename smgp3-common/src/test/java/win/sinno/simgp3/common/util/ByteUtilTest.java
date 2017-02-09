package win.sinno.simgp3.common.util;

import org.junit.Assert;
import org.junit.Test;
import win.sinno.smgp3.common.util.ByteUtil;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 上午11:28
 */
public class ByteUtilTest {

    @Test
    public void test() {
        System.out.println(0xff);
    }

    @Test
    public void testByte2Hex() {
        byte[] bs = new byte[4];
        bs[0] = 34;
        bs[1] = 122;
        bs[2] = 1;
        bs[3] = 12;

        String s1 = ByteUtil.byte2HexString(bs[0]);
        System.out.println(s1);

        String s2 = ByteUtil.byteArray2HexString(bs);
        System.out.println(s2);

        Assert.assertTrue(s2.startsWith(s1));
    }

    @Test
    public void testByte2short() {
        byte[] bs = new byte[2];
        bs[0] = 34;
        bs[1] = 122;
        Assert.assertArrayEquals(bs, ByteUtil.short2byte(ByteUtil.byte2short(bs)));

        short s = 16234;
        byte[] buf = new byte[10];
        int offset = 3;
        ByteUtil.short2byte(s, buf, offset);
        Assert.assertEquals(s, ByteUtil.byte2short(buf, offset));
    }

    @Test
    public void testByte2int() {
        byte[] bs = new byte[4];
        bs[0] = 0;
        bs[1] = 0;
        bs[2] = 1;
        bs[3] = 12;

        int i1 = ByteUtil.byte2int(bs);
        System.out.println(i1);

        int i = 213232;
        byte[] buf = new byte[100];
        int offset = 50;

        Assert.assertTrue(i == ByteUtil.byte2int(ByteUtil.int2byte(i)));

        //with buf offset
        ByteUtil.int2byte(i, buf, offset);
        Assert.assertTrue(i == ByteUtil.byte2int(buf, offset));


        byte[] bytes = new byte[8];
        bytes[0] = 0;
        bytes[1] = 125;
        bytes[2] = 123;
        bytes[3] = 1;
        bytes[4] = 0;
        bytes[5] = 0;
        bytes[6] = 1;
        bytes[7] = 12;
        int i2 = ByteUtil.byte2int(bytes, 4);
        System.out.println(i2);

        Assert.assertEquals(i1, i2);
    }


    @Test
    public void testlong() {
        byte[] bytes = new byte[8];
        bytes[0] = 0;
        bytes[1] = 125;
        bytes[2] = 123;
        bytes[3] = 1;
        bytes[4] = 1;
        bytes[5] = 1;
        bytes[6] = 0;
        bytes[7] = 0;

        Long l = ByteUtil.byte2long(bytes);
        System.out.println(l);

        System.out.println(".........");


        byte[] bs = ByteUtil.long2byte(l);

        for (byte b : bs) {
            System.out.println(b);
        }
        System.out.println(".........");
        byte[] buf = new byte[14];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = 2;
        }
        ByteUtil.long2byte(l, buf, 2);

        for (byte bu : buf) {
            System.out.println(bu);
        }
        System.out.println(".........");
        ByteUtil.long2byte(l, buf, 6);

        for (byte bu : buf) {
            System.out.println(bu);
        }
        System.out.println(".........");

        try {
            ByteUtil.long2byte(l, buf, 7);
            Assert.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println(".........");
        long max = 9223372036854775807l;
        long ll = 32984344213232l;

        byte[] bbb = ByteUtil.long2byte(ll);
        for (byte b : bbb) {
            System.out.println(b);
        }
        byte[] lbuf = new byte[100];
        int offset = 49;

        System.out.println(ByteUtil.byte2long(ByteUtil.long2byte(ll)));
        Assert.assertTrue(ll == ByteUtil.byte2long(ByteUtil.long2byte(ll)));

        //with buf offset
        ByteUtil.long2byte(ll, lbuf, offset);
        Assert.assertTrue(ll == ByteUtil.byte2long(lbuf, offset));
    }
}
