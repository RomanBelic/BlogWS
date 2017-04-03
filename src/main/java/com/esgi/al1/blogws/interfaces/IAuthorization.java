package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.models.UserType;
import org.springframework.stereotype.Service;

/**
 * Created by Romaaan on 02/04/2017.
 */
@Service
public interface IAuthorization {
    boolean isUserOfType(int userId, UserType type);
}
