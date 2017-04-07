package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.UserRepository;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.utils.GeneratedQuery;
import com.esgi.al1.blogws.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romaaan on 25/03/2017.
 */
@Service
public class UserControllerService extends AbstractControllerService<User>{

    private GeneratedQuery generateSelect (Object...args){
        return new GeneratedQuery(this.queries.GetAllUsers, args);
    }

    private GeneratedQuery generateLimitedSelect (Object...args){
        return new GeneratedQuery(this.queries.GetAllUsersLimit, args);
    }

    private GeneratedQuery generatedDelete (Object...args){
        return new GeneratedQuery(this.queries.DeleteUser, args);
    }

    private GeneratedQuery generateGetUser (Object...args){
        return new GeneratedQuery(this.queries.GetUser, args);
    }

    private GeneratedQuery generateUpdateUser (HashMap<String,Object> sqlParams, Object...args){
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
            fullQuery = String.format(this.queries.UpdateUser + "%s %s", updStr, queries.WhereUserId);
        }
        generatedQuery.setFullQuery(fullQuery);
        generatedQuery.setSqlValuesArray(paramArr);
        return generatedQuery;
    }

    private GeneratedQuery generateInsertUser (HashMap<String,Object> sqlParams, Object...args){
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

            fullquery = String.format(this.queries.InsertUser + "%s %s", insCols, insVals);
        }
        generatedQuery.setFullQuery(fullquery);
        generatedQuery.setSqlValuesArray(paramArr);
        return generatedQuery;
    }

    @Autowired
    public UserControllerService(UserRepository userRepository, Queries queries) {
        super(userRepository, queries);
        selectAllQueryGenerator = this::generateSelect;
        selectLimitedQueryGenerator = this::generateLimitedSelect;
        deleteQueryGenerator = this::generatedDelete;
        selectSingleQueryGenerator = this::generateGetUser;
        updateQueryGenerator = this::generateUpdateUser;
        insertQueryGenerator = this::generateInsertUser;
    }

   
}
