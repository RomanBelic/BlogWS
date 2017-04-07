package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 20/03/2017.
 */
public class GeneratedQuery {

    private Object[] paramArr;
    private String fullQuery;

    public void setParamArr(Object[] paramArr) {
        this.paramArr = paramArr;
    }

    public void setFullQuery(String fullQuery) {
        this.fullQuery = fullQuery;
    }

    public GeneratedQuery() {

    }

    public GeneratedQuery(String fullQuery, Object[] paramArr) {
        this.fullQuery = fullQuery;
        this.paramArr = paramArr;
    }
    public Object[] getParamArr() {
        return paramArr;
    }

    public String getFullQuery() {
        return fullQuery;
    }

}
