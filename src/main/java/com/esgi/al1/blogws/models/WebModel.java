package com.esgi.al1.blogws.models;

import java.io.Serializable;

/**
 * Created by Romaaan on 19/03/2017.
 */
public class WebModel<T> implements Serializable {

    private String apiTag;

    public String getApiTag() {
        return apiTag;
    }

    public void setApiTag(String apiTag) {
        this.apiTag = apiTag;
    }

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
