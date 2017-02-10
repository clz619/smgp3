package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.protocol.body.SmgpLoginBody;
import win.sinno.smgp3.protocol.message.SmgpLogin;

/**
 * smgp login message encoder
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午3:40
 */
public class SmgpLoginEncoder implements ISmgpMessageEncoder<SmgpLogin> {

    private SmgpLoginEncoder() {
    }

    private static class SmgpLoginEncoderHolder {
        private static final SmgpLoginEncoder HOLDER = new SmgpLoginEncoder();
    }

    public static SmgpLoginEncoder getInstance() {
        return SmgpLoginEncoderHolder.HOLDER;
    }

    /**
     * 消息编码
     *
     * @param smgpLogin
     * @return
     */
    @Override
    public byte[] encode(SmgpLogin smgpLogin) {

        //smgp login body
        SmgpLoginBody smgpLoginBody = smgpLogin.getBody();

        //TODO
        return new byte[0];
    }

}
