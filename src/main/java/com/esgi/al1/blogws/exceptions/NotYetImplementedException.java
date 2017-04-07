package com.esgi.al1.blogws.exceptions;

import java.io.Serializable;

/**
 * Created by Romaaan on 07/04/2017.
 */
public class NotYetImplementedException extends Throwable implements Serializable{

    public NotYetImplementedException(String msg){
        super(msg);
    }

}
