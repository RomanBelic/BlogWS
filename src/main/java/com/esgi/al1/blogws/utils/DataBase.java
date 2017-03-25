package com.esgi.al1.blogws.utils;

import org.springframework.stereotype.Component;

/**
 * Created by Romaaan on 20/03/2017.
 */

public final class DataBase {

    private String name;

    public String getName() {
        return name;
    }

    private DataTable postTable;

    public DataTable getPostTable() {
        return postTable;
    }

    public DataBase (){

    }
    public DataBase (DataBaseBuilder builder){
        this.name = builder.getName();
        this.postTable = builder.getPostTable();
    }

    @Override
    public String toString(){return this.name;}
}
