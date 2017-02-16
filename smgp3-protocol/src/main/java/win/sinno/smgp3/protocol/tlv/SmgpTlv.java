package win.sinno.smgp3.protocol.tlv;

import win.sinno.smgp3.protocol.ISmgpProtocol;

/**
 * smgp tvl message
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午4:42
 */
public class SmgpTlv<V> implements ISmgpProtocol {

    /**
     * 字段的标签，用于唯一标识可选参数
     */
    private Integer tag;

    /**
     * 字段的长度
     * V的长度
     */
    private Integer length;

    /**
     * 字段内容
     */
    private V value;

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SmgpTlv{" +
                "tag=" + tag +
                ", length=" + length +
                ", value=" + value +
                '}';
    }
}
