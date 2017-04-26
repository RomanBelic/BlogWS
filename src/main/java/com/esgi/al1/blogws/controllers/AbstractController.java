package com.esgi.al1.blogws.controllers;
import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.interfaces.IResponse.IWebResponse;
import com.esgi.al1.blogws.interfaces.IResponse.IWebModelGenerator;
import com.esgi.al1.blogws.models.ServiceModel;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.Settings;
import com.esgi.al1.blogws.utils.WebModelBuilder;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public abstract class AbstractController {

    protected <T> WebModel<T> generateBodyResponse(IResponse<T> resp, String apiTag, String action) {
        IWebResponse<T> webResponser = (IResponse<T> arg) ->
                new WebModelBuilder<T>().
                        buildAPITag(apiTag).
                        buildAPIAction(action).
                        buildContent(arg.getResponse()).
                        build();
        return webResponser.convertResponse(resp);
    }

    protected <T> WebModel<T> generateWebResponse(IResponse<ServiceModel<T>> resp, String apiTag, String action,
                                                  HttpServletResponse httpResp) {
        IWebModelGenerator<T> webResponser = (IResponse <ServiceModel<T>> arg) -> {
            ServiceModel<T> serviceModel = resp.getResponse();
            httpResp.setStatus(serviceModel.getHttpCode());
            return new WebModelBuilder<T>().
                    buildAPITag(apiTag).
                    buildAPIAction(action).
                    buildContent(serviceModel.getContent()).
                    build();
        };
        return webResponser.convertResponse(resp);
    }

    protected int saveRequestToLog(HttpServletRequest request){
        String isLogEnabled;
        if ((isLogEnabled = Settings.getServerProperties().get("LogEnabled")) != null && isLogEnabled.equals("1")) {
            String msg = String.format("Connection from %s:%d to %s:%d at %s\r\n",
                    request.getRemoteAddr(),
                    request.getRemotePort(),
                    request.getLocalAddr(),
                    request.getLocalPort(),
                    new Date().toString());
            return Log.writeToFile("Log.txt", msg);
        }
        return 0;
    }
}
