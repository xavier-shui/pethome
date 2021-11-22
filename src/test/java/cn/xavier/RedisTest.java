package cn.xavier;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
public class RedisTest extends BaseTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testTtl() {
        // redisTemplate.opsForValue().set("code", "2345", 90, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("code"));
        // 不足一分钟直接返回0
        System.out.println(redisTemplate.getExpire("code", TimeUnit.MINUTES));
    }
}
