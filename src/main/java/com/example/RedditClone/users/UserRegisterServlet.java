package com.example.RedditClone.users;

import com.example.RedditClone.modelMessages.ModelMessage;
import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.helpers.Redirects;
import org.springframework.security.crypto.bcrypt.BCrypt;

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

        ModelMessage modelMessage = new ModelMessage();

        if (user.isValid() == false)
        {
            modelMessage.setMessage("Model validation error.", true);
            request.setAttribute(Parameters.ModelParams.modelMessage, modelMessage);
            request.getRequestDispatcher(Redirects.UserRedirects.userRegister).forward(request, response);
            return;
        }

        if (userDb == null)
        {
            //userName is free

            if (user.getPassword().equals(user.getPasswordConfirmation()))
            {
                //add new user to db
                String passwordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                user.setPassword(passwordHash);

                if (userController.RegisterUser(user))
                {
                    userDb = userController.GetUserByName(user.getUserName());

                    HttpSession session = request.getSession(true);
                    session.setAttribute(Parameters.SessionParams.sessionUser, userDb);
                    response.sendRedirect(Redirects.PostRedirects.postIndex);

                    return;
                }
                else
                {
                    modelMessage.setMessage("Unexpected error. Account could not be created.", true);
                }
            }
            else
            {
                //password mismatch
                modelMessage.setMessage("Passwords do not match.", true);
            }
        }
        else
        {
            //userName is already taken, show error
            modelMessage.setMessage("User with this name already exists.", true);
        }

        request.setAttribute(Parameters.ModelParams.modelMessage, modelMessage);
        request.getRequestDispatcher(Redirects.UserRedirects.userRegister).forward(request, response);
    }
}
