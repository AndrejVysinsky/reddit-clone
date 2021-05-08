<%@ page import="com.example.RedditClone.posts.PostController" %>
<%@ page import="com.example.RedditClone.users.User" %>
<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page import="com.example.RedditClone.postVotes.PostVote" %>
<%@ page import="com.example.RedditClone.posts.Post" %>
<%@ page import="com.example.RedditClone.posts.PostController" %>
<%@ page import="com.example.RedditClone.comments.Comment" %>
<%@ page import="com.example.RedditClone.commentVotes.CommentVote" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Post - create</title>
    </head>
    <body class="bg-dark">
        <%@include file="../shared/navbar.jsp"%>
        <div class="container myContainer bg-dark">
            <div class="bg-white rounded">
                <div class="row justify-content-center">
                    <div class="col-auto text-center postVote">
                        <%
                            String postIdParam = request.getParameter(Parameters.PostParams.postId);
                            Integer postId = null;
                            if (postIdParam != null)
                            {
                                postId = Integer.parseInt(postIdParam);
                            }

                            PostController postController = new PostController();
                            Post post = postController.GetPostById(postId);

                            if (post == null)
                            {
                                response.sendRedirect("index.jsp");
                            }

                            if (sessionUser != null)
                            {
                                PostVote postVote = post.getUserPostVote(sessionUser.getUserId());
                        %>
                        <form action="/post-vote">
                            <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=post.getPostId()%>">
                            <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                            <input type="hidden" name="<%=Parameters.PostVoteParams.isUpvote%>" value="<%=true%>">
                            <button class="btn align-middle fa fa-arrow-up
                                                    <%=postVote == null ? "text-dark" : postVote.isUpvote() ? "text-warning" : "text-dark"%>" formmethod="post">
                            </button><br>
                        </form>

                        <span class="align-middle text-dark"><%=post.getPoints()%></span><br>

                        <form action="/post-vote">
                            <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=post.getPostId()%>">
                            <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                            <input type="hidden" name="<%=Parameters.PostVoteParams.isUpvote%>" value="<%=false%>">
                            <button class="btn align-middle fa fa-arrow-down
                                                    <%=postVote == null ? "text-dark" : !postVote.isUpvote() ? "text-warning" : "text-dark"%>" formmethod="post">
                            </button><br>
                        </form>
                        <%
                        }
                        else
                        {
                        %>
                        <a class="btn align-middle fa fa-arrow-up text-dark"></a><br>
                        <span class="align-middle text-dark"><%=post.getPoints()%></span><br>
                        <a class="btn align-middle fa fa-arrow-down text-dark"></a><br>
                        <%
                            }
                        %>
                    </div>
                    <div class="col-11 bg-white">
                        <div style="padding: 10px 5px 5px 5px">
                            <div class="row">
                                            <span class="font-weight-thin" style="color:dimgrey">
                                                Posted by
                                            <a class="font-weight-thin" style="color:dimgrey" href="index.jsp"><%=post.getAuthor().getUserName()%></a>, <%=post.getTimePassedSinceCreation()%>
                                            </span>
                            </div>
                            <div class="row">
                                <h4 class="text-left"><%=post.getHeader()%>
                                </h4>
                            </div>
                            <div class="row text-left">
                                <%=post.getBody().replaceAll("(\r\n|\n)", "<br />")%>
                            </div>
                            <br>
                            <div class="row font-weight-thin" style="color:dimgrey">
                                <span class="fa fa-comments align-middle" style="padding: 5px"> <%=post.getCommentCount()%> <%=post.getCommentCount() == 1 ? "Comment" : "Comments"%></span>
                                <%
                                    if (sessionUser != null && sessionUser.getUserId().equals(post.getAuthor().getUserId()))
                                    {
                                %>
                                    <a href="edit.jsp?postId=<%=post.getPostId()%>" class="btn fa fa-pencil" style="color: dimgrey; margin-left: 5px; padding: 5px;"></a>
                                    <form action="/post-delete">
                                        <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=post.getPostId()%>">
                                        <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                                        <button class="btn fa fa-times text-danger" onclick="return confirm('Are you sure that you want to delete your post?')" formmethod="post" style="padding: 0px; margin-left: 5px"></button>
                                    </form>
                                <%
                                    }
                                %>

                            </div>
                        </div>

                        <div class="row justify-content-center" style="margin-top:10px; margin-bottom:10px">
                            <%
                                if (sessionUser == null)
                                {
                            %>
                            <div class="border rounded col-12 text-center" style="padding: 10px">
                                <span>Log in or register to leave a comment</span>
                                <a class="btn btn-primary btn-sm" href="../users/login.jsp">Login</a>
                                <a class="btn btn-primary btn-sm" href="../users/register.jsp">Register</a>
                            </div>
                            <%
                            }
                            else
                            {
                            %>
                            <form action="${pageContext.request.contextPath}/comment-create" class="col-12" style="padding: 0px">
                                <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=post.getPostId()%>">
                                <textarea name="<%=Parameters.CommentParams.commentText%>" class="form-control" cols="30" rows="5" style="margin-bottom: 5px"></textarea>
                                <button class="btn btn-primary btn-sm" formmethod="post">Add comment</button>
                            </form>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="container" style="padding-left: 40px">
                    <%
                        if (post.getComments() != null)
                        {
                            for (Comment comment : post.getComments())
                            {
                        %>
                            <div class="row">
                                <div class="col-auto">
                                    <img src="https://www.redditstatic.com/avatars/avatar_default_12_0079D3.png" alt="" width="20px">
                                </div>
                                <div class="col-11">
                                    <div class="row">
                                        <a class="font-weight-thin" style="color:dimgrey" href="index.jsp"><%=comment.getAuthor().getUserName()%></a>, <%=comment.getTimePassedSinceCreation()%>
                                        <%
                                            if (sessionUser != null && sessionUser.getUserId() == comment.getAuthor().getUserId())
                                            {
                                        %>
                                            <form action="/comment-delete">
                                                <input type="hidden" name="<%=Parameters.CommentParams.commentId%>" value="<%=comment.getCommentId()%>">
                                                <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=comment.getPostId()%>">
                                                <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                                                <button class="btn fa fa-times text-danger" onclick="return confirm('Are you sure that you want to delete your comment?')" formmethod="post" style="padding: 0px; margin-left: 5px"></button>
                                            </form>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <div class="row">
                                        <span><%=comment.getCommentText().replaceAll("(\r\n|\n)", "<br />")%></span>
                                    </div>
                                    <div class="row">
                                        <%
                                            if (sessionUser != null)
                                            {
                                                CommentVote commentVote = comment.GetUserCommentVote(sessionUser.getUserId());
                                        %>
                                        <form action="/comment-vote">
                                            <input type="hidden" name="<%=Parameters.CommentParams.commentId%>" value="<%=comment.getCommentId()%>">
                                            <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                                            <input type="hidden" name="<%=Parameters.PostVoteParams.isUpvote%>" value="<%=true%>">
                                            <button class="btn align-middle fa fa-arrow-up
                                                    <%=commentVote == null ? "text-dark" : commentVote.isUpvote() ? "text-warning" : "text-dark"%>" formmethod="post"
                                                    style="padding: 0">
                                            </button><br>
                                        </form>

                                        <span class="align-middle text-dark" style="padding: 0px 10px 0px 10px"><%=comment.getPoints()%></span><br>

                                        <form action="/comment-vote">
                                            <input type="hidden" name="<%=Parameters.CommentParams.commentId%>" value="<%=comment.getCommentId()%>">
                                            <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                                            <input type="hidden" name="<%=Parameters.PostVoteParams.isUpvote%>" value="<%=false%>">
                                            <button class="btn align-middle fa fa-arrow-down
                                                    <%=commentVote == null ? "text-dark" : !commentVote.isUpvote() ? "text-warning" : "text-dark"%>" formmethod="post"
                                                    style="padding: 0">
                                            </button><br>
                                        </form>
                                        <%
                                            }
                                        else
                                            {
                                        %>
                                        <button class="btn align-middle fa fa-arrow-up text-dark" style="padding: 0"></button><br>
                                        <span class="align-middle text-dark" style="padding: 0px 10px 0px 10px"><%=comment.getPoints()%></span><br>
                                        <button class="btn align-middle fa fa-arrow-down text-dark" style="padding: 0"></button><br>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                            <br>
                        <%
                            }
                        }
                    %>
                </div>
                <br>
            </div>
        </div>
    </body>
</html>