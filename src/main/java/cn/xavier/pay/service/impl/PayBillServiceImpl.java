package cn.xavier.pay.service.impl;

import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.GuardClauseUtil;
import cn.xavier.order.service.IProductOrderService;
import cn.xavier.pay.constants.PayConstants;
import cn.xavier.pay.domain.AlipayInfo;
import cn.xavier.pay.domain.PayBill;
import cn.xavier.pay.mapper.AlipayInfoMapper;
import cn.xavier.pay.mapper.PayBillMapper;
import cn.xavier.pay.service.IPayBillService;
import cn.xavier.pay.utils.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PayBillServiceImpl extends BaseServiceImpl<PayBill> implements IPayBillService {

    @Autowired
    private AlipayInfoMapper alipayInfoMapper;

    @Autowired
    private PayBillMapper payBillMapper;

    @Autowired
    private IProductOrderService productOrderService;

    @Override
    public String pay(PayBill bill) {

        GuardClauseUtil.check(bill == null || bill.getId() == null, "账单都没有调我支付干啥");

        PayBill payBill = payBillMapper.loadById(bill.getId());
        GuardClauseUtil.check(payBill == null, "你账单没做持久化");

        // equals()方法会比较值和精度 （1.0 与 1.00 返回结果为 false） ，而 compareTo()则会忽略精度
        GuardClauseUtil.check(payBill.getMoney().compareTo(new BigDecimal(0)) == 0, "免费的单子你不要搞事");

        int payChannel = bill.getPayChannel().intValue();
        String payData = null;

        // 不同的支付方式
        switch (payChannel) {
            case PayConstants.BALANCE: {
                break;
            }
            case PayConstants.ALIPAY: {
                Long shopId = bill.getShop_id();
                AlipayInfo alipayInfo = alipayInfoMapper.loadByShopId(shopId);
                payData = AlipayUtils.pay(payBill, alipayInfo);
                break;
            }
            case PayConstants.WECHAT: {
                break;
            }
            case PayConstants.UNION_PAY: {
                break;
            }
            default: {
                break;
            }
        }
        return payData;
    }

    @Override
    public PayBill findByUnionPaySn(String unionPaySn) {
        return payBillMapper.loadByUnionPaySn(unionPaySn);
    }
}
