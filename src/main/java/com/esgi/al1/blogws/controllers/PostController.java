package com.esgi.al1.blogws.controllers;
import com.esgi.al1.blogws.controllers.Mapping.APITags;
import com.esgi.al1.blogws.controllers.Mapping.APIActions;
import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.interfaces.IResponse.IWebModelResponse;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.AbstractControllerService;
import com.esgi.al1.blogws.services.PostControllerService;
import com.esgi.al1.blogws.utils.*;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.*;
import sun.security.provider.certpath.OCSPResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 18/03/2017.
 */

@RestController
@RequestMapping (value = Mapping.PostAPI)
public class PostController extends AbstractController <Post> {

        private final PostControllerService postControllerService;

        @Autowired
        public PostController(PostControllerService postControllerService) {
                this.postControllerService = postControllerService;
        }

        @RequestMapping (value = Mapping.GetAll + "/{start}/{end}", method = RequestMethod.GET)
        public @ResponseBody
        @ResponseStatus(value = HttpStatus.OK)
        WebModel<List<Post>>
        getAllPosts (@PathVariable Integer start, @PathVariable Integer end){
                IResponse<List<Post>> resp = () -> postControllerService.getAllPosts(start, end);
                Log.i("getting limited posts");
                return generateBodyResponse(resp, APITags.PostAPITag, APIActions.getPosts);
        }

        @RequestMapping (value = Mapping.FindById, method = RequestMethod.GET)
        public @ResponseBody
        @ResponseStatus(value = HttpStatus.OK)
        WebModel<Post>
        getPostById (@PathVariable Integer id) {
                IResponse<Post> resp = () -> postControllerService.getPost(id);
                return generateBodyResponse(resp,APITags.PostAPITag, APIActions.getPosts);
        }

        @RequestMapping (value =  Mapping.GetAll, method = RequestMethod.GET)
        public @ResponseBody
        @ResponseStatus(value = HttpStatus.OK)
        WebModel<List<Post>>
        getAllPosts () {
                IResponse<List<Post>> resp = postControllerService::getAllPosts;
                Log.i("getting all posts");
                return generateBodyResponse(resp, APITags.PostAPITag, APIActions.getPosts);
        }

        @RequestMapping (value =  Mapping.UpdateById , method = RequestMethod.PUT)
        public @ResponseBody
        @ResponseStatus(HttpStatus.OK)
        WebModel<Integer>
        updatePost (@PathVariable Integer id,
                    @RequestParam(value = "Text", required = false) String text,
                    @RequestParam(value = "Description", required = false) String desc,
                    @RequestParam(value = "AuthorID", required = false) Integer authorId,
                    @RequestParam(value = "Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                    @RequestParam(value = "Tags", required = false) String tags,
                    @RequestParam(value = "Title", required = false) String title,
                    @RequestParam(value = "FileName", required = false) String fileName,
                    HttpServletRequest request)throws IOException {

                HashMap<String,Object> sqlParams = new HashMap<>(8);
                if (text != null) sqlParams.put(PostTable.Columns.Text.getName(), text);
                if (desc != null) sqlParams.put(PostTable.Columns.Description.getName(), desc);
                if (authorId != null) sqlParams.put(PostTable.Columns.AuthorId.getName(), authorId);
                if (date != null) sqlParams.put(PostTable.Columns.Date.getName(), date);
                if (tags != null) sqlParams.put(PostTable.Columns.Tags.getName(), tags);
                if (title != null) sqlParams.put(PostTable.Columns.Title.getName(), title);
                if (fileName != null) sqlParams.put(PostTable.Columns.FileName.getName(), fileName);
                byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
                if (binaryContent.length > 0) sqlParams.put(PostTable.Columns.BinaryContent.getName(), binaryContent);

                IResponse<Integer> resp = () -> postControllerService.updatePost(sqlParams, id);
                Log.i("updating post");
                return generateBodyResponse(resp, APITags.PostAPITag, APIActions.updatePost);
        }

        @RequestMapping (value = Mapping.DeleteById, method = RequestMethod.DELETE)
        public @ResponseBody
        @ResponseStatus(HttpStatus.OK)
        WebModel<Integer>
        deletePost (@PathVariable Integer id) {
                IResponse<Integer> resp = () -> postControllerService.deletePost(id);
                Log.i("deleting post");
                return generateBodyResponse(resp,APITags.PostAPITag, APIActions.deletePost);
        }

        @RequestMapping (value = Mapping.Insert, method = RequestMethod.POST)
        public @ResponseBody
        @ResponseStatus(HttpStatus.CREATED)
        WebModel<Integer>
        insertPost (@RequestParam(value = "Text", required = false) String text,
                    @RequestParam(value = "Description", required = false) String desc,
                    @RequestParam(value = "AuthorID", required = false) Integer authorId,
                    @RequestParam(value = "Date",  required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                    @RequestParam(value = "Tags", required = false) String tags,
                    @RequestParam(value = "Title", required = false) String title,
                    @RequestParam(value = "FileName", required = false) String fileName,
                    HttpServletRequest request) throws IOException {

                HashMap<String,Object> sqlParams = new HashMap<>(8);
                if (text != null) sqlParams.put(PostTable.Columns.Text.getName(), text);
                if (desc != null) sqlParams.put(PostTable.Columns.Description.getName(), desc);
                if (authorId != null) sqlParams.put(PostTable.Columns.AuthorId.getName(), authorId);
                if (date != null) sqlParams.put(PostTable.Columns.Date.getName(), date);
                if (tags != null) sqlParams.put(PostTable.Columns.Tags.getName(), tags);
                if (title != null) sqlParams.put(PostTable.Columns.Title.getName(), title);
                if (text != null) sqlParams.put(PostTable.Columns.FileName.getName(), fileName);
                byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
                if (binaryContent.length > 0) sqlParams.put(PostTable.Columns.BinaryContent.getName(), binaryContent);

                IResponse<Integer> resp = () -> postControllerService.insertPost(sqlParams);
                Log.i("inserting post");
                return generateBodyResponse(resp,APITags.PostAPITag, APIActions.insertPost);
        }

        @RequestMapping (value = Mapping.DownloadImage, method = RequestMethod.GET)
        @ResponseBody
        @ResponseStatus(HttpStatus.OK)
        public WebModel<Integer>
        downloadPostImageById (@PathVariable (value="id") Integer post_id, HttpServletResponse response) throws IOException {
                Post post = postControllerService.getPost(post_id);
                int imgLength = post.getBinaryContent().length;
                long timeNow = Date.from(new Date().toInstant()).getTime();
                response.addHeader("Content-Disposition", "attachment; filename=" + timeNow + "_" + post.getFileName());
                response.addHeader("Content-Length", String.valueOf(imgLength));
                response.addHeader("Cache-Control", "no-store");
                response.addHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setContentType("image/jpeg");
                try (OutputStream os = response.getOutputStream()) {
                        os.write(post.getBinaryContent());
                        os.flush();
                }
                return generateBodyResponse(() -> imgLength, APITags.PostAPITag, APIActions.downloadImage);
        }
}
