package com.blog.service;

import com.blog.pojo.Reply;

import java.util.List;

public interface ReplyService {

    // 查询某一个评论下所有作者回复的合集
    List<Reply> getReplyListByCommentId(Integer comment_id);

    // 增加回复
    void addReply(Reply reply);

    // 获取所有回复
    List<Reply> getAllReply();

    // 根据回复 id 删除回复
    void deleteReplyById(Integer id);



    // 获取博客 id
    Integer getBlogIdByReplyId(Integer id);

    String getBlogTitleByReplyId(Integer id);
}
