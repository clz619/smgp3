package win.sinno.smgp3.protocol.tlv;

import win.sinno.smgp3.protocol.constant.SmgpTagEnum;

/**
 * tlv 4 pktotal
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午2:13
 */
public class SmgpTlv4PkTotal extends SmgpTlv<Integer> {

    public SmgpTlv4PkTotal() {
        setTag(SmgpTagEnum.PK_TOTAL.getVal());
        setLength(1);
    }

    public SmgpTlv4PkTotal(Integer value) {
        this();
        setValue(value);
    }

    @Override
    public String toString() {
        return "SmgpTlv4PkTotal{} " + super.toString();
    }
}
