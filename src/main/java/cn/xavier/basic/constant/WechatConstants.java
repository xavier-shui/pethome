package cn.xavier.basic.constant;

import java.text.MessageFormat;

/**
 * 微信登录相关常量
 * @author Zheng-Wei Shui
 * @date 11/25/2021
 */
public class WechatConstants {

    private static final String APPID = "wxd853562a0548a7d0";
    private static final String SECRET = "4a5d5615f93f24bdba2ba8534642dbb6";

    // 授权码模式获取token
    private static final String ACCESS_TOKEN_URL =
            MessageFormat.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code=CODE&grant_type=authorization_code",
                    APPID, SECRET);

    // 获取用户个人信息（UnionID机制):
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

    public static String getSpecifiedTokenUrl(String code) {
        return ACCESS_TOKEN_URL.replace("CODE", code);
    }

    public static String getSpecifiedUserInfoUrl(String accessToken, String openId) {
        return USER_INFO_URL.replace("ACCESS_TOKEN", accessToken)
                .replace("OPENID", openId);
    }
}
