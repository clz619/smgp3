package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.body.SmgpLoginBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpLogin;

/**
 * smgp login message encode
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

        String spPwd = smgpLogin.getSpPwd();

        SmgpHeader header = smgpLogin.getHeader();

        SmgpLoginBody body = smgpLogin.getBody();

        //验证
        byte[] authClientBytes = SmgpAuthenticatorEncoder.generateAuthClient(body.getClientId()
                , spPwd, body.getTimeStampyyMMddmmss());

        byte[] bytes = new byte[header.getPacketLength()];

        int offset = 0;

        //set header
        SmgpHeaderEncoder.encode(header, bytes, offset);
        offset += 12;

        //clientId
        byte[] clientIdBytes = body.getClientId().getBytes();
        System.arraycopy(clientIdBytes, 0, bytes, offset, clientIdBytes.length);
        offset += 8;

        //authClient
        System.arraycopy(authClientBytes, 0, bytes, offset, authClientBytes.length);
        offset += 16;

        //loginMode
        bytes[offset] = (byte) body.getLoginMode();
        offset += 1;

        //timestamp
        ByteUtil.int2byte(body.getTimeStamp(), bytes, offset);
        offset += 4;

        //client version
        bytes[offset] = (byte) body.getClientVersion();

        return bytes;
    }

}
