package win.sinno.smgp3.sp;

import org.slf4j.Logger;
import win.sinno.smgp3.common.config.LoggerConfigs;
import win.sinno.smgp3.common.util.LongMsgContentSplitUtil;
import win.sinno.smgp3.communication.factory.SmgpSubmitFactory;
import win.sinno.smgp3.protocol.message.SmgpSubmit;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4PkNumber;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4PkTotal;
import win.sinno.smgp3.protocol.tlv.SmgpTlv4TpUdhi;
import win.sinno.smgp3.protocol.tlv.TpUdhiMessage;
import win.sinno.smgp3.sp.handler.ISmgpReplyHandler;
import win.sinno.smgp3.sp.handler.ISmgpReportHandler;
import win.sinno.smgp3.sp.handler.ISmgpSubmitRespHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * smgp sp manager
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/16 下午5:11
 */
public class SmgpSpManager {

    private static final Logger LOG = LoggerConfigs.SMGP3_LOG;

    private SmgpSpManager() {
    }

    private static class SmgpSpManagerHolder {
        private static final SmgpSpManager HOLDER = new SmgpSpManager();
    }

    public static SmgpSpManager getInstance() {
        return SmgpSpManagerHolder.HOLDER;
    }

    private Map<String, SmgpSp> spMap = new HashMap<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * start smgp sp
     *
     * @param smgpSp
     */
    public synchronized void start(SmgpSp smgpSp) {
        //sp name
        String name = smgpSp.getName();

        SmgpSp oldSp = getSp(name);
        if (oldSp != null) {

            if (oldSp.isConnectFlag()) {
                LOG.warn("sp:{} is connected.do not 2 start!", name);
                return;
            } else {
                //rm old sp
                rmSp(name);
            }
        }

        LOG.info("start sp : {}", new Object[]{smgpSp.toString()});

        executorService.submit(smgpSp);

        spMap.put(name, smgpSp);

    }

    /**
     * add submit resp handler
     *
     * @param name
     * @param submitRespHandler
     */
    public synchronized boolean addSubmitRespHandler(String name, ISmgpSubmitRespHandler submitRespHandler) {
        SmgpSp sp = getSp(name);

        LOG.info("sp:{} add submit resp handler:{}", new Object[]{submitRespHandler.toString()});

        if (sp != null) {
            sp.addSmgpSubmitRespHandler(submitRespHandler);
            return true;
        } else {
            LOG.warn("sp:{} not exist!", name);
        }

        return false;
    }

    /**
     * add report handler
     *
     * @param name
     * @param smgpReportHandler
     */
    public synchronized boolean addReportHandler(String name, ISmgpReportHandler smgpReportHandler) {
        SmgpSp sp = getSp(name);

        LOG.info("sp:{} add report handler:{}", new Object[]{smgpReportHandler.toString()});

        if (sp != null) {
            sp.addSmgpReportHandler(smgpReportHandler);
            return true;
        } else {
            LOG.warn("sp:{} not exist!", name);
        }

        return false;
    }

    /**
     * add reply handler
     *
     * @param name
     * @param smgpReplyHandler
     */
    public synchronized boolean addReplyHandler(String name, ISmgpReplyHandler smgpReplyHandler) {
        SmgpSp sp = getSp(name);
        LOG.info("sp:{} add reply handler:{}", new Object[]{smgpReplyHandler.toString()});
        if (sp != null) {
            sp.addSmgpReplyHandler(smgpReplyHandler);
            return true;
        } else {
            LOG.warn("sp:{} not exist!", name);
        }

        return false;
    }

    /**
     * send msg
     * return header's seqId
     *
     * @param name
     * @param mobile
     * @param content
     * @return
     */
    public Integer send(String name, String mobile, String content) {

        LOG.info("sp:{} send msg mobile:{},content:{}", new Object[]{name, mobile, content});

        Integer seqId = null;

        SmgpSp sp = getSp(name);

        if (sp != null) {
            //2 send

            if (LongMsgContentSplitUtil.isLongMsg(content)) {
                //long msg
                List<String> msg = LongMsgContentSplitUtil.split(content);

                List<SmgpSubmit> smgpSubmits = new ArrayList<SmgpSubmit>();

                List<Integer> seqIds = new ArrayList<>();

                for (int i = 1; i <= msg.size(); i++) {
                    SmgpSubmit smgpSubmit = SmgpSubmitFactory.builder(sp.nextSeqId()).spId(sp.getSpId()).srcTermId(sp.getSpSrcTermId())
                            .mobile(mobile).msgContent(msg.get(i - 1)).build();

                    TpUdhiMessage tpUdhiMessage = new TpUdhiMessage();
                    tpUdhiMessage.setSign(msg.hashCode());
                    tpUdhiMessage.setTn(msg.size());
                    tpUdhiMessage.setIdx(i);
                    smgpSubmit.getBody().setTpUdhiMessage(tpUdhiMessage);

                    smgpSubmit.getBody().addSmgpTlv(new SmgpTlv4TpUdhi(1))
                            .addSmgpTlv(new SmgpTlv4PkTotal(msg.size()))
                            .addSmgpTlv(new SmgpTlv4PkNumber(i));

                    smgpSubmits.add(smgpSubmit);

                    seqIds.add(smgpSubmit.getHeader().getSequenceId());
                }

                sp.sendSubmit((mobile.hashCode() + content.hashCode()) & 0xff, smgpSubmits);

                seqId = seqIds.get(0);
            } else {
                //single msg

                SmgpSubmit smgpSubmit = SmgpSubmitFactory.builder(sp.nextSeqId()).spId(sp.getSpId()).srcTermId(sp.getSpSrcTermId())
                        .mobile(mobile).msgContent(content).build();
                sp.sendSubmit(smgpSubmit);

                seqId = smgpSubmit.getHeader().getSequenceId();
            }

        } else {
            LOG.warn("sp:{} not exist!", new Object[]{name});
        }

        return seqId;
    }

    protected SmgpSp getSp(String name) {
        return spMap.get(name);
    }

    protected synchronized void rmSp(String name) {
        SmgpSp sp = spMap.get(name);
        if (sp != null) {
            sp.stop();
            spMap.remove(name);
        }
    }


}
