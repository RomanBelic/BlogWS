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
             cn = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=&convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&allowMultiQueries=true&useSSL=false");
        }
        catch (SQLException e){
            Log.err("MySqlConnector : " + e.getMessage());
        }
        return cn;
    }


}
