package cn.xavier.basic.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 每次请求的日志加UUID，好区分
 * @author Zheng-Wei Shui
 * @date 11/29/2021
 */
@Component
@Aspect
@Slf4j
@Order(0) // 一个切点有多个增强时，主要是为了把@Before弄到@Around前面执行
public class RequestLogManager {

    private static final String KEY = "requestId";

     @Before("execution(* cn.xavier.*.controller.*.*(..))")
     public void before() {
         //  入口传入请求ID
         String uuid = UUID.randomUUID().toString();
         MDC.put(KEY, uuid.substring(uuid.length() - 17)); // 不要太长, 屏幕不好显示
     }

     @After("execution(* cn.xavier.*.controller.*.*(..))")
     public void after() {
         //  出口移除请求ID
         MDC.remove(KEY);
     }
}
