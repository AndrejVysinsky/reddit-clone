package com.example.RedditClone.users;

import com.example.RedditClone.modelMessages.ModelMessage;
import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.helpers.Redirects;
import org.springframework.security.crypto.bcrypt.BCrypt;

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

        User userDb = userController.GetUserByName(user.getUserName());

        if (userDb != null && BCrypt.checkpw(user.getPassword(), userDb.getPassword()))
        {
            //logged in, save user to session
            HttpSession session = request.getSession(true);
            session.setAttribute(Parameters.SessionParams.sessionUser, userDb);
            response.sendRedirect(Redirects.PostRedirects.postIndex);
        }
        else
        {
            //wrong password or user does not exist

            ModelMessage modelMessage = new ModelMessage();
            modelMessage.setMessage("Incorrect password or username does not exist.", true);

            request.setAttribute(Parameters.ModelParams.modelMessage, modelMessage);
            request.getRequestDispatcher(Redirects.UserRedirects.userLogin).forward(request, response);
        }
    }
}
