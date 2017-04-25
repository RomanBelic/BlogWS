package com.esgi.al1.blogws.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;

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

    public static int writeToFile(String filename, String message){
        byte wbytes[] = message.getBytes();
        try (OutputStream os = new FileOutputStream(filename, true)){
            os.write(wbytes);
        }catch(Exception e){
            Log.err(e.getMessage());
            return 0;
        }
        return wbytes.length;
    }
}

