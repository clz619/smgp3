package win.sinno.smgp3.protocol.tlv;

import win.sinno.smgp3.protocol.constant.SmgpTagEnum;

/**
 * tlv 4 pknumber
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午2:15
 */
public class SmgpTlv4PkNumber extends SmgpTlv<Integer> {

    public SmgpTlv4PkNumber() {
        setTag(SmgpTagEnum.PK_NUMBER.getVal());
        setLength(1);
    }

    public SmgpTlv4PkNumber(Integer value) {
        this();
        setValue(value);
    }

    @Override
    public String toString() {
        return "SmgpTlv4PkNumber{} " + super.toString();
    }
}
