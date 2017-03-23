package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.utils.GeneratedStatement;

import java.util.HashMap;

/**
 * Created by Romaaan on 20/03/2017.
 */

public interface IGeneratePreparedQuery {
    GeneratedStatement generateStatement(HashMap<String, Object> sqlParams);
}


