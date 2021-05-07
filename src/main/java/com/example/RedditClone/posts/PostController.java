package com.example.RedditClone.posts;

import com.example.RedditClone.comments.Comment;
import com.example.RedditClone.comments.CommentController;
import com.example.RedditClone.helpers.DatabaseConnectionManager;
import com.example.RedditClone.helpers.ParameterMapping;
import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.postVotes.PostVote;
import com.example.RedditClone.postVotes.PostVoteController;
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

public class PostController
{
    public List<Post> GetAllPosts()
    {
        String sql = "SELECT * FROM posts";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Post> posts = new ArrayList<>();
            PostVoteController postVoteController = new PostVoteController();
            while (rs.next())
            {
                Post post = new Post();

                UserController userController = new UserController();
                User user = userController.GetUserById(rs.getInt(Parameters.UserParams.userId));

                post.setAuthor(user);

                post.setPostId(rs.getInt(Parameters.PostParams.postId));
                post.setHeader(rs.getString(Parameters.PostParams.postHeader));
                post.setBody(rs.getString(Parameters.PostParams.postBody));
                post.setPoints(rs.getInt(Parameters.PostParams.postPoints));
                post.setCreateTime(rs.getLong(Parameters.PostParams.createTime));

                List<PostVote> postVotes = postVoteController.GetAllPostVotesForPost(post.getPostId());

                int points = 0;
                for (PostVote postVote : postVotes)
                {
                    if (postVote.isUpvote())
                        points++;
                    else
                        points--;
                }

                post.setPoints(points);
                post.setPostVotes((ArrayList<PostVote>) postVotes);

                CommentController commentController = new CommentController();
                List<Comment> comments = commentController.GetCommentsForPost(post.getPostId());
                post.setComments((ArrayList<Comment>) comments);

                posts.add(post);
            }
            return posts;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Post CreatePost(Post post)
    {
        String sql = "INSERT INTO posts(userId, postHeader, postBody, createTime) values(?, ?, ?, ?)";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, post.getAuthor().getUserId());
            ps.setString(2, post.getHeader());
            ps.setString(3, post.getBody());
            ps.setLong(4, post.getCreateTime());

            int success = ps.executeUpdate();

            if (success > 0)
            {
                sql = "SELECT LAST_INSERT_ID()";
                ps = con.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                if (rs.next())
                {
                    post.setPostId(rs.getInt(1));
                    return post;
                }
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int UpdatePost(Post post)
    {
        String sql = "UPDATE posts SET postHeader = ?, postBody = ? WHERE postId = ? AND userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, post.getHeader());
            ps.setString(2, post.getBody());
            ps.setInt(3, post.getPostId());
            ps.setInt(4, post.getAuthor().getUserId());

            return ps.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Post GetPostById(Integer postId)
    {
        String sql = "SELECT * FROM posts WHERE postId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, postId);

            ResultSet rs = ps.executeQuery();

            PostVoteController postVoteController = new PostVoteController();
            if (rs.next())
            {
                Post post = new Post();

                UserController userController = new UserController();
                User user = userController.GetUserById(rs.getInt(Parameters.UserParams.userId));

                post.setAuthor(user);

                post.setPostId(rs.getInt(Parameters.PostParams.postId));
                post.setHeader(rs.getString(Parameters.PostParams.postHeader));
                post.setBody(rs.getString(Parameters.PostParams.postBody));
                post.setPoints(rs.getInt(Parameters.PostParams.postPoints));
                post.setCreateTime(rs.getLong(Parameters.PostParams.createTime));

                List<PostVote> postVotes = postVoteController.GetAllPostVotesForPost(post.getPostId());

                int points = 0;
                for (PostVote postVote : postVotes)
                {
                    if (postVote.isUpvote())
                        points++;
                    else
                        points--;
                }

                post.setPoints(points);
                post.setPostVotes((ArrayList<PostVote>) postVotes);

                CommentController commentController = new CommentController();
                List<Comment> comments = commentController.GetCommentsForPost(postId);
                post.setComments((ArrayList<Comment>) comments);

                return post;
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Post MapParamsToPost(Map<String, String[]> params)
    {
        HashMap<String, String> trimmedParams = ParameterMapping.trimParamMap(params);

        Post post = new Post();

        if (trimmedParams.containsKey(Parameters.PostParams.postId))
        {
            post.setPostId(Integer.parseInt(trimmedParams.get(Parameters.PostParams.postId)));
        }

        post.setHeader(trimmedParams.get(Parameters.PostParams.postHeader));
        post.setBody(trimmedParams.get(Parameters.PostParams.postBody));

        return post;
    }
}
