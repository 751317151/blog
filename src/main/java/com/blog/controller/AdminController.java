package com.blog.controller;

import com.blog.pojo.BlogType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.blog.pojo.Blogger;
import com.blog.pojo.Comment;
import com.blog.pojo.Reply;
import com.blog.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private CommentService commentService;
    @Resource
    private ReplyService replyService;
    @Resource
    private LinkService linkService;
    @Resource
    private BloggerService bloggerService;

    // 管理员界面
    @RequestMapping("/manage")
    public ModelAndView manage(@RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        // 分页博客
        modelAndView.addObject("pageInfo", blogService.getAllBlog(currentPage));

        // 类型
        modelAndView.addObject("blogTypeList", blogTypeService.getAllTypes());

        // 评论
        List<Comment> commentList = commentService.getAllComment();
        for (Comment comment : commentList) {
            System.out.println(comment.getCommentDate() + "评论时间");
            comment.setBlogTitle(commentService.getTitleByBlogId(comment.getBlog_id()));
        }
        modelAndView.addObject("commentList", commentList);

        // 回复
        List<Reply> replyList = replyService.getAllReply();
        for (Reply reply : replyList) {
            Integer id = reply.getId();
            reply.setBlog_id(replyService.getBlogIdByReplyId(id));
            reply.setBlogTitle(replyService.getBlogTitleByReplyId(id));
            System.out.println(reply.toString());
        }
        modelAndView.addObject("replyList", replyList);

        // 链接
        modelAndView.addObject("linkList", linkService.getAllLink());

        // 博主
        modelAndView.addObject("blogger", bloggerService.getBloggerInfo());

        modelAndView.setViewName("/back/admin");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/modifyBloggerInfo")
    public boolean modifyBloggerInfo(@RequestBody Blogger blogger) {
        if (blogger.getUsername().equals("")) {
            return false;
        } else {
            bloggerService.updateBloggerInfo(blogger);
            return true;
        }
    }

    @RequestMapping("/quit")
    public void quit(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception{
        session.removeAttribute("LoginUser");
        response.sendRedirect("/adminLogin");
    }
}
