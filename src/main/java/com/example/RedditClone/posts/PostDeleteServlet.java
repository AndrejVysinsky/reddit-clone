package com.example.RedditClone.posts;

import com.example.RedditClone.helpers.Redirects;
import com.example.RedditClone.users.User;
import com.example.RedditClone.users.UserController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PostDeleteServlet", value = "/post-delete")
public class PostDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserController userController = new UserController();
        User sessionUser = userController.GetLoggedInUser(request);

        PostController postController = new PostController();
        Post post = postController.MapParamsToPost(request.getParameterMap());

        if (post.getPostId() != null && sessionUser != null)
        {
            Post postDb = postController.GetPostById(post.getPostId());

            if (sessionUser.getUserId().equals(postDb.getAuthor().getUserId()))
            {
                int result = postController.DeletePost(postDb);

                if (result > 0)
                {
                    response.sendRedirect(Redirects.PostRedirects.postIndex);
                    return;
                }
            }
        }

        response.sendRedirect(Redirects.PostRedirects.postIndex + "?message=fail");
    }
}
