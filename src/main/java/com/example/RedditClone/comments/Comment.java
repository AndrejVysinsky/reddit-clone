package com.example.RedditClone.comments;

import com.example.RedditClone.commentVotes.CommentVote;
import com.example.RedditClone.postVotes.PostVote;
import com.example.RedditClone.users.User;

import java.util.ArrayList;
import java.util.Date;

public class Comment
{
    private Integer commentId;
    private Integer postId;
    private String commentText;
    private User author;
    private int points;
    private long createTime;
    private ArrayList<CommentVote> commentVotes;

    public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
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

    public ArrayList<CommentVote> getCommentVotes() {
        return commentVotes;
    }

    public void setCommentVotes(ArrayList<CommentVote> commentVotes) {
        this.commentVotes = commentVotes;
    }

    public CommentVote GetUserCommentVote(Integer userId)
    {
        for (CommentVote commentVote : commentVotes)
        {
            if (commentVote.getUserId() == userId)
                return commentVote;
        }
        return null;
    }
}
