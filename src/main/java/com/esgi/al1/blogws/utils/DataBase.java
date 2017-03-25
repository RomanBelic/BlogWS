package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 20/03/2017.
 */

public final class DataBase {

    private String name;

    private final DataTable postTable = new PostTable("post","p");

    private final DataTable userTable = new PostTable("user","u");

    public DataBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DataTable getPostTable() {
        return postTable;
    }

    public DataTable getUserTable() {
        return userTable;
    }

    public DataBase (){

    }

    @Override
    public String toString(){return this.name;}
}
