package com.example.RedditClone.posts;

import com.example.RedditClone.helpers.GsonMapping;
import com.example.RedditClone.helpers.ParameterMapping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "PostsCreateServlet", value = "/posts-create")
public class PostsCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PostsController postsController = new PostsController();
        Post post = postsController.MapParamsToPost(request.getParameterMap());

        if (post == null)
        {
            response.sendRedirect("/views/posts/index.jsp");
            return;
        }

        response.getWriter().write(post.toString());
    }
}
