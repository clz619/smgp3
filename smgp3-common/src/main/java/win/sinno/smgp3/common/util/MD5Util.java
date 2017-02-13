package win.sinno.smgp3.common.util;

import win.sinno.smgp3.common.config.LoggerConfigs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    protected static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest = null;

    /**
     * MessageDigest初始化
     *
     */
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
        }
    }


    /**
     * 对byte类型的数组进行MD5加密
     */
    public static byte[] getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return messagedigest.digest();
    }

}