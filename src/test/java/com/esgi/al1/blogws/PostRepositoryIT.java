package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.Queries;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostRepository.class)
@ActiveProfiles("Test")
@DataJpaTest
public class PostRepositoryIT {

    @Autowired
    PostRepository postRepository;
    @Autowired
    Queries queries;
    Post post;

    public void init(){
        post = new Post();
        post.setDate(new Date());
        post.setId(1);
        post.setTitle("Post title");
        post.setText("Post text");
        post.setAuthorId(1);
    }

    @Test
    public void should_save_post(){
        int inserted = postRepository.insert(queries.InsertPost, post);
        assertTrue( inserted > 0);
    }

    @Test
    public void should_get_post_by_id(){
        Post returnedPost = postRepository.get(queries.GetPost, post.getId());
        assertThat(returnedPost.getId()).isEqualTo(post.getId());
    }

    @Test
    public void should_get_post_by_authorId(){
        Post returnedPost = postRepository.get(queries.GetPost, post.getAuthorId());
        assertThat(returnedPost.getAuthorId()).isEqualTo(post.getAuthorId());
    }

    @Test
    public void should_get_post_by_date(){
        Post returnedPost = postRepository.get(queries.GetPost, post.getDate());
        assertThat(returnedPost.getDate()).isEqualTo(post.getDate());
    }

    @Test
    public void should_get_post_by_title(){
        Post returnedPost = postRepository.get(queries.GetPost, post.getTitle());
        assertThat(returnedPost.getTitle()).isEqualTo(post.getTitle());
    }

    @Test
    public void should_update_post_authorId_by_postId(){
        post.setAuthorId(2);
        int confirmUpdated = postRepository.updateOrDelete(queries.UpdatePost, post.getId());
        assertThat(confirmUpdated).isNotNull();
    }

    @Test
    public void should_update_postDate_by_postId(){
        post.setDate(new Date(2017, 12, 31));
        int confirmUpdated = postRepository.updateOrDelete(queries.UpdatePost, post.getId());
        assertThat(confirmUpdated).isNotNull();
    }

    @Test
    public void should_update_postTitle_by_postId(){
        post.setTitle("Post title modified");
        int confirmUpdated = postRepository.updateOrDelete(queries.UpdatePost, post.getId());
        assertThat(confirmUpdated).isNotNull();
    }

    @Test
    public void should_get_all_posts(){
        List<Post> listPosts = postRepository.getAll(queries.GetAllPosts);
        assertThat(listPosts).isNotEmpty();
    }

    @Test
    public void should_get_all_posts_by_authorId(){
        List<Post> listPosts = postRepository.getAll(queries.GetAllPosts, 1);
        assertThat(listPosts).isNotEmpty();
    }

    @Test
    public void should_get_all_posts_by_date(){
        List<Post> listPosts = postRepository.getAll(queries.GetAllPosts, new Date());
        assertThat(listPosts).isNotEmpty();
    }
}
