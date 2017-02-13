package win.sinno.smgp3.communication;

import win.sinno.smgp3.protocol.message.SmgpLogin;
import win.sinno.smgp3.protocol.message.SmgpLoginResp;

/**
 * smgp processer，获取消息，写消息
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午4:31
 */
public interface ISmgpCommunication {


    /**
     * send smgp message
     *
     * @param message
     */
    void send(byte[] message);

    /**
     * receive smgp message
     *
     * @return
     */
    void receive(byte[] message);

    ///////send

    /**
     * send smgp Login message
     *
     * @param smgpLogin
     */
    void sendLogin(SmgpLogin smgpLogin);

    ///////TODO other send

    ///////hander msg

    /**
     * receive -> hander
     *
     * @param bytes
     */
    void handlerMessage(byte[] bytes);

    /**
     * handler smgp LoginResp message
     *
     * @param smgpLoginResp
     */
    void handlerLoginResp(SmgpLoginResp smgpLoginResp);

    /////TODO  other message handler

}
