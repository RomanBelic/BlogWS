package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.models.Post;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 19/03/2017.
 */
public interface IPostControllerService {

    List<Post> getAllPosts ();
    Post getPost(int id);
    List<Post> getAllPosts(int start, int end);
    int updatePost(HashMap<String,Object> sqlParams, int id);
    int deletePost(int id);
    int insertPost(HashMap<String,Object> sqlParams);
}
