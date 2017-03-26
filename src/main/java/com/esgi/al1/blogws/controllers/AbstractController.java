package com.esgi.al1.blogws.controllers;

import com.esgi.al1.blogws.interfaces.IResponse.IWebModelResponse;
import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.utils.WebModelBuilder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractController<T> {

    protected <S> WebModel <S> generateBodyResponse(IResponse<S> resp, String apiTag, String action) {
        IWebModelResponse<S> webReponser = (IResponse<S> arg) ->
                new WebModelBuilder<S>().
                        buildAPITag(apiTag).
                        buildAPIAction(action).
                        buildContent(arg.getResponse()).
                        build();
        return webReponser.convertResponse(resp);
    }
}
