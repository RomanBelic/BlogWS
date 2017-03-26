package com.esgi.al1.blogws.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Romaaan on 26/03/2017.
 */
public interface IResultReader<T> {
    T readData(ResultSet rs) throws SQLException;
}
