package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.utils.GeneratedStatement;

import java.util.List;

/**
 * Created by Romaaan on 25/03/2017.
 */
public interface IUserRepository {

    List<User> getAll();
    int updateUser(GeneratedStatement gst, int id);
    int deleteUser(int id);
    int insertUser(GeneratedStatement gst);
    List<User> getAllByLimit(int start, int end);
    User getUserById(int id);

}
