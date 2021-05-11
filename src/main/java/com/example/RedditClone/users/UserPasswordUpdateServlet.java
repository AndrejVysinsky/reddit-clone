package com.example.RedditClone.users;

import com.example.RedditClone.modelMessages.ModelMessage;
import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.helpers.Redirects;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserPasswordUpdateServlet", value = "/user-password-update")
public class UserPasswordUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserController userController = new UserController();
        User user = userController.MapParamsToUser(request.getParameterMap());

        HttpSession session = request.getSession(true);
        User sessionUser = (User)session.getAttribute(Parameters.SessionParams.sessionUser);

        ModelMessage modelMessage = new ModelMessage();

        if (sessionUser == null)
        {
            //trying to change password without being logged in - impossible? (for regular user)
            response.sendRedirect(Redirects.PostRedirects.postIndex);
            return;
        }

        user.setUserName(sessionUser.getUserName());

        if (user.isValid())
        {
            if (user.getPassword().equals(user.getPasswordConfirmation()))
            {
                //passwords match and is valid, update it
                boolean result = userController.UpdateUserPassword(sessionUser, user.getPassword());

                if (result)
                {
                    //password changed
                    modelMessage.setMessage("Password updated.", false);
                }
                else
                {
                    //error updating db
                    modelMessage.setMessage("Error occurred. Password was not updated.", true);
                }
            }
            else
            {
                modelMessage.setMessage("Passwords do not match.", true);
            }
        }
        else
        {
            //failed password validation
            modelMessage.setMessage("Model validation error.", true);
        }

        request.setAttribute(Parameters.ModelParams.modelMessage, modelMessage);
        request.getRequestDispatcher(Redirects.UserRedirects.userProfile).forward(request, response);
    }
}
