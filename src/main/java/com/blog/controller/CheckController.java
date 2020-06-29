package com.blog.controller;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.blog.pojo.Blogger;
import com.blog.service.BloggerService;
import com.blog.util.MD5Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Controller
public class CheckController {

    @Resource
    private BloggerService bloggerService;

    // 登录页面
    @RequestMapping("/adminLogin")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "/back/login";
    }

    // 验证登录
    @ResponseBody
    @RequestMapping("/checkLogin")
    public boolean login(@RequestBody Blogger blogger, HttpSession session) {

        // 获取登录实体
//        Subject subject = SecurityUtils.getSubject();
        // 获取加密后的密码
        String saltPassword = MD5Util.md5(blogger.getPassword(), "admin");
        System.out.println(blogger.getPassword() + "  " + saltPassword);

        Blogger loginInfo = bloggerService.getLoginInfo();

        System.out.println(blogger.getUsername() + " : " + loginInfo.getUsername());
        System.out.println(saltPassword + " : " + loginInfo.getPassword());
        if (blogger.getUsername().equals(loginInfo.getUsername()) && saltPassword.equals(loginInfo.getPassword())) {
            System.out.println("用户密码正确");
            session.setAttribute("LoginUser", loginInfo);
            return true;
        }
        System.out.println("用户密码错误");
        return false;
    }

    // 忘记密码
    @RequestMapping("/forget")
    public String forget() {
        return "/back/forget";
    }

    // 发送作者邮箱
    @ResponseBody
    @RequestMapping("/checkMail")
    public boolean checkMail(@RequestBody Blogger blogger) {

        if (blogger.getEmail().equals(bloggerService.getBloggerEmail())) {

            Blogger admin = bloggerService.getLoginInfo();

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
            msg.setTo(admin.getEmail()); // 收件人邮箱
            msg.setSubject("密码找回"); // 标题
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(date);
            String content = "亲爱的博主您好，您于 " + time + " 登陆个人博客管理账户失败，" +
                    "您的账号为：" + admin.getUsername() + ", 密码为：" + admin.getHint() + " 请尝试" + "重新登陆。";
            msg.setText(content); // 文本信息

            try {
                mailSender.send(msg);
            } catch (MailException e) {
                System.err.println("发送失败:" + e.getMessage());
                return false;
            }
        }
        return true;
    }
}
