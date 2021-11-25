package cn.xavier.basic.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zheng-Wei Shui
 * @date 11/24/2021
 */
@Configuration
public class PetHomeMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**","/webjars/**","/v2/**","/swagger-ui.html/**")
                .excludePathPatterns("/fastDfs/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/user/registerByPhone")
                .excludePathPatterns("/error") // 错误页面
                .excludePathPatterns("/verifyCode/**");
    }
}
