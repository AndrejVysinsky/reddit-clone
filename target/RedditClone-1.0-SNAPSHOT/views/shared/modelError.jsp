<%@ page import="com.example.RedditClone.modelMessages.ModelMessage" %>
<%
    ModelMessage modelMessage = (ModelMessage) request.getAttribute(Parameters.ModelParams.modelMessage);

    if (modelMessage != null)
    {
%>
    <div class="form-group">
        <span class="<%=modelMessage.isErrorMessage() ? "text-danger" : "text-success"%>"><%=modelMessage.getMessage()%></span>
    </div>
<%
    }
%>
