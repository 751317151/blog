package com.blog.service.impl;

import org.springframework.stereotype.Service;
import com.blog.dao.BlogDao;
import com.blog.dao.ReplyDao;
import com.blog.pojo.Reply;
import com.blog.service.ReplyService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyDao replyDao;


    @Override
    public List<Reply> getReplyListByCommentId(Integer comment_id) {
        return replyDao.getReplyListByCommentId(comment_id);
    }

    @Override
    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }

    @Override
    public List<Reply> getAllReply() {
        return replyDao.getAllReply();
    }

    @Override
    public void deleteReplyById(Integer id) {
        replyDao.deleteReplyById(id);
    }

    // 获取博客 id
    @Override
    public Integer getBlogIdByReplyId(Integer id) {
        Integer blogId = replyDao.getBlogIdByReplyId(id);
        return blogId;
    }

    @Override
    public String getBlogTitleByReplyId(Integer id) {
        return replyDao.getBlogTitleByReplyId(id);
    }

}
