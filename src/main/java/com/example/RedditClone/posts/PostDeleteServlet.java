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

        if (sessionUser == null)
        {
            response.sendRedirect(Redirects.PostRedirects.postIndex);
            return;
        }

        ModelMessage modelMessage = new ModelMessage();

        if (post.getPostId() != null)
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
                else
                {
                    modelMessage.setMessage("Error occurred. Post was not deleted.", true);
                }
            }
            else
            {
                //not yours to remove
                modelMessage.setMessage("You do not have permission to remove this post.", true);
            }
        }
        else
        {
            //not specified postId
            response.sendRedirect(Redirects.PostRedirects.postIndex);
            return;
        }

        request.setAttribute(Parameters.ModelParams.modelMessage, modelMessage);
        request.getRequestDispatcher(Redirects.PostRedirects.postDetails + "?postId=" + post.getPostId()).forward(request, response);
    }
}
