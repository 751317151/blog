package com.blog.controller;


import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.blog.pojo.Email;

import java.util.Properties;

@Controller
public class EmailController {

    // 给作者发送邮箱
    @ResponseBody
    @RequestMapping(value = "/sendEmail")
    public boolean sendEmail(@RequestBody Email email) {
        if (email.getNickname().equals("")||email.getSender().equals("")||email.getMessage().equals("")) {
            return false;
        } else {
            System.out.println(email.toString());

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.qq.com");
            mailSender.setUsername("751317151@qq.com");
            mailSender.setPassword("邮箱授权码");

            // 加认证机制
            Properties javaMailProperties = new Properties();
            javaMailProperties.put("mail.smtp.auth", true);
            javaMailProperties.put("mail.smtp.starttls.enable", true);
            javaMailProperties.put("mail.smtp.timeout", 5000);
            mailSender.setJavaMailProperties(javaMailProperties);

            // 邮件信息
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("hh@huahaohh.cn"); // 发件人邮箱
            msg.setTo("hh@huahaohh.cn"); // 收件人邮箱
            msg.setSubject(email.getNickname() + "留言"); // 标题
            msg.setText(email.getMessage() + "  我的邮箱：" + email.getSender()); // 文本信息

            try {
                mailSender.send(msg);
            } catch (MailException e) {
                System.err.println("发送失败:" + e.getMessage());
                return false;
            }
            return true;
        }
    }

}
