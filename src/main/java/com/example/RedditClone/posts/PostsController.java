package com.example.RedditClone.posts;

import com.example.RedditClone.helpers.DatabaseConnectionManager;
import com.example.RedditClone.helpers.ParameterMapping;
import com.example.RedditClone.helpers.Parameters;
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

public class PostsController
{
    public List<Post> GetAllPosts()
    {
        String sql = "SELECT * FROM posts";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Post> posts = new ArrayList<>();
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
        String sql = "INSERT INTO posts(userId, header, body) values(?, ?, ?)";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, post.getAuthor().getUserId());
            ps.setString(2, post.getHeader());
            ps.setString(3, post.getBody());

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

    public Post MapParamsToPost(Map<String, String[]> params)
    {
        HashMap<String, String> trimmedParams = ParameterMapping.trimParamMap(params);

        Post post = new Post();

        post.setHeader(trimmedParams.get(Parameters.PostParams.postHeader));
        post.setBody(trimmedParams.get(Parameters.PostParams.postBody));

        return post;
    }
}
