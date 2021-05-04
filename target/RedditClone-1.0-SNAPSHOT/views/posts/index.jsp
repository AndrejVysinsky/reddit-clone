<%@ page import="com.example.RedditClone.posts.PostsController" %>
<%@ page import="com.example.RedditClone.users.User" %>
<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>
        <div class="my-div">
        <%
            User loggedInUser = null; // get logged in user
        %>

        <form action="${pageContext.request.contextPath}/posts-create">
            <div class="form-group">
                <input type="text" name="<%=Parameters.PostParams.postHeader%>" class="form-control">
            </div>
            <div class="form-group">
                <input type="text" name="<%=Parameters.PostParams.postBody%>" class="form-control">
            </div>
            <div class="form-group">
                <input type="hidden" name="<%=Parameters.UserParams.userId%>" class="form-control" value="<%=loggedInUser != null ? loggedInUser.getUserId() : 0%>">
            </div>
            <button formmethod="post" class="btn btn-primary">Odoslať</button>
        </form>
        </div>
    </body>
</html>