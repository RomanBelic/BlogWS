package com.esgi.al1.blogws.controllers;

import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.models.ServerInfo;
import com.esgi.al1.blogws.models.SystemInfo;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.SystemInfoControllerService;
import com.esgi.al1.blogws.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by Romaaan on 05/04/2017.
 */
@RestController
@RequestMapping(value = Mapping.SystemAPI)
public class SystemInfoController extends AbstractController{

    private final SystemInfoControllerService systemInfoControllerService;

    @Autowired
    public SystemInfoController(SystemInfoControllerService systemInfoControllerService) {
        this.systemInfoControllerService = systemInfoControllerService;
    }

    @RequestMapping (value = Mapping.SystemInfo, method = RequestMethod.GET)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    WebModel<SystemInfo>
    getSystemInfo (HttpServletRequest request){
        IResponse<SystemInfo> resp = systemInfoControllerService::getSystemInfo;
        Log.i("getting system info");
        saveRequestToLog(request);
        return generateBodyResponse(resp, Mapping.APITags.SysAPITag, Mapping.APIActions.getSystemInfo);
    }

    @RequestMapping (value = Mapping.ServerInfo, method = RequestMethod.GET)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    WebModel<ServerInfo>
    getServerInfo (HttpServletRequest request){
        IResponse<ServerInfo> resp = systemInfoControllerService::getServerInfo;
        Log.i("getting server info");
        saveRequestToLog(request);
        return generateBodyResponse(resp, Mapping.APITags.SysAPITag, Mapping.APIActions.getServInfo);
    }


    @RequestMapping (value = Mapping.SetProperties, method = RequestMethod.PUT)
    public @ResponseBody
    WebModel<Integer>
    setSystemProperties (@PathVariable Integer userId,
                         HttpServletRequest request, HttpServletResponse response){
        HashMap<String,String> paramMap = new HashMap<>(8);
        Enumeration<String> enumStr = request.getParameterNames();
        while (enumStr.hasMoreElements()){
            String paramKey = enumStr.nextElement();
            String paramVal = request.getParameter(paramKey);
            paramMap.put(paramKey, paramVal);
        }
        Log.i("setting system params");
        saveRequestToLog(request);
        int status = systemInfoControllerService.setSystemProperties(paramMap, userId);
        response.setStatus(status);
        return generateBodyResponse(()-> status, Mapping.APITags.SysAPITag, Mapping.APIActions.setSysProps);
    }

    @RequestMapping (value = Mapping.SetServerProperties, method = RequestMethod.PUT)
    public @ResponseBody
    WebModel<Integer>
    setServerProperties (@PathVariable Integer userId,
                         HttpServletRequest request, HttpServletResponse response){
        HashMap<String,String> paramMap = new HashMap<>(8);
        Enumeration<String> enumStr = request.getParameterNames();
        while (enumStr.hasMoreElements()){
            String paramKey = enumStr.nextElement();
            String paramVal = request.getParameter(paramKey);
            paramMap.put(paramKey, paramVal);
        }
        Log.i("setting server params");
        saveRequestToLog(request);
        int status = systemInfoControllerService.setServerProperties(paramMap, userId);
        response.setStatus(status);
        return generateBodyResponse(()-> status, Mapping.APITags.SysAPITag, Mapping.APIActions.setServProps);
    }

}
