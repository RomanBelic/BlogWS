package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.SqlConfig;
import com.esgi.al1.blogws.utils.SqlConfigBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import org.slf4j.Logger;

import static org.junit.Assert.assertEquals;


/**
 * Created by Chris GAGOUDE on 22/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("Test")
public class MySqlConnectorIT {
    SqlConfig sqlConfig;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql//localhost:3306/blogws?user=root&password=mysqlroot";
    private static final String LOGIN = "root";
    private static final String PASS = "mysqlroot";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";


    @Before
    public void init(){
        SqlConfigBuilder sqlConfigBuilder = new SqlConfigBuilder();
        sqlConfigBuilder.buildConnectionParams("convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&allowMultiQueries=true&useSSL=false");
        sqlConfigBuilder.buildLogin(LOGIN);
        sqlConfigBuilder.buildPass(PASS);
        sqlConfigBuilder.buildDriver(JDBC_DRIVER);
        sqlConfigBuilder.buildHost(HOST);
        sqlConfigBuilder.buildPort(PORT);
        sqlConfigBuilder.build();

        sqlConfig = new SqlConfig(sqlConfigBuilder);
    }

    @Test
    public void should_not_throws_SQLException(){
        MySqlConnector mySqlConnector = new MySqlConnector(sqlConfig);
        Connection connection = null;
        try{
            connection = mySqlConnector.getNewConnection();
            Assert.fail("JDBC Connection not thrown");
        }catch (SQLException ex){
            Log.err("Error JDBC Connection : " + ex.getMessage());
        }
    }
}
