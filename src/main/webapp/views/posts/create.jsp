<%@ page import="com.example.RedditClone.posts.PostsController" %>
<%@ page import="com.example.RedditClone.users.User" %>
<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Post - create</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>
        <div class="my-div">
            <%
                if (sessionUser == null)
                {
                    response.sendRedirect("index.jsp");
                    return;
                }
            %>

            <form action="${pageContext.request.contextPath}/posts-create">
                <h5>Create post</h5>
                <div class="form-group">
                    <input type="text" name="<%=Parameters.PostParams.postHeader%>" class="form-control">
                </div>
                <div class="form-group">
                    <textarea type="text" name="<%=Parameters.PostParams.postBody%>" class="form-control" rows="10"></textarea>
                </div>
                <button formmethod="post" class="btn btn-primary">Create</button>
            </form>
        </div>
    </body>
</html>