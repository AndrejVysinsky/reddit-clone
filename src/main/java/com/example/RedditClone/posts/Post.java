package com.example.RedditClone.posts;

import com.example.RedditClone.comments.Comment;
import com.example.RedditClone.users.User;

import java.util.List;

public class Post
{
    private String header;
    private String body;
    private User author;
    private List<Comment> comments;
}
