package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.interfaces.IPostControllerService;
import com.esgi.al1.blogws.interfaces.IPostRepository;
import com.esgi.al1.blogws.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 19/03/2017.
 */

@Service
public class PostControllerService implements IPostControllerService {

    private final IPostRepository postRepository;

    @Autowired
    public PostControllerService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    @Override
    public Post getPost(int id) {
        return null;
    }

    @Override
    public List<Post> getAllPost(int start, int end) {
        return null;
    }

    @Override
    public int updatePost(HashMap<String, Object> sqlParams, int id) {
        return 0;
    }

}
