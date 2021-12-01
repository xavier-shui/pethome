package cn.xavier.pay.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.pay.domain.AlipayInfo;
import cn.xavier.pay.domain.PayBill;

/**
 * Pay bill service
 */
public interface IPayBillService extends IBaseService<PayBill> {
    /**
     *  统一支付接口
     *
     * @param bill       bill
     * @param alipayInfo alipay info
     * @return the string
     */
    String pay(PayBill bill);

    /**
     * Find by 统一支付单号
     *
     * @param unionPaySn union pay sn
     * @return the pay bill
     */
    PayBill findByUnionPaySn(String unionPaySn);
}
