package cn.xavier;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class EmailTest extends BaseTest {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 简单邮件
     */
    @Test
    public void send() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //设置发送人, 必须设置，不设虽然测试通过，但没收到邮件
        mailMessage.setFrom("shuizhengwei@yeah.net");
        //邮件主题
        mailMessage.setSubject("Test");
        //邮件内容
        mailMessage.setText("test.....");
        //收件人
        mailMessage.setTo("shuizhengwei@yeah.net");
        javaMailSender.send(mailMessage);
    }

    /**
     * 复杂邮件
     *
     * @throws Exception exception
     */
    @Test
    public void test2() throws Exception{
        //创建复杂邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //发送复杂邮件的工具类
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
        helper.setFrom("shuizhengwei@yeah.net");
        helper.setSubject("Test MIME");

        // 读取html
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/sneakyThrow.html"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        helper.setText(sb.toString(),true);
        //添加附件
        helper.addAttachment("people.jpg",new File("L:\\a\\temp_desktop_wallpaper\\056d_6947288935244582146.jpg"));
        //收件人
        helper.setTo("shuizhengwei@yeah.net");
        // helper.setCc();
        javaMailSender.send(mimeMessage);
    }
}