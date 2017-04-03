package com.esgi.al1.blogws;

import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.DataBase;
import com.esgi.al1.blogws.utils.Queries;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Chris GAGOUDE on 03/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("Test")
public class PostQueriesIT {

    DataBase dataBase;
    Queries queries;

    @Before
    public void init(){
        dataBase = new DataBase("blogws_test");

        dataBase.getPostTable();
        queries = new Queries(dataBase);
    }

    @Test
    public void should_get_all_posts(){
        String getAllPosts = queries.GetAllPosts;
        assertThat(getAllPosts != null);
    }

    @Test
    public void should_get_post_by_id(){
        String getPostById = queries.GetPost;
        assertThat(getPostById != null);
    }

    @Test
    public void should_get_posts_limited_to_a_number(){
        String getPostLimited = queries.GetAllPostsLimit;
        assertThat(getPostLimited != null);
    }

    @Test
    public void should_insert_post(){
        String insertPost = queries.InsertPost;
        assertThat(insertPost != null);
    }

    @Test
    public void should_update_post_by_id(){
        String updatePost = queries.UpdatePost; // WherePostId
        assertThat(updatePost != null);
    }

    @Test
    public void should_update_all_posts(){
        String updateAllPosts = queries.UpdateAllPosts; //Update all posts
        assertThat(updateAllPosts != null);
    }

    @Test
    public void should_delete_post_by_id(){
        String deletePost = queries.DeletePost;  // WherePostId
        assertThat(deletePost != null);
    }

    @Test
    public void should_delete_all_posts_from_the_postTable(){
        String deleteAllPosts = queries.DeleteAllPost;  // DeleteAllPosts from the table
        assertThat(deleteAllPosts != null);
    }
}
