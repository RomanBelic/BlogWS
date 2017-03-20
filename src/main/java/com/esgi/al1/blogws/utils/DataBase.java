package com.esgi.al1.blogws.utils;

import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Romaaan on 20/03/2017.
 */
public class DataBase {

    private static final DataTable TP_instance = new PostTable("post","p");

    public static byte[] ConvertBlob(Blob blob) throws SQLException {
        byte[] bytes = (blob != null && blob.length() > 0) ? blob.getBytes(1, (int)blob.length()) : new byte[0];
        return bytes;
    }

    public static class Queries {
        public static final String getAllPosts = "SELECT * FROM  blogws." + TP_instance.name + " Order By " + PostTable.Columns.Date + " DESC";
    }


}
