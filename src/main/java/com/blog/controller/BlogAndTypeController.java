package com.blog.controller;

import com.blog.pojo.*;
import com.blog.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BlogAndTypeController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private LinkService linkService;
    @Resource
    private CommentService commentService;
    @Resource
    private ReplyService replyService;

    /**
     * 根据id查询博客详细信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/blog")
    public ModelAndView getBlogById(@RequestParam("id") Integer id, HttpServletRequest request) {

        id = Integer.parseInt(request.getParameter("id"));
        ModelAndView modelAndView = new ModelAndView();

        // 博客实例
        Blog blog = blogService.getBlogById(id);
        modelAndView.addObject("blog", blog);

        // 获取博客名称
        int blogTypeId = blog.getBlogTypeId();
        String typeName = blogTypeService.getTypeNameById(blogTypeId);
        modelAndView.addObject("typeName", typeName);

        // 刷新访问量
        blog.setClickHit(blog.getClickHit() + 1);
        blogService.updateClickHitById(id);

        // 获取六个最新博客
        List<Blog> sixList = blogService.getSix();
        modelAndView.addObject("sixList", sixList);

        // 获取所有的博客类别
        List<BlogType> blogTypeList = blogTypeService.getAllTypes();
        modelAndView.addObject("blogTypeList", blogTypeList);

        // 获取所有链接名称
        List<Link> linkList = linkService.getAllLink();
        modelAndView.addObject("linkList", linkList);

        // 根据博客 id 获取评论信息
        List<Comment> commentList = commentService.getCommentByBlogId(id);
        modelAndView.addObject("commentList", commentList);

        // 装配回复评论集合
        for (Comment comment : commentList) {
            Integer comment_id = comment.getId();
            List<Reply> replyList = replyService.getReplyListByCommentId(comment_id);
            comment.setReplyList(replyList);
        }

        modelAndView.setViewName("/fore/detail");
        return modelAndView;
    }

    /**
     * 按类别分页查看博客
     * @param currentPage
     * @param blogTypeId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/blogType")
    public ModelAndView blogType(@RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
                                 @RequestParam(value = "blogTypeId",required = false) Integer blogTypeId, HttpServletRequest request) throws Exception {

        // 默认查询第一种类型
        int defaultBlogTypeId = blogTypeService.getAllTypes().get(0).getId();

        if(blogTypeId == null){
            blogTypeId = defaultBlogTypeId;
        }
        System.out.println(blogTypeId);
        ModelAndView modelAndView = new ModelAndView();

        // 分页
        modelAndView.addObject("pageInfo", blogService.getPageByBlogType(currentPage, blogTypeId));

        // 获取所有类别
        List<BlogType> blogTypeList = blogTypeService.getAllTypes();
        modelAndView.addObject("blogTypeList", blogTypeList);

        // 将分类的id传回前台
        modelAndView.addObject("blogTypeId",blogTypeId);

        modelAndView.setViewName("/fore/type");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/getBlogTypeById")
    public BlogType getBlogTypeById(@RequestParam("blogId")String blogId ){
        BlogType blogType = blogTypeService.getBlogTypeById(Integer.valueOf(blogId));
        System.out.println(blogType);
        return blogType;
    }

    // 发表评论
    @RequestMapping("/commentSubmit")
    public String commentSubmit(@RequestParam("id") Integer id, HttpServletRequest request) {

        int blog_id = Integer.parseInt(request.getParameter("id"));

        // 评论实例
        Comment comment = new Comment();
        comment.setName(request.getParameter("name"));
        comment.setEmail(request.getParameter("email"));
        comment.setContent(request.getParameter("content"));
        comment.setBlog_id(blog_id);
        commentService.updateComment(comment);

        return "redirect:/blog?id=" + comment.getBlog_id();
    }

    @ResponseBody
    @RequestMapping("/getLinkById")
    public Link getLinkById(@RequestParam("linkId")String linkId){
        Link link = linkService.getLinkById(Integer.parseInt(linkId));
        return link;
    }
}
