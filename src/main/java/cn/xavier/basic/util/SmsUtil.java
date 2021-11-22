package cn.xavier.basic.util;

import lombok.SneakyThrows;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SmsUtil {

    public static final String UID = "WeiEr";
    public static final String KEY = "d41d8cd98f00b204e980";


    /**
     * 调用中国网建发送短信
     *
     * @param phone   手机号,如果是多个手机号,请用逗号分割
     * @param message 短信内容
     * @return 大于0	短信发送数量, 小于0失败 @see http://www.smschinese.cn/api.shtml
     */
    @SneakyThrows
    public static int sendSms(String phone,String message) {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", UID),
                new NameValuePair("Key", KEY),
                new NameValuePair("smsMob", phone),
                new NameValuePair("smsText", message)};
        post.setRequestBody(data);

        // close need not be part of basic interface since underlying connection
        // is released back to the connection manager automatically after every execute
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
        post.releaseConnection();
        //返回消息状态
        return Integer.parseInt(result);
    }

}