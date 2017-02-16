package win.sinno.smgp3.common.util;

import win.sinno.smgp3.common.config.LoggerConfigs;

import java.io.UnsupportedEncodingException;

/**
 * 消息内容工具
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/14 上午10:22
 */
public class MsgContentUtil {

    /**
     * String -> array
     * <p>
     * charset name = iso-8859-1
     *
     * @param msgContent
     * @return
     */
    private static byte[] formatMsg(String msgContent) {
        if (msgContent == null || msgContent.trim().length() == 0) {
            return null;
        }

        try {
            return msgContent.getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * String -> array
     * <p>
     * msgFormat = 8 , charset name = iso-10646-ucs-2
     * msgFormat = 15 , charset name = gb18030
     * msgFormat = other , charset name = iso-8859-1
     *
     * @param msgContent
     * @param msgFormat
     * @return
     */
    public static byte[] formatMsg(String msgContent, int msgFormat) {

        if (msgContent == null || msgContent.trim().length() == 0) {
            return null;
        }

        try {
            if (msgFormat == 8) {
                return msgContent.getBytes("iso-10646-ucs-2");
            } else if (msgFormat == 15) {
                return msgContent.getBytes("gb18030");
            }

            return msgContent.getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * byte array -> String
     * <p>
     * charset name = iso-8859-1
     *
     * @param bytes
     * @return
     */
    public static String formatMsg(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {

            return new String(bytes, "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * byte array -> String
     * <p>
     * msgFormat = 8 , charset name = iso-10646-ucs-2
     * msgFormat = 15 , charset name = gb18030
     * msgFormat = other , charset name = iso-8859-1
     *
     * @param bytes
     * @param msgFormat
     * @return
     */
    public static String formatMsg(byte[] bytes, int msgFormat) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            if (msgFormat == 8) {
                return new String(bytes, "iso-10646-ucs-2");
            } else if (msgFormat == 15) {
                return new String(bytes, "gb18030");
            }

            return new String(bytes, "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
            return null;
        }
    }

}
