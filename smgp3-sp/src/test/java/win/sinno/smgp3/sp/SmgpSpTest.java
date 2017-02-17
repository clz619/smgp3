package win.sinno.smgp3.sp;

import org.junit.Test;
import win.sinno.smgp3.common.util.ByteUtil;
import win.sinno.smgp3.common.util.LongMsgContentSplitUtil;
import win.sinno.smgp3.communication.decoder.SmgpDeliverDecoder;
import win.sinno.smgp3.communication.factory.SmgpSubmitFactory;
import win.sinno.smgp3.protocol.message.SmgpDeliver;
import win.sinno.smgp3.protocol.message.SmgpSubmit;
import win.sinno.smgp3.protocol.message.SmgpSubmitResp;
import win.sinno.smgp3.protocol.model.SmgpReplyMessage;
import win.sinno.smgp3.protocol.model.SmgpReportMessage;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4PkNumber;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4PkTotal;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4TpUdhi;
import win.sinno.smgp3.protocol.tlv.TpUdhiMessage;
import win.sinno.smgp3.sp.handler.ISmgpReplyHandler;
import win.sinno.smgp3.sp.handler.ISmgpReportHandler;
import win.sinno.smgp3.sp.handler.ISmgpSubmitRespHandler;

import java.util.List;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/13 下午1:59
 */
public class SmgpSpTest {

    private static String host = "";
    private static int port = 8890;
    private static String spId = "";
    private static String spPwd = "";
    private static String spSrcTermId = "";
    private String mobile = "";
    private String msg = "";
    private String longMsg = "";

    @Test
    public void loginTest() {
        SmgpSp smgpSp = new SmgpSp("test", host, port, spId, spPwd, spSrcTermId);

        Thread thread = new Thread(smgpSp);

        thread.start();

        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("........send submit...");
        // send..

        SmgpSubmit smgpSubmit = SmgpSubmitFactory.builder().spId(spId).srcTermId(spSrcTermId)
                .mobile(mobile).msgContent(msg).build();

//        smgpSp.sendSubmit(smgpSubmit);

        try {
            Thread.sleep(20000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeliver() {

        byte[] deliverBytes = {0, 0, 0, -45, 0, 0, 0, 3, 0, 0, 0, 1, 2, 3, 113, 2, 20, 21, 87, 65, -111, -108, 1, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 56, 57, 54, 56, 49, 57, 50, 57, 54, 56, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 48, 54, 57, 48, 54, 57, 52, 51, 49, 57, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 122, 105, 100, 58, 2, 3, 113, 2, 20, 21, 87, 65, 88, 121, 32, 115, 117, 98, 58, 48, 48, 49, 32, 100, 108, 118, 114, 100, 58, 48, 48, 49, 32, 83, 117, 98, 109, 105, 116, 32, 100, 97, 116, 101, 58, 49, 55, 48, 50, 49, 52, 49, 53, 53, 55, 32, 100, 111, 110, 101, 32, 100, 97, 116, 101, 58, 49, 55, 48, 50, 49, 52, 49, 53, 53, 55, 32, 115, 116, 97, 116, 58, 68, 69, 76, 73, 86, 82, 68, 32, 101, 114, 114, 58, 48, 48, 48, 32, 84, 101, 120, 116, 58, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        SmgpDeliver smgpDeliver = SmgpDeliverDecoder.getInstance().decode(deliverBytes);
        System.out.println(smgpDeliver);

        String str = ByteUtil.byteArray2HexString(deliverBytes);

        System.out.println(str);

    }


    @Test
    public void spTest() {
        SmgpSp smgpSp = new SmgpSp("test", host, port, spId, spPwd, spSrcTermId);

        Thread thread = new Thread(smgpSp);

        thread.start();

        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        smgpSp.addSmgpSubmitRespHandler(new ISmgpSubmitRespHandler() {
            @Override
            public void handler(SmgpSubmitResp smgpSubmitResp) {
                System.out.println(".....submit resp handler...." + smgpSubmitResp.toString());
            }
        })
                .addSmgpReportHandler(new ISmgpReportHandler() {
                    @Override
                    public void handler(SmgpReportMessage smgpReportMessage) {
                        System.out.println(".....report handler...." + smgpReportMessage.toString());
                    }
                })
                .addSmgpReplyHandler(new ISmgpReplyHandler() {
                    @Override
                    public void handler(SmgpReplyMessage smgpReplyMessage) {
                        System.out.println(".....reply handler...." + smgpReplyMessage.toString());
                    }
                })
        ;

        System.out.println("........send submit...");
        // send..


        SmgpSubmit smgpSubmit = SmgpSubmitFactory.builder().spId(spId).srcTermId(spSrcTermId)
                .mobile(mobile).msgContent(msg).build();

        smgpSp.sendSubmit(smgpSubmit);

        try {
            Thread.sleep(20000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLongMsg() {
        SmgpSp smgpSp = new SmgpSp("test", host, port, spId, spPwd, spSrcTermId);

        Thread thread = new Thread(smgpSp);

        thread.start();

        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        smgpSp.addSmgpSubmitRespHandler(new ISmgpSubmitRespHandler() {
            @Override
            public void handler(SmgpSubmitResp smgpSubmitResp) {
                System.out.println(".....submit resp handler...." + smgpSubmitResp.toString());
            }
        })
                .addSmgpReportHandler(new ISmgpReportHandler() {
                    @Override
                    public void handler(SmgpReportMessage smgpReportMessage) {
                        System.out.println(".....report handler...." + smgpReportMessage.toString());
                    }
                })
                .addSmgpReplyHandler(new ISmgpReplyHandler() {
                    @Override
                    public void handler(SmgpReplyMessage smgpReplyMessage) {
                        System.out.println(".....reply handler...." + smgpReplyMessage.toString());
                    }
                })
        ;

        System.out.println("........send submit...");

        List<String> msg = LongMsgContentSplitUtil.split(longMsg);

        for (int i = 1; i <= msg.size(); i++) {
            SmgpSubmit smgpSubmit = SmgpSubmitFactory.builder().spId(spId).srcTermId(spSrcTermId)
                    .mobile(mobile).msgContent(msg.get(i - 1)).build();

            TpUdhiMessage tpUdhiMessage = new TpUdhiMessage();
            tpUdhiMessage.setSign(msg.hashCode());
            tpUdhiMessage.setTn(msg.size());
            tpUdhiMessage.setIdx(i);
            smgpSubmit.getBody().setTpUdhiMessage(tpUdhiMessage);

            smgpSubmit.getBody().addSmgpTlv(new SmgpTlv4TpUdhi(1))
                    .addSmgpTlv(new SmgpTlv4PkTotal(msg.size()))
                    .addSmgpTlv(new SmgpTlv4PkNumber(i));

            smgpSp.sendSubmit(smgpSubmit);
        }

        try {
            Thread.sleep(20000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
