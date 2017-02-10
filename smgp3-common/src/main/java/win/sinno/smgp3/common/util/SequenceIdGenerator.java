package win.sinno.smgp3.common.util;

/**
 * 消息流水线生成器
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午4:27
 */
public class SequenceIdGenerator {

    /**
     * 当前id
     */
    private static int curSeqId = 0;

    public static final int maxSeqId = Integer.MAX_VALUE;

    public static synchronized int nextSeqId() {

        if (curSeqId == maxSeqId) {
            curSeqId = 0;
        }

        curSeqId++;

        return curSeqId;
    }
}
