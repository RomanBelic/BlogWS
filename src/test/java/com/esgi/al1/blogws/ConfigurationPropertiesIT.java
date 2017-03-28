package com.esgi.al1.blogws;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.*;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationPropertiesIT {


    @Test
    public void should_get_mysql_properties(){
        when()
                .get("/Post")
        .then()
                .statusCode(200)
                .body("databasetest", CoreMatchers.is("blogws_test"))
                .body("host", CoreMatchers.is("localhost"))
                .body("sqlport", CoreMatchers.is(3306))
                .body("driver", CoreMatchers.is("jdbc:mysql:"))
                .body("dbparam", CoreMatchers.is("?convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&allowMultiQueries=true"));

    }

    @Test
    public void should_get_web_properties(){
        when()
              .get("/Post")
        .then()
                .statusCode(200)
                .body("connectionstring.host", CoreMatchers.is("localhost"))
                .body("connectionstring.webport", CoreMatchers.is(8090));
    }


}
