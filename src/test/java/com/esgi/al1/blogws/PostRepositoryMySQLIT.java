package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dao.PostDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Chris GAGOUDE on 21/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostDAO.class)
public class PostRepositoryMySQLIT {

    @Autowired
    PostDAO postDAO;

    @Test
    public void should_get_post_data(){

    }

    @Test
    public void should_get_all(){

    }

}
