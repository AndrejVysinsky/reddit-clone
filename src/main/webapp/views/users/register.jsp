<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page import="com.example.RedditClone.helpers.Redirects" %>
<%@ page import="com.example.RedditClone.helpers.ModelConstraints" %>
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
                    <form id="registrationForm" action="${pageContext.request.contextPath}/user-register">
                        <h4 class="text-white">Create new account</h4>
                        <div class="form-group">
                            <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userName%>">User name:</label>
                            <input class="form-control" type="text" name="<%=Parameters.UserParams.userName%>" required>
                        </div>

                        <div class="form-group">
                            <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userPassword%>">Password:</label>
                            <input class="form-control" type="password" name="<%=Parameters.UserParams.userPassword%>">
                        </div>

                        <div class="form-group">
                            <label class="font-weight-bold text-white" for="<%=Parameters.UserParams.userPasswordConfirmation%>">Password confirmation:</label>
                            <input class="form-control" id="passwordConfirmation" type="password" name="<%=Parameters.UserParams.userPasswordConfirmation%>">
                        </div>

                        <%@include file="../shared/modelError.jsp"%>

                        <button formmethod="post" class="btn btn-primary">Register</button>
                    </form>
                </div>
            </div>
        </div>
    </body>

    <style>
        .error {
            color: #FFF;
        }
    </style>

    <script>
        $( document ).ready(function() {
            $('#registrationForm').validate({
                rules: {
                    userName: {
                        required: true,
                        rangelength: [<%=ModelConstraints.UserConstraints.minUsernameLength%>, <%=ModelConstraints.UserConstraints.maxUsernameLength%>]
                    },
                    userPassword: {
                        required: true,
                        rangelength: [<%=ModelConstraints.UserConstraints.minPasswordLength%>, <%=ModelConstraints.UserConstraints.maxPasswordLength%>]
                    },
                    userPasswordConfirmation: {
                        required: true,
                        rangelength: [<%=ModelConstraints.UserConstraints.minPasswordLength%>, <%=ModelConstraints.UserConstraints.maxPasswordLength%>]
                    }
                }
            });
        });
    </script>
</html>
