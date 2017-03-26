package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.AbstractRepository;
import com.esgi.al1.blogws.interfaces.IGenerateParametrizedQuery;
import com.esgi.al1.blogws.utils.GeneratedQuery;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romaaan on 26/03/2017.
 */
@Service
public abstract class AbstractControllerService<T> {

    protected final AbstractRepository<T> repository;
    protected final Queries queries;

    protected final IGenerateParametrizedQuery updateGenerator = (HashMap<String, Object> sqlParams)-> {
        Object[] paramArr = new Object[sqlParams.size()];
        String updStr = "";
        if (sqlParams.size() > 0) {
            int index = 0;
            for (Map.Entry<String, Object> e : sqlParams.entrySet()) {
                updStr += "," + e.getKey() + "=?";
                paramArr[index] = e.getValue();
                index++;
            }
            index = updStr.indexOf(',');
            updStr = (index != -1 && updStr.length() > 0) ? updStr.substring(index + 1, updStr.length()) : updStr;
        }
        return new GeneratedQuery(updStr, paramArr);
    };

    protected final IGenerateParametrizedQuery insertGenerator = (HashMap<String, Object> sqlParams)-> {
        Object[] paramArr = new Object[sqlParams.size()];
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
            index = insCols.indexOf(',');
            insCols = (index != -1 && insCols.length() > 0) ? insCols.substring(index + 1, insCols.length()) : insCols;
            insCols = " (".concat(insCols).concat(")");

            index = insVals.indexOf(',');
            insVals = (index != -1 && insVals.length() > 0) ? insVals.substring(index + 1, insVals.length()) : insVals;
            insVals = " VALUES (".concat(insVals).concat(")");
        }
        return new GeneratedQuery(insCols.concat(insVals), paramArr);
    };

    @Autowired
    public AbstractControllerService(AbstractRepository<T> repository, Queries queries) {
        this.repository = repository;
        this.queries = queries;
    }
}
