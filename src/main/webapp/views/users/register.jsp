<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>

        <div class="my-div">
            <form action="${pageContext.request.contextPath}/user-register">
                <div class="form-group">
                    <label class="font-weight-bold" for="<%=Parameters.UserParams.userName%>">Používateľské meno:</label>
                    <input class="form-control" type="text" name="<%=Parameters.UserParams.userName%>">
                </div>

                <div class="form-group">
                    <label class="font-weight-bold" for="<%=Parameters.UserParams.userPassword%>">Heslo:</label>
                    <input class="form-control" type="password" name="<%=Parameters.UserParams.userPassword%>">
                </div>

                <div class="form-group">
                    <label class="font-weight-bold" for="<%=Parameters.UserParams.userPasswordConfirmation%>">Potvrdenie hesla:</label>
                    <input class="form-control" type="password" name="<%=Parameters.UserParams.userPasswordConfirmation%>">
                </div>

                <button formmethod="post" class="btn btn-primary">Registrovať</button>
            </form>
        </div>
    </body>
</html>
