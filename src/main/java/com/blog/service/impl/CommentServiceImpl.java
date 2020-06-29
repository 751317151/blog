package com.blog.service.impl;

import com.blog.dao.ReplyDao;
import org.springframework.stereotype.Service;
import com.blog.dao.CommentDao;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;
    @Resource
    private ReplyDao replyDao;

    @Override
    public List<Comment> getCommentByBlogId(int blog_id) {
        return commentDao.getCommentByBlogId(blog_id);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.saveComment(comment);
        commentDao.updateCommentHit(comment.getBlog_id());
    }

    @Override
    public List<Comment> getAllComment() {
        return commentDao.getAllComment();
    }

    @Override
    public String getTitleByBlogId(int id) {
        return commentDao.getTitleByBlogId(id);
    }

    @Override
    public void deleteCommentById(Integer id) {
        // 获取博客id
        Integer blog_id = commentDao.getBlogIdByCommentId(id);
        // 减少评论数量
        commentDao.minusCommentHit(blog_id);
        // 删除评论的回复
        replyDao.deleteReplyByCommentId(id);
        // 删除评论
        commentDao.deleteCommentById(id);
    }

}
