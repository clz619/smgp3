package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.config.LoggerConfigs;
import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.protocol.body.SmgpDeliverBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpDeliver;
import win.sinno.smgp3.protocol.model.SmgpReportMessage;

import java.io.UnsupportedEncodingException;

/**
 * smgp deliver message decode
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/14 下午1:58
 */
public class SmgpDeliverDecoder implements ISmgpMessageDecoder<SmgpDeliver> {

    private SmgpDeliverDecoder() {
    }

    private static class SmgpDeliverDecoderHolder {
        private static SmgpDeliverDecoder INSTANCE = new SmgpDeliverDecoder();
    }

    public static SmgpDeliverDecoder getInstance() {
        return SmgpDeliverDecoderHolder.INSTANCE;
    }

    /**
     * @param bytes
     * @return
     */
    @Override
    public SmgpDeliver decode(byte[] bytes) {
        SmgpDeliver smgpDeliver = new SmgpDeliver();

        try {
            SmgpHeader header = SmgpHeaderDecoder.decode(bytes);

            SmgpDeliverBody body = new SmgpDeliverBody();

            int offset = SmgpHeader.HEADER_LENGTH;

            byte[] msgIdBytes = new byte[10];

            System.arraycopy(bytes, offset, msgIdBytes, 0, 10);
            body.setMsgId(ByteUtil.byteArray2HexString(msgIdBytes));
            offset += 10;

            body.setIsReport(ByteUtil.byte2int(bytes[offset]));
            offset++;

            body.setMsgFormat(ByteUtil.byte2int(bytes[offset]));
            offset++;

            byte[] recvTimeBytes = new byte[14];
            System.arraycopy(bytes, offset, recvTimeBytes, 0, 14);

            body.setRecvTime(new String(recvTimeBytes, "ISO-8859-1").trim());

            offset += 14;

            byte[] srcTermIdBytes = new byte[21];
            System.arraycopy(bytes, offset, srcTermIdBytes, 0, 21);
            body.setSrcTermId(new String(srcTermIdBytes, "ISO-8859-1").trim());
            offset += 21;

            byte[] destTermIdBytes = new byte[21];
            System.arraycopy(bytes, offset, destTermIdBytes, 0, 21);
            body.setDestTermId(new String(destTermIdBytes, "ISO-8859-1").trim());
            offset += 21;

            int msgLength = ByteUtil.byte2int(bytes[offset]);
            body.setMsgLength(msgLength);
            offset++;

            byte[] msgContentBytes = new byte[msgLength];
            System.arraycopy(bytes, offset, msgContentBytes, 0, msgLength);

            if (body.getIsReport() == 1) {
                //回执
                SmgpReportMessage smgpReportMessage = SmgpReportMessageDecoder.decode(msgContentBytes);
                smgpDeliver.setSmgpReportMessage(smgpReportMessage);
            } else {
                //上行
                body.setMsgContent(new String(msgContentBytes, "iso-10646-ucs-2").trim());
            }
            smgpDeliver.setBinary(bytes);
            smgpDeliver.setHeader(header);
            smgpDeliver.setBody(body);
        } catch (UnsupportedEncodingException e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
        }
        return smgpDeliver;
    }
}
