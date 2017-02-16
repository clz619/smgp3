package win.sinno.smgp3.communication.decoder;

import win.sinno.smgp3.common.util.ByteUtil;

/**
 * smgp msgId decoder
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/15 上午11:46
 */
public class SmgpMsgIdDecoder {

    /**
     * 短消息流水号，用来唯一标识一条短消息。
     * 该字段在短消息的转发处理流程中保持唯一。
     * MsgId字段包含以下三部分内容：
     * SMGW代码：3字节（BCD码）
     * 编码规则如下：
     * 3位区号（不足前添0）+2位设备类别+1位序号
     * 区号：所在省长途区号
     * 设备类别：SMGW取06
     * 序号：所在省的设备编码，例如第一个网关编号为1
     * 时间：4字节（BCD码），格式为MMDDHHMM（月日时分）
     * 序列号：3字节（BCD码），取值范围为000000～999999，从0开始，顺序累加，步长为1，循环使用。
     * <p>
     * 例如某SMGW的代码为010061，在2003年1月16日下午5时0分收到一条短消息，这条短消息的MsgID为：0x01006101161700012345，其中010061表示SMGW代码，01161700表示接收时间，012345表示消息序列号。
     * <p>
     * {2, 3, 113, 2, 20, 21, 87, 65, 88, 121} --> 02037102141557415879
     * <p>
     * BCD码 每4位二进制位，表示为一个十进制位
     *
     * @param bytes
     * @return
     */
    public static String decode(byte[] bytes) {
        StringBuilder builder = new StringBuilder();

        for (byte b : bytes) {
            byte b1 = (byte) (b >> 4);
            byte b2 = (byte) (b & 0x0f);
            builder.append(ByteUtil.byte2int(b1));
            builder.append(ByteUtil.byte2int(b2));
        }

        return builder.toString();
    }
}
