package cn.xavier.pay.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.pay.domain.PayBill;

public interface PayBillMapper extends BaseMapper<PayBill> {
    /**
     * Load by 统一支付单号
     *
     * @param unionPaySn union pay sn
     * @return the pay bill
     */
    PayBill loadByUnionPaySn(String unionPaySn);

}
