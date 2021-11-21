package cn.xavier.basic.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SmsUtil {

    public static final String UID = "yanghq";
    public static final String KEY = "d41d8";


    /**
     * 调用中国网建发送短信
     * @param phone   手机号,如果是多个手机号,请用逗号分割
     * @param message  短信内容
     * @throws Exception
     */
    public static void sendSms(String phone,String message) throws Exception {

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", UID),
                new NameValuePair("Key", KEY),
                new NameValuePair("smsMob", phone),
                new NameValuePair("smsText", message)};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
        System.out.println(result); //打印返回消息状态


        post.releaseConnection();

    }

}