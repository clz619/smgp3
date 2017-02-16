package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.constant.SmgpTagEnum;
import win.sinno.smgp3.protocol.tlv.*;

/**
 * smgp tlv collection decoder
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午3:54
 */
public class SmgpTlvCollectionDecoder implements ISmgpMessageDecoder<SmgpTlvCollection> {

    private SmgpTlvCollectionDecoder() {
    }

    private static class SmgpTlvCollectionDecoderHolder {
        private static SmgpTlvCollectionDecoder HOLDER = new SmgpTlvCollectionDecoder();
    }

    public static SmgpTlvCollectionDecoder getInstance() {
        return SmgpTlvCollectionDecoderHolder.HOLDER;
    }

    /**
     * @param bytes
     * @return
     */
    @Override
    public SmgpTlvCollection decode(byte[] bytes) {

        SmgpTlvCollection collection = new SmgpTlvCollection();

        int len = bytes.length;
        int offset = 0;

        while (offset < len) {

            SmgpTlvDecoderTmp tmp = decode(bytes, offset);

            if (tmp.getSmgpTlv() != null) {
                collection.put(tmp.getSmgpTlv());
            }

            offset += tmp.getLen();
        }

        return collection;
    }

    private SmgpTlvDecoderTmp decode(byte[] bytes, int offset) {


        int tag = ByteUtil.byte2short(bytes, offset);
        offset += 2;

        int length = ByteUtil.byte2short(bytes, offset);
        offset += 2;

        byte[] valBytes = new byte[length];
        System.arraycopy(bytes, offset, valBytes, 0, length);
        offset += length;

        SmgpTlv smgpTlv = null;

        SmgpTagEnum smgpTagEnum = SmgpTagEnum.getById(tag);

        if (smgpTagEnum != null) {
            switch (smgpTagEnum) {

                case TP_UDHI:
                    smgpTlv = new SmgpTlv4TpUdhi();
                    smgpTlv.setValue(ByteUtil.byte2int(valBytes[0]));
                    break;

                case PK_TOTAL:
                    smgpTlv = new SmgpTlv4PkTotal();
                    smgpTlv.setValue(ByteUtil.byte2int(valBytes[0]));
                    break;

                case PK_NUMBER:
                    smgpTlv = new SmgpTlv4PkNumber();
                    smgpTlv.setValue(ByteUtil.byte2int(valBytes[0]));
                    break;

                default:
                    //若解析别的需要
                    smgpTlv = new SmgpTlv();
                    smgpTlv.setTag(smgpTagEnum.getVal());
                    smgpTlv.setLength(length);
                    break;
            }
        }

        SmgpTlvDecoderTmp tmp = new SmgpTlvDecoderTmp(4 + length, smgpTlv);
        return tmp;
    }

    private static class SmgpTlvDecoderTmp {

        private int len;
        private SmgpTlv smgpTlv;

        public SmgpTlvDecoderTmp(int len, SmgpTlv smgpTlv) {
            this.len = len;
            this.smgpTlv = smgpTlv;
        }

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }

        public SmgpTlv getSmgpTlv() {
            return smgpTlv;
        }

        public void setSmgpTlv(SmgpTlv smgpTlv) {
            this.smgpTlv = smgpTlv;
        }
    }

}
