package cn.xavier.pay.constants;

/**
 * @author Zheng-Wei Shui
 * @date 11/30/2021
 */
public class PayConstants {
    /**
     * 待支付
     */
    public static final int TO_BE_PAID = 0;
    /**
     * 已支付
     */
    public static final int PAID = 1;
    /**
     * 取消
     */
    public static final int CANCELLED = -1;

    /**
     * BALANCE 余额
     */
    public static final int BALANCE = 0;
    /**
     * ALIPAY 支付宝
     */
    public static final int ALIPAY = 1;
    /**
     * WECHAT 微信
     */
    public static final int WECHAT = 2;
    /**
     * UNION_PAY 银联
     */
    public static final int UNION_PAY = 3;

    /**
     * 领养订单
     */
    public static final String BUSINESS_TYPE_ADOPT = "business_type_adopt";
    /**
     * 服务订单
     */
    public static final String BUSINESS_TYPE_PRODUCT = "business_type_product";
}
