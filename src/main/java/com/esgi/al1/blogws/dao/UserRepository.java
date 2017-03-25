package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.interfaces.IUserRepository;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.models.UserType;
import com.esgi.al1.blogws.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Romaaan on 25/03/2017.
 */
@Repository
public class UserRepository implements IUserRepository {

    private final MySqlConnector connector;

    private final Queries queries;

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
    public UserRepository(MySqlConnector connector, Queries queries) {
        this.connector = connector;
        this.queries = queries;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public int updateUser(GeneratedStatement gst, int id) {
        return 0;
    }

    @Override
    public int deleteUser(int id) {
        return 0;
    }

    @Override
    public int insertUser(GeneratedStatement gst) {
        return 0;
    }

    @Override
    public List<User> getAllByLimit(int start, int end) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
