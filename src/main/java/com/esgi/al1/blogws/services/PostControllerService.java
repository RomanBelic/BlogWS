package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.PostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.GeneratedQuery;
import com.esgi.al1.blogws.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romaaan on 26/03/2017.
 */
@Service
public class PostControllerService extends AbstractControllerService<Post>  {


    private GeneratedQuery generateSelect (Object...args){
        return new GeneratedQuery(this.queries.GetAllPosts, args);
    }

    private GeneratedQuery generateLimitedSelect (Object...args){
        return new GeneratedQuery(this.queries.GetAllPostsLimit, args);
    }

    private GeneratedQuery generatedDelete (Object...args){
        return new GeneratedQuery(this.queries.DeletePost, args);
    }

    private GeneratedQuery generateGetPost (Object...args){
        return new GeneratedQuery(this.queries.GetPost, args);
    }

    private GeneratedQuery generateUpdatePost (HashMap<String,Object> sqlParams, Object...args){
        GeneratedQuery generatedQuery = new GeneratedQuery();
        Object[] paramArr = new Object[sqlParams.size() + args.length];
        String updStr = "";
        String fullQuery = "";
        if (sqlParams.size() > 0) {
            int index = 0;
            for (Map.Entry<String, Object> e : sqlParams.entrySet()) {
                updStr += "," + e.getKey() + "=?";
                paramArr[index] = e.getValue();
                index++;
            }
            for (Object arg : args){
                paramArr[index] = arg;
                index++;
            }

            index = updStr.indexOf(',');
            updStr = (index != -1 && updStr.length() > 0) ? updStr.substring(index + 1, updStr.length()) : updStr;
            fullQuery = String.format(this.queries.UpdatePost + "%s %s", updStr, queries.WherePostId);
        }
        generatedQuery.setFullQuery(fullQuery);
        generatedQuery.setSqlValuesArray(paramArr);
        return generatedQuery;
    }

    private GeneratedQuery generateInsertPost (HashMap<String,Object> sqlParams, Object...args){
        GeneratedQuery generatedQuery = new GeneratedQuery();
        Object[] paramArr = new Object[sqlParams.size() + args.length];
        String fullquery = "";
        String insCols = "";
        String insVals = "";
        if (sqlParams.size() > 0) {
            int index = 0;
            for (Map.Entry<String, Object> e : sqlParams.entrySet()) {
                insCols += "," + e.getKey();
                insVals += ",?";
                paramArr[index] = e.getValue();
                index++;
            }
            for (Object arg : args){
                paramArr[index] = arg;
                index++;
            }

            index = insCols.indexOf(',');
            insCols = (index != -1 && insCols.length() > 0) ? insCols.substring(index + 1, insCols.length()) : insCols;
            insCols = " (".concat(insCols).concat(")");

            index = insVals.indexOf(',');
            insVals = (index != -1 && insVals.length() > 0) ? insVals.substring(index + 1, insVals.length()) : insVals;
            insVals = " VALUES (".concat(insVals).concat(")");

            fullquery = String.format(this.queries.InsertPost + "%s %s", insCols, insVals);
        }
        generatedQuery.setFullQuery(fullquery);
        generatedQuery.setSqlValuesArray(paramArr);
        return generatedQuery;
    }

    @Autowired
    public PostControllerService(PostRepository postRepository, Queries queries) {
        super(postRepository, queries);
        selectAllQueryGenerator = this::generateSelect;
        selectLimitedQueryGenerator = this::generateLimitedSelect;
        deleteQueryGenerator = this::generatedDelete;
        selectSingleQueryGenerator = this::generateGetPost;
        updateQueryGenerator = this::generateUpdatePost;
        insertQueryGenerator = this::generateInsertPost;
    }
}
