<%@ page import="com.example.RedditClone.errors.ModelError" %>
<%
    ModelError modelError = (ModelError) request.getAttribute(Parameters.ErrorParams.modelError);

    if (modelError != null)
    {
%>
    <div class="form-group">
        <span class="text-danger"><%=modelError.getErrorMessage()%></span>
    </div>
<%
    }
%>
