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

        byte bb = -74;
        int i = bb;
        System.out.println(i);

        int i2 = 140;
        byte b2 = (byte) i2;
        System.out.println(b2);
        byte[] b2b = new byte[4];
        b2b[3] = b2;
        System.out.println(ByteUtil.byte2int(b2b));

        System.out.println((b2 & 0xff));

        System.out.println(ByteUtil.byte2int(b2));

        System.out.println(0xff);

        byte[] b = new byte[4];
        b[3] = -74;
        System.out.println(ByteUtil.byte2int(b));

        String s = "0, 0, 0, -74, 0, 0, 0, 2, 0, 0, 0, 2,6,1," +
                "3," +
                "100, 100, 121, 0, 0, 0, 0, 0, 0, 0," +
                "48, 48," +
                "48, 0, 0, 0, 0, 0," +
                "48, 0, 0, 0, 0, 0," +
                "15," +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0," +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0," +
                "49, 49, 56, 55, 48, 48, 57, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0," +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0," +
                "1," +
                "49, 56, 57, 54, 56, 49, 57, 50, 57, 54, 56, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0," +
                "-95, -66, -74, -93, -33, -53, -44, -58, -95, -65, -48, -62, -76, -70, -69, -40, 46, -64, -95, -48, -62, -64, -49, -71, -53, -65, -51, 46, -51, -53, -74, -87, -69, -40, 84, 0," +
                "0, 0, 0, 0, 0, 0, 0, 0";

        String[] arr = s.split(",");
        System.out.println(arr.length);
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

    @Test
    public void testTimeStamp() {
        int i = 301000000;

        System.out.println(Integer.toHexString(i));

        int t = 0x11F0E540;
        System.out.println(t);

        System.out.println(Integer.toHexString(1212000000));

    }

    public void testMsgId() {
        String msgId = "02037102141557415879";

        //
        byte[] deliverMsgIdBytes = {2, 3, 113, 2, 20, 21, 87, 65, 88, 121};

        byte[] seqBytes={65,88,121};



    }
}
