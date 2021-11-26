package cn.xavier.basic.service;

import java.util.Map;

/**
 * 验证码业务
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
public interface IVerifyCodeService {
    /**
     * Send sms code 给手机发验证码
     *
     * @param params 手机号 和 验证码类型
     */
    void sendSmsCode(Map<String, String> params);
}
