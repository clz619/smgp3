package win.sinno.smgp3.protocol.tlv;

import win.sinno.smgp3.protocol.ISmgpProtocol;

/**
 * TODO
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 下午4:55
 */
public class TpUdhiMessage implements ISmgpProtocol {

    /**
     * 长短信参考号,一个字节 最大0xff
     * <p>
     * 每个SP给每个用户发送的每条参考号都应该不同,可以从0
     * 开始每次加1,最大255,便于同一个终端对同一个SP的消息的不同的长短信进行识别.
     */
    private int sign;

    /**
     * 本条长短信的总消息数
     * 从1到255
     */
    private int tn;

    /**
     * 本条消息在长短信中的位置或序号，从1到255
     */
    private int idx;

    public TpUdhiMessage() {
    }

    public TpUdhiMessage(int sign, int tn, int idx) {
        this.sign = sign;
        this.tn = tn;
        this.idx = idx;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getTn() {
        return tn;
    }

    public void setTn(int tn) {
        this.tn = tn;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public String toString() {
        return "TpUdhiMessage{" +
                "sign=" + sign +
                ", tn=" + tn +
                ", idx=" + idx +
                '}';
    }
}
