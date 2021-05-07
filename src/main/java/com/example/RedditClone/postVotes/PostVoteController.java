package com.example.RedditClone.postVotes;

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

public class PostVoteController
{
    public List<PostVote> GetAllPostVotesForPost(Integer postId)
    {
        String sql = "SELECT * FROM postvotes WHERE postId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postId);

            ResultSet rs = ps.executeQuery();

            List<PostVote> postVotes = new ArrayList<>();
            while (rs.next())
            {
                PostVote postVote = new PostVote();

                postVote.setPostId(postId);
                postVote.setUserId(rs.getInt(Parameters.UserParams.userId));
                postVote.setUpvote(rs.getBoolean(Parameters.PostVoteParams.isUpvote));

                postVotes.add(postVote);
            }
            return postVotes;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public PostVote GetPostVote(Integer postId, Integer userId)
    {
        String sql = "SELECT * FROM postvotes WHERE postId = ? AND userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                PostVote postVote = new PostVote();

                postVote.setPostId(rs.getInt(Parameters.PostParams.postId));
                postVote.setUserId(rs.getInt(Parameters.UserParams.userId));
                postVote.setUpvote(rs.getBoolean(Parameters.PostVoteParams.isUpvote));

                return postVote;
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int CreatePostVote(PostVote postVote)
    {
        String sql = "INSERT INTO postVotes(postId, userId, isUpvote) values(?, ?, ?)";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postVote.getPostId());
            ps.setInt(2, postVote.getUserId());
            ps.setBoolean(3, postVote.isUpvote());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int UpdatePostVote(PostVote postVote)
    {
        String sql = "UPDATE postVotes SET isUpvote = ? WHERE postId = ? AND userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setBoolean(1, postVote.isUpvote());
            ps.setInt(2, postVote.getPostId());
            ps.setInt(3, postVote.getUserId());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int DeletePostVote(PostVote postVote)
    {
        String sql = "DELETE FROM postVotes WHERE postId = ? AND userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postVote.getPostId());
            ps.setInt(2, postVote.getUserId());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public PostVote MapParamsToPost(Map<String, String[]> params)
    {
        HashMap<String, String> trimmedParams = ParameterMapping.trimParamMap(params);

        PostVote postVote = new PostVote();

        String postId = trimmedParams.get(Parameters.PostParams.postId);

        if (postId != null)
        {
            postVote.setPostId(Integer.parseInt(postId));
        }

        String userId = trimmedParams.get(Parameters.UserParams.userId);

        if (userId != null)
        {
            postVote.setUserId(Integer.parseInt(userId));
        }

        String isUpvote = trimmedParams.get(Parameters.PostVoteParams.isUpvote);

        if (isUpvote != null)
        {
            postVote.setUpvote(Boolean.parseBoolean(isUpvote));
        }

        return postVote;
    }
}
