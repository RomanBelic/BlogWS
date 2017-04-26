package com.esgi.al1.blogws.utils;

import com.esgi.al1.blogws.models.ServiceModel;

/**
 * Created by Romaaan on 26/04/2017.
 */
public class ServiceModelBuilder<T> {

    private int htppCode;
    private T content;

    public ServiceModelBuilder (){

    }

    public ServiceModelBuilder<T> buildContent (T content){
        this.content = content;
        return this;
    }

    public ServiceModelBuilder<T> buildHttpCode (int htppCode){
        this.htppCode = htppCode;
        return this;
    }

    public ServiceModel<T> build () {
        return new ServiceModel<>(this);
    }

    public int getHtppCode() {
        return htppCode;
    }

    public T getContent() {
        return content;
    }
}
