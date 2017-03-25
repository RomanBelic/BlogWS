package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.interfaces.IPostControllerService;
import com.esgi.al1.blogws.interfaces.IPostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 19/03/2017.
 * comitted by Mokrane
 */
//Grosse usine à gaz

@Service
public class PostControllerService implements IPostControllerService {

    private final IPostRepository postRepository;

    @Autowired
    public PostControllerService(PostRepository postRepository, Queries queries) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    @Override
    public Post getPost(int id) {
       return postRepository.getPostById(id);
    }

    @Override
    public List<Post> getAllPosts(int start, int end) {
        return postRepository.getAllByLimit(start, end);
    }

    @Override
    public int updatePost(HashMap<String, Object> sqlParams, int id) {
        GeneratedStatement gst = DBUtils.generateUpdateStatement(sqlParams);
        return postRepository.updatePost(gst, id);
    }

    @Override
    public int deletePost(int id) {
        return postRepository.deletePost(id);
    }

    @Override
    public int insertPost(HashMap<String, Object> sqlParams) {
        GeneratedStatement gst = DBUtils.generateInsertStatement(sqlParams);
        return postRepository.insertPost(gst);
    }

}
