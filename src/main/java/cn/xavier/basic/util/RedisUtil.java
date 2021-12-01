package cn.xavier.basic.util;

import cn.xavier.basic.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Zheng-Wei Shui
 * @date 11/25/2021
 */
@Component
public class RedisUtil {

    @Autowired //如果此类不受Spring管理，不能直接注入获取bean, 且写static无法注入
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 验证码校验
     * Verify code check *
     *
     * @param businessKey  business key 业务键
     * @param codeProvided code provided 客户提供的验证码
     * @throws BusinessException 验证码已失效或有误抛此异常
     */
    public void verifyCodeCheck(String businessKey, String codeProvided) throws BusinessException {
        String verifyCode = stringRedisTemplate.opsForValue().get(businessKey);
        if (!StringUtils.hasLength(verifyCode)) {
            throw new BusinessException("验证码已失效，请重新获取!");
        }

        // 不分大小写
        if (!verifyCode.equalsIgnoreCase(codeProvided)) {
            throw new BusinessException("验证码有误!");
        }
    }

}
