package win.sinno.smgp3.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Long msg content split
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午5:28
 */
public class LongMsgContentSplitUtil {


    public static final int SINGLE_MAX_LEN = 70;

    public static final int SPLIT_LEN = 67;

    public static List<String> split(String msg) {
        if (msg == null) {
            return null;
        }

        List<String> segments = new ArrayList<String>();

        if (msg.length() <= SINGLE_MAX_LEN) {
            segments.add(msg);
            return segments;
        }

        int len = msg.length();
        int splitNum = len / 67;

        if (len % 67 > 0) {
            splitNum++;
        }

        int offset = 0;

        for (int i = 0; i < splitNum; i++) {
            if (i != splitNum - 1) {
                // 67个字节
                segments.add(msg.substring(offset, offset + SPLIT_LEN));
                offset += SPLIT_LEN;
            } else {
                segments.add(msg.substring(offset, len));
            }
        }

        return segments;
    }
}
