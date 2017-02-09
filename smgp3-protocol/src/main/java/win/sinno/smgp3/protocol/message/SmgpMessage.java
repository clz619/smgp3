package win.sinno.smgp3.protocol.message;

import win.sinno.smgp3.protocol.body.ISmgpBody;
import win.sinno.smgp3.protocol.header.ISmgpHeader;

/**
 * smgp实体
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午2:26
 */
public class SmgpMessage<HEAD extends ISmgpHeader, BODY extends ISmgpBody> {

    private byte[] binary;

    private HEAD header;

    private BODY body;

    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public HEAD getHeader() {
        return header;
    }

    public void setHeader(HEAD header) {
        this.header = header;
    }

    public BODY getBody() {
        return body;
    }

    public void setBody(BODY body) {
        this.body = body;
    }
}
