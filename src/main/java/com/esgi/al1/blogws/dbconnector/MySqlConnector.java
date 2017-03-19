package com.esgi.al1.blogws.dbconnector;

import com.esgi.al1.blogws.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Romaaan on 18/03/2017.
 */

public class MySqlConnector {

    public static Connection getNewConnection () throws SQLException{
        Connection cn = null;
        try {
             cn = DriverManager.getConnection("");
        }
        catch (SQLException e){
            Log.err("MySqlConnector : " + e.getMessage());
            throw e;
        }
        return cn;
    }


}