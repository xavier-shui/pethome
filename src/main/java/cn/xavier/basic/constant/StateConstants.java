package cn.xavier.basic.constant;

/**
 * 状态常量
 *
 * @author Zheng-Wei Shui
 * @date 11/20/2021
 */
public class StateConstants {
    /**
     * 待提交
     */
    public static final int TO_BE_SUBMITTED = 0;
    /**
     * 待审核
     */
    public static final int TO_BE_VERIFIED = 1;
    /**
     * 审核通过
     */
    public static final int VERIFIED = 2;
    /**
     * 驳回, 等待修改后再次提交
     */
    public static final int TO_BE_RESUBMITTED = 3;
    /**
     * 拒绝，不可再次提交
     */
    public static final int REFUSED = 4;
}
