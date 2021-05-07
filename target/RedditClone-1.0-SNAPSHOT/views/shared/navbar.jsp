<%@ page import="com.example.RedditClone.users.User" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="../../css/site.css">

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<style>
    .my-div {
        width: 30%;
        margin-left: 20px;
    }
</style>
<%
    User sessionUser = (User)session.getAttribute(Parameters.SessionParams.sessionUser);
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="../posts/index.jsp">Fake Reddit</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a class="nav-link" href="../posts/index.jsp">Posts</a></li>
                <%
                    if (sessionUser == null)
                    {
                %>
                        <li class="nav-item"><a class="nav-link" href="../users/login.jsp">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="../users/register.jsp">Register</a></li>
                <%
                    }
                    else
                    {
                %>
                        <li class="nav-item"><a class="nav-link" href="../users/profile.jsp"><%=sessionUser.getUserName()%></a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user-logout">Logout</a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js" integrity="sha512-n/4gHW3atM3QqRcbCn6ewmpxcLAHGaDjpEBu4xZd47N0W2oQ+6q7oc3PXstrJYXcbNU1OHdQ1T7pAP+gi5Yu8g==" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js" integrity="sha512-UdIMMlVx0HEynClOIFSyOrPggomfhBKJE28LKl8yR3ghkgugPnG6iLfRfHwushZl1MOPSY6TsuBDGPK2X4zYKg==" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<script src="js/jquery-validation-sk.js"></script>