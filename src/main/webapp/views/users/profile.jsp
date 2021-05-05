<%@ page import="com.example.RedditClone.helpers.Redirects" %>
<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>

        <%
            if (sessionUser == null)
            {
                response.sendRedirect(Redirects.UserRedirects.userLogin);
                return;
            }
        %>

        <div class="my-div">
            <h5>Profile</h5>
            <div class="form-group">
                <label class="font-weight-bold" for="<%=Parameters.UserParams.userName%>">User name:</label>
                <input class="form-control" type="text" name="<%=Parameters.UserParams.userName%>" disabled="disabled" value="<%=sessionUser.getUserName()%>">
            </div>

            <div class="form-group">
                <label class="font-weight-bold" for="<%=Parameters.UserParams.userName%>">Image:</label>
                <img class="img-fluid" src="" alt="">
            </div>

            <hr>

            <h6>Reset password</h6>
            <form action="${pageContext.request.contextPath}/user-password-update">
                <div class="form-group">
                    <label class="font-weight-bold" for="<%=Parameters.UserParams.userPassword%>">New password:</label>
                    <input class="form-control" type="password" name="<%=Parameters.UserParams.userPassword%>">
                </div>
                <div class="form-group">
                    <label class="font-weight-bold" for="<%=Parameters.UserParams.userPassword%>">Confirm new password:</label>
                    <input class="form-control" type="password" name="<%=Parameters.UserParams.userPasswordConfirmation%>">
                </div>
                <button class="btn btn-primary" formmethod="post">Change password</button>
            </form>
        </div>
    </body>
</html>