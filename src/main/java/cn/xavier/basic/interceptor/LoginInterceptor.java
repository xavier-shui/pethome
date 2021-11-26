package cn.xavier.basic.interceptor;

import cn.xavier.basic.constant.RedisConstants;
import cn.xavier.basic.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 * @author Zheng-Wei Shui
 * @date 11/24/2021
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 只有token有效才放行(后期可以补充资源权限)
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token) && (redisTemplate.getExpire(token) != RedisConstants.KEY_DOES_NOT_EXIST)) {
            // 刷新token有效期
            redisTemplate.expire(token, 30, TimeUnit.MINUTES);
            return true;
        }
        // 返回错误信息并阻止放行
        AjaxResponse.failureResponse(response);
        return false;
    }
}
