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

    public final String WhereUserId;
    public final String GetAllUsers;
    public final String UpdateUser;
    public final String DeleteUser;
    public final String InsertUser;
    public final String GetAllUsersLimit;
    public final String GetUser;
    public final String GetUserByLoginPassword;

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
        this.GetAllPostsLimit = this.GetAllPosts.concat(" LIMIT ?,?");

        this.WhereUserId = (" WHERE ").concat(UserTable.Columns.Id.getName()).concat("=?");
        this.GetAllUsers = new QueryBuilder().buildCommand("SELECT * FROM").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getUserTable().getName()).
                buildWhere("WHERE 1=1").
                buildOrderBy("Order By " + UserTable.Columns.DateCreated.getName() + " DESC").build();
        this.UpdateUser = new QueryBuilder().buildCommand("UPDATE").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getUserTable().getName()).
                buildUpdateArguments("SET").build();
        this.DeleteUser = new QueryBuilder().buildCommand("DELETE FROM").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getUserTable().getName()).
                buildWhere(WhereUserId).build();
        this.InsertUser = new QueryBuilder().buildCommand("INSERT INTO").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getUserTable().getName()).build();
        this.GetUser = new QueryBuilder().buildCommand("SELECT * FROM").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getUserTable().getName()).
                buildWhere(WhereUserId).build();
        this.GetAllUsersLimit = this.GetAllUsers.concat(" LIMIT ?,?");
        this.GetUserByLoginPassword = new QueryBuilder().buildCommand("SELECT * FROM").
                buildDatabase(dataBase.getName()).
                buildTable(dataBase.getUserTable().getName()).
                buildWhere("WHERE Login=? AND Password=?").build();
    }

}
