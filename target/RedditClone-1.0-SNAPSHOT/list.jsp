<%@ page import="com.example.RedditClone.sweet.SweetController" %>
<%@ page import="com.example.RedditClone.sweet.Sweet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zaco3
  Date: 1. 4. 2021
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="navbar.jsp"%>

    <%

        SweetController sweetController = new SweetController();

        List<Sweet> sweets = sweetController.getAllSweets();

    %>

    <table class="table table-striped table-responsive-sm col-5">
        <thead class="thead-dark">
            <th>Názov</th>
            <th>Kategória</th>
            <th>Hmotnosť</th>
            <th>Cena</th>
            <th>Alergény</th>
        </thead>

        <tbody>
            <%
                for (Sweet sweet : sweets)
                {
            %>
                <tr>
                    <td>
                        <%=sweet.getName()%>
                    </td>
                    <td>
                        <%=sweet.getCategoryName()%>
                    </td>
                    <td>
                        <%=sweet.getWeight()%>
                    </td>
                    <td>
                        <%=sweet.getPrice()%>
                    </td>
                    <td>
                        <%=sweet.getAllergens()%>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>

</body>
</html>
