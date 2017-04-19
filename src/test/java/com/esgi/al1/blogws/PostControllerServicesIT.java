package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.services.AbstractControllerService;
import com.esgi.al1.blogws.services.PostControllerService;
import com.esgi.al1.blogws.utils.GeneratedQuery;
import com.esgi.al1.blogws.utils.Queries;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class PostControllerServicesIT {

    @Mock
    PostControllerService postControllerService;

    @Captor
    ArgumentCaptor<Post> argumentCaptor;

    List<Post> listPost = new ArrayList<>();

    @Before
    public void init(){
        Post post = new Post();
        post.setDate(new Date());
        post.setId(1);
        post.setTitle("Post title");
        post.setText("Post text");
        post.setAuthorId(1);

        Post post2 = new Post();
        post2 = new Post();
        post2.setDate(new Date());
        post2.setId(1);
        post2.setTitle("Post title");
        post2.setText("Post text");
        post2.setAuthorId(1);

        listPost.add(post);
        listPost.add(post2);
    }

    @Test
    public void should_get_all_posts(){
        Mockito.when(postControllerService.getAll()).thenReturn(listPost);
        assertThat(argumentCaptor.getAllValues().size()).isNotNull();
    }



}
