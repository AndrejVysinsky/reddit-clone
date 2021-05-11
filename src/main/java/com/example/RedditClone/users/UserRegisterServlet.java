package com.example.RedditClone.users;

import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.helpers.Redirects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserRegisterServlet", value = "/user-register")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserController userController = new UserController();
        User user = userController.MapParamsToUser(request.getParameterMap());

        User userDb = userController.GetUserByName(user.getUserName());

        if (userDb == null)
        {
            //userName is free

            if (user.getPassword().equals(user.getPasswordConfirmation()))
            {
                //add new user to db
                if (userController.RegisterUser(user))
                {
                    userDb = userController.GetUserByName(user.getUserName());

                    HttpSession session = request.getSession(true);
                    session.setAttribute(Parameters.SessionParams.sessionUser, userDb);
                    response.sendRedirect(Redirects.PostRedirects.postIndex);
                }
                else
                {
                    response.sendRedirect(Redirects.UserRedirects.userRegister + "?message=unexpectedError");
                }
            }
            else
            {
                //password mismatch
                response.sendRedirect(Redirects.UserRedirects.userRegister + "?message=passwordMismatch");
            }
        }
        else
        {
            //userName is already taken, show error
            response.sendRedirect(Redirects.UserRedirects.userRegister + "?message=nameTaken");
        }
    }
}
