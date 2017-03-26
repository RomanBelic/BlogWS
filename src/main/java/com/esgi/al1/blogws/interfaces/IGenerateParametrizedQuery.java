package com.esgi.al1.blogws.interfaces;

import com.esgi.al1.blogws.utils.GeneratedQuery;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * Created by Romaaan on 26/03/2017.
 */
public interface IGenerateParametrizedQuery {
    GeneratedQuery generate(@NotNull HashMap<String,Object> params);
}
