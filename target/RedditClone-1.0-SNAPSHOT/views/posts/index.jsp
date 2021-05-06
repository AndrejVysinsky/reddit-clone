<%@ page import="com.example.RedditClone.posts.PostsController" %>
<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page import="com.example.RedditClone.posts.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>
        <div class="container">

            <%
                PostsController postsController = new PostsController();
                ArrayList<Post> posts = (ArrayList<Post>)postsController.GetAllPosts();

                if (sessionUser != null)
                {
            %>
                <a class="btn btn-primary" href="create.jsp">Add post</a>
            <%
                }

                for (Post post : posts) {
            %>
                <div class="row" style="margin: 4px;">
                    <div class="col-auto bg-secondary rounded-left text-center" style="padding: 5px 15px 5px 15px">
                        <%
                            if (sessionUser != null)
                            {
                        %>
                            <form action="/post-vote">
                                <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=post.getPostId()%>">
                                <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                                <input type="hidden" name="<%=Parameters.PostVoteParams.isUpvote%>" value="<%=true%>">
                                <button class="btn align-middle fa fa-arrow-up text-white" formmethod="post"></button><br>
                            </form>

                            <span class="align-middle text-white"><%=post.getPoints()%></span><br>

                            <form action="/post-vote">
                                <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=post.getPostId()%>">
                                <input type="hidden" name="<%=Parameters.UserParams.userId%>" value="<%=sessionUser.getUserId()%>">
                                <input type="hidden" name="<%=Parameters.PostVoteParams.isUpvote%>" value="<%=false%>">
                                <button class="btn align-middle fa fa-arrow-down text-white" formmethod="post"></button><br>
                            </form>
                        <%
                            }
                            else
                            {
                        %>
                            <span class="align-middle fa fa-arrow-up text-white"></span><br>
                            <span class="align-middle text-white"><%=post.getPoints()%></span><br>
                            <span class="align-middle fa fa-arrow-down text-white"></span><br>
                        <%
                            }
                        %>
                    </div>
                    <div class="col-8 bg-dark rounded-right">
                        <div style="padding: 5px 5px 5px 10px">
                            <div class="row">
                                <a class="font-weight-thin" style="color:dimgrey">
                                    Posted by u/<%=post.getAuthor().getUserName()%>, <%=post.getTimePassedSinceCreation()%>
                                </a>
                            </div>
                            <div class="row">
                                <a class="text-white"><h4 class="text-left"><%=post.getHeader()%>
                                </h4></a>
                            </div>
                            <div class="row text-left text-white">
                                <%=post.getBody()%>
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