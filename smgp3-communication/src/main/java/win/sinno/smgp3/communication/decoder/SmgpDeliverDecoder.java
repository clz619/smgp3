package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.config.LoggerConfigs;
import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.common.util.MsgContentUtil;
import win.sinno.smgp3.protocol.body.SmgpDeliverBody;
import win.sinno.smgp3.protocol.constant.SmgpTagEnum;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpDeliver;
import win.sinno.smgp3.protocol.model.SmgpReportMessage;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4TpUdhi;
import win.sinno.smgp3.protocol.tlv.SmgpTlvCollection;
import win.sinno.smgp3.protocol.tlv.TpUdhiMessage;

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

        SmgpHeader header = SmgpHeaderDecoder.getInstance().decode(bytes);

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

        body.setRecvTime(MsgContentUtil.formatMsg(recvTimeBytes).trim());

        offset += 14;

        byte[] srcTermIdBytes = new byte[21];
        System.arraycopy(bytes, offset, srcTermIdBytes, 0, 21);
        body.setSrcTermId(MsgContentUtil.formatMsg(srcTermIdBytes).trim());
        offset += 21;

        byte[] destTermIdBytes = new byte[21];
        System.arraycopy(bytes, offset, destTermIdBytes, 0, 21);
        body.setDestTermId(MsgContentUtil.formatMsg(destTermIdBytes).trim());
        offset += 21;

        int msgLength = ByteUtil.byte2int(bytes[offset]);
        body.setMsgLength(msgLength);
        offset++;

        byte[] msgContentBytes = new byte[msgLength];
        System.arraycopy(bytes, offset, msgContentBytes, 0, msgLength);

        //TODO rm log
        LoggerConfigs.SMGP3_LOG.info("smgp deliver msgContent byts :{}", msgContentBytes);

        offset += msgLength;

        //reserve
        offset += 8;

        if (body.getIsReport() == 1) {

            SmgpReportMessage smgpReportMessage = SmgpReportMessageDecoder.getInstance().decode(msgContentBytes);
            smgpDeliver.setSmgpReportMessage(smgpReportMessage);
        } else {
            //reply

            if (offset == bytes.length) {
                LoggerConfigs.SMGP3_LOG.info("smgp deliver no tlv");

                //normal msg
                body.setMsgContent(MsgContentUtil.formatMsg(msgContentBytes, 8).trim());
            } else {
                //tlv byte parse
                LoggerConfigs.SMGP3_LOG.info("smgp deliver has tlv");

                int tlvBytesLen = bytes.length - offset;
                byte[] tlvBytes = new byte[tlvBytesLen];

                System.arraycopy(bytes, offset, tlvBytes, 0, tlvBytesLen);

                //TODO bytes -> SmgpTlvCollection
                SmgpTlvCollection smgpTlvCollection = SmgpTlvCollectionDecoder.getInstance().decode(tlvBytes);

                body.setSmgpTlvCollection(smgpTlvCollection);
                if (smgpTlvCollection.isContains(SmgpTagEnum.TP_UDHI)) {
                    //long msg handler
                    SmgpTlv4TpUdhi udhi = (SmgpTlv4TpUdhi) smgpTlvCollection.get(SmgpTagEnum.TP_UDHI);

                    if (1 == udhi.getValue()) {
                        byte[] udhiBytes = new byte[6];
                        System.arraycopy(msgContentBytes, 0, udhiBytes, 0, 6);

                        TpUdhiMessage tpUdhiMessage = SmgpTpUdhiMessageDecoder.getInstance().decode(udhiBytes);
                        body.setTpUdhiMessage(tpUdhiMessage);

                        byte[] msgContentWithUdhiBytes = new byte[msgContentBytes.length - 6];
                        System.arraycopy(msgContentBytes, 6, msgContentWithUdhiBytes, 0, msgContentWithUdhiBytes.length);

                        body.setMsgContent(MsgContentUtil.formatMsg(msgContentWithUdhiBytes, 8).trim());
                    } else {
                        body.setMsgContent(MsgContentUtil.formatMsg(msgContentBytes, 8).trim());
                    }

                } else {
                    //normal msg
                    body.setMsgContent(MsgContentUtil.formatMsg(msgContentBytes, 8).trim());
                }

            }

        }
        smgpDeliver.setBinary(bytes);
        smgpDeliver.setHeader(header);
        smgpDeliver.setBody(body);

        return smgpDeliver;
    }
}
