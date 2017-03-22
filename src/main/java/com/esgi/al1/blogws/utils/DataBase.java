package com.esgi.al1.blogws.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romaaan on 20/03/2017.
 */
public final class DataBase {

    private static final DataTable PT_instance = new PostTable("post","p");

    public static class Queries {
        public static final String wherePostId = " WHERE ".concat(PostTable.Columns.Id.getName()).concat("=?");
        public static final String getAllPosts = "SELECT * FROM  ".concat("blogws.").
                                                        concat(PT_instance.name).
                                                        concat(" Order By ").
                                                        concat(PostTable.Columns.Date.getName()).
                                                        concat(" DESC");
        public static final String updatePost = "UPDATE ".concat("blogws.").concat(PT_instance.name).concat(" SET ");
        public static final String deletePost = "DELETE FROM ".concat("blogws.").
                                                        concat(PT_instance.name).
                                                        concat(wherePostId);
        public static final String insertPost = "INSERT INTO ".concat("blogws.").concat(PT_instance.name);
        public static final String getAllPostsLimit = "SELECT * FROM  ".concat("blogws.").
                                                        concat(PT_instance.name).
                                                        concat(" Order By ").
                                                        concat(PostTable.Columns.Date.getName()).
                                                        concat(" DESC ").
                                                        concat("LIMIT ?,?");
        public static final String getPost = "SELECT * FROM blogws.".concat(PT_instance.name).concat(wherePostId);
    }

}
