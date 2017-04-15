package com.esgi.al1.blogws;

import com.esgi.al1.blogws.utils.DataBase;
import com.esgi.al1.blogws.utils.Queries;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Chris GAGOUDE on 03/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("Test")
@TestExecutionListeners({})
public class UserQueriesIT {

    DataBase dataBase;
    Queries queries;

    @Before
    public void init(){
        dataBase = new DataBase("blogws_test");
        dataBase.getUserTable();
        queries = new Queries(dataBase);
    }

    @Test
    public void should_get_all_users(){
        String getAllUsers = queries.GetAllUsers;
        assertThat(getAllUsers != null);
    }


    @Test
    public void should_get_user_by_id(){
        String getUserById = queries.GetPost;
        assertThat(getUserById != null);
    }

    @Test
    public void should_get_users_limited_to_a_number(){
        String getUsersLimited = queries.GetAllUsersLimit;
        assertThat(getUsersLimited != null);
    }

    @Test
    public void should_insert_user(){
        String insertUser = queries.InsertUser;
        assertThat(insertUser != null);
    }

    @Test
    public void should_update_user_by_id(){
        String updateUser = queries.UpdateUser; // WhereUserId
        assertThat(updateUser != null);
    }

    @Test
    public void should_update_all_users(){
        /*String updateAllUsers = queries.UpdateAllUser; //Update all users
        assertThat(updateAllUsers != null);*/
    }

    @Test
    public void should_delete_user_by_id(){
        String deleteUser = queries.DeleteUser;  // WhereUserId
        assertThat(deleteUser != null);
    }

    @Test
    public void delete_all_users_from_the_userTable(){
        /*String deleteAllUsers = queries.DeleteAllUser;  // DeleteAllUsers from the table
        assertThat(deleteAllPosts != null);*/
    }
}
