package win.sinno.smgp3.common.util;

import java.util.HashMap;
import java.util.Map;

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
    private int curSeqId = 0;

    public static final int maxSeqId = Integer.MAX_VALUE;

    private static final Map<String, SequenceIdGenerator> CACHE = new HashMap<String, SequenceIdGenerator>();

    private String name;

    public static SequenceIdGenerator getInstance(String name) {

        SequenceIdGenerator sequenceIdGenerator = CACHE.get(name);

        if (sequenceIdGenerator == null) {

            synchronized (CACHE) {
                sequenceIdGenerator = CACHE.get(name);

                if (sequenceIdGenerator == null) {
                    sequenceIdGenerator = new SequenceIdGenerator(name);
                    CACHE.put(name, sequenceIdGenerator);
                }
            }
        }

        return sequenceIdGenerator;
    }

    private SequenceIdGenerator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized int nextSeqId() {
        if (curSeqId == maxSeqId) {
            curSeqId = 0;
        }

        curSeqId++;

        return curSeqId;
    }
}
