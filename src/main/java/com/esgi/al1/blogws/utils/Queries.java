package com.esgi.al1.blogws.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Romaaan on 24/03/2017.
 */

@Component
public class Queries {

    public final String WherePostId;
    public final String GetAllPosts;
    public final String UpdatePost;
    public final String DeletePost;
    public final String InsertPost;
    public final String GetAllPostsLimit;
    public final String GetPost;

    @Autowired
    public Queries (DataBase dataBase){
        this.WherePostId = (" WHERE ").concat(PostTable.Columns.Id.getName()).concat("=?");
        this.GetAllPosts = new QueryBuilder().buildCommand("SELECT * FROM").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getPostTable().getName()).
                buildWhere("WHERE 1=1").
                buildOrderBy("Order By " + PostTable.Columns.Date.getName() + " DESC").build();
        this.UpdatePost = new QueryBuilder().buildCommand("UPDATE").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getPostTable().getName()).
                buildUpdateArguments("SET").build();
        this.DeletePost = new QueryBuilder().buildCommand("DELETE FROM").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getPostTable().getName()).
                buildWhere(WherePostId).build();
        this.InsertPost = new QueryBuilder().buildCommand("INSERT INTO").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getPostTable().getName()).build();
        this.GetPost = new QueryBuilder().buildCommand("SELECT * FROM").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getPostTable().getName()).
                buildWhere(WherePostId).build();
        this.GetAllPostsLimit = GetAllPosts.concat(" LIMIT ?,?");
    }

}
