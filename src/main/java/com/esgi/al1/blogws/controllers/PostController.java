package com.esgi.al1.blogws.controllers;

import com.esgi.al1.blogws.interfaces.WorkingController;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.models.WebModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Romaaan on 18/03/2017.
 */

@Controller
@RequestMapping (value = "/" + Mapping.PostAPI)
public class PostController {

        @RequestMapping (value = "/" + Mapping.AllPosts)
        public @ResponseBody
        WebModel<Post> kek (){
              Post p = new  Post.PostBuilder(1,2).build();
              return new WebModel<Post>(Mapping.PostAPI,p);
        }
}
