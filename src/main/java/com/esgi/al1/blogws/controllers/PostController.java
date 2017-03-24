package com.esgi.al1.blogws.controllers;
import com.esgi.al1.blogws.controllers.Mapping.APITags;
import com.esgi.al1.blogws.controllers.Mapping.APIActions;
import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.interfaces.IResponse.IWebModelResponse;
import com.esgi.al1.blogws.interfaces.IPostControllerService;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.models.SqlConfig;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.PostControllerService;
import com.esgi.al1.blogws.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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

        public <T> WebModel<T> generateResponse(IResponse <T> resp, String apiTag, String action) {
                IWebModelResponse<T> wm = (IResponse<T> arg) ->
                        new WebModelBuilder<T>().
                                buildAPITag(apiTag).
                                buildAPIAction(action).
                                buildContent(arg.getResponse()).
                                build();
                return wm.convertResponse(resp);
        }

        @RequestMapping (value = Mapping.AllPosts + "/{start}/{end}", method = RequestMethod.GET)
        public @ResponseBody
        WebModel<List<Post>>
        getAllPosts ( int start, int end){
                IResponse<List<Post>> resp = () -> postControllerService.getAllPost(start, end);
                Log.i("getting limited posts");
                return generateResponse(resp, APITags.PostAPITag, APIActions.getPosts);
        }

        @RequestMapping (value =  Mapping.FindPost, method = RequestMethod.GET)
        public @ResponseBody
        WebModel<Post>
        getPostById ( int id) {
                IResponse<Post> resp = () -> postControllerService.getPost(id);
                Log.i("getting a post by id");
                return generateResponse(resp,APITags.PostAPITag, APIActions.getPosts);
        }

        @RequestMapping (value =  Mapping.AllPosts, method = RequestMethod.GET)
        public @ResponseBody
        WebModel<List<Post>>
        getAllPosts (){
                IResponse<List<Post>> resp = postControllerService::getAllPosts;
                Log.i("getting all posts");
                return generateResponse(resp,APITags.PostAPITag, APIActions.getPosts);
        }

        @RequestMapping (value =  Mapping.UpdatePost , method = RequestMethod.PUT)
        public @ResponseBody
        WebModel<Integer>
        updatePost ( int id,
                    @RequestParam(value = "Text", required = false) String text,
                    @RequestParam(value = "Description", required = false) String desc,
                    @RequestParam(value = "AuthorID", required = false) Integer authorId,
                    @RequestParam(value = "Date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                    @RequestParam(value = "Tags", required = false) String tags,
                    @RequestParam(value = "Title", required = false) String title,
                    HttpServletRequest request)throws IOException {

                HashMap<String,Object> sqlParams = new HashMap<>(8);
                if (text != null) sqlParams.put(PostTable.Columns.Text.getName(), text);
                if (desc != null) sqlParams.put(PostTable.Columns.Description.getName(), desc);
                if (authorId != null) sqlParams.put(PostTable.Columns.AuthorId.getName(), authorId);
                if (date != null) sqlParams.put(PostTable.Columns.Date.getName(), date);
                if (tags != null) sqlParams.put(PostTable.Columns.Tags.getName(), tags);
                if (title != null) sqlParams.put(PostTable.Columns.Title.getName(), title);
                byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
                if (binaryContent.length > 0) sqlParams.put(PostTable.Columns.BinaryContent.getName(), binaryContent);

                IResponse<Integer> resp = () -> postControllerService.updatePost(sqlParams, id);
                Log.i("updating post");
                return generateResponse(resp,APITags.PostAPITag, APIActions.updatePost);
        }

        @RequestMapping (value =  Mapping.DeletePost , method = RequestMethod.DELETE)
        public @ResponseBody
        WebModel<Integer>
        deletePost ( int id) {
                IResponse<Integer> resp = () -> postControllerService.deletePost(id);
                Log.i("deleteting post");
                return generateResponse(resp,APITags.PostAPITag, APIActions.deletePost);
        }

        @RequestMapping (value = Mapping.InsertPost, method = RequestMethod.POST)
        public @ResponseBody
        WebModel<Integer>
        insertPost (@RequestParam(value = "Text", required = false) String text,
                    @RequestParam(value = "Description", required = false) String desc,
                    @RequestParam(value = "AuthorID", required = false) Integer authorId,
                    @RequestParam(value = "Date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                    @RequestParam(value = "Tags", required = false) String tags,
                    @RequestParam(value = "Title", required = false) String title,
                    HttpServletRequest request) throws IOException {

                HashMap<String,Object> sqlParams = new HashMap<>(8);
                if (text != null) sqlParams.put(PostTable.Columns.Text.getName(), text);
                if (desc != null) sqlParams.put(PostTable.Columns.Description.getName(), desc);
                if (authorId != null) sqlParams.put(PostTable.Columns.AuthorId.getName(), authorId);
                if (date != null) sqlParams.put(PostTable.Columns.Date.getName(), date);
                if (tags != null) sqlParams.put(PostTable.Columns.Tags.getName(), tags);
                if (title != null) sqlParams.put(PostTable.Columns.Title.getName(), title);
                byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
                if (binaryContent.length > 0) sqlParams.put(PostTable.Columns.BinaryContent.getName(), binaryContent);

                IResponse<Integer> resp = () -> postControllerService.insertPost(sqlParams);
                Log.i("inserting post");
                return generateResponse(resp,APITags.PostAPITag, APIActions.insertPost);
        }
}
