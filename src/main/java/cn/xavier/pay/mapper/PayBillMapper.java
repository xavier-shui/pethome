package cn.xavier.pay.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.pay.domain.PayBill;

public interface PayBillMapper extends BaseMapper<PayBill> {
    PayBill loadByUnionPaySn(String unionPaySn);
}
