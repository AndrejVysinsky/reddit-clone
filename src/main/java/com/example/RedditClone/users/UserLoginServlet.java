package com.example.RedditClone.users;

import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.helpers.Redirects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", value = "/user-login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserController userController = new UserController();
        User user = userController.MapParamsToUser(request.getParameterMap());

        User userDb = userController.LoginUser(user);

        if (userDb != null)
        {
            //logged in, save user to session
            HttpSession session = request.getSession(true);
            session.setAttribute(Parameters.SessionParams.sessionUser, userDb);
            response.sendRedirect(Redirects.PostRedirects.postIndex);
        }
        else
        {
            //wrong password or user does not exist
            response.sendRedirect(Redirects.UserRedirects.userLogin + Redirects.ModelRedirects.modelError + "Incorrect password or username does not exist.");
        }
    }
}
