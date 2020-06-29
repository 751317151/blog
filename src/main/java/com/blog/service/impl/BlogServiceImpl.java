package com.blog.service.impl;

import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;
import com.blog.dao.BlogDao;
import com.blog.pojo.Blog;
import com.blog.pojo.Page;
import com.blog.service.BlogService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("blogService")
@Transactional
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;
    @Resource
    private CommentService commentService;

    @Override
    public List<Blog> getForeBlog(int blogCount) {
        return blogDao.getForeBlog(blogCount);
    }

    @Override
    public int selectCount() {
        return blogDao.selectCount();
    }

    @Override
    public Page<Blog> getBlogByPage(int currentPage) {
        HashMap<String, Object> map = new HashMap<>();
        Page<Blog> page = new Page<>();

        // 当前页数
        page.setCurrentPage(currentPage);

        // 每页显示条数
        int pageSize = 5;
        page.setPageSize(pageSize);

        // 记录总数
        int sum = blogDao.selectCount();
        page.setSum(sum);

        // 总页数
        double decimal = sum;
        Double num = Math.ceil(decimal / pageSize);
        page.setTotalPage(num.intValue());

        // 设置开始结束页数
        map.put("start", (currentPage - 1) * pageSize);
        map.put("size", page.getPageSize());

        // 每页显示的数据
        List<Blog> blogList = blogDao.getBlogByPage(map);
        page.setBlogList(blogList);

        return page;
    }

    @Override
    public Page<Blog> getAllBlog(int currentPage) {
        HashMap<String, Object> map = new HashMap<>();
        Page<Blog> page = new Page<>();

        // 当前页数
        page.setCurrentPage(currentPage);

        // 每页显示条数
        int pageSize = 5;
        page.setPageSize(pageSize);

        // 记录总数
        int sum = blogDao.selectCount();
        page.setSum(sum);

        // 总页数
        double decimal = sum;
        Double num = Math.ceil(decimal / pageSize);
        page.setTotalPage(num.intValue());

        // 设置开始结束页数
        map.put("start", (currentPage - 1) * pageSize);
        map.put("size", page.getPageSize());

        // 每页显示的数据
        List<Blog> blogList = blogDao.getAllBlog(map);
        page.setBlogList(blogList);

        System.out.println(page);

        return page;
    }

    @Override
    public Blog getBlogById(int id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public void updateClickHitById(int id) {
        blogDao.updateClickHitById(id);
    }

    @Override
    public List<Blog> getSix() {
        return blogDao.getSix();
    }

    @Override
    public int selectCountByBlogType(int blogTypeId) {
        return blogDao.selectCountByBlogType(blogTypeId);
    }

    @Override
    public Page<Blog> getPageByBlogType(int currentPage, int blogTypeId) {
        HashMap<String, Object> map = new HashMap<>();
        Page<Blog> page = new Page<>();

        // 当前页数
        System.out.println(currentPage + "当前页数");
        page.setCurrentPage(currentPage);

        // 每页显示条数
        int pageSize = 5;
        page.setPageSize(pageSize);

        // 记录总数
        int sum = blogDao.selectCountByBlogType(blogTypeId);
        page.setSum(sum);

        // 总页数
        double decimal = sum;
        Double num = Math.ceil(decimal / pageSize);
        page.setTotalPage(num.intValue());

        // 设置开始结束页数
        map.put("start", (currentPage - 1) * pageSize);
        map.put("size", page.getPageSize());
        map.put("blogTypeId", blogTypeId);

        // 每页显示的数据
        List<Blog> blogList = blogDao.getPageByBlogType(map);
        System.out.println(blogList);
        page.setBlogList(blogList);

        System.out.println(page);
        return page;
    }

    @Override
    public void insertBlog(Blog blog) {
        blogDao.insertBlog(blog);
    }

    @Override
    public void deleteBlogById(int id) {
        // 删除博客评论
        List<Comment> comments = commentService.getCommentByBlogId(id);
        for (Comment comment : comments) {
            commentService.deleteCommentById(comment.getId());
        }
        // 删除博客
        blogDao.deleteBlogById(id);
    }

    @Override
    public void updateBlogById(Blog blog) {
        blogDao.updateBlogById(blog);
    }


    @Override
    public List<Integer> searchBlog(String str) {
        return blogDao.searchBlog(str);
    }


}
