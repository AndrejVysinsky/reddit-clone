package com.example.RedditClone.users;

import com.example.RedditClone.helpers.DatabaseConnectionManager;
import com.example.RedditClone.helpers.ParameterMapping;
import com.example.RedditClone.helpers.Parameters;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserController
{
    public User MapParamsToUser(Map<String, String[]> params)
    {
        HashMap<String, String> trimmedParams = ParameterMapping.trimParamMap(params);

        User user = new User();
        user.setUserName(trimmedParams.get(Parameters.UserParams.userName));
        user.setPassword(trimmedParams.get(Parameters.UserParams.userPassword));
        user.setPasswordConfirmation(trimmedParams.get(Parameters.UserParams.userPasswordConfirmation));

        return user;
    }

    public User GetUserByName(String userName)
    {
        String sql = "SELECT * FROM users WHERE userName = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, userName);
            return GetUserFromResultSet(ps.executeQuery());

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User GetUserById(Integer userId)
    {
        String sql = "SELECT * FROM users WHERE userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            return GetUserFromResultSet(ps.executeQuery());

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User GetUserFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next())
        {
            User user = new User();

            user.setUserId(rs.getInt(Parameters.UserParams.userId));
            user.setUserName(rs.getString(Parameters.UserParams.userName));
            user.setPassword(rs.getString(Parameters.UserParams.userPassword));
            //user.setProfileImage(rs.getString(Parameters.UserParams.userPicture)); not in db currently

            return user;
        }
        return null;
    }

    public boolean RegisterUser(User user)
    {
        String sql = "INSERT INTO users(userName, userPassword) VALUES(?, ?)";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User LoginUser(User user)
    {
        String sql = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                User userDb = new User();

                userDb.setUserId(rs.getInt(Parameters.UserParams.userId));
                userDb.setUserName(rs.getString(Parameters.UserParams.userName));
                //userDb.setProfileImage(rs.getString(Parameters.UserParams.userImage));

                return userDb;
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean UpdateUserPassword(User user, String newPassword)
    {
        String sql = "UPDATE users SET userPassword = ? WHERE userId = ?";

        try {
            Connection con = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setInt(2, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User GetLoggedInUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        return (User)session.getAttribute(Parameters.SessionParams.sessionUser);
    }
}
