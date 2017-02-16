package win.sinno.smgp3.protocol.tlv;

import win.sinno.smgp3.protocol.constant.SmgpTagEnum;

/**
 * tlv 4 tpudhi
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午2:10
 */
public class SmgpTlv4TpUdhi extends SmgpTlv<Integer> {

    public SmgpTlv4TpUdhi() {
        setTag(SmgpTagEnum.TP_UDHI.getVal());
        setLength(1);
    }

    public SmgpTlv4TpUdhi(Integer value) {
        this();
        setValue(value);
    }

    @Override
    public String toString() {
        return "SmgpTlv4TpUdhi{} " + super.toString();
    }
}
