<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page import="com.example.RedditClone.helpers.Redirects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>

        <%
            if (sessionUser != null)
            {
                response.sendRedirect("profile.jsp");
                return;
            }
        %>

        <div class="my-div">
            <form action="${pageContext.request.contextPath}/user-login">
                <h5>Login with existing account</h5>
                <div class="form-group">
                    <label class="font-weight-bold" for="<%=Parameters.UserParams.userName%>">User name:</label>
                    <input class="form-control" type="text" name="<%=Parameters.UserParams.userName%>">
                </div>

                <div class="form-group">
                    <label class="font-weight-bold" for="<%=Parameters.UserParams.userPassword%>">Password:</label>
                    <input class="form-control" type="password" name="<%=Parameters.UserParams.userPassword%>">
                </div>

                <button formmethod="post" class="btn btn-primary">Login</button>
            </form>
        </div>
    </body>
</html>
