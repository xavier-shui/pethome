package cn.xavier.basic.exception;

/**
 * 自定义业务异常类
 * @author Zheng-Wei Shui
 * @date 11/20/2021
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
