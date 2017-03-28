package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.utils.SqlConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Created by Chris GAGOUDE on 22/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySqlConnector.class)
@DataJpaTest
@ActiveProfiles("Test")
public class MySqlConnectorIT {

    @Mock
    SqlConfig sqlConfig;

    @Mock
    MySqlConnector mySqlConnector;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    public void should_get_mysql_config_not_null(){
        assertNotNull(sqlConfig);
        assertNotNull(mySqlConnector);
    }
}
