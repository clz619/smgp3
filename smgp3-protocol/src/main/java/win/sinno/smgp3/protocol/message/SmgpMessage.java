package win.sinno.smgp3.protocol.message;

import win.sinno.smgp3.protocol.body.ISmgpBody;
import win.sinno.smgp3.protocol.header.ISmgpHeader;

import java.util.Arrays;

/**
 * smgp实体
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 下午2:26
 */
public class SmgpMessage<HEADER extends ISmgpHeader, BODY extends ISmgpBody> {

    public SmgpMessage() {
    }

    public SmgpMessage(byte[] binary) {
        this.binary = binary;
    }

    public SmgpMessage(HEADER header, BODY body) {
        this.header = header;
        this.body = body;
    }

    public SmgpMessage(byte[] binary, HEADER header, BODY body) {
        this.binary = binary;
        this.header = header;
        this.body = body;
    }

    private byte[] binary;

    private HEADER header;

    private BODY body;

    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public HEADER getHeader() {
        return header;
    }

    public void setHeader(HEADER header) {
        this.header = header;
    }

    public BODY getBody() {
        return body;
    }

    public void setBody(BODY body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "SmgpMessage{" +
                "binary=" + Arrays.toString(binary) +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
