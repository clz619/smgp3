package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.config.LoggerConfigs;
import win.sinno.smgp3.protocol.model.SmgpReportMessage;

/**
 * when smgp devlier message isReport=1,
 * msgContent need use this decode
 * <p>
 * “状态报告格式”采用SMPP V3.4中的规定，
 * 即“id:IIIIIIIIII sub:SSS dlvrd:DDD Submit date:YYMMDDhhmm done date: YYMMDDhhmm stat:DDDDDDD err:E Text:……”。
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/14 下午4:35
 */
public class SmgpReportMessageDecoder implements ISmgpMessageDecoder<SmgpReportMessage> {

    private SmgpReportMessageDecoder() {
    }

    private static class SmgpReportMessageDecoderHolder {
        private static final SmgpReportMessageDecoder HOLDER = new SmgpReportMessageDecoder();
    }

    public static SmgpReportMessageDecoder getInstance() {
        return SmgpReportMessageDecoderHolder.HOLDER;
    }

    /**
     * //id
     * int offset = 3;
     * byte[] idBytes = new byte[10];
     * System.arraycopy(bytes, offset, idBytes, 0, 10);
     * offset += 10 + 1;
     * <p>
     * //sub
     * offset += 4;
     * byte[] subBytes = new byte[3];
     * System.arraycopy(bytes, offset, subBytes, 0, 3);
     * offset += 3 + 1;
     * <p>
     * //dlvrd
     * offset += 6;
     * byte[] dlvrdBytes = new byte[3];
     * System.arraycopy(bytes, offset, dlvrdBytes, 0, 3);
     * offset += 3 + 1;
     * <p>
     * //submit date
     * offset += 12;
     * byte[] submitDateBytes = new byte[10];
     * System.arraycopy(bytes, offset, submitDateBytes, 0, 10);
     * offset += 10 + 1;
     * <p>
     * //done date
     * offset += 10;
     * byte[] doneDateBytes = new byte[10];
     * System.arraycopy(bytes, offset, doneDateBytes, 0, 10);
     * offset += 10 + 1;
     * <p>
     * //stat
     * offset += 5;
     * byte[] statBytes = new byte[7];
     * System.arraycopy(bytes, offset, statBytes, 0, 7);
     * offset += 7 + 1;
     * <p>
     * //err
     * offset += 4;
     * byte[] errBytes = new byte[3];
     * System.arraycopy(bytes, offset, errBytes, 0, 3);
     * offset += 3 + 1;
     * <p>
     * //txt
     * offset += 5;
     * byte[] txtBytes = new byte[20];
     * System.arraycopy(bytes, offset, txtBytes, 0, 20);
     *
     * @param bytes
     * @return
     */
    public SmgpReportMessage decode(byte[] bytes) {

        SmgpReportMessage smgpReportMessage = new SmgpReportMessage();

        try {
            //id
            int offset = 3;
            byte[] idBytes = new byte[10];
            System.arraycopy(bytes, offset, idBytes, 0, 10);
            smgpReportMessage.setId(SmgpMsgIdDecoder.decode(idBytes));
            offset += 10 + 1;

            //sub
            offset += 4;
            byte[] subBytes = new byte[3];
            System.arraycopy(bytes, offset, subBytes, 0, 3);
            smgpReportMessage.setSub(new String(subBytes, "ISO-8859-1"));
            offset += 3 + 1;

            //dlvrd
            offset += 6;
            byte[] dlvrdBytes = new byte[3];
            System.arraycopy(bytes, offset, dlvrdBytes, 0, 3);
            smgpReportMessage.setDlvrd(new String(dlvrdBytes, "ISO-8859-1"));
            offset += 3 + 1;

            //submit date
            offset += 12;
            byte[] submitDateBytes = new byte[10];
            System.arraycopy(bytes, offset, submitDateBytes, 0, 10);
            smgpReportMessage.setSubmitDate(new String(submitDateBytes, "ISO-8859-1"));
            offset += 10 + 1;

            //done date
            offset += 10;
            byte[] doneDateBytes = new byte[10];
            System.arraycopy(bytes, offset, doneDateBytes, 0, 10);
            smgpReportMessage.setDoneDate(new String(doneDateBytes, "ISO-8859-1"));
            offset += 10 + 1;

            //stat
            offset += 5;
            byte[] statBytes = new byte[7];
            System.arraycopy(bytes, offset, statBytes, 0, 7);
            smgpReportMessage.setStat(new String(statBytes, "ISO-8859-1"));
            offset += 7 + 1;

            //err
            offset += 4;
            byte[] errBytes = new byte[3];
            System.arraycopy(bytes, offset, errBytes, 0, 3);
            smgpReportMessage.setErr(new String(errBytes, "ISO-8859-1"));
            offset += 3 + 1;

            //txt
            offset += 5;
            byte[] txtBytes = new byte[20];
            System.arraycopy(bytes, offset, txtBytes, 0, 20);
            smgpReportMessage.setText(new String(txtBytes, "ISO-8859-1"));

        } catch (Exception e) {
            LoggerConfigs.SMGP3_LOG.error(e.getMessage(), e);
        }
        return smgpReportMessage;
    }
}
