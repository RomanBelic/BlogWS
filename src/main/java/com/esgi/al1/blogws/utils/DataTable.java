package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 20/03/2017.
 */
public abstract class DataTable {

    public DataTable(String name, String alias){
        this.name = name;
        this.alias = alias;
    }

    private final String name;
    private final String alias;

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
