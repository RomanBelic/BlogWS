package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.GeneratedStatement;
import com.esgi.al1.blogws.utils.SqlParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 19/03/2017.
 */
public interface IPostRepository {
    List<Post> getAll();
    int updatePost(GeneratedStatement gst, int id);
    int deletePost(int id);
    int insertPost(GeneratedStatement gst);
    List<Post> getAllByLimit(int start, int end);
    Post getPostById(int id);
}
