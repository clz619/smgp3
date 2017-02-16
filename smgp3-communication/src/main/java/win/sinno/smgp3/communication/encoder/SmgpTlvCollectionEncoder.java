package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.protocol.constant.SmgpTagEnum;
import win.sinno.smgp3.protocol.tlv.SmgpTlv;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4PkNumber;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4PkTotal;
import win.sinno.smgp3.protocol.tlv.SmgpTlvCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * smgp tlv collection encoder
 * <p>
 * support TP_UDHI,PK_TOTAL,PK_NUMBER
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午1:17
 */
public class SmgpTlvCollectionEncoder implements ISmgpMessageEncoder<SmgpTlvCollection> {

    private SmgpTlvCollectionEncoder() {
    }

    private static class SmgpTlvCollectionEncoderHolder {
        private static final SmgpTlvCollectionEncoder HOLDER = new SmgpTlvCollectionEncoder();
    }

    public static SmgpTlvCollectionEncoder getInstance() {
        return SmgpTlvCollectionEncoderHolder.HOLDER;
    }

    private static final byte[] TP_UDHI_BYTES = {0x00, 0x02, 0x00, 0x01, 0x01};

    /**
     * 消息编码
     *
     * @param smgpTlvCollection
     * @return
     */
    @Override
    public byte[] encode(SmgpTlvCollection smgpTlvCollection) {

        if (smgpTlvCollection.size() == 0) {
            return null;
        }

        int len = 0;
        List<byte[]> tlvByteCollection = new ArrayList<>();

        Collection<SmgpTlv> smgpTlvs = smgpTlvCollection.getTlvs();

        Iterator<SmgpTlv> smgpTlvIterator = smgpTlvs.iterator();

        while (smgpTlvIterator.hasNext()) {

            SmgpTlv smgpTlv = smgpTlvIterator.next();

            Integer tag = smgpTlv.getTag();

            SmgpTagEnum smgpTagEnum = SmgpTagEnum.getById(tag);

            byte[] bytes = null;

            switch (smgpTagEnum) {

                case TP_UDHI:

                    bytes = TP_UDHI_BYTES;

                    break;

                case PK_TOTAL:

                    bytes = new byte[5];
                    bytes[0] = 0x00;
                    bytes[1] = 0x09;
                    bytes[2] = 0x00;
                    bytes[3] = 0x01;
                    bytes[4] = ((SmgpTlv4PkTotal) smgpTlv).getValue().byteValue();

                    break;

                case PK_NUMBER:

                    bytes = new byte[5];
                    bytes[0] = 0x00;
                    bytes[1] = 0x0a;
                    bytes[2] = 0x00;
                    bytes[3] = 0x01;
                    bytes[4] = ((SmgpTlv4PkNumber) smgpTlv).getValue().byteValue();

                    break;

                default:
                    //TODO - tlv parse
                    break;
            }

            tlvByteCollection.add(bytes);
            len += bytes.length;
        }

        //tlv bytes
        int offset = 0;
        byte[] tlvBytes = new byte[len];

        Iterator<byte[]> bytesIt = tlvByteCollection.iterator();

        while (bytesIt.hasNext()) {

            byte[] bytes = bytesIt.next();

            System.arraycopy(bytes, 0, tlvBytes, offset, bytes.length);

            offset += bytes.length;
        }

        return tlvBytes;
    }
}
