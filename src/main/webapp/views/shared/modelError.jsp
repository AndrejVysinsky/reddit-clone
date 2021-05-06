
<%
    String modelError = request.getParameter("modelError");

    if (modelError != null)
    {
%>
    <div class="form-group">
        <span class="text-danger"><%=modelError%></span>
    </div>
<%
    }
%>
