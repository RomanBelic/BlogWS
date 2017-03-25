package com.esgi.al1.blogws.interfaces;
import com.esgi.al1.blogws.models.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 25/03/2017.
 */
public interface IUserService {

    List<User> getAllUsers ();
    User getUser(int id);
    List<User> getAllUsers(int start, int end);
    int updateUser(HashMap<String,Object> sqlParams, int id);
    int deleteUser(int id);
    int insertUser(HashMap<String,Object> sqlParams);
}
