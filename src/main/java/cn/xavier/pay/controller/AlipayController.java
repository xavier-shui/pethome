package cn.xavier.pay.controller;

import cn.xavier.order.constant.OrderConstants;
import cn.xavier.order.domain.AdoptOrder;
import cn.xavier.order.domain.ProductOrder;
import cn.xavier.order.service.IAdoptOrderService;
import cn.xavier.order.service.IProductOrderService;
import cn.xavier.pay.constants.AlipayConfig;
import cn.xavier.pay.constants.PayConstants;
import cn.xavier.pay.domain.AlipayInfo;
import cn.xavier.pay.domain.PayBill;
import cn.xavier.pay.service.IAlipayInfoService;
import cn.xavier.pay.service.IPayBillService;
import cn.xavier.pet.service.IPetService;
import cn.xavier.quartz.constant.JobConstants;
import cn.xavier.quartz.service.IQuartzService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class AlipayController {

    @Autowired
    private IPayBillService payBillService;

    @Autowired
    private IAlipayInfoService alipayInfoService;

    @Autowired
    private IPetService petService;

    @Autowired
    private IAdoptOrderService adoptOrderService;

    @Autowired
    private IQuartzService quartzService;

    @Autowired
    private IProductOrderService productOrderService;


    @PostMapping("/notify")
    public void notify(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        try {
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }

            // 都是为了获取公钥
            String unionPaySn = request.getParameter("out_trade_no");
            PayBill bill = payBillService.findByUnionPaySn(unionPaySn);
            if (bill == null) { // 确保有支付单
                return;
            }
            AlipayInfo alipayInfo = alipayInfoService.getByShopId(bill.getShop_id());

            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayInfo.getAlipay_public_key(), AlipayConfig
                    .charset, AlipayConfig.sign_type); //调用SDK验证签名

            /* 实际验证过程建议商户务必添加以下校验：
            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            4、验证app_id是否为该商户本身。
            */
            if (signVerified) {//验证成功
                //商户订单号
                String out_trade_no = request.getParameter("out_trade_no");

                //支付宝交易号
                String trade_no = request.getParameter("trade_no");

                //交易状态
                String trade_status = request.getParameter("trade_status");

                if (trade_status.equals("TRADE_FINISHED")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知

                    // 修改支付单状态
                    bill.setState(PayConstants.PAID);
                    bill.setUpdateTime(new Date());
                    payBillService.update(bill);

                    // 生成流水单, 未做

                    String jobName = "";
                    switch(bill.getBusinessType()) {
                        case PayConstants.BUSINESS_TYPE_ADOPT: { // 宠物收养订单
                            AdoptOrder adoptOrder = adoptOrderService.findById(bill.getBusinessKey());
                            // 下架宠物
                            petService.adopt(adoptOrder.getPet_id(), adoptOrder.getUser_id());

                            // 修改订单状态
                            adoptOrder.setState(OrderConstants.TO_BE_DELIVERED);
                            adoptOrderService.update(adoptOrder);

                            // 获取jobName
                            jobName = JobConstants.jobNameConstruct(JobConstants.ADOPT_ORDER_PAYMENT_TIMEOUT, adoptOrder.getPaySn());
                            break;
                        }
                        case PayConstants.BUSINESS_TYPE_PRODUCT: { // 服务订单
                            ProductOrder productOrder = productOrderService.findById(bill.getBusinessKey());
                            // 修改订单状态
                            productOrder.setState(OrderConstants.TO_BE_DELIVERED);
                            productOrderService.update(productOrder);
                            // 获取jobName
                            jobName = JobConstants.jobNameConstruct(JobConstants.PRODUCT_ORDER_PAYMENT_TIMEOUT, productOrder.getPaySn());
                            break;
                        }
                        default: break;
                    }

                    // 移除定时任务
                    if (StringUtils.hasLength(jobName)) {
                        quartzService.removeJob(jobName);
                    }
                    // 可以通知商户
                }
                // System.out.println("success");

            } else {//验证失败

                //调试用，写文本函数记录程序运行情况是否正常
                //String sWord = AlipaySignature.getSignCheckContentV1(params);
                //AlipayConfig.logResult(sWord);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
