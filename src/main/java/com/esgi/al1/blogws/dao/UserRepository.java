package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.models.UserType;
import com.esgi.al1.blogws.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by Romaaan on 25/03/2017.
 */
@Repository
public class UserRepository extends AbstractRepository<User>  {

    private User getUserData(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt(UserTable.Columns.Id.getOrdinal()));
        u.setIdType(rs.getInt(UserTable.Columns.IdType.getOrdinal()));
        u.setName(rs.getString(UserTable.Columns.Name.getOrdinal()));
        u.setLastName(rs.getString(UserTable.Columns.LastName.getOrdinal()));
        u.setDateCreated(rs.getDate(UserTable.Columns.DateCreated.getOrdinal()));
        u.setBinaryContent(DBUtils.ConvertBlob(rs.getBlob(UserTable.Columns.BinaryContent.getOrdinal())));
        u.setFileName(rs.getString(UserTable.Columns.FileName.getOrdinal()));
        u.setType(UserType.getUserTypeById(u.getIdType()));

        return u;
    }

    @Autowired
    public UserRepository(MySqlConnector connector) {
        super(connector);
        this.setRsReader(this::getUserData);
    }
}
