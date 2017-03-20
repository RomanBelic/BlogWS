package com.esgi.al1.blogws.controllers;

import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.interfaces.IResponse.IWebModelResponse;
import com.esgi.al1.blogws.interfaces.IPostControllerService;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.PostControllerService;
import com.esgi.al1.blogws.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Romaaan on 18/03/2017.
 */

@RestController
@RequestMapping (value = "/" + Mapping.PostAPI)
public class PostController {

        private final IPostControllerService postControllerService;

        @Autowired
        public PostController(PostControllerService postControllerService) {
                this.postControllerService = postControllerService;
        }

        @RequestMapping (value = "/" + Mapping.AllPosts, method = RequestMethod.GET)
        public @ResponseBody
        WebModel<List<Post>>
        getAllPosts (){
                IResponse<List<Post>> resp = () -> postControllerService.getAllPosts();
                IWebModelResponse<List<Post>> wm = (IResponse<List<Post>> response) ->
                        new WebModel<List<Post>>(Mapping.PostAPI, response.getResponseBody());

                Log.i("getting all posts");
                return wm.convertResponse(resp);
        }
}
