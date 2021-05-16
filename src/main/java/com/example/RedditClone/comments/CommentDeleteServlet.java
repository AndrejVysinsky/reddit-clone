package com.example.RedditClone.comments;

import com.example.RedditClone.helpers.Redirects;
import com.example.RedditClone.posts.Post;
import com.example.RedditClone.posts.PostController;
import com.example.RedditClone.users.User;
import com.example.RedditClone.users.UserController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CommentDeleteServlet", value = "/comment-delete")
public class CommentDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserController userController = new UserController();
        User sessionUser = userController.GetLoggedInUser(request);

        CommentController commentController = new CommentController();
        Comment comment = commentController.MapParamsToComment(request.getParameterMap());

        if (comment.getPostId() != null && sessionUser != null)
        {
            Comment commentDb = commentController.GetCommentById(comment.getCommentId());

            if (sessionUser.getUserId().equals(commentDb.getAuthor().getUserId()))
            {
                int result = commentController.DeleteComment(commentDb);

                if (result > 0)
                {
                    response.sendRedirect(Redirects.PostRedirects.postDetails + "?postId=" + commentDb.getPostId());
                    return;
                }
            }
        }

        response.sendRedirect(Redirects.PostRedirects.postIndex + "?message=fail");
    }
}
