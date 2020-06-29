package com.blog.service.impl;

import com.blog.dao.BlogDao;
import com.blog.service.BlogService;
import org.springframework.stereotype.Service;
import com.blog.dao.BlogTypeDao;
import com.blog.pojo.BlogType;
import com.blog.service.BlogTypeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("blogTypeService")
@Transactional
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeDao blogTypeDao;
    @Resource
    private BlogService blogService;
    @Resource
    private BlogDao blogDao;

    @Override
    public String getTypeNameById(int id) {
        return blogTypeDao.getTypeNameById(id);
    }

    @Override
    public List<BlogType> getAllTypes() {
        return blogTypeDao.getAllTypes();
    }

    @Override
    public void addBlogType(BlogType blogType) {
        blogTypeDao.addBlogType(blogType);
    }

    @Override
    public void deleteBlogTypeById(int id) {
        // 删除博客
        List<Integer> blogIdList = blogTypeDao.getBlogIdListByTypeId(id);
        for (Integer blogId : blogIdList) {
            blogService.deleteBlogById(blogId);
        }

        // 删博客类型
        blogTypeDao.deleteBlogTypeById(id);
    }

    @Override
    public Integer getIdByTypeName(String typeName) {
        return blogTypeDao.getIdByTypeName(typeName);
    }

    @Override
    public void updateBlogType(BlogType blogType) {
        blogTypeDao.updateBlogType(blogType);
    }

    @Override
    public BlogType getBlogTypeById(Integer blogId) {
        return blogTypeDao.getBlogTypeById(blogId);
    }

    // 根据博客类型id获取博客列表
    @Override
    public List<Integer> getBlogIdsByTypeId(int blogTypeId) {
        List<Integer> blogIdList = blogTypeDao.getBlogIdListByTypeId(blogTypeId);
        return blogIdList;
    }

}
