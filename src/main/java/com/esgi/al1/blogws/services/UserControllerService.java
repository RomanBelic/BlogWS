package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.UserRepository;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.utils.GeneratedQuery;
import com.esgi.al1.blogws.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 25/03/2017.
 */
@Service
public class UserControllerService extends AbstractControllerService<User>{

    @Autowired
    public UserControllerService(UserRepository userRepository, Queries queries) {
        super(userRepository, queries);
    }

    public List<User> getAllUsers() {
        return repository.getAll(queries.GetAllUsers);
    }

    public User getUser(int id) {
        return repository.get(queries.GetUser, id);
    }

    public List<User> getAllUsers(int start, int end) {
        return repository.getAll(queries.GetAllUsersLimit, start,end);
    }

    public int updateUser(HashMap<String, Object> sqlParams, int id) {
        GeneratedQuery generatedQuery = updateGenerator.generate(sqlParams);
        String query = String.format(queries.UpdateUser + "%s %s", generatedQuery.getParamStr(), queries.WhereUserId);
        return repository.updateOrDelete(query, generatedQuery.getParamArr(), id);
    }

    public int deleteUser(int id) {
        return repository.updateOrDelete(queries.DeleteUser, id);
    }

    public int insertUser(HashMap<String, Object> sqlParams) {
        GeneratedQuery generatedQuery = insertGenerator.generate(sqlParams);
        String query = String.format(queries.InsertUser + "%s", generatedQuery.getParamStr());
        return repository.insert(query, generatedQuery.getParamArr());
    }
}
