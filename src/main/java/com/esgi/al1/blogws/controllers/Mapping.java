package com.esgi.al1.blogws.controllers;

/**
 * Created by Romaaan on 18/03/2017.
 */
class Mapping {

    public static final String PostAPI ="/Post";
    public static final String UserAPI ="/User";
    public static final String SystemAPI ="/System";

    public static final String GetAll ="/GetAll";
    public static final String FindById ="/Get/{id}";
    public static final String UpdateById ="/Update/{id}";
    public static final String DeleteById = "/Delete/{id}";
    public static final String Insert = "/Insert";
    public static final String DownloadImage = "/DownloadImage/{id}";
    public static final String ShowImage = "/ShowImage/{id}";
    public static final String SystemInfo ="/SystemInfo";
    public static final String ServerInfo ="/ServerInfo";
    public static final String SetProperties ="/SetProperties/{userId}";

    public static class APITags {
        public static final String PostAPITag = "PostAPI";
        public static final String UserAPITag = "UserAPI";
        public static final String SysAPITag = "SysAPI";
    }

    public static class APIActions {
        public static final String getPosts = "getPost";
        public static final String updatePost = "updatePost";
        public static final String insertPost = "insertPost";
        public static final String deletePost = "deletePost";

        public static final String downloadImage = "downloadImage";

        public static final String getUsers = "getUser";
        public static final String updateUser = "updateUser";
        public static final String insertUser = "insertUser";
        public static final String deleteUser = "deleteUser";

        public static final String getSystemInfo = "SysInfo";
        public static final String getServInfo = "ServInfo";
        public static final String setSysProps = "SetSysProps";
    }
}
