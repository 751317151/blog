package com.blog.controller;

import com.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.blog.pojo.BlogType;
import com.blog.service.BlogTypeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("adminBlogType")
public class AdminBlogTypeController {

    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private BlogService blogService;


    /**
     * 增加博客类别
     * @param blogType
     * @return
     */
    @ResponseBody
    @RequestMapping("/addBlogType")
    public boolean addBlogType(@RequestBody BlogType blogType) {
        if (!blogType.getTypeName().equals("")) {
            System.out.println(blogType.toString());
            BlogType addBlogType = new BlogType(blogType.getTypeName(), blogType.getOrderNum());
            blogTypeService.addBlogType(blogType);
            return true;
        }
        return false;
    }

    /**
     * 修改博客分类
     * @param blogType
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyBlogType")
    public boolean modifyBlogType(@RequestBody BlogType blogType) {
        if (blogType.getId() != null) {
            System.out.println(blogType.toString());
            blogTypeService.updateBlogType(blogType);
            return true;
        }
        return false;
    }


    @ResponseBody
    @RequestMapping("deleteBlogTypeById")
    public Boolean deleteBlogTypeById(@RequestParam("blogTypeId")String blogTypeId){
        System.out.println(blogTypeId);
        // 删除博客类别
        blogTypeService.deleteBlogTypeById(Integer.parseInt(blogTypeId));

        return true;
    }
}
