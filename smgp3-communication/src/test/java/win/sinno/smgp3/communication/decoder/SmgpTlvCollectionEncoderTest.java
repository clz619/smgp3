package win.sinno.smgp3.communication.decoder;

import org.junit.Assert;
import org.junit.Test;
import win.sinno.smgp3.communication.encoder.SmgpTlvCollectionEncoder;
import win.sinno.smgp3.protocol.tlv.*;

/**
 * tlv collection encoder test
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午2:09
 */
public class SmgpTlvCollectionEncoderTest {

    @Test
    public void test() {

        SmgpTlvCollection smgpTlvCollection = new SmgpTlvCollection();

        SmgpTlv4TpUdhi tlv4TpUdhi = new SmgpTlv4TpUdhi();
        TpUdhiMessage tpUdhiMessage = new TpUdhiMessage();
        tlv4TpUdhi.setValue(tpUdhiMessage);

        SmgpTlv4PkTotal tlv4PkTotal = new SmgpTlv4PkTotal();
        tlv4PkTotal.setValue(4);

        SmgpTlv4PkNumber tlv4PkNumber = new SmgpTlv4PkNumber();
        tlv4PkNumber.setValue(2);

        smgpTlvCollection.put(tlv4TpUdhi);
        smgpTlvCollection.put(tlv4PkTotal);
        smgpTlvCollection.put(tlv4PkNumber);

        byte[] bs = SmgpTlvCollectionEncoder.getInstance().encode(smgpTlvCollection);

        for (byte b : bs) {
            System.out.println(b);
        }

        Assert.assertEquals(15, bs.length);
    }
}
