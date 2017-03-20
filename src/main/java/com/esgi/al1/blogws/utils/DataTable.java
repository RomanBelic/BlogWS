package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 20/03/2017.
 */
public abstract class DataTable {

    public DataTable(String name, String alias){
        this.name = name;
        this.alias = alias;
    }

    protected final String name;
    protected final String alias;

    @Override
    public String toString(){
        return this.name;
    }
}
