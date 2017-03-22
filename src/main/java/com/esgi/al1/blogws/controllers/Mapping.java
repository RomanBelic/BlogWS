package com.esgi.al1.blogws.controllers;

/**
 * Created by Romaaan on 18/03/2017.
 */
public class Mapping {

    public static final String PostAPI ="/Post";
    public static final String AllPosts ="/GetAll";
    public static final String FindPost ="/GetPost/{id}";
    public static final String UpdatePost ="/UpdatePost/{id}";
    public static final String DeletePost = "/DeletePost/{id}";
    public static final String InsertPost = "/InsertPost";

    public static class APITags {
        public static final String PostAPITag = "PostAPI";
    }

    public static class APIActions {
        public static final String getPosts = "getPosts";
        public static final String updatePost = "updatePost";
        public static final String insertPost = "insertPost";
        public static final String deletePost = "deletePost";
    }
}
