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

        <div class="container myContainer">
            <div class="row justify-content-center" style="margin-bottom: 20px">
                <div class="col-7 bg-dark rounded" style="padding: 15px">
                    <h4 class="text-white">Profile</h4>
                    <div class="form-group">
                        <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userName%>">User name:</label>
                        <input class="form-control" type="text" name="<%=Parameters.UserParams.userName%>" disabled="disabled" value="<%=sessionUser.getUserName()%>">
                    </div>

                    <div class="form-group">
                        <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userName%>">Image:</label>
                        <img class="img-fluid" src="" alt="">
                    </div>

                    <hr>

                    <h5 class="text-white">Reset password</h5>
                    <form action="${pageContext.request.contextPath}/user-password-update">
                        <div class="form-group">
                            <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userPassword%>">New password:</label>
                            <input class="form-control" type="password" name="<%=Parameters.UserParams.userPassword%>">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userPassword%>">Confirm new password:</label>
                            <input class="form-control" type="password" name="<%=Parameters.UserParams.userPasswordConfirmation%>">
                        </div>

                        <%@include file="../shared/modelError.jsp"%>

                        <button class="btn btn-primary" formmethod="post">Change password</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>