package com.esgi.al1.blogws;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.dao.UserRepository;
import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.models.UserType;
import com.esgi.al1.blogws.utils.DataBase;
import com.esgi.al1.blogws.utils.Queries;
import com.esgi.al1.blogws.utils.SqlConfig;
import com.esgi.al1.blogws.utils.SqlConfigBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import com.esgi.al1.blogws.models.User;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Chris GAGOUDE on 01/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("Production")
@TestExecutionListeners({})
public class UserRepositoryIT {
    private MySqlConnector connector;
    private SqlConfig sqlConfig;
    UserRepository userRepository;

    public static final String JDBC_DRIVER = "jdbc:mysql";
    public static final String LOGIN = "root";
    public static final String PASS = "mysqlroot";
    public static final String HOST = "localhost";
    public static final String PORT = "3306";

    DataBase dataBase;

    @Autowired
    Queries queries;
    Connection connection = null;

    User userAdmin;
    User user;

    @Before
    public void init(){
        sqlConfig = new SqlConfigBuilder()
                .buildLogin(LOGIN)
                .buildPass(PASS)
                .buildDriver(JDBC_DRIVER)
                .buildHost(HOST)
                .buildPort(PORT).build();

        connector = new MySqlConnector(sqlConfig);
        userRepository = new UserRepository(connector);

        dataBase = new DataBase("blogws");
        dataBase.getPostTable();
        queries = new Queries(dataBase);

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
        String queryInsert  = "INSERT INTO blogws.user(IdType, Name, LastName, DateCreated) "+
                "VALUES (?,?,?,?) ";

        int insertedUser = userRepository.insert(queryInsert, 2, user.getName(), user.getLastName(), new Date());

        assertTrue(  insertedUser > 0);
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
