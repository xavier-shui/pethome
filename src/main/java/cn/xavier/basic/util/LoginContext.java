package cn.xavier.basic.util;

import cn.xavier.basic.domain.LoginInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginContext {

    /**
     * 获取当前登录用户信息
     *
     * @param request
     * @return
     */
    public static LoginInfo getLoginInfo(HttpServletRequest request) {
        //从请求头中获取token
        String token = request.getHeader("token");
        //使用token从redis中获取登录信息
        if (!StringUtils.isEmpty(token)) {
            //1 获取spring容器
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            //2 通过容器获取bean
            RedisTemplate redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);
            //3 获取登录信息
            return (LoginInfo)redisTemplate.opsForValue().get(token); // null也能强转，结果也是null
        }
        return null;
    }
}