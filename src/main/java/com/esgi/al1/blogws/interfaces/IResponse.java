package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.models.WebModel;

/**
 * Created by Romaaan on 18/03/2017.
 */
public interface IResponse<T> {

    T getResponseBody();

    interface IWebModelResponse <T>{
        WebModel<T> convertResponse (IResponse<T> response);
    }
}
