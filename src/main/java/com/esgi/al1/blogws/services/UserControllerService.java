package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.UserRepository;
import com.esgi.al1.blogws.interfaces.IUserRepository;
import com.esgi.al1.blogws.interfaces.IUserService;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.utils.DBUtils;
import com.esgi.al1.blogws.utils.GeneratedStatement;
import com.esgi.al1.blogws.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 25/03/2017.
 */
@Service
public class UserControllerService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserControllerService(UserRepository userRepository, Queries queries) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers(int start, int end) {
        return userRepository.getAllByLimit(start, end);
    }

    @Override
    public int updateUser(HashMap<String, Object> sqlParams, int id) {
        GeneratedStatement gst = DBUtils.generateUpdateStatement(sqlParams);
        return userRepository.updateUser(gst, id);
    }

    @Override
    public int deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public int insertUser(HashMap<String, Object> sqlParams) {
        GeneratedStatement gst = DBUtils.generateInsertStatement(sqlParams);
        return userRepository.insertUser(gst);
    }
}
