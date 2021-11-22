package cn.xavier.basic.service;

/**
 * 验证码业务
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
public interface IVerifyCodeService {
    /**
     * Send sms code 给手机发验证码
     *
     * @param phone 手机号
     */
    void sendSmsCode(String phone);
}
