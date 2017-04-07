package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 20/03/2017.
 */
public class GeneratedQuery {

    private Object[] sqlValuesArray;
    private String fullQuery;

    public void setSqlValuesArray(Object[] sqlValuesArray) {
        this.sqlValuesArray = sqlValuesArray;
    }

    public void setFullQuery(String fullQuery) {
        this.fullQuery = fullQuery;
    }

    public GeneratedQuery() {

    }

    public GeneratedQuery(String fullQuery, Object[] sqlValuesArray) {
        this.fullQuery = fullQuery;
        this.sqlValuesArray = sqlValuesArray;
    }
    public Object[] getSqlValuesArray() {
        return sqlValuesArray;
    }

    public String getFullQuery() {
        return fullQuery;
    }

}
