package com.esgi.al1.blogws.controllers;

import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.interfaces.IUserService;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.UserControllerService;
import com.esgi.al1.blogws.utils.DBUtils;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.UserTable;
import com.esgi.al1.blogws.utils.WebModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Romaaan on 25/03/2017.
 */
@RestController
@RequestMapping(value = Mapping.UserAPI)
public class UserController {

    private final IUserService userControllerService;

    @Autowired
    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    private <T> WebModel<T> generateResponse(IResponse<T> resp, String apiTag, String action) {
        IResponse.IWebModelResponse<T> wm = (IResponse<T> arg) ->
                new WebModelBuilder<T>().
                        buildAPITag(apiTag).
                        buildAPIAction(action).
                        buildContent(arg.getResponse()).
                        build();
        return wm.convertResponse(resp);
    }

    @RequestMapping (value = Mapping.GetAll + "/{start}/{end}", method = RequestMethod.GET)
    public @ResponseBody
    WebModel<List<User>>
    getAllUsers ( int start, int end){
        IResponse<List<User>> resp = () -> userControllerService.getAllUsers(start, end);
        Log.i("getting limited Users");
        return generateResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.getUsers);
    }

    @RequestMapping (value = Mapping.FindById, method = RequestMethod.GET)
    public @ResponseBody
    WebModel<User>
    getUserById (@PathVariable Integer id) {
        IResponse<User> resp = () -> userControllerService.getUser(id);
        return generateResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.getUsers);
    }

    @RequestMapping (value =  Mapping.GetAll, method = RequestMethod.GET)
    public @ResponseBody
    WebModel<List<User>>
    getAllUsers () {
        IResponse<List<User>> resp = userControllerService::getAllUsers;
        Log.i("getting all Users");
        return generateResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.getUsers);
    }

    @RequestMapping (value =  Mapping.UpdateById , method = RequestMethod.PUT)
    public @ResponseBody
    WebModel<Integer>
    updateUser (@PathVariable Integer id,
                @RequestParam(value = "Name", required = false) String name,
                @RequestParam(value = "LastName", required = false) String lastname,
                @RequestParam(value = "IdType", required = false) Integer idType,
                @RequestParam(value = "Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateCreated,
                @RequestParam(value = "FileName", required = false) String fileName,
                HttpServletRequest request)throws IOException {

        HashMap<String,Object> sqlParams = new HashMap<>(8);
        if (name != null) sqlParams.put(UserTable.Columns.Name.getName(), name);
        if (lastname != null) sqlParams.put(UserTable.Columns.LastName.getName(), lastname);
        if (idType != null) sqlParams.put(UserTable.Columns.IdType.getName(), idType);
        if (dateCreated != null) sqlParams.put(UserTable.Columns.DateCreated.getName(), dateCreated);
        if (name != null) sqlParams.put(UserTable.Columns.FileName.getName(), fileName);
        byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
        if (binaryContent.length > 0) sqlParams.put(UserTable.Columns.BinaryContent.getName(), binaryContent);

        IResponse<Integer> resp = () -> userControllerService.updateUser(sqlParams, id);
        Log.i("updating User");
        return generateResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.updateUser);
    }

    @RequestMapping (value = Mapping.DeleteById, method = RequestMethod.DELETE)
    public @ResponseBody
    WebModel<Integer>
    deleteUser (@PathVariable Integer id) {
        IResponse<Integer> resp = () -> userControllerService.deleteUser(id);
        Log.i("deleting User");
        return generateResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.deleteUser);
    }

    @RequestMapping (value = Mapping.Insert, method = RequestMethod.POST)
    public @ResponseBody
    WebModel<Integer>
    insertUser (@RequestParam(value = "Name", required = false) String name,
                @RequestParam(value = "LastName", required = false) String lastname,
                @RequestParam(value = "IdType", required = false) Integer idType,
                @RequestParam(value = "Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateCreated,
                @RequestParam(value = "FileName", required = false) String fileName,
                HttpServletRequest request) throws IOException {

        HashMap<String,Object> sqlParams = new HashMap<>(8);
        if (name != null) sqlParams.put(UserTable.Columns.Name.getName(), name);
        if (lastname != null) sqlParams.put(UserTable.Columns.LastName.getName(), lastname);
        if (idType != null) sqlParams.put(UserTable.Columns.IdType.getName(), idType);
        if (dateCreated != null) sqlParams.put(UserTable.Columns.DateCreated.getName(), dateCreated);
        if (name != null) sqlParams.put(UserTable.Columns.FileName.getName(), fileName);
        byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
        if (binaryContent.length > 0) sqlParams.put(UserTable.Columns.BinaryContent.getName(), binaryContent);

        IResponse<Integer> resp = () -> userControllerService.insertUser(sqlParams);
        Log.i("inserting User");
        return generateResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.insertUser);
    }

    @RequestMapping (value =  Mapping.DownloadImage, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebModel<Integer>
    downloadUserImageById (@PathVariable (value="id") Integer User_id, HttpServletResponse response) throws IOException {
        User User = userControllerService.getUser(User_id);
        int imgLength = User.getBinaryContent().length;
        long timeNow = Date.from(new Date().toInstant()).getTime();
        response.addHeader("Content-Disposition", "attachment; filename=" + timeNow + "_" + User.getFileName());
        response.addHeader("Content-Length", String.valueOf(imgLength));
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try (OutputStream os = response.getOutputStream()) {
            os.write(User.getBinaryContent());
            os.flush();
        }
        return generateResponse(() -> imgLength, Mapping.APITags.UserAPITag, Mapping.APIActions.downloadImage);
    }
    
    
}
