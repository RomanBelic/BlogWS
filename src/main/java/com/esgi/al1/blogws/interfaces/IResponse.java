package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.models.ServiceModel;
import com.esgi.al1.blogws.models.WebModel;

/**
 * Created by Romaaan on 18/03/2017.
 */
public interface IResponse<T> {

    T getResponse();

    interface IWebResponse<T>{
        WebModel<T> convertResponse (IResponse<T> response);
    }

    //Generateur du webmodel à partir du model service
    interface IWebModelGenerator<T>{
        WebModel<T> convertResponse (IResponse<ServiceModel<T>> response);
    }
}
