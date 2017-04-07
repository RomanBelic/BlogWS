package com.esgi.al1.blogws.utils;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Romaaan on 20/03/2017.
 */


public class DBUtils {

    public static byte[] ConvertBlob(Blob blob) throws SQLException {
        return (blob != null && blob.length() > 0) ? blob.getBytes(1, (int)blob.length()) : new byte[0];
    }

    public static byte[] ConvertInputStream(InputStream is) throws IOException {
        byte[] buffer = new byte[4096];
        byte[] output;
        int nRead;
        ByteArrayOutputStream bao = new ByteArrayOutputStream(4096);
        while ((nRead = is.read(buffer,0,buffer.length)) > 0){
            bao.write(buffer,0, nRead);
        }
        output = bao.toByteArray();
        bao.close();
        return output;
    }


}
