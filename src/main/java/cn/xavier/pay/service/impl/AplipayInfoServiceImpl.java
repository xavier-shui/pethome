package cn.xavier.pay.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.pay.domain.AlipayInfo;
import cn.xavier.pay.domain.PayBill;
import cn.xavier.pay.mapper.AlipayInfoMapper;
import cn.xavier.pay.service.IAlipayInfoService;
import cn.xavier.pay.service.IPayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplipayInfoServiceImpl extends BaseServiceImpl<AlipayInfo> implements IAlipayInfoService {
    @Autowired
    private AlipayInfoMapper alipayInfoMapper;
    @Override
    public AlipayInfo getByShopId(Long shop_id) {
        return alipayInfoMapper.loadByShopId(shop_id);
    }
}
