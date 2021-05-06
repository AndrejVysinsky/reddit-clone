package com.example.RedditClone.posts;

import com.example.RedditClone.helpers.Redirects;
import com.example.RedditClone.users.User;
import com.example.RedditClone.users.UserController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "PostsCreateServlet", value = "/posts-create")
public class PostsCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserController userController = new UserController();
        User sessionUser = userController.GetLoggedInUser(request);

        PostsController postsController = new PostsController();
        Post post = postsController.MapParamsToPost(request.getParameterMap());

        if (post.getHeader() != null && post.getBody() != null && sessionUser != null)
        {
            post.setAuthor(sessionUser);
            post.setCreateTime(System.currentTimeMillis());
            post = postsController.CreatePost(post);

            response.sendRedirect(Redirects.PostRedirects.postDetails + "?postId=" + post.getPostId());
            return;
        }

        response.sendRedirect(Redirects.PostRedirects.postCreate + "?message=fail");
    }
}
