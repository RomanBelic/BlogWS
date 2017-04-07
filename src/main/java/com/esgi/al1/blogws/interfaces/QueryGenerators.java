package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.exceptions.NotYetImplementedException;
import com.esgi.al1.blogws.utils.GeneratedQuery;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * Created by Romaaan on 26/03/2017.
 */
public class QueryGenerators {

    public interface IGenerateUpdateQuery {
        GeneratedQuery generate(@NotNull HashMap<String, Object> keyValues, Object... params) throws NotYetImplementedException;
    }

    public interface IGenerateInsertQuery {
        GeneratedQuery generate(@NotNull HashMap<String, Object> keyValues) throws NotYetImplementedException;
    }

    public interface IGenerateSelectQuery {
        GeneratedQuery generate(Object... params) throws NotYetImplementedException;
    }

    public interface IGenerateDeleteQuery {
        GeneratedQuery generate(Object... params) throws NotYetImplementedException;
    }

}
