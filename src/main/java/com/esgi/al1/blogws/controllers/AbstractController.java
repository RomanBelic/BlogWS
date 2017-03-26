package com.esgi.al1.blogws.controllers;

/**
 * Created by Romaaan on 26/03/2017.
 */

import com.esgi.al1.blogws.interfaces.IResponse.IWebModelResponse;
import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.AbstractControllerService;
import com.esgi.al1.blogws.services.PostControllerService;
import com.esgi.al1.blogws.utils.WebModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractController<T> {

    protected <T> WebModel <T> generateBodyResponse(IResponse<T> resp, String apiTag, String action) {
        IWebModelResponse<T> webReponser = (IResponse<T> arg) ->
                new WebModelBuilder<T>().
                        buildAPITag(apiTag).
                        buildAPIAction(action).
                        buildContent(arg.getResponse()).
                        build();
        return webReponser.convertResponse(resp);
    }
}
