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
     * 转换消息
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

            if (msgFormat == 15) {
                return msgContent.getBytes("gb18030");
            } else if (msgFormat == 8) {
                return msgContent.getBytes("iso-10646-ucs-2");
            }

            return msgContent.getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
            return null;
        }

    }

}
