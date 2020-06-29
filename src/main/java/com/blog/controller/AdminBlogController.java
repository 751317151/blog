package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.blog.pojo.*;
import com.blog.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("adminBlog")
public class AdminBlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;

    // 创作博文
    @RequestMapping("/compose")
    public ModelAndView compose() {
        ModelAndView modelAndView = new ModelAndView();
        // 获取所有博客类别实例
        List<BlogType> blogTypeList = blogTypeService.getAllTypes();
        modelAndView.addObject("blogTypeList", blogTypeList);
        modelAndView.setViewName("/back/compose");
        return modelAndView;
    }

    // 获取博文内容进行添加
    @ResponseBody
    @RequestMapping("/getContent")
    public boolean getContent(@RequestBody Blog blog) {
        if (!blog.getTitle().equals("")) {
            Blog savedBlog = new Blog(blog.getTitle(), blog.getSummary(), blog.getContent(), blog.getKeyword(), blog.getBlogTypeId());
            System.out.println(savedBlog.toString());
            try{
                blogService.insertBlog(savedBlog);
                return true;
            }catch (Exception e){
                return false;
            }

        }
        return false;
    }

    // 由 id 转到编辑页面
    @RequestMapping("/turnToEdit")
    public ModelAndView turnToEdit(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id != null) {

            Blog blog = blogService.getBlogById(id);
            modelAndView.addObject("blog", blog);
            System.out.println(blog.toString());

            List<BlogType> blogTypeList = blogTypeService.getAllTypes();
            modelAndView.addObject("blogTypeList", blogTypeList);
            modelAndView.setViewName("/back/edit");
        }
        return modelAndView;
    }

    // 获取博文实体进行更新
    @ResponseBody
    @RequestMapping("/edit")
    public boolean edit(@RequestBody Blog blog) {
        if (blog.getId() != null) {
            Blog updateBlog = new Blog(blog.getId(), blog.getTitle(), blog.getSummary(), blog.getContent(), blog.getKeyword(), blog.getBlogTypeId());
            System.out.println(blog.toString());
            blogService.updateBlogById(blog);
            return true;
        }
        return false;
    }

    // 由博文id进行删除
    @ResponseBody
    @RequestMapping("/deleteBlogById")
    public boolean deleteBlogById(@RequestParam("deleteId") String deleteId) {
        System.out.println("deleteId" + deleteId);
        Integer id = Integer.valueOf(deleteId);
        // 删除博客
        blogService.deleteBlogById(id);
        return true;
    }



}
