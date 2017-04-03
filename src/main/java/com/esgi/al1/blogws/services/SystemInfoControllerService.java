package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.SystemRepository;
import com.esgi.al1.blogws.interfaces.IAuthorization;
import com.esgi.al1.blogws.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by Romaaan on 02/04/2017.
 */
@Service
public class SystemInfoControllerService  {

    private UserControllerService userControllerService;
    private final SystemRepository repository;

    @Autowired
    public SystemInfoControllerService(UserControllerService userControllerService, SystemRepository repository) {
        this.userControllerService = userControllerService;
        this.repository = repository;
    }

    private final IAuthorization authorizationService = (int userId, UserType type) ->
        userControllerService.getUser(userId).getType() == type;

    public int setSystemProperties(HashMap<String,String> params, int userId){
        if (authorizationService.isUserOfType(userId, UserType.Admin)){
            repository.setSystemProperties(params);
           return HttpServletResponse.SC_OK;
        }
        return HttpServletResponse.SC_UNAUTHORIZED;
    }



}
