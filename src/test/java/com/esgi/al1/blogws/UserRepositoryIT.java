package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dao.UserRepository;
import com.esgi.al1.blogws.models.UserType;
import com.esgi.al1.blogws.utils.Queries;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.esgi.al1.blogws.models.User;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Chris GAGOUDE on 01/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRepository.class)
@ActiveProfiles("Test")
public class UserRepositoryIT {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Queries queries;
    User userAdmin;
    User user;

    public void init(){
        userAdmin = new User();
        userAdmin.setId(1);
        userAdmin.setId(1);
        userAdmin.setDateCreated(new Date());
        userAdmin.setName("Luiz");
        userAdmin.setLastName("Enrique");
        userAdmin.setType(UserType.Admin);

        user = new User();
        user.setId(2);
        user.setId(2);
        user.setDateCreated(new Date());
        user.setName("Junior");
        user.setLastName("Neymar");
        user.setType(UserType.User);
    }

    @Test
    public void should_save_user(){
        int insertedUserAdmin = userRepository.insert(queries.InsertUser, userAdmin);
        int insertedUser = userRepository.insert(queries.InsertUser, user);

        assertTrue( insertedUserAdmin > 0 && insertedUser > 0);
    }

    @Test
    public void should_get_user_admin_by_id(){
        User returnedUser = userRepository.get(queries.GetUser, userAdmin.getId());
        assertThat(returnedUser.getId()).isEqualTo(userAdmin.getId());
    }

    @Test
    public void should_get_user_by_id(){
        User returnedUser = userRepository.get(queries.GetUser, user.getId());
        assertThat(returnedUser.getId()).isEqualTo(user.getId());
    }

    @Test
    public void should_get_all_users(){
        List<User> listUsers = userRepository.getAll(queries.GetAllUsers);
        assertThat(listUsers).isNotEmpty();
    }

    @Test
    public void should_get_all_users_by_type_admin(){
        List<User> listUsers = userRepository.getAll(queries.GetAllUsers, UserType.Admin);
        assertThat(listUsers).isNotEmpty();
    }

    @Test
    public void should_get_all_users_by_type_who_are_not_admin(){
        List<User> listUsers = userRepository.getAll(queries.GetAllUsers, UserType.User);
        assertThat(listUsers).isNotEmpty();
    }

}
