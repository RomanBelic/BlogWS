package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.AbstractRepository;
import com.esgi.al1.blogws.exceptions.NotYetImplementedException;
import com.esgi.al1.blogws.interfaces.QueryGenerators.IGenerateDeleteQuery;
import com.esgi.al1.blogws.interfaces.QueryGenerators.IGenerateInsertQuery;
import com.esgi.al1.blogws.interfaces.QueryGenerators.IGenerateSelectQuery;
import com.esgi.al1.blogws.interfaces.QueryGenerators.IGenerateUpdateQuery;
import com.esgi.al1.blogws.models.ServiceModel;
import com.esgi.al1.blogws.utils.GeneratedQuery;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.Queries;
import com.esgi.al1.blogws.utils.ServiceModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 26/03/2017.
 */
@Service
public abstract class AbstractControllerService<T> {

    protected final AbstractRepository<T> repository;
    protected final Queries queries;

    protected IGenerateUpdateQuery updateQueryGenerator = (HashMap<String,Object> sqlParams, Object...args) -> {
        throw new NotYetImplementedException("update generator not implemented");
    };

    protected IGenerateInsertQuery insertQueryGenerator = (HashMap<String,Object> sqlParams) -> {
        throw new NotYetImplementedException("insert generator not implemented");
    };

    protected IGenerateSelectQuery selectAllQueryGenerator = (Object...args) -> {
        throw new NotYetImplementedException("select generator not implemented");
    };

    protected IGenerateDeleteQuery deleteQueryGenerator = (Object...args) -> {
        throw new NotYetImplementedException("delete generator not implemented");
    };

    protected IGenerateSelectQuery selectLimitedQueryGenerator = (Object...args) -> {
        throw new NotYetImplementedException("select limited generator not implemented");
    };

    protected IGenerateSelectQuery selectSingleQueryGenerator = (Object...args) -> {
        throw new NotYetImplementedException("select single generator not implemented");
    };

    @Autowired
    public AbstractControllerService(AbstractRepository<T> repository, Queries queries) {
        this.repository = repository;
        this.queries = queries;
    }

    protected <T> ServiceModel<T> generateServiceResponse (T content, int httpStatus){
        return new ServiceModelBuilder<T>().
                buildContent(content).
                buildHttpCode(httpStatus).
                build();
    }

    public List<T> getAll() {
        List<T> result = null;
        try {
            GeneratedQuery genQuery = selectAllQueryGenerator.generate();
            result = repository.getAll(genQuery.getFullQuery());
        }catch (NotYetImplementedException e){
            Log.err(e.getMessage());
        }
        return result;
    }

    public T get(int id) {
        T result = null;
        try {
            GeneratedQuery genQuery = selectSingleQueryGenerator.generate(id);
            result = repository.get(genQuery.getFullQuery(), genQuery.getSqlValuesArray());
        }catch (NotYetImplementedException e){
            Log.err(e.getMessage());
        }
        return result;
    }

    public List<T> getAllLimit(int start, int end) {
        List<T> result = null;
        try {
            GeneratedQuery genQuery = selectLimitedQueryGenerator.generate(start, end);
            result = repository.getAll(genQuery.getFullQuery(), genQuery.getSqlValuesArray());
        }catch (NotYetImplementedException e){
            Log.err(e.getMessage());
        }
        return result;
    }

    public int update(HashMap<String, Object> sqlParams, int id) {
        try {
            GeneratedQuery genQuery = updateQueryGenerator.generate(sqlParams, id);
            return repository.updateOrDelete(genQuery.getFullQuery(), genQuery.getSqlValuesArray());
        }catch (NotYetImplementedException e){
            return -1;
        }
    }

    public int delete(int id) {
        try {
            GeneratedQuery genQuery = deleteQueryGenerator.generate(id);
            return repository.updateOrDelete(genQuery.getFullQuery(), genQuery.getSqlValuesArray());
        }catch (NotYetImplementedException e){
            return -1;
        }
    }

    public int insert(HashMap<String, Object> sqlParams) {
        try {
            GeneratedQuery genQuery = insertQueryGenerator.generate(sqlParams);
            return repository.insert(genQuery.getFullQuery(), genQuery.getSqlValuesArray());
        }catch (NotYetImplementedException e){
           return -1;
        }
    }

}
