package win.sinno.smgp3.common.util;

/**
 * 验证工具
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/10 下午5:47
 */
public class AuthenticatorUtil {

    /**
     * 生成客户端认证码
     * <p>
     * 客户端认证码，用来鉴别客户端的合法性。
     * 其值通过单向MD5 hash计算得出，表示如下：
     * AuthenticatorClient =MD5（ClientID+7 字节的二进制0（0x00） + Shared secret+Timestamp）
     * Shared secret 由服务器端与客户端事先商定，最长15字节。
     * 此处Timestamp格式为：MMDDHHMMSS（月日时分秒），经TimeStamp字段值转换成字符串，转换后右对齐，左补0x30得到。
     * 例如3月1日0时0分0秒，TimeStamp字段值为0x11F0E540，此处为0301000000。
     *
     * @param spId
     * @param spPwd
     * @param timestamp
     * @return
     */
    public static byte[] generateAuthClient(String spId, String spPwd, String timestamp) {

        if (spId == null || timestamp == null || timestamp == null) {
            return null;
        }

        byte[] spIdBytes = spId.getBytes();
        byte[] spPwdBytes = spPwd.getBytes();
        byte[] timestampBytes = timestamp.getBytes();

        int len = spIdBytes.length + 7 + spPwdBytes.length + timestampBytes.length;

        byte[] auths = new byte[len];

        // set byte val
        System.arraycopy(spIdBytes, 0, auths, 0, spIdBytes.length);

        for (int i = 0; i < 7; i++) {
            auths[spIdBytes.length + i] = 0x00;
        }

        System.arraycopy(spPwdBytes, 0, auths, spIdBytes.length + 7, spPwdBytes.length);

        System.arraycopy(timestampBytes, 0, auths,
                spIdBytes.length + 7 + spPwdBytes.length, timestampBytes.length);

        byte[] authMd5 = new MD5Util().getMD5ofBytes(auths, len);

        return authMd5;
    }
}
