package com.esgi.al1.blogws.services;

import com.esgi.al1.blogws.dao.SystemRepository;
import com.esgi.al1.blogws.interfaces.IAuthorization;
import com.esgi.al1.blogws.models.ServerInfo;
import com.esgi.al1.blogws.models.SystemInfo;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Romaaan on 02/04/2017.
 */
@Service
public class SystemInfoControllerService  {

    private final UserControllerService userControllerService;
    private final IAuthorization authorizationService;
    private final SystemRepository repository;

    @Autowired
    public SystemInfoControllerService(UserControllerService userControllerService, SystemRepository repository) {
        this.userControllerService = userControllerService;
        this.repository = repository;
        this.authorizationService = (int userId, UserType type) -> {
            User u = userControllerService.get(userId);
            return u != null && u.getType() == type;
        };
    }

    public int setSystemProperties(HashMap<String,String> params, int userId){
        if (authorizationService.isUserOfType(userId, UserType.Admin)){
            return repository.setSystemProperties(params);
        }
        return HttpStatus.UNAUTHORIZED.value();
    }

    public SystemInfo getSystemInfo(){
        return repository.getSystemInfo();
    }

    public ServerInfo getServerInfo(){
        return repository.getServerInfo();
    }



}
