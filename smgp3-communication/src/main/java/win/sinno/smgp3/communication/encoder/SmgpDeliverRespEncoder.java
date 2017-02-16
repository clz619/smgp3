package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.body.SmgpDeliverRespBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpDeliverResp;

/**
 * smgp DeliverResp message encode
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/14 下午3:11
 */
public class SmgpDeliverRespEncoder implements ISmgpMessageEncoder<SmgpDeliverResp> {

    private SmgpDeliverRespEncoder() {
    }

    private static class SmgpDeliverRespEncoderHolder {
        private static final SmgpDeliverRespEncoder INSTANCE = new SmgpDeliverRespEncoder();
    }

    public static SmgpDeliverRespEncoder getInstance() {
        return SmgpDeliverRespEncoderHolder.INSTANCE;
    }


    /**
     * 消息编码
     *
     * @param smgpDeliverResp
     * @return
     */
    @Override
    public byte[] encode(SmgpDeliverResp smgpDeliverResp) {

        SmgpHeader header = smgpDeliverResp.getHeader();
        SmgpDeliverRespBody body = smgpDeliverResp.getBody();

        byte[] bytes = new byte[26];

        byte[] headerBytes = SmgpHeaderEncoder.encode(header);

        System.arraycopy(headerBytes, 0, bytes, 0, 12);

        byte[] msgIdBytes = SmgpMsgIdEncoder.encode(body.getMsgId());

        System.arraycopy(msgIdBytes, 0, bytes, 12, msgIdBytes.length);

        ByteUtil.int2byte(body.getStatus(), bytes, 22);

        return bytes;
    }
}
