package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.DataBase;
import com.esgi.al1.blogws.utils.Queries;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("Test")
@DataJpaTest
public class PostRepositoryIT {

    @Autowired
    PostRepository postRepository;

    public void init(){
    }

    @Test
    public void should_get_post_data(){

    }
}
