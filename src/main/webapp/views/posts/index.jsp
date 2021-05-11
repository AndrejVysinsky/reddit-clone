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

            <div class="row justify-content-start" style="margin-bottom: 20px">
                <div class="col-auto bg-dark rounded" style="padding: 15px">
                    <a class="btn myColor2 text-white fa fa-star" href="index.jsp?sort=new" style="margin-left: 15px;"> New</a>
                    <a class="btn myColor2 text-white fa fa-trophy" href="index.jsp?sort=top" style="margin-left: 20px; margin-right: 20px"> Top</a>
                    <%
                        if (sessionUser != null)
                        {
                    %>
                        <a class="btn myColor2 text-white fa fa-plus" href="create.jsp" style="margin-right: 15px"> Add post</a>
                    <%
                        }
                    %>
                </div>
            </div>

            <%
                PostController postController = new PostController();

                ArrayList<Post> posts = new ArrayList<>();
                if (request.getParameter("user") != null)
                {
                    posts = (ArrayList<Post>) postController.GetUserPosts(request.getParameter("user"));
                }
                else
                {
                    posts = (ArrayList<Post>) postController.GetAllPosts();

                    if (request.getParameter("sort") != null)
                    {
                        String sortBy = request.getParameter("sort");

                        if (sortBy.equals("new"))
                        {
                            posts = (ArrayList<Post>) postController.SortPostsByDate(posts);
                        }
                        else if (sortBy.equals("top"))
                        {
                            posts = (ArrayList<Post>) postController.SortPostsByVotes(posts);
                        }
                    }
                }

                for (Post post : posts) {
                    String postDetailsUrl = "details.jsp?postId=" + post.getPostId();
            %>
                <div class="row justify-content-start">
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
                                <a class="font-weight-thin" style="color:dimgrey" href="index.jsp?user=<%=post.getAuthor().getUserName()%>"><%=post.getAuthor().getUserName()%></a>, <%=post.getTimePassedSinceCreation()%>
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