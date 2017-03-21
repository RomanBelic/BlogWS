package com.esgi.al1.blogws;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
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
                .body("server", CoreMatchers.notNullValue())
                .body("host", CoreMatchers.is("localhost"))
                .body("server.port", CoreMatchers.is(2000));
    }


}
