package com.esgi.al1.blogws.utils;

import com.esgi.al1.blogws.models.WebModel;

/**
 * Created by Romaaan on 22/03/2017.
 */
public class WebModelBuilder<T> {

    public WebModel<T> model;

    public WebModelBuilder(){
        model = new WebModel<T>();
    }

    public WebModelBuilder<T> buildAPITag(String tag){
        model.setApiTag(tag);
        return this;
    }

    public WebModelBuilder<T> buildAPIAction(String action){
        model.setAction(action);
        return this;
    }

    public WebModelBuilder<T> buildContent(T content){
        model.setContent(content);
        return this;
    }

    public WebModel<T> build (){
        return this.model;
    }

}
