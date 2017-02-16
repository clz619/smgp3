package win.sinno.smgp3.common.util;

/**
 * byte format tool
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 上午10:35
 */
public class ByteUtil {

    private static final String[] HEX_CODE = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static final String HEX_STR = "0123456789abcdef";

    private ByteUtil() {
    }

    //////////byte 2 other///////

    public static String byte2HexString(byte b) {

        int n = b;

        if (n < 0) {
            n = 256 + n;
        }

        int d1 = n / 16;
        int d2 = n % 16;

        return HEX_CODE[d1] + HEX_CODE[d2];
    }

    public static String byteArray2HexString(byte[] bytes) {

        StringBuilder builder = new StringBuilder();

        for (byte b : bytes) {
            builder.append(byte2HexString(b));
        }

        return builder.toString();
    }

    public static short byte2short(byte[] bytes) {

        if (bytes.length != 2) {
            throw new IllegalArgumentException("bytes length:" + bytes.length + " can not format to short!");
        }

        return (short)
                ((bytes[0] & 0xff) << 8
                        | (bytes[1] & 0xff))
                ;
    }

    public static short byte2short(byte[] bytes, int offset) {

        if (bytes.length < offset + 2) {
            throw new IllegalArgumentException("bytes (length:" + bytes.length + ") < (offset:" + offset + ")+2 can not format to short!");
        }

        return (short)
                ((bytes[offset] & 0xff) << 8
                        | (bytes[offset + 1] & 0xff)
                )
                ;
    }

    public static int byte2int(byte b) {
        return b & 0xff;
    }

    public static int byte2int(byte[] bytes) {

        if (bytes.length != 4) {
            throw new IllegalArgumentException("bytes length:" + bytes.length + " can not format to int!");
        }

        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | bytes[3] & 0xff
                ;
    }

    /**
     * bytes中下标offset开始4个字节，转换为int
     *
     * @param bytes
     * @param offset
     * @return
     */
    public static int byte2int(byte[] bytes, int offset) {

        if (bytes.length < offset + 4) {
            throw new IllegalArgumentException("bytes (length:" + bytes.length + ") < (offset:" + offset + ")+4 can not format to int!");
        }

        return (bytes[offset] & 0xff) << 24
                | (bytes[offset + 1] & 0xff) << 16
                | (bytes[offset + 2] & 0xff) << 8
                | bytes[offset + 3] & 0xff
                ;
    }

    public static long byte2long(byte[] bytes) {

        if (bytes.length != 8) {
            throw new IllegalArgumentException("bytes length:" + bytes.length + " can not format to long!");
        }

        return ((long) (bytes[0] & 0xff)) << 56
                | ((long) (bytes[1] & 0xff)) << 48
                | ((long) (bytes[2] & 0xff)) << 40
                | ((long) (bytes[3] & 0xff)) << 32
                | ((long) (bytes[4] & 0xff)) << 24
                | ((long) (bytes[5] & 0xff)) << 16
                | ((long) (bytes[6] & 0xff)) << 8
                | ((long) (bytes[7] & 0xff))
                ;
    }

    public static long byte2long(byte[] bytes, int offset) {

        if (bytes.length < offset + 8) {
            throw new IllegalArgumentException("bytes (length:" + bytes.length + ") < (offset:" + offset + ")+8 can not format to long!");
        }

        return ((long) (bytes[offset] & 0xff)) << 56
                | ((long) (bytes[offset + 1] & 0xff)) << 48
                | ((long) (bytes[offset + 2] & 0xff)) << 40
                | ((long) (bytes[offset + 3] & 0xff)) << 32
                | ((long) (bytes[offset + 4] & 0xff)) << 24
                | ((long) (bytes[offset + 5] & 0xff)) << 16
                | ((long) (bytes[offset + 6] & 0xff)) << 8
                | ((long) (bytes[offset + 7] & 0xff))
                ;
    }

    //////////other 2 byte///////

    public static byte char2Byte(char c) {
        byte b = (byte) HEX_STR.indexOf(c);
        return b;
    }

    public static byte[] short2byte(short s) {

        byte[] bytes = new byte[2];

        bytes[0] = (byte) (s >> 8);
        bytes[1] = (byte) s;
        return bytes;
    }

    public static void short2byte(short s, byte[] buf, int offset) {

        if (buf.length < offset + 2) {
            throw new IllegalArgumentException("byte array buf (length:" + buf.length + ")<(offset:" + offset + ")+2 can not set a short bytes");
        }

        buf[offset] = (byte) (s >> 8);
        buf[offset + 1] = (byte) s;
    }

    public static byte[] int2byte(int i) {

        byte[] bytes = new byte[4];

        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) (i >> 16);
        bytes[2] = (byte) (i >> 8);
        bytes[3] = (byte) i;

        return bytes;
    }

    /**
     * 将i转为bytes并将值设置至buf，其实byte下标为offset
     *
     * @param i
     * @param buf
     * @param offset
     */
    public static void int2byte(int i, byte[] buf, int offset) {

        if (buf.length < offset + 4) {
            throw new IllegalArgumentException("byte array buf (length:" + buf.length + ")<(offset:" + offset + ")+4 can not set a int bytes");
        }

        buf[offset] = (byte) (i >> 24);
        buf[offset + 1] = (byte) (i >> 16);
        buf[offset + 2] = (byte) (i >> 8);
        buf[offset + 3] = (byte) i;
    }

    public static byte[] long2byte(long l) {

        byte[] bytes = new byte[8];

        bytes[0] = (byte) (l >> 56);
        bytes[1] = (byte) (l >> 48);
        bytes[2] = (byte) (l >> 40);
        bytes[3] = (byte) (l >> 32);
        bytes[4] = (byte) (l >> 24);
        bytes[5] = (byte) (l >> 16);
        bytes[6] = (byte) (l >> 8);
        bytes[7] = (byte) l;

        return bytes;
    }

    public static void long2byte(long l, byte[] buf, int offset) {

        if (buf.length < offset + 8) {
            throw new IllegalArgumentException("byte array buf (length:" + buf.length + ")<(offset:" + offset + ")+8 can not set a int bytes");
        }

        buf[offset] = (byte) (l >> 56);
        buf[offset + 1] = (byte) (l >> 48);
        buf[offset + 2] = (byte) (l >> 40);
        buf[offset + 3] = (byte) (l >> 32);
        buf[offset + 4] = (byte) (l >> 24);
        buf[offset + 5] = (byte) (l >> 16);
        buf[offset + 6] = (byte) (l >> 8);
        buf[offset + 7] = (byte) l;
    }

}
