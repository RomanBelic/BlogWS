package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.Settings;
import com.esgi.al1.blogws.utils.SqlConfig;
import com.esgi.al1.blogws.utils.SqlConfigBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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

    private MySqlConnector connector;
    private SqlConfig sqlConfig;

    public static final String JDBC_DRIVER = "jdbc:mysql";
    public static final String LOGIN = "root";
    public static final String PASS = "mysqlroot";
    public static final String HOST = "localhost";
    public static final String PORT = "3306";


    @Before
    public void init(){
        sqlConfig = new SqlConfigBuilder()
        .buildLogin(LOGIN)
        .buildPass(PASS)
        .buildDriver(JDBC_DRIVER)
        .buildHost(HOST)
        .buildPort(PORT).build();

        connector = new MySqlConnector(sqlConfig);
    }

    @Test
    public void should_not_throws_ClassNotFoundException_or_SQLException(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            Log.err("JDBC driver not found");
        }

        Connection connection = null;
        try{
            connection = connector.getNewConnection();
            assertTrue(connection != null);
            connection.close();
        }catch (SQLException ex){
            Log.err("Error JDBC Connection : " + ex.getMessage());
        }
    }
}
