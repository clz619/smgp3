package win.sinno.smgp3.protocol.header;

import win.sinno.smgp3.protocol.define.SmgpRequestEnum;

/**
 * smgp message header
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午2:23
 */
public interface ISmgpHeader {
    /**
     * 数据包长度，指包头和包体的长度之和。单位是"字节"
     */
    int getPacketLength();

    void setPacketLength(int packetLength);

    /**
     * 请求标识，表示smgp数据包的类型，请求包的请求标识和应答包的请求标识一一对应
     * {@link SmgpRequestEnum}
     */
    int getRequestId();

    void setRequestId(int requestId);

    /**
     * 消息流水号，用于匹配请求数据包和应答数据包
     */
    int getSequenceId();

    void setSequenceId(int sequenceId);
}
