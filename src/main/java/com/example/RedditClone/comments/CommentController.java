package com.example.RedditClone.comments;

import com.example.RedditClone.commentVotes.CommentVote;
import com.example.RedditClone.commentVotes.CommentVoteController;
import com.example.RedditClone.helpers.DatabaseConnectionManager;
import com.example.RedditClone.helpers.ParameterMapping;
import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.postVotes.PostVote;
import com.example.RedditClone.postVotes.PostVoteController;
import com.example.RedditClone.posts.Post;
import com.example.RedditClone.users.User;
import com.example.RedditClone.users.UserController;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentController
{
    public List<Comment> GetCommentsForPost(Integer postId)
    {
        String sql = "SELECT * FROM comments WHERE postId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postId);

            ResultSet rs = ps.executeQuery();

            List<Comment> comments = new ArrayList<>();
            CommentVoteController commentVoteController = new CommentVoteController();
            while (rs.next())
            {
                Comment comment = new Comment();

                UserController userController = new UserController();
                User user = userController.GetUserById(rs.getInt(Parameters.UserParams.userId));

                comment.setAuthor(user);

                comment.setCommentId(rs.getInt(Parameters.CommentParams.commentId));
                comment.setPostId(rs.getInt(Parameters.PostParams.postId));
                comment.setCommentText(rs.getString(Parameters.CommentParams.commentText));
                comment.setPoints(rs.getInt(Parameters.CommentParams.commentPoints));
                comment.setCreateTime(rs.getLong(Parameters.CommentParams.createTime));

                List<CommentVote> commentVotes = commentVoteController.GetAllCommentVotesForComment(comment.getCommentId());

                int points = 0;
                for (CommentVote commentVote : commentVotes)
                {
                    if (commentVote.isUpvote())
                        points++;
                    else
                        points--;
                }

                comment.setPoints(points);
                comment.setCommentVotes((ArrayList<CommentVote>) commentVotes);

                comments.add(comment);
            }
            return comments;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Comment GetCommentById(Integer commentId)
    {
        String sql = "SELECT * FROM comments WHERE commentId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, commentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                Comment comment = new Comment();

                UserController userController = new UserController();
                User user = userController.GetUserById(rs.getInt(Parameters.UserParams.userId));

                comment.setAuthor(user);

                comment.setCommentId(commentId);
                comment.setPostId(rs.getInt(Parameters.PostParams.postId));
                comment.setPoints(rs.getInt(Parameters.CommentParams.commentPoints));
                comment.setCreateTime(rs.getLong(Parameters.CommentParams.createTime));

                CommentVoteController commentVoteController = new CommentVoteController();
                List<CommentVote> commentVotes = commentVoteController.GetAllCommentVotesForComment(commentId);

                int points = 0;
                for (CommentVote commentVote : commentVotes)
                {
                    if (commentVote.isUpvote())
                        points++;
                    else
                        points--;
                }

                comment.setPoints(points);
                comment.setCommentVotes((ArrayList<CommentVote>) commentVotes);

                return comment;
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int CreateComment(Comment comment)
    {
        String sql = "INSERT INTO comments(postId, userId, commentText, createTime) values(?, ?, ?, ?)";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, comment.getPostId());
            ps.setInt(2, comment.getAuthor().getUserId());
            ps.setString(3, comment.getCommentText());
            ps.setLong(4, comment.getCreateTime());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Comment MapParamsToComment(Map<String, String[]> params)
    {
        HashMap<String, String> trimmedParams = ParameterMapping.trimParamMap(params);

        Comment comment = new Comment();

        comment.setPostId(Integer.parseInt(trimmedParams.get(Parameters.PostParams.postId)));
        comment.setCommentText(trimmedParams.get(Parameters.CommentParams.commentText));

        return comment;
    }
}
