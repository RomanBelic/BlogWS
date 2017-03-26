package com.esgi.al1.blogws.controllers;

import com.esgi.al1.blogws.interfaces.IResponse;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.models.WebModel;
import com.esgi.al1.blogws.services.AbstractControllerService;
import com.esgi.al1.blogws.services.PostControllerService;
import com.esgi.al1.blogws.services.UserControllerService;
import com.esgi.al1.blogws.utils.DBUtils;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.UserTable;
import com.esgi.al1.blogws.utils.WebModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController  extends AbstractController<User>{

    private final UserControllerService userControllerService;

    @Autowired
    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    @RequestMapping (value = Mapping.GetAll + "/{start}/{end}", method = RequestMethod.GET)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    WebModel<List<User>>
    getAllUsers (@PathVariable Integer start, @PathVariable Integer end){
        IResponse<List<User>> resp = () -> userControllerService.getAllUsers(start, end);
        Log.i("getting limited Users");
        return generateBodyResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.getUsers);
    }

    @RequestMapping (value = Mapping.FindById, method = RequestMethod.GET)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    WebModel<User>
    getUserById (@PathVariable Integer id) {
        IResponse<User> resp = () -> userControllerService.getUser(id);
        return generateBodyResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.getUsers);
    }

    @RequestMapping (value =  Mapping.GetAll, method = RequestMethod.GET)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    WebModel<List<User>>
    getAllUsers () {
        IResponse<List<User>> resp = userControllerService::getAllUsers;
        Log.i("getting all Users");
        return generateBodyResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.getUsers);
    }

    @RequestMapping (value =  Mapping.UpdateById , method = RequestMethod.PUT)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
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
        if (fileName != null) sqlParams.put(UserTable.Columns.FileName.getName(), fileName);
        byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
        if (binaryContent.length > 0) sqlParams.put(UserTable.Columns.BinaryContent.getName(), binaryContent);

        IResponse<Integer> resp = () -> userControllerService.updateUser(sqlParams, id);
        Log.i("updating User");
        return generateBodyResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.updateUser);
    }

    @RequestMapping (value = Mapping.DeleteById, method = RequestMethod.DELETE)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    WebModel<Integer>
    deleteUser (@PathVariable Integer id) {
        IResponse<Integer> resp = () -> userControllerService.deleteUser(id);
        Log.i("deleting User");
        return generateBodyResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.deleteUser);
    }

    @RequestMapping (value = Mapping.Insert, method = RequestMethod.POST)
    public @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
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
        if (fileName != null) sqlParams.put(UserTable.Columns.FileName.getName(), fileName);
        byte[] binaryContent = DBUtils.ConvertInputStream(request.getInputStream());
        if (binaryContent.length > 0) sqlParams.put(UserTable.Columns.BinaryContent.getName(), binaryContent);

        IResponse<Integer> resp = () -> userControllerService.insertUser(sqlParams);
        Log.i("inserting User");
        return generateBodyResponse(resp, Mapping.APITags.UserAPITag, Mapping.APIActions.insertUser);
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
        return generateBodyResponse(() -> imgLength, Mapping.APITags.UserAPITag, Mapping.APIActions.downloadImage);
    }
    
    
}
