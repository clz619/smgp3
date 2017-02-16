package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.common.util.ByteUtil;

/**
 * smgp msgId encoder
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午2:42
 */
public class SmgpMsgIdEncoder {

    public static byte[] encode(String msgId) {

        byte[] bytes = new byte[10];

        int len = msgId.length();

        for (int i = 0, j = 0; i < len && j < 10; i = i + 2, j++) {
            char c1 = msgId.charAt(i);
            char c2 = msgId.charAt(i + 1);

            byte b1 = ByteUtil.char2Byte(c1);
            byte b2 = ByteUtil.char2Byte(c2);

            bytes[j] = (byte) ((b1 << 4) | b2);
        }

        return bytes;
    }


}
