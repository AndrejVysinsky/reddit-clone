package com.example.RedditClone.posts;

import com.example.RedditClone.comments.Comment;
import com.example.RedditClone.users.User;

import java.util.List;

public class Post
{
    private String header;
    private String body;
    private User author;
    private int points;

    public Post(String header, String body, User author, int points) {
        this.header = header;
        this.body = body;
        this.author = author;
        this.points = points;
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
}
