package com.example.RedditClone.posts;

import com.example.RedditClone.users.User;

import java.util.Date;

public class Post
{
    private Integer postId;
    private String header;
    private String body;
    private User author;
    private int points;
    private long createTime;

    public Post()
    {

    }

    public Post(String header, String body, User author, int points) {
        this.header = header;
        this.body = body;
        this.author = author;
        this.points = points;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getTimePassedSinceCreation()
    {
        Date date = new Date(createTime);

        return date.toString();
    }
}
