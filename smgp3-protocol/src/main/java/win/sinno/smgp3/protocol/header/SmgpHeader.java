package win.sinno.smgp3.protocol.header;

/**
 * smgp header
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午2:15
 */
public class SmgpHeader implements ISmgpHeader {

    public static final int HEADER_LENGTH = 12;

    /**
     * 默认为header长度
     */
    private int packetLength = HEADER_LENGTH;

    private int requestId;

    private int sequenceId;

    public int getPacketLength() {
        return packetLength;
    }

    public void setPacketLength(int packetLength) {
        this.packetLength = packetLength;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }
}
