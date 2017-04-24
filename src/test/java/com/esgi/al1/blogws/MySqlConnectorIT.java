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

import javax.validation.constraints.AssertTrue;
import java.sql.SQLException;
import java.sql.Connection;

import static org.junit.Assert.assertTrue;


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
    private static final String DB_URL = "jdbc:mysql://localhost:3306/blogws_test";
    private static final String LOGIN = "root";
    private static final String PASS = "mysqlroot";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";

    @Before
    public void init(){
        SqlConfigBuilder sqlConfigBuilder = new SqlConfigBuilder();
        sqlConfigBuilder.buildConnectionParams(DB_URL);
        sqlConfigBuilder.buildLogin(LOGIN);
        sqlConfigBuilder.buildPass(PASS);
        sqlConfigBuilder.buildDriver(JDBC_DRIVER);
        sqlConfigBuilder.buildHost(HOST);
        sqlConfigBuilder.buildPort(PORT);
        sqlConfigBuilder.build();

        sqlConfig = new SqlConfig(sqlConfigBuilder);
    }

    @Test
    public void should_not_throws_ClassNotFoundException_or_SQLException(){
        MySqlConnector mySqlConnector = new MySqlConnector(sqlConfig);

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            Log.err("JDBC driver not found");
        }

        Connection connection = null;
        try{
            connection = mySqlConnector.getNewConnection();
            assertTrue(connection != null);
        }catch (SQLException ex){
            Log.err("Error JDBC Connection : " + ex.getMessage());
        }
    }
}
