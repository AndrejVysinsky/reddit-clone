package com.example.RedditClone.commentVotes;

import com.example.RedditClone.comments.Comment;
import com.example.RedditClone.comments.CommentController;
import com.example.RedditClone.helpers.Redirects;
import com.example.RedditClone.postVotes.PostVote;
import com.example.RedditClone.postVotes.PostVoteController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CommentVoteServlet", value = "/comment-vote")
public class CommentVoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CommentVoteController commentVoteController = new CommentVoteController();
        CommentVote commentVote = commentVoteController.MapParamsToPost(request.getParameterMap());

        if (commentVote.getCommentId() != 0 && commentVote.getUserId() != 0)
        {
            CommentVote commentVoteDb = commentVoteController.GetCommentVote(commentVote.getCommentId(), commentVote.getUserId());

            int result = 0;

            if (commentVoteDb == null)
            {
                result = commentVoteController.CreatePostVote(commentVote);
            }
            else
            {
                if (commentVoteDb.isUpvote() == commentVote.isUpvote())
                {
                    //same arrow clicked, remove postVote
                    result = commentVoteController.DeleteCommentVote(commentVote);
                }
                else
                {
                    //different arrow clicked, update postVote
                    result = commentVoteController.UpdateCommentVote(commentVote);
                }
            }

            if (result > 0)
            {
                CommentController commentController = new CommentController();
                Comment comment = commentController.GetCommentById(commentVote.getCommentId());

                response.sendRedirect(Redirects.PostRedirects.postDetails + "?postId=" + comment.getPostId());
                return;
            }
        }
        else
        {
            //wrong parameters
            response.sendRedirect(Redirects.PostRedirects.postIndex + "?message=fail");
        }
    }
}
