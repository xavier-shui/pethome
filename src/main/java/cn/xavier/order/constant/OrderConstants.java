package cn.xavier.order.constant;

/**
 * @author Zheng-Wei Shui
 * @date 11/29/2021
 */
public class OrderConstants {
    /**
     * 待支付
     */
    public static final int TO_BE_PAID = 0;
    /**
     * 待报账, 收购订单
     */
    public static final int TO_BE_REIMBURSED = 1;
    /**
     * 待打款, 收购订单
     */
    public static final int PENDING_PAYMENT = 2;
    /**
     * 完成
     */
    public static final int COMPLETED = 3;
    /**
     * 取消
     */
    public static final int CANCELLED = -1;


    /**
     * 领养订单, 待发货
     */
    public static final int TO_BE_DELIVERED = 1;

    /**
     * 领养订单, 待确认
     */
    public static final int TO_BE_CONFIRMED = 2;

}
