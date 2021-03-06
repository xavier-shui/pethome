package cn.xavier.basic.aop;

import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.util.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 新增,修改和删除数据请求的切面公共代码
 *
 * @author Zheng-Wei Shui
 * @date 11/13/2021
 */
@Component
@Aspect
@Slf4j
@Order(1)
public class AjaxResponseManager {

    /**
     * @param
     * @return the json response  切面的返回值可以相同或是子类型
     */
    @Around("execution(cn.xavier.basic.util.AjaxResponse cn.xavier.*.controller.*.*(..))")
    public AjaxResponse around(ProceedingJoinPoint joinPoint) {
        // 日志
        log.debug("{} executed with parameters {}!!!!!!!!!!!",
                joinPoint.getSignature().toString(),
                Arrays.asList(joinPoint.getArgs()).toString());

        try {
            return (AjaxResponse) joinPoint.proceed();
            // 有业务异常先catch
        } catch (BusinessException e) {
            // 自己写的业务异常不追踪栈了
            return AjaxResponse.of()
                    .setSuccess(false)
                    .setMessage(e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            return AjaxResponse.of()
                    .setSuccess(false)
                    .setMessage("系统错误，操作失败");
        }
    }
}
