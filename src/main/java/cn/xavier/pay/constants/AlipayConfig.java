package cn.xavier.pay.constants;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Zheng-Wei Shui
 * @date 11/30/2021
 */
public class AlipayConfig {

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数
    public static String notify_url = "http://b16f-110-184-230-165.ngrok.io/notify";

    // 页面跳转同步通知页面路径
    public static String return_url = "http://localhost/success.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "logs";


    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
