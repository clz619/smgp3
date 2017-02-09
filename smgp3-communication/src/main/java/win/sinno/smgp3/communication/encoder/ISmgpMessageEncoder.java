package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.protocol.message.SmgpMessage;

/**
 * smgp message 编码器
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午4:10
 */
public interface ISmgpMessageEncoder<MSG extends SmgpMessage> {
    /**
     * 消息编码
     *
     * @param msg
     * @return
     */
    byte[] encode(MSG msg);
}
