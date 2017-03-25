package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 24/03/2017.
 */
public class DataBaseBuilder {

    private String name;
    private DataTable postTable;

    public DataBaseBuilder buildName(String name){
        this.name = name;
        return this;
    }

    public DataBaseBuilder buildPostTable(String name, String alias){
        this.postTable = new PostTable(name, alias);
        return this;
    }

    public String getName() {
        return name;
    }

    public DataTable getPostTable() {
        return postTable;
    }

    public DataBase build(){
        return new DataBase(this);
    }
}
