package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.GeneratedQuery;
import com.esgi.al1.blogws.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 26/03/2017.
 */
@Service
public class PostControllerService extends AbstractControllerService<Post>  {


    @Autowired
    public PostControllerService(PostRepository postRepository, Queries queries) {
        super(postRepository, queries);
    }

    public List<Post> getAllPosts() {
        return repository.getAll(queries.GetAllPosts);
    }


    public Post getPost(int id) {
        return repository.get(queries.GetPost, id);
    }


    public List<Post> getAllPosts(int start, int end) {
        return repository.getAll(queries.GetAllPostsLimit, start,end);
    }

    public int updatePost(HashMap<String, Object> sqlParams, int id) {
        GeneratedQuery gq = updateGenerator.generate(sqlParams);
        String query = String.format(queries.UpdatePost + "%s %s", gq.getParamStr(), queries.WherePostId);
        return repository.updateOrDelete(query, gq.getParamArr(), id);
    }

    public int deletePost(int id) {
        return repository.updateOrDelete(queries.DeletePost, id);
    }

    public int insertPost(HashMap<String, Object> sqlParams) {
        GeneratedQuery gq = insertGenerator.generate(sqlParams);
        String query = String.format(queries.InsertPost + "%s", gq.getParamStr());
        return repository.insert(query, gq.getParamArr());
    }
}
