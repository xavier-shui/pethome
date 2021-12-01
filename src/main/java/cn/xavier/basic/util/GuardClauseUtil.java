package cn.xavier.basic.util;

import cn.xavier.basic.exception.BusinessException;

/**
 * 卫语句
 * @author Zheng-Wei Shui
 * @date 12/1/2021
 */
public class GuardClauseUtil {

    /**
     * 检查条件为真，抛异常
     *
     * @param condition condition
     * @param message   message
     * @throws BusinessException business exception
     */
    public static void check(boolean condition, String message) throws BusinessException {
        throwIfTrue(condition).message(message); // 这里是为了试验函数式接口
    }

    /**
     *  if true 抛业务异常
     *
     * @param b b
     * @return the throw exception function
     */
    private static ThrowExceptionFunction throwIfTrue(boolean b) throws BusinessException {
        return message -> {
            if (b) {
                throw new BusinessException(message);
            }
        };
    }


    /**
     * 内部函数式接口
     */
    @FunctionalInterface
    interface ThrowExceptionFunction { // 本类外面不访问方法，不加public

        /**
         * 抛异常
         *
         * @param message message
         */
        void message(String message);
    }

}