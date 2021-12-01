package cn.xavier.pay.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.pay.domain.AlipayInfo;
import cn.xavier.pay.domain.PayBill;

public interface AlipayInfoMapper extends BaseMapper<AlipayInfo> {
    AlipayInfo loadByShopId(Long shop_id);
}
