package com.esgi.al1.blogws.utils;

import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

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

    private static void setPreparedParameter(int index, Object value, PreparedStatement st) throws SQLException {
        if (value instanceof Integer)
            st.setInt(index, (int)value);
        if (value instanceof String)
            st.setString(index, String.valueOf(value));
        if (value instanceof Date)
            st.setDate(index, new java.sql.Date(((Date) value).getTime()));
        if (value instanceof Long)
            st.setLong(index, (long)value);
        if (value instanceof Float)
            st.setFloat(index, (float)value);
        if (value instanceof Double)
            st.setDouble(index, (double)value);
        if (value instanceof byte[]) {
            ByteArrayInputStream bai = new ByteArrayInputStream((byte[])value);
            st.setBlob(index, bai);
        }
        if (value == null)
            st.setNull(index, Types.NULL);
    }

    public static void copySqlParamsToStatement(List<SqlParam> lstP, PreparedStatement ps) throws SQLException {
        for(SqlParam p : lstP){
            setPreparedParameter(p.getIndex(), p.getValue(), ps);
        }
    }

}