package com.esgi.al1.blogws.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Romaaan on 18/03/2017.
 */
public class Log {

    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    public static void err(String msg, Object...args){
        logger.error(String.format(msg,args));
    }

    public static void i(String msg, Object...args){
        logger.info(String.format(msg,args));
    }

}
