package win.sinno.smgp3.communication.decoder;

import org.junit.Test;
import win.sinno.smgp3.protocol.tlv.SmgpTlvCollection;

/**
 * smgp tlv collection decoder
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午4:31
 */
public class SmgpTlvCollectionDecoderTest {

    @Test
    public void testTlv() {

        byte[] tlvBytes = {0, 1, 0, 1, 0, 0, 2, 0, 1, 0};

        SmgpTlvCollection smgpTlvCollection = SmgpTlvCollectionDecoder.getInstance().decode(tlvBytes);

        System.out.println(smgpTlvCollection.toString());
    }
}
