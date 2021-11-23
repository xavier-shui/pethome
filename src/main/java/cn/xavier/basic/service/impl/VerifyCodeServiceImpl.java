package cn.xavier.basic.service.impl;

import cn.xavier.basic.constant.RedisConstants;
import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.service.IVerifyCodeService;
import cn.xavier.basic.util.StrUtils;
import cn.xavier.user.domain.User;
import cn.xavier.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendSmsCode(String phone) {
        // 1. 参数校验
        if (StringUtils.isEmpty(phone)) {
            throw new BusinessException("手机号不能为空!");
        }
        String regex = "^1[345789]\\d{9}$";
        if (!phone.matches(regex)) {
            throw new BusinessException("手机号格式不正确!");
        }


        // 2. 已注册校验
        User user = userMapper.loadByPhone(phone);
        if (user != null) {
            throw new BusinessException("手机号已被注册!");
        }


        // 3. 该手机号是否已经生成过验证码
        String key = RedisConstants.USER_REGISTER_VERIFY_CODE_KEY_PREFIX + phone;
        // verifyCode里面不用存时间戳
        String verifyCode = stringRedisTemplate.opsForValue().get(key);
        // The command returns -2 if the key does not exist.
        // The command returns -1 if the key exists but has no associated expire.
        Long ttlMinutes = stringRedisTemplate.getExpire(key, TimeUnit.MINUTES);
        // 3.1 是否已过重发时间
        if (ttlMinutes >= 2) {
            throw new BusinessException("至少需间隔一分钟再获取验证码!");
        } else if (ttlMinutes == RedisConstants.KEY_DOES_NOT_EXIST) { // 没生成过验证码或已过期
            verifyCode = StrUtils.getRandomString(6);
            // 4. 保存验证码信息至redis， 验证码过期时间三分钟
            stringRedisTemplate.opsForValue().set(key, verifyCode, 3, TimeUnit.MINUTES);
        } else {
            // 就用原来的验证码延长有效期
            stringRedisTemplate.expire(key, 3, TimeUnit.MINUTES);
        }


        // 5. 发送短信(为避免接收短信延迟导致客户输入过期验证码，写明过期时间)
        // 测试网建平台过了6分钟才收到短信
        // 指定日期时间格式
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 提醒客户验证码失效时间, 秒级精度还是能保证的
        String expireAt = pattern.format(LocalDateTime.now().plusMinutes(3));
        String message = String.format("您的验证码是%s, 请在%s前使用!", verifyCode, expireAt);
        // int responseNumber = SmsUtil.sendSms(phone, message);
        // if (responseNumber < 0) {
        //     throw new BusinessException("验证码发送失败，请确认您的手机号是否正确!");
        // }
        System.out.println(message);
    }

}
