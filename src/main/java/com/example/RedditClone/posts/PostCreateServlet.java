package com.example.RedditClone.posts;

import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.helpers.Redirects;
import com.example.RedditClone.modelMessages.ModelMessage;
import com.example.RedditClone.users.User;
import com.example.RedditClone.users.UserController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PostCreateServlet", value = "/post-create")
public class PostCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserController userController = new UserController();
        User sessionUser = userController.GetLoggedInUser(request);

        PostController postController = new PostController();
        Post post = postController.MapParamsToPost(request.getParameterMap());

        if (sessionUser == null)
        {
            response.sendRedirect(Redirects.PostRedirects.postIndex);
            return;
        }

        post.setAuthor(sessionUser);
        post.setCreateTime(System.currentTimeMillis());

        if (post.isValid())
        {
            post = postController.CreatePost(post);

            response.sendRedirect(Redirects.PostRedirects.postDetails + "?postId=" + post.getPostId());
        }
        else
        {
            ModelMessage modelMessage = new ModelMessage();
            modelMessage.setMessage("Model validation error.", true);

            request.setAttribute(Parameters.ModelParams.modelMessage, modelMessage);
            request.getRequestDispatcher(Redirects.PostRedirects.postCreate).forward(request, response);
        }
    }
}
