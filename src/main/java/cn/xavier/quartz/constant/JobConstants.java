package cn.xavier.quartz.constant;

/**
 * 作业类型常量类
 * @author Zheng-Wei Shui
 * @date 12/2/2021
 */
public class JobConstants {
    /**
     * 领养订单支付超时
     */
    public static final String ADOPT_ORDER_PAYMENT_TIMEOUT = "adopt_order_payment_timeout";
    /**
     * 服务订单支付超时
     */
    public static final String PRODUCT_ORDER_PAYMENT_TIMEOUT = "product_order_payment_timeout";
    /**
     * 领养订单确认超时
     */
    public static final String ADOPT_ORDER_CONFIRM_TIMEOUT = "adopt_order_confirm_timeout";
    /**
     * 服务订单确认超时
     */
    public static final String PRODUCT_ORDER_CONFIRM_TIMEOUT = "product_order_confirm_timeout";

    /**
     * 冒号分割符，获取时一截取就没有了
     *
     * @param jobType job type
     * @param paySn   pay sn
     * @return the string
     */
    public static String jobNameConstruct(String jobType, String paySn) {
        return jobType + ":" + paySn;
    }


    /*
    也可以常量自带:,
    提供extractJobTypeFromJobName方法和extractPaySnFromJobName方法
    (根据:索引截取子串)
     */
}
