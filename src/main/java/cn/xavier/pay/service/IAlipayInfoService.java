package cn.xavier.pay.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.pay.domain.AlipayInfo;
import cn.xavier.pay.domain.PayAccount;

public interface IAlipayInfoService extends IBaseService<AlipayInfo> {
    AlipayInfo getByShopId(Long shop_id);
}
