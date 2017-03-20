package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.models.Post;

import java.util.List;

/**
 * Created by Romaaan on 19/03/2017.
 */
public interface IPostRepository {
    List<Post> getAll();
}
