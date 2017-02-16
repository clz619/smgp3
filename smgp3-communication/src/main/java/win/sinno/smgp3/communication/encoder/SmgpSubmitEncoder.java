package win.sinno.smgp3.communication.encoder;

import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.common.util.MsgContentUtil;
import win.sinno.smgp3.protocol.body.SmgpSubmitBody;
import win.sinno.smgp3.protocol.header.SmgpHeader;
import win.sinno.smgp3.protocol.message.SmgpSubmit;
import win.sinno.smgp3.protocol.model.TpUdhiMessage;

/**
 * smgp submit encode
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/14 上午10:09
 */
public class SmgpSubmitEncoder implements ISmgpMessageEncoder<SmgpSubmit> {

    private SmgpSubmitEncoder() {
    }

    private static class SmgpSubmitEncoderHolder {
        private static final SmgpSubmitEncoder INSTANCE = new SmgpSubmitEncoder();
    }

    public static SmgpSubmitEncoder getInstance() {
        return SmgpSubmitEncoderHolder.INSTANCE;
    }

    /**
     * 消息编码
     *
     * @param smgpSubmit
     * @return
     */
    @Override
    public byte[] encode(SmgpSubmit smgpSubmit) {

        if (1 == smgpSubmit.getBody().getTpudhi()) {

            return encodeLongMsg(smgpSubmit);

        } else {

            return encodeShortMsg(smgpSubmit);

        }

    }

    private byte[] encodeShortMsg(SmgpSubmit smgpSubmit) {
        SmgpHeader header = smgpSubmit.getHeader();

        SmgpSubmitBody body = smgpSubmit.getBody();

        int len = SmgpHeader.HEADER_LENGTH;

        byte msgTypeByte = (byte) body.getMsgType();
        len += 1;
        byte needReportByte = (byte) body.getNeedReport();
        len += 1;
        byte priorityByte = (byte) body.getPriority();
        len += 1;

        byte[] serviceIdBytes = body.getServiceId().getBytes();
        len += 10;

        byte[] feeTypeBytes = body.getFeeType().getBytes();
        len += 2;

        byte[] feeCodeBytes = body.getFeeCode().getBytes();
        len += 6;

        byte[] fixedFeeBytes = body.getFixedFee().getBytes();
        len += 6;

        byte msgFormatByte = (byte) body.getMsgFormat();
        len += 1;

        byte[] validTimeBytes = body.getValidTime().getBytes();
        len += 17;

        byte[] atTimeBytes = body.getAtTime().getBytes();
        len += 17;

        byte[] srcTermIdBytes = body.getSrcTermId().getBytes();
        len += 21;

        byte[] chargeTermIdBytes = body.getChargeTermId().getBytes();
        len += 21;

        byte destTermIdCountByte = 1;
        len += 1;

        byte[] destTermIdBytes = body.getDestTermId().getBytes();
        len += 21;

        int msgLength = body.getMsgContent().length() * 2;
        byte msgLengthByte = (byte) msgLength;
        len += 1;

        byte[] msgContentBytes = MsgContentUtil.formatMsg(body.getMsgContent(), body.getMsgFormat());
        len += msgLength;

        len += 8;

        byte[] bytes = new byte[len];

        int offset = 0;
        ByteUtil.int2byte(len, bytes, offset);
        offset += 4;

        ByteUtil.int2byte(header.getRequestId(), bytes, offset);
        offset += 4;

        ByteUtil.int2byte(header.getSequenceId(), bytes, offset);
        offset += 4;

        bytes[offset] = msgTypeByte;
        offset++;

        bytes[offset] = needReportByte;
        offset++;

        bytes[offset] = priorityByte;
        offset++;

        System.arraycopy(serviceIdBytes, 0, bytes, offset, serviceIdBytes.length);
        offset += 10;

        System.arraycopy(feeTypeBytes, 0, bytes, offset, feeTypeBytes.length);
        offset += 2;

        System.arraycopy(feeCodeBytes, 0, bytes, offset, feeCodeBytes.length);
        offset += 6;

        System.arraycopy(fixedFeeBytes, 0, bytes, offset, fixedFeeBytes.length);
        offset += 6;

        bytes[offset] = msgFormatByte;
        offset++;

        System.arraycopy(validTimeBytes, 0, bytes, offset, validTimeBytes.length);
        offset += 17;

        System.arraycopy(atTimeBytes, 0, bytes, offset, atTimeBytes.length);
        offset += 17;

        System.arraycopy(srcTermIdBytes, 0, bytes, offset, srcTermIdBytes.length);
        offset += 21;

        System.arraycopy(chargeTermIdBytes, 0, bytes, offset, chargeTermIdBytes.length);
        offset += 21;

        bytes[offset] = destTermIdCountByte;
        offset++;

        System.arraycopy(destTermIdBytes, 0, bytes, offset, destTermIdBytes.length);
        offset += 21;

        bytes[offset] = msgLengthByte;
        offset++;

        System.arraycopy(msgContentBytes, 0, bytes, offset, msgContentBytes.length);
        offset += msgLength;

        return bytes;
    }

    private byte[] encodeLongMsg(SmgpSubmit smgpSubmit) {
        SmgpHeader header = smgpSubmit.getHeader();

        SmgpSubmitBody body = smgpSubmit.getBody();

        TpUdhiMessage tpUdhiMessage = body.getTpUdhiMessage();

        int len = SmgpHeader.HEADER_LENGTH;

        byte msgTypeByte = (byte) body.getMsgType();
        len += 1;
        byte needReportByte = (byte) body.getNeedReport();
        len += 1;
        byte priorityByte = (byte) body.getPriority();
        len += 1;

        byte[] serviceIdBytes = body.getServiceId().getBytes();
        len += 10;

        byte[] feeTypeBytes = body.getFeeType().getBytes();
        len += 2;

        byte[] feeCodeBytes = body.getFeeCode().getBytes();
        len += 6;

        byte[] fixedFeeBytes = body.getFixedFee().getBytes();
        len += 6;

        byte msgFormatByte = (byte) body.getMsgFormat();
        len += 1;

        byte[] validTimeBytes = body.getValidTime().getBytes();
        len += 17;

        byte[] atTimeBytes = body.getAtTime().getBytes();
        len += 17;

        byte[] srcTermIdBytes = body.getSrcTermId().getBytes();
        len += 21;

        byte[] chargeTermIdBytes = body.getChargeTermId().getBytes();
        len += 21;

        byte destTermIdCountByte = 1;
        len += 1;

        byte[] destTermIdBytes = body.getDestTermId().getBytes();
        len += 21;

        int msgLength = body.getMsgContent().length() * 2;
        len += 1;

        byte[] msgContentBytes = MsgContentUtil.formatMsg(body.getMsgContent(), body.getMsgFormat());
        len += msgLength + 6;

        len += 8;

        //tp udhi
        len += 5;
        //pktotal
        len += 5;
        //pknumber
        len += 5;

        byte[] bytes = new byte[len];

        int offset = 0;
        ByteUtil.int2byte(len, bytes, offset);
        offset += 4;

        ByteUtil.int2byte(header.getRequestId(), bytes, offset);
        offset += 4;

        ByteUtil.int2byte(header.getSequenceId(), bytes, offset);
        offset += 4;

        bytes[offset] = msgTypeByte;
        offset++;

        bytes[offset] = needReportByte;
        offset++;

        bytes[offset] = priorityByte;
        offset++;

        System.arraycopy(serviceIdBytes, 0, bytes, offset, serviceIdBytes.length);
        offset += 10;

        System.arraycopy(feeTypeBytes, 0, bytes, offset, feeTypeBytes.length);
        offset += 2;

        System.arraycopy(feeCodeBytes, 0, bytes, offset, feeCodeBytes.length);
        offset += 6;

        System.arraycopy(fixedFeeBytes, 0, bytes, offset, fixedFeeBytes.length);
        offset += 6;

        bytes[offset] = msgFormatByte;
        offset++;

        System.arraycopy(validTimeBytes, 0, bytes, offset, validTimeBytes.length);
        offset += 17;

        System.arraycopy(atTimeBytes, 0, bytes, offset, atTimeBytes.length);
        offset += 17;

        System.arraycopy(srcTermIdBytes, 0, bytes, offset, srcTermIdBytes.length);
        offset += 21;

        System.arraycopy(chargeTermIdBytes, 0, bytes, offset, chargeTermIdBytes.length);
        offset += 21;

        bytes[offset] = destTermIdCountByte;
        offset++;

        System.arraycopy(destTermIdBytes, 0, bytes, offset, destTermIdBytes.length);
        offset += 21;

        //加入6个字节 TP_UDHI头长度
        byte msgLengthByte = (byte) (msgLength + 6);
        bytes[offset] = msgLengthByte;
        offset++;

        //tpudhi
        bytes[offset] = 0x05;
        offset++;

        bytes[offset] = 0x00;
        offset++;

        bytes[offset] = 0x03;
        offset++;

        bytes[offset] = (byte) tpUdhiMessage.getSign();
        offset++;

        byte tnByte = (byte) tpUdhiMessage.getTn();
        bytes[offset] = tnByte;
        offset++;

        byte idxByte = (byte) tpUdhiMessage.getIdx();
        bytes[offset] = idxByte;
        offset++;


        //content
        System.arraycopy(msgContentBytes, 0, bytes, offset, msgContentBytes.length);
        offset += msgLength;

        offset += 8;

        //add tpudhi tag
        bytes[offset] = 0x00;
        offset++;
        bytes[offset] = 0x02;
        offset++;
        bytes[offset] = 0x00;
        offset++;
        bytes[offset] = 0x01;
        offset++;
        bytes[offset] = 0x01;
        offset++;
        //add pk total
        bytes[offset] = 0x00;
        offset++;
        bytes[offset] = 0x09;
        offset++;
        bytes[offset] = 0x00;
        offset++;
        bytes[offset] = 0x01;
        offset++;
        bytes[offset] = tnByte;
        offset++;
        //add pknumber
        bytes[offset] = 0x00;
        offset++;
        bytes[offset] = 0x0a;
        offset++;
        bytes[offset] = 0x00;
        offset++;
        bytes[offset] = 0x01;
        offset++;
        bytes[offset] = idxByte;
        offset++;

        return bytes;
    }


}
