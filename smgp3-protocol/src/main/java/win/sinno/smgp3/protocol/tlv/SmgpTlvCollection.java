package win.sinno.smgp3.protocol.tlv;

import win.sinno.smgp3.protocol.ISmgpProtocol;
import win.sinno.smgp3.protocol.constant.SmgpTagEnum;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * smgp tlv collection
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 上午11:18
 */
public class SmgpTlvCollection implements ISmgpProtocol {

    /**
     * map.contains:
     * tag,length,value.
     */
    private Map<Integer, SmgpTlv> tlvMap = new HashMap<>();

    public int size() {
        return tlvMap.size();
    }

    public boolean isContains(Integer tag) {
        return tlvMap.containsKey(tag);
    }

    public boolean isContains(SmgpTagEnum smgpTagEnum) {
        return isContains(smgpTagEnum.getVal());
    }

    public boolean isContainsTpudhi() {
        return isContains(SmgpTagEnum.TP_UDHI);
    }

    public boolean isContainsPkTotal() {
        return isContains(SmgpTagEnum.PK_TOTAL);
    }

    public boolean isContainsPkNumber() {
        return isContains(SmgpTagEnum.PK_NUMBER);
    }

    public void put(SmgpTlv tlv) {
        put(tlv.getTag(), tlv);
    }

    public void put(Integer tag, SmgpTlv tlv) {
        tlvMap.put(tag, tlv);
    }

    public void put(SmgpTagEnum smgpTagEnum, SmgpTlv tlv) {
        put(smgpTagEnum.getVal(), tlv);
    }

    public void putTpudhi(SmgpTlv tlv) {
        put(SmgpTagEnum.TP_UDHI, tlv);
    }

    public void putPkTotal(SmgpTlv tlv) {
        put(SmgpTagEnum.PK_TOTAL, tlv);
    }

    public void putPkNumber(SmgpTlv tlv) {
        put(SmgpTagEnum.PK_NUMBER, tlv);
    }

    public SmgpTlv get(Integer tag) {
        return tlvMap.get(tag);
    }

    public SmgpTlv get(SmgpTagEnum smgpTagEnum) {
        return get(smgpTagEnum.getVal());
    }

    public SmgpTlv getTpudhi() {
        return get(SmgpTagEnum.TP_UDHI);
    }

    public SmgpTlv getPkTotal() {
        return get(SmgpTagEnum.PK_TOTAL);
    }

    public SmgpTlv getPkNumber() {
        return get(SmgpTagEnum.PK_NUMBER);
    }

    public Collection<SmgpTlv> getTlvs() {
        return tlvMap.values();
    }

    @Override
    public String toString() {
        return "SmgpTlvCollection{" +
                "tlvMap=" + tlvMap +
                '}';
    }
}
