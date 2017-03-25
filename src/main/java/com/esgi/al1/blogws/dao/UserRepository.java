package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.interfaces.IUserRepository;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.models.User;
import com.esgi.al1.blogws.models.UserType;
import com.esgi.al1.blogws.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romaaan on 25/03/2017.
 */
@Repository
public class UserRepository implements IUserRepository  {

    private final MySqlConnector connector;
    private final Queries queries;

    @Autowired
    public UserRepository(MySqlConnector connector, Queries queries) {
        this.connector = connector;
        this.queries = queries;
    }

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

    @Override
    public List<User> getAll() {
        List<User> lstp = new ArrayList<>(64);
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(queries.GetAllUsers);
            ResultSet rs = st.executeQuery();
            User p;
            while (rs.next()){
                p = getUserData(rs);
                lstp.add(p);
            }
        }catch (SQLException e){
            Log.err("User dao : " + e.getMessage());
            Log.err("Query : " + queries.GetAllUsers);
        }
        return lstp;
    }

    @Override
    public int updateUser(GeneratedStatement gst, int id) {
        int rows = -1;
        String query = null;
        try(Connection cn = connector.getNewConnection()){
            query = queries.UpdateUser.concat(gst.getParamStr()).concat(queries.WhereUserId);
            PreparedStatement st = cn.prepareStatement(query);
            DBUtils.copySqlParamsToStatement(gst.getLstParams(), st);
            st.setInt(gst.getLastParam().getIndex() + 1, id);
            rows = st.executeUpdate();

        }catch (SQLException e){
            Log.err("User dao : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return rows;
    }

    @Override
    public int deleteUser(int id) {
        int rows = -1;
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(queries.DeleteUser);
            st.setInt(1, id);
            rows = st.executeUpdate();
        }catch (SQLException e){
            Log.err("User dao : " + e.getMessage());
            Log.err("Query : " + queries.DeleteUser);
        }
        return rows;
    }

    @Override
    public int insertUser(GeneratedStatement gst){
        int id = -1;
        String query = null;
        try(Connection cn = connector.getNewConnection()){
            query = queries.InsertUser.concat(gst.getParamStr());
            PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            DBUtils.copySqlParamsToStatement(gst.getLstParams(), st);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            id =  (rs.next()) ? rs.getInt(1) : 0;
        }catch (SQLException e){
            Log.err("User dao : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return id;
    }

    @Override
    public List<User> getAllByLimit(int start, int end) {
        List<User> lstp = new ArrayList<>(64);
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(queries.GetAllUsersLimit);
            st.setInt(1, start);
            st.setInt(2, end);
            ResultSet rs = st.executeQuery();
            User p;
            while (rs.next()){
                p = getUserData(rs);
                lstp.add(p);
            }
        }catch (SQLException e){
            Log.err("User dao : " + e.getMessage());
            Log.err("Query : " + queries.GetAllUsersLimit);
        }
        return lstp;
    }

    @Override
    public User getUserById(int id) {
        User p = null;
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(queries.GetUser);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            p = rs.next() ? getUserData(rs) : null;
        }catch (SQLException e){
            Log.err("User dao : " + e.getMessage());
            Log.err("Query : " + queries.GetUser);
        }
        return p;
    }
}
