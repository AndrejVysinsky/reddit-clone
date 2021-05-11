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

        <div class="container myContainer">
            <div class="row justify-content-center" style="margin-bottom: 20px">
                <div class="col-7 bg-dark rounded" style="padding: 15px">
                    <form action="${pageContext.request.contextPath}/user-login">
                        <h4 class="text-white">Login with existing account</h4>
                        <div class="form-group">
                            <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userName%>">User name:</label>
                            <input class="form-control" type="text" name="<%=Parameters.UserParams.userName%>">
                        </div>

                        <div class="form-group">
                            <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userPassword%>">Password:</label>
                            <input class="form-control" type="password" name="<%=Parameters.UserParams.userPassword%>">
                        </div>

                        <%@include file="../shared/modelError.jsp"%>

                        <button formmethod="post" class="btn btn-primary">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
