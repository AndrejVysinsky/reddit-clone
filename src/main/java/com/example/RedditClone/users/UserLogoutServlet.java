package com.example.RedditClone.users;

import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.helpers.Redirects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLogoutServlet", value = "/user-logout")
public class UserLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        User sessionUser = (User)session.getAttribute(Parameters.SessionParams.sessionUser);

        if (sessionUser != null)
        {
            session.removeAttribute(Parameters.SessionParams.sessionUser);
        }

        response.sendRedirect(Redirects.UserRedirects.usersLogin);
    }
}
