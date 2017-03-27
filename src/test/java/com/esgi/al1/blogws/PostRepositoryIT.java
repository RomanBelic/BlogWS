package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.models.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import static org.junit.Assert.*;

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


    @Test
    public void should_get_post_data_by_id(){
        Post post = new Post();
        post.setDate(new Date(2017, 03, 27));
        post.setAuthorId(123);
        post.setId(1);
        post.setTitle("First post");
        post.setDescription("Our first post for testing purposes.");
        post.setTags(" ");
        post.setBinaryContent(new byte[20]);
        post.setText("Post text");
        post.setFileName("postFile.txt");

        Post queryPost = postRepository.get("SELECT * FROM Post "+
                "WHERE Id="+post.getId());

        assertThat(queryPost.getId()).isEqualTo(post.getId());
    }

    @Test
    public void should_get_all(){

    }

}
