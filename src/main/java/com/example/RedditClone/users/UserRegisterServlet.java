package com.example.RedditClone.users;

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
                userController.RegisterUser(user);
                response.sendRedirect("/views/users/register.jsp?message=success");
            }
            else
            {
                //password mismatch
                response.sendRedirect("/views/users/register.jsp?message=passwordMismatch");
            }

        }
        else
        {
            //userName is already taken, show error
            response.sendRedirect("/views/users/register.jsp?message=nameTaken");
        }
    }
}
