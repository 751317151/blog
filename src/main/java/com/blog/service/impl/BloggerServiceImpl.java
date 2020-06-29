package com.blog.service.impl;

import org.springframework.stereotype.Service;
import com.blog.dao.BloggerDao;
import com.blog.pojo.Blogger;
import com.blog.service.BloggerService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BloggerServiceImpl implements BloggerService {

    @Resource
    private BloggerDao bloggerDao;

    @Override
    public boolean loginCheck(String username, String password) {
        Blogger blogger = bloggerDao.getLoginInfo();
        System.out.println("service: " + blogger.getUsername() + "  " + blogger.getPassword());
        if (username.equals(blogger.getUsername()) && password.equals(blogger.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getBloggerEmail() {
        return bloggerDao.getBloggerEmail();
    }

    @Override
    public Blogger getLoginInfo() {
        return bloggerDao.getLoginInfo();
    }

    @Override
    public Blogger getBloggerByUsername(String username) {
        return bloggerDao.getBloggerByUsername(username);
    }

    @Override
    public Blogger getBloggerInfo() {
        return bloggerDao.getBloggerInfo();
    }

    @Override
    public void updateBloggerInfo(Blogger blogger) {
        bloggerDao.updateBloggerInfo(blogger);
    }

}
