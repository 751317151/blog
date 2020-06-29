package com.blog.dao;

import com.blog.pojo.BlogType;

import java.util.List;

public interface BlogTypeDao {

    // 根据博客类别 id 查询博客类别名称
    String getTypeNameById(int id);

    // 获取博客类别合集
    List<BlogType> getAllTypes();

    // 添加类型
    void addBlogType(BlogType blogType);

    // 获取该类型下所有博客 id
    List<Integer> getBlogIdListByTypeId(int id);

    // 删除类型
    void deleteBlogTypeById(int id);

    // 根据类型名查找 id
    Integer getIdByTypeName(String typeName);

    // 更新类型
    void updateBlogType(BlogType blogType);

    BlogType getBlogTypeById(Integer blogId);
}
