package com.esgi.al1.blogws;

import com.esgi.al1.blogws.controllers.PostController;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.models.PostDTO;
import com.jayway.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class PostControllerIT {

    //@Autowired
    PostController postController;

    @LocalServerPort
    int port;

    @Before
    public void restassuredinit(){
        RestAssured.port = port;
    }

    @Test
    public void soulhd_load_web_context(){
        assertThat(postController).isNotNull();
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
        byte arr[] = new byte[]{1};

        when()
                .get("/Post/1")
        .then()
                .statusCode(200)
                .body("id", CoreMatchers.is(1))
                .body("authorId", CoreMatchers.is(1))
                .body("date", CoreMatchers.is(new Date()))
                .body("description", CoreMatchers.is("My first description"))
                .body("tags", CoreMatchers.is(" "))
                .body("binaryContent", CoreMatchers.is(arr))
                .body("text", CoreMatchers.is("My first text post"))
                .body("fileName", CoreMatchers.is("filePost.txt"));
    }

    @Test
    public void should_not_found_unknow_post(){
        when()
                .get("/Post/89")
        .then()
                .statusCode(404);
    }

    public void should_create_post(){
        Date todayDate = new Date();
        int authorId = 1;
        int id = 1;
        String title = "Fisrt title post";
        String description = "My first description";
        String tags = "tags";
        byte arr[] = new byte[]{1, 2};
        String text = "My first text post";
        String fileName = "file.txt";

        Post post = new Post();
        post.setId(id);
        post.setDate(todayDate);
        post.setAuthorId(authorId);
        post.setDescription(description);
        post.setTags(tags);
        post.setBinaryContent(arr);
        post.setText(text);
        post.setFileName(fileName);
        given()
                .log().all()
                .body(post)
        .when()
                .post("/Post")
        .then()
                .log().all()
                .statusCode(201)
                .body("Date", CoreMatchers.is(new Date()))
                .body("authorId", CoreMatchers.is(1))
                .body("id", CoreMatchers.is(1))
                .body("title", CoreMatchers.is("Fisrt title post"))
                .body("description", CoreMatchers.is("My first description"))
                .body("tags", CoreMatchers.is("tags"))
                .body("binaryContent", CoreMatchers.is(new byte[]{1, 2}))
                .body("text", CoreMatchers.is("My first text post"))
                .body("FileName", CoreMatchers.is("file.txt"));

    }

    @Test
    public void should_not_create_post_when_title_and_text_are_null(){
        PostDTO postDTO = PostDTO.builder()
                .id(2).build();

        given()
                .log().all()
                .contentType(JSON)
                .body(postDTO)
        .when()
                .post("/Post")
        .then()
                .log().all()
                .statusCode(400);
    }


}
