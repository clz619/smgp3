package win.sinno.simgp3.common.util;

import org.junit.Test;
import win.sinno.smgp3.common.util.SequenceIdGenerator;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午4:35
 */
public class SequenceIdGeneratorTest {

    @Test
    public void testNextSeqId() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("i1 : " + SequenceIdGenerator.nextSeqId());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("i2 : " + SequenceIdGenerator.nextSeqId());
                }
            }
        }.start();

        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
