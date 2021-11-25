package cn.xavier.basic.constant;

/**
 * @author Zheng-Wei Shui
 * @date 11/21/2021
 */
public class RedisConstants {
    /**
     * 用户验证码key前缀
     */
    public static String getSmsCodeKeyPrefix(String type) {
        return String.format("user_%s_sms_verify_code:", type);
    }

    /**
     * KEY_DOES_NOT_EXIST key过期时间返回值意义: 不存在key
     */
    public static final int KEY_DOES_NOT_EXIST = -2;

    /**
     * KEY_HAS_NO_ASSOCIATED_EXPIRE key过期时间返回值意义: key存在但没设过期时间
     */
    public static final int KEY_HAS_NO_ASSOCIATED_EXPIRE = -1;

}
