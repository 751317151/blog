package com.blog.pojo;

import com.blog.util.Date2StringUtil;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {

    private Integer id;
    private String title;
    private String summary;
    private Date createDate;
    private String createTime;
    private Date releaseDate;
    private String releaseTime;
    private Integer clickHit;
    private Integer commentHit;
    private String content;
    private String keyword;
    private int blogTypeId;

    private String typeName;
    private int typeCount;

    public Blog() {
    }

    public Blog(String title, String summary, String content, String keyword, int blogTypeId) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.keyword = keyword;
        this.blogTypeId = blogTypeId;
    }

    public Blog(Integer id, String title, String summary, String content, String keyword, int blogTypeId) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.keyword = keyword;
        this.blogTypeId = blogTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCreateTime() {
        if(createDate != null){
            createTime = Date2StringUtil.date2String(createDate);
        }
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getCommentHit() {
        return commentHit;
    }

    public void setCommentHit(Integer commentHit) {
        this.commentHit = commentHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getBlogTypeId() {
        return blogTypeId;
    }

    public void setBlogTypeId(int blogTypeId) {
        this.blogTypeId = blogTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", createDate=" + createDate +
                ", releaseDate=" + releaseDate +
                ", clickHit=" + clickHit +
                ", commentHit=" + commentHit +
                ", content='" + content + '\'' +
                ", keyword='" + keyword + '\'' +
                ", blogTypeId=" + blogTypeId +
                ", typeName='" + typeName + '\'' +
                ", typeCount=" + typeCount +
                '}';
    }

}
