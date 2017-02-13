package win.sinno.smgp3.communication;

import win.sinno.smgp3.protocol.message.SmgpActiveTest;
import win.sinno.smgp3.protocol.message.SmgpActiveTestResp;
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
     * send smgp message, if send fail try count
     *
     * @param message
     * @param trycount
     */
    void send(byte[] message, int trycount);

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

    /**
     * send smgp ActiveTest message
     *
     * @param smgpActiveTest
     */
    void sendActiveTest(SmgpActiveTest smgpActiveTest);

    /**
     * send smgp ActiveTestResp message
     *
     * @param smgpActiveTestResp
     */
    void sendActiveTestResp(SmgpActiveTestResp smgpActiveTestResp);

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

    /**
     * handler smgp ActiveTest message
     *
     * @param smgpActiveTest
     */
    void handlerActiveTest(SmgpActiveTest smgpActiveTest);

    /**
     * handler smgp ActiveTestResp message
     *
     * @param smgpActiveTestResp
     */
    void handlerActiveTestResp(SmgpActiveTestResp smgpActiveTestResp);


    /////TODO  other message handler

}
