package com.esgi.al1.blogws.dbconnector;

import com.esgi.al1.blogws.utils.SqlConfig;
import com.esgi.al1.blogws.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Romaaan on 18/03/2017.
 */
@Component
public class MySqlConnector {

    @Autowired
    public MySqlConnector(SqlConfig sqlCfg){
        this.sqlCfg = sqlCfg;
    }

    private final SqlConfig sqlCfg;

    public Connection getNewConnection () throws SQLException{

        Connection cn;
        try {
             cn = DriverManager.getConnection(sqlCfg.getConnectionString());
        }
        catch (SQLException e){
            Log.err("MySqlConnector : " + e.getMessage());
            throw e;
        }
        return cn;
    }

}
