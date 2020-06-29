package com.blog.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.blog.pojo.Blog;
import com.blog.pojo.BlogType;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;

    // 访问主页
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        // 首页博客数量
        int blogCount = 5;
        List<Blog> blogList = blogService.getForeBlog(blogCount);
        modelAndView.addObject("blogList", blogList);
        modelAndView.setViewName("/fore/index");
        return modelAndView;
    }

    /**
     * 按页查看博客,首页更多
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping("/moreBlog")
    public ModelAndView setPage(@RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        // 分页
        modelAndView.addObject("pageInfo", blogService.getBlogByPage(currentPage));

        // 获取所有的博客类别
        List<BlogType> blogTypeList = blogTypeService.getAllTypes();
        modelAndView.addObject("blogTypeList", blogTypeList);

        modelAndView.setViewName("/fore/more");
        return modelAndView;
    }

    @RequestMapping("/contact")
    public ModelAndView contact(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fore/contact");
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fore/search");
        return modelAndView;
    }

    @RequestMapping("/searchDetail")
    public ModelAndView searchDetail(@RequestParam(required = false) String str) {
        ModelAndView modelAndView = new ModelAndView();
        List<Integer> idList = blogService.searchBlog(str);
        System.out.println(" ID :" + idList);
        if (idList.isEmpty()) {
            modelAndView.setViewName("/fore/none");
        } else {
            List<Blog> blogList = new ArrayList<>();
            for (Integer i : idList) {
                blogList.add(blogService.getBlogById(i));
            }
            modelAndView.addObject("blogList", blogList);
            modelAndView.setViewName("/fore/result");
        }
        return modelAndView;
    }

}
