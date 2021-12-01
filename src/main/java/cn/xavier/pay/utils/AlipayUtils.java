package cn.xavier.pay.utils;

import cn.xavier.pay.constants.AlipayConfig;
import cn.xavier.pay.domain.AlipayInfo;
import cn.xavier.pay.domain.PayBill;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * @author Zheng-Wei Shui
 * @date 11/30/2021
 */
public class AlipayUtils {
    /**
     * Pay string
     *
     * @param bill       支付单
     * @param alipayInfo 商家收款号
     * @return the string
     */
    public static String pay(PayBill bill, AlipayInfo alipayInfo) {
        String result = null;
        try {
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                    alipayInfo.getAppid(),
                    alipayInfo.getMerchant_private_key(),
                    "json",
                    AlipayConfig.charset,
                    alipayInfo.getAlipay_public_key(),
                    AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = bill.getUnionPaySn();
            //付款金额，必填
            String total_amount = bill.getMoney().toString();
            //订单名称，必填
            String subject = bill.getDigest();
            //商品描述，可空
            String body = bill.getDigest();

            alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                    + "\"total_amount\":\""+ total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //输出
        return result;
    }
}
