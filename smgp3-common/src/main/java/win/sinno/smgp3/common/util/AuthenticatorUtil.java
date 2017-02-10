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
     *
     * @param spId
     * @param spPwd
     * @param timestamp
     * @return
     */
    private static byte[] generateAuthClient(String spId, String spPwd, String timestamp) {

        if (spId == null || timestamp == null || timestamp == null) {
            return null;
        }

        byte[] spIdBytes = spId.getBytes();
        byte[] spPwdBytes = spPwd.getBytes();
        byte[] timestampBytes = timestamp.getBytes();

        int len = spIdBytes.length + 7 + spPwdBytes.length + timestampBytes.length;

        byte[] auths = new byte[len];

        //TODO copy

        byte[] authMd5 = new MD5Util().getMD5ofBytes(auths, len);

        return authMd5;
    }
}
