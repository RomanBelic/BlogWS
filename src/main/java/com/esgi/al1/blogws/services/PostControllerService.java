package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.interfaces.IPostControllerService;
import com.esgi.al1.blogws.interfaces.IPostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.GeneratedStatement;
import com.esgi.al1.blogws.utils.SqlParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.esgi.al1.blogws.interfaces.IGeneratePreparedQuery;
/**
 * Created by Romaaan on 19/03/2017.
 * comitted by Mokrane
 */

@Service
public class PostControllerService implements IPostControllerService {

    private final IPostRepository postRepository;

    private final IGeneratePreparedQuery updategenerator = (HashMap<String,Object> sqlParams) -> {
        List<SqlParam> lstp = new ArrayList<>(sqlParams.size());
        String updStr = "";
        int index = 1;
        for (Map.Entry<String,Object> e : sqlParams.entrySet()){
            updStr += "," + e.getKey() + "=?";
            lstp.add(new SqlParam(index, e.getValue()));
            index ++;
        }
        index = updStr.indexOf(',');
        updStr = (index != -1 && updStr.length() > 0) ? updStr.substring(index + 1, updStr.length()) : updStr;
        return new GeneratedStatement(lstp, updStr);
    };

    private final IGeneratePreparedQuery insertgenerator = (HashMap<String,Object> sqlParams) -> {
        List<SqlParam> lstp = new ArrayList<>(sqlParams.size());
        String insCols = "";
        String insVals = "";
        int index = 1;
        for (Map.Entry<String,Object> e : sqlParams.entrySet()){
            insCols += "," + e.getKey();
            insVals += ",?";
            lstp.add(new SqlParam(index, e.getValue()));
            index ++;
        }
        index = insCols.indexOf(',');
        insCols = (index != -1 && insCols.length() > 0) ? insCols.substring(index + 1, insCols.length()) : insCols;
        insCols = " (".concat(insCols).concat(")");

        index = insVals.indexOf(',');
        insVals = (index != -1 && insVals.length() > 0) ? insVals.substring(index + 1, insVals.length()) : insVals;
        insVals = " VALUES (".concat(insVals).concat(")");

        return new GeneratedStatement(lstp, insCols.concat(insVals));
    };

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
       return postRepository.getPostById(id);
    }

    @Override
    public List<Post> getAllPost(int start, int end) {
        return postRepository.getAllByLimit(start, end);
    }

    @Override
    public int updatePost(HashMap<String, Object> sqlParams, int id) {
        GeneratedStatement gst = updategenerator.generateStatement(sqlParams);
        return postRepository.updatePost(gst, id);
    }

    @Override
    public int deletePost(int id) {
        return postRepository.deletePost(id);
    }

    @Override
    public int insertPost(HashMap<String, Object> sqlParams) {
        GeneratedStatement gst = insertgenerator.generateStatement(sqlParams);
        return postRepository.insertPost(gst);
    }

}
