package com.blog.controller;

import com.blog.pojo.Reply;
import com.blog.service.ReplyService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.blog.dao.CommentDao;
import com.blog.pojo.Comment;
import com.blog.pojo.Email;
import com.blog.service.CommentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Controller
@RequestMapping("adminCommentAndReply")
public class AdminCommentAndReplyController {

    @Resource
    private CommentDao commentDao;
    @Resource
    private CommentService commentService;
    @Resource
    private ReplyService replyService;

    @ResponseBody
    @RequestMapping("/sendCommentEmail")
    public boolean sendCommentEmail(@RequestBody Email email) {
        if (email.getCommentId() != null) {
            email.setNickname("BlackStar");
//            email.setSender("hh@huahaohh.cn");
            String commentAddress = commentDao.getEmailByCommentId(email.getCommentId());

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

            mailSender.setHost("smtp.qq.com");
            mailSender.setUsername("hh@huahaohh.cn");
            mailSender.setPassword("授权码");

            // 加认证机制
            Properties javaMailProperties = new Properties();
            javaMailProperties.put("mail.smtp.auth", true);
            javaMailProperties.put("mail.smtp.starttls.enable", true);
            javaMailProperties.put("mail.smtp.timeout", 5000);
            mailSender.setJavaMailProperties(javaMailProperties);

            // 邮件信息
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("hh@huahaohh.cn"); // 发件人邮箱
            msg.setTo(commentAddress); // 收件人邮箱
            msg.setSubject(email.getSubject()); // 标题
            msg.setText(email.getMessage()); // 文本信息
            try {
                mailSender.send(msg);
            } catch (MailException e) {
                System.err.println("发送失败:" + e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/deleteCommentById")
    public boolean deleteCommentById(@RequestBody Comment comment) {
        Integer id = comment.getId();
        if (id != null) {
            commentService.deleteCommentById(id);
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/replyComment")
    public boolean replyComment(@RequestBody Reply reply) {
        if (reply.getCommentId() == null) {
            return false;
        } else {
            replyService.addReply(reply);
            return true;
        }
    }

    @ResponseBody
    @RequestMapping("/deleteReplyById")
    public boolean deleteReplyById(@RequestBody Reply reply) {
        if (reply.getId() == null) {
            return false;
        } else {
            replyService.deleteReplyById(reply.getId());
            return true;
        }
    }
}
