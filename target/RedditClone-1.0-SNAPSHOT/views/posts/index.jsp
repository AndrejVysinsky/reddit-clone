<%@ page import="com.example.RedditClone.posts.PostController" %>
<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page import="com.example.RedditClone.posts.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.RedditClone.postVotes.PostVote" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>
        <div class="container myContainer">

            <%
                PostController postController = new PostController();
                ArrayList<Post> posts = (ArrayList<Post>) postController.GetAllPosts();

                if (sessionUser != null)
                {
            %>
                <a class="btn btn-primary" href="create.jsp">Add post</a>
            <%
                }

                for (Post post : posts) {
                    String postDetailsUrl = "details.jsp?postId=" + post.getPostId();
            %>
                <div class="row justify-content-center">
                    <div class="col-auto myColor2 rounded-left text-center postVote">
                        <%
                            if (sessionUser != null)
                            {
                                PostVote postVote = post.getUserPostVote(sessionUser.getUserId());
                        %>
                            <a href="<%=postDetailsUrl%>" class="btn align-middle fa fa-arrow-up
                                    <%=postVote == null ? "text-white" : postVote.isUpvote() ? "text-warning" : "text-white"%>" formmethod="post">
                            </a>
                            <br>

                            <span class="align-middle text-white"><%=post.getPoints()%></span><br>

                            <a href="<%=postDetailsUrl%>" class="btn align-middle fa fa-arrow-down
                                    <%=postVote == null ? "text-white" : !postVote.isUpvote() ? "text-warning" : "text-white"%>" formmethod="post">
                            </a>
                        <%
                            }
                            else
                            {
                        %>
                            <a href="<%=postDetailsUrl%>" class="btn align-middle fa fa-arrow-up text-white"></a><br>
                            <span class="align-middle text-white"><%=post.getPoints()%></span><br>
                            <a href="<%=postDetailsUrl%>" class="btn align-middle fa fa-arrow-down text-white"></a><br>
                        <%
                            }
                        %>
                    </div>
                    <div class="col-11 bg-dark rounded-right">
                        <div style="padding: 10px 5px 5px 10px">
                            <div class="row">
                                <span class="font-weight-thin" style="color:dimgrey">
                                    Posted by
                                <a class="font-weight-thin" style="color:dimgrey" href="index.jsp"><%=post.getAuthor().getUserName()%></a>, <%=post.getTimePassedSinceCreation()%>
                                </span>
                            </div>
                            <div class="row">
                                <a class="text-white" href="<%=postDetailsUrl%>"><h4 class="text-left"><%=post.getHeader()%>
                                </h4></a>
                            </div>
                            <div class="row text-left text-white">
                                <%
                                    String postBody = post.getBody();

                                    if (postBody.length() > 30)
                                    {
                                        postBody = postBody.substring(0, 30) + "...";
                                    }
                                %>
                                <%=postBody%>
                            </div>
                            <br>
                            <div class="row font-weight-thin" style="color:dimgrey; padding-bottom: 5px">
                                <span class="fa fa-comments align-middle"> <%=post.getCommentCount()%> <%=post.getCommentCount() == 1 ? "Comment" : "Comments"%></span>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
            <%
                }

                if (posts.size() == 0)
                {
            %>
                <h6>No posts found.</h6>
            <%
                }
            %>
        </div>
    </body>
</html>