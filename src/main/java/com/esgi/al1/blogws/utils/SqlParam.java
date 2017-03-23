package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 20/03/2017.
 */
public class SqlParam {
    private final int index;
    private final Object value;

    public SqlParam (int index, Object value){
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public Object getValue() {
        return value;
    }
}
