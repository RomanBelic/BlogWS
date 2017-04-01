package com.esgi.al1.blogws;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.springframework.boot.test.context.SpringBootTest.*;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ConfigurationPropertiesIT {

    @Test
    public void should_get_application_properties() {
        when()
                .get("/Post")
                .then()
                .statusCode(200)
                .body("database.test", CoreMatchers.is("blogws_test"))
                .body("connectionstring.host", CoreMatchers.is("localhost"))
                .body("connectionstring.sqlport", CoreMatchers.is(3306))
                .body("connectionstring.driver", CoreMatchers.is("jdbc:mysql"))
                .body("connectionstring.dbparams", CoreMatchers.is("convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&allowMultiQueries=true&useSSL=false"))
                .body("connectionstring.login", CoreMatchers.is("root"))
                .body("connectionstring.pass", CoreMatchers.is(" "));
    }
}
