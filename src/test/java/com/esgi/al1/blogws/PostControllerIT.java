package com.esgi.al1.blogws;

import com.jayway.restassured.RestAssured;
import lombok.Value;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)    // Will listen on default port 8090
public class PostControllerIT {

    @LocalServerPort
    private int port;

    @Before
    public void init(){
        RestAssured.port = port;
    }

    @Test
    public void should_get_all_posts(){
        when()
                .get("/Post")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void should_get_all_post_by_id(){
        // Not finished yet
    }
}
