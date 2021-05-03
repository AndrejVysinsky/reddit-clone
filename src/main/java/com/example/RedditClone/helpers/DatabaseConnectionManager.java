package com.example.RedditClone.helpers;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    public static Connection getDatabaseConnection() throws NamingException, SQLException
    {
        Context initContext = new InitialContext();
        Context env = (Context) initContext.lookup("java:/comp/env");
        DataSource dataSource = (DataSource) env.lookup("mysqlpool");

        Connection con = dataSource.getConnection();

        return con;
    }
}
