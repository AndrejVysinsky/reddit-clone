package com.example.RedditClone.postVotes;

import com.example.RedditClone.helpers.Redirects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PostVoteCreateServlet", value = "/post-vote")
public class PostVoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PostVoteController postVoteController = new PostVoteController();
        PostVote postVote = postVoteController.MapParamsToPost(request.getParameterMap());

        if (postVote.getPostId() != 0 && postVote.getUserId() != 0)
        {
            PostVote userPostVoteDb = postVoteController.GetPostVote(postVote.getPostId(), postVote.getUserId());

            if (userPostVoteDb == null)
            {
                postVoteController.CreatePostVote(postVote);
            }
            else
            {
                if (userPostVoteDb.isUpvote() == postVote.isUpvote())
                {
                    //same arrow clicked, remove postVote
                    postVoteController.DeletePostVote(postVote);
                }
                else
                {
                    //different arrow clicked, update postVote
                    postVoteController.UpdatePostVote(postVote);
                }
            }
        }
        else
        {
            //wrong parameters
        }
        response.sendRedirect(Redirects.PostRedirects.postIndex);
    }
}
