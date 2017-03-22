package com.esgi.al1.blogws.controllers;
import com.esgi.al1.blogws.controllers.Mapping.APITags;
import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.interfaces.IResponse.IWebModelResponse;
import com.esgi.al1.blogws.interfaces.IPostControllerService;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.PostControllerService;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.PostTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 18/03/2017.
 */

@RestController
@RequestMapping (value = Mapping.PostAPI)
public class PostController {

        private final IPostControllerService postControllerService;

        @Autowired
        public PostController(PostControllerService postControllerService) {
                this.postControllerService = postControllerService;
        }

        @RequestMapping (value = Mapping.PostsLimit, method = RequestMethod.GET)
        public @ResponseBody
        WebModel<List<Post>>
        getAllPosts (@PathVariable(required = true) int start,@PathVariable(required = true) int end){
                IResponse<List<Post>> resp = () -> postControllerService.getAllPost(start, end);
                IWebModelResponse<List<Post>> wm = (IResponse<List<Post>> response) ->
                        new WebModel<>(APITags.PostAPITag, response.getResponse());
                Log.i("getting limited posts");
                return wm.convertResponse(resp);
        }

        @RequestMapping (value = Mapping.FindPost, method = RequestMethod.GET)
        public @ResponseBody
        WebModel<Post>
        getPostById (@PathVariable(required = true) int id){
                IResponse<Post> resp = () -> postControllerService.getPost(id);
                IWebModelResponse<Post> wm = (IResponse<Post> response) ->
                        new WebModel<>(APITags.PostAPITag, response.getResponse());
                Log.i("getting limited posts");
                return wm.convertResponse(resp);
        }

        @RequestMapping (value = Mapping.AllPosts, method = RequestMethod.GET)
        public @ResponseBody
        WebModel<List<Post>>
        getAllPosts (){
                IResponse<List<Post>> resp = postControllerService::getAllPosts;
                IWebModelResponse<List<Post>> wm = (IResponse<List<Post>> response) ->
                        new WebModel<>(APITags.PostAPITag, response.getResponse());
                Log.i("getting all posts");
                return wm.convertResponse(resp);
        }

        @RequestMapping (value = Mapping.UpdatePost, method = RequestMethod.PUT)
        public @ResponseStatus
        int
        updatePost (@PathVariable(required = true) int id,
                    @RequestParam(value = "Text", required = false) String text,
                    @RequestParam(value = "Description", required = false) String desc,
                    @RequestParam(value = "AuthorID", required = false) Integer authorId,
                    @RequestParam(value = "Date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                    @RequestParam(value = "Tags", required = false) String tags,
                    @RequestParam(value = "Title", required = false) String title
        ) {
                HashMap<String,Object> sqlParams = new HashMap<>(8);
                if (text != null) sqlParams.put(PostTable.Columns.Text.getName(), text);
                if (desc != null) sqlParams.put(PostTable.Columns.Description.getName(), desc);
                if (authorId != null) sqlParams.put(PostTable.Columns.AuthorId.getName(), authorId);
                if (date != null) sqlParams.put(PostTable.Columns.Date.getName(), date);
                if (tags != null) sqlParams.put(PostTable.Columns.Tags.getName(), tags);
                if (title != null) sqlParams.put(PostTable.Columns.Title.getName(), title);

                IResponse<Integer> resp = () -> postControllerService.updatePost(sqlParams, id);
                Log.i("updating post");
                return resp.getResponse();
        }

        @RequestMapping (value = Mapping.DeletePost, method = RequestMethod.DELETE)
        public @ResponseStatus
        int
        deletePost (@PathVariable(required = true) int id) {
                IResponse<Integer> resp = () -> postControllerService.deletePost(id);
                Log.i("deleteting post");
                return resp.getResponse();
        }

        @RequestMapping (value = Mapping.InsertPost, method = RequestMethod.POST)
        public @ResponseStatus
        int
        insertPost (@RequestParam(value = "Text", required = false) String text,
                    @RequestParam(value = "Description", required = false) String desc,
                    @RequestParam(value = "AuthorID", required = false) Integer authorId,
                    @RequestParam(value = "Date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                    @RequestParam(value = "Tags", required = false) String tags,
                    @RequestParam(value = "Title", required = false) String title)
        {
                HashMap<String,Object> sqlParams = new HashMap<>(8);
                if (text != null) sqlParams.put(PostTable.Columns.Text.getName(), text);
                if (desc != null) sqlParams.put(PostTable.Columns.Description.getName(), desc);
                if (authorId != null) sqlParams.put(PostTable.Columns.AuthorId.getName(), authorId);
                if (date != null) sqlParams.put(PostTable.Columns.Date.getName(), date);
                if (tags != null) sqlParams.put(PostTable.Columns.Tags.getName(), tags);
                if (title != null) sqlParams.put(PostTable.Columns.Title.getName(), title);

                IResponse<Integer> resp = () -> postControllerService.insertPost(sqlParams);
                Log.i("inserting post");
                return resp.getResponse();
        }

}
