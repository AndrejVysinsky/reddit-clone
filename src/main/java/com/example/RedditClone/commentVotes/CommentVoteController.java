package com.example.RedditClone.commentVotes;

import com.example.RedditClone.helpers.DatabaseConnectionManager;
import com.example.RedditClone.helpers.ParameterMapping;
import com.example.RedditClone.helpers.Parameters;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentVoteController
{
    public List<CommentVote> GetAllCommentVotesForComment(Integer commentId)
    {
        String sql = "SELECT * FROM commentVotes WHERE commentId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, commentId);

            ResultSet rs = ps.executeQuery();

            List<CommentVote> commentVotes = new ArrayList<>();
            while (rs.next())
            {
                CommentVote commentVote = new CommentVote();

                commentVote.setCommentId(commentId);
                commentVote.setUserId(rs.getInt(Parameters.UserParams.userId));
                commentVote.setUpvote(rs.getBoolean(Parameters.CommentVoteParams.isUpvote));

                commentVotes.add(commentVote);
            }
            return commentVotes;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CommentVote GetCommentVote(Integer commentId, Integer userId)
    {
        String sql = "SELECT * FROM commentVotes WHERE commentId = ? AND userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, commentId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                CommentVote commentVote = new CommentVote();

                commentVote.setCommentId(commentId);
                commentVote.setUserId(userId);
                commentVote.setUpvote(rs.getBoolean(Parameters.PostVoteParams.isUpvote));

                return commentVote;
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int CreatePostVote(CommentVote commentVote)
    {
        String sql = "INSERT INTO commentVotes(commentId, userId, isUpvote) values(?, ?, ?)";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, commentVote.getCommentId());
            ps.setInt(2, commentVote.getUserId());
            ps.setBoolean(3, commentVote.isUpvote());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int UpdateCommentVote(CommentVote commentVote)
    {
        String sql = "UPDATE commentVotes SET isUpvote = ? WHERE commentId = ? AND userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setBoolean(1, commentVote.isUpvote());
            ps.setInt(2, commentVote.getCommentId());
            ps.setInt(3, commentVote.getUserId());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int DeleteCommentVote(CommentVote commentVote)
    {
        String sql = "DELETE FROM commentVotes WHERE commentId = ? AND userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, commentVote.getCommentId());
            ps.setInt(2, commentVote.getUserId());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int DeleteCommentVotesForComment(Integer commentId)
    {
        String sql = "DELETE FROM commentVotes WHERE commentId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, commentId);

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public CommentVote MapParamsToPost(Map<String, String[]> params)
    {
        HashMap<String, String> trimmedParams = ParameterMapping.trimParamMap(params);

        CommentVote commentVote = new CommentVote();

        String commentId = trimmedParams.get(Parameters.CommentParams.commentId);

        if (commentId != null)
        {
            commentVote.setCommentId(Integer.parseInt(commentId));
        }

        String userId = trimmedParams.get(Parameters.UserParams.userId);

        if (userId != null)
        {
            commentVote.setUserId(Integer.parseInt(userId));
        }

        String isUpvote = trimmedParams.get(Parameters.PostVoteParams.isUpvote);

        if (isUpvote != null)
        {
            commentVote.setUpvote(Boolean.parseBoolean(isUpvote));
        }

        return commentVote;
    }
}
