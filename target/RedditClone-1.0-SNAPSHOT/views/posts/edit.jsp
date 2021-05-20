<%@ page import="com.example.RedditClone.posts.PostController" %>
<%@ page import="com.example.RedditClone.users.User" %>
<%@ page import="com.example.RedditClone.helpers.Parameters" %>
<%@ page import="com.example.RedditClone.posts.Post" %>
<%@ page import="com.example.RedditClone.posts.PostController" %>
<%@ page import="com.example.RedditClone.helpers.ModelConstraints" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Post - edit</title>
    </head>
    <body>
        <%@include file="../shared/navbar.jsp"%>
        <div class="container">
            <%
                String postIdParam = request.getParameter(Parameters.PostParams.postId);
                Integer postId = null;
                if (postIdParam != null)
                {
                    postId = Integer.parseInt(postIdParam);
                }

                PostController postController = new PostController();
                Post post = postController.GetPostById(postId);

                if (post == null)
                {
                    response.sendRedirect("index.jsp");
                }

                if (sessionUser == null)
                {
                    response.sendRedirect("index.jsp");
                    return;
                }
            %>

            <form id="postForm" action="${pageContext.request.contextPath}/post-edit">
                <h5>Edit post</h5>
                <div class="form-group">
                    <input type="text" name="<%=Parameters.PostParams.postHeader%>" class="form-control" value="<%=post.getHeader()%>">
                </div>
                <div class="form-group">
                    <textarea type="text" name="<%=Parameters.PostParams.postBody%>" class="form-control" rows="10"><%=post.getBody()%></textarea>
                </div>

                <input type="hidden" name="<%=Parameters.PostParams.postId%>" value="<%=post.getPostId()%>">

                <%@include file="../shared/modelError.jsp"%>

                <button formmethod="post" class="btn btn-primary">Create</button>
            </form>
        </div>
    </body>

    <style>
        .error {
            color: #FFF;
        }
    </style>

    <script>
        $( document ).ready(function() {
            $('#postForm').validate({
                rules: {
                    postHeader: {
                        required: true,
                        rangelength: [<%=ModelConstraints.PostConstraints.minPostHeaderLength%>, <%=ModelConstraints.PostConstraints.maxPostHeaderLength%>]
                    },
                    postBody: {
                        required: true,
                        minlength: <%=ModelConstraints.PostConstraints.minPostBodyLength%>
                    }
                }
            });
        });
    </script>
</html>