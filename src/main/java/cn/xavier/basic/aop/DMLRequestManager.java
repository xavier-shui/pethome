package cn.xavier.basic.aop;

import cn.xavier.basic.util.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 新增,修改和删除数据请求的切面公共代码
 * @author Zheng-Wei Shui
 * @date 11/13/2021
 */
@Component
@Aspect
@Slf4j
public class DMLRequestManager {

    /**
     *
     * @param
     * @return the json response  切面的返回值可以相同或是子类型
     */
    @Around("execution(cn.xavier.basic.util.AjaxResponse cn.xavier.*.controller.*.*(..))")
    public AjaxResponse around(ProceedingJoinPoint joinPoint) {
        // 日志
        log.debug(joinPoint.getSignature().toString() + " executed with parameters " +  Arrays.asList(joinPoint.getArgs()).toString() + "!!!!!!!!!!!");

        try {
            joinPoint.proceed();
            return AjaxResponse.of();
        } catch (Throwable e) {
            e.printStackTrace();
            return AjaxResponse.of()
                    .setSuccess(false)
                    .setMessage("操作失败");
        }
    }
}
