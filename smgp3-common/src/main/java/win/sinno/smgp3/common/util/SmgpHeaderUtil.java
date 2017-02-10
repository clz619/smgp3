package win.sinno.smgp3.common.util;

/**
 * smgp header util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午4:22
 */
public class SmgpHeaderUtil {

    /**
     * 获取smgp消息 数据包长度
     *
     * @param bytes
     * @return
     */
    public static int getMessagePacketLength(byte[] bytes) {

        return ByteUtil.byte2int(bytes, 0);
    }

    /**
     * 获取smgp消息 请求标识
     *
     * @param bytes
     * @return
     */
    public static int getMessageRequestId(byte[] bytes) {

        return ByteUtil.byte2int(bytes, 4);
    }

    /**
     * 获取smgp消息 消息流水号
     *
     * @param bytes
     * @return
     */
    public static int getMessageSequenceId(byte[] bytes) {

        return ByteUtil.byte2int(bytes, 8);
    }

}
