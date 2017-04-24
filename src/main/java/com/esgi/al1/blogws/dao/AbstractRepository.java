package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.interfaces.IResultReader;
import com.esgi.al1.blogws.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Romaaan on 26/03/2017.
 */
@Repository
public abstract class AbstractRepository<T> {

    protected IResultReader<T> rsReader = (ResultSet rs) -> null;

    private final MySqlConnector connector;

    protected void setRsReader (IResultReader<T> rsReader){
        this.rsReader = rsReader;
    }

    @Autowired
    public AbstractRepository(MySqlConnector connector) {
        this.connector = connector;
    }

    private void setPreparedParameter(int index, Object value, PreparedStatement st) throws SQLException {
        if (value instanceof Integer)
            st.setInt(index, (int)value);
        if (value instanceof String)
            st.setString(index, String.valueOf(value));
        if (value instanceof java.util.Date)
            st.setDate(index, new java.sql.Date(((java.util.Date) value).getTime()));
        if (value instanceof Long)
            st.setLong(index, (long)value);
        if (value instanceof Float)
            st.setFloat(index, (float)value);
        if (value instanceof Double)
            st.setDouble(index, (double)value);
        if (value instanceof byte[]) {
            ByteArrayInputStream bai = new ByteArrayInputStream((byte[])value);
            st.setBlob(index, bai);
        }
        if (value == null)
            st.setNull(index, Types.NULL);
    }

    //On parcourt le nested array "params[]" et on met les indexes des params dans le PreparedStatement
    private int copySqlParamsToStatement(int startIndex, PreparedStatement st, Object... params) throws SQLException {
        int nIndex = startIndex;
        for (Object value : params){
            if (value instanceof Object[]){
                startIndex += nIndex;
                nIndex = copySqlParamsToStatement(nIndex, st, (Object[])value);
            }else {
                nIndex ++;
                setPreparedParameter(nIndex, value, st);
            }
        }
        return nIndex;
    }

    public List<T> getAll(@NotNull String query, Object...params) {
        List<T> lstRes = new ArrayList<>(64);
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(query);
            copySqlParamsToStatement(0, st, params);
            ResultSet rs = st.executeQuery();
            T res;
            while (rs.next()){
                res = rsReader.readData(rs);
                lstRes.add(res);
            }
        }catch (SQLException e){
            Log.err("GetAll Failed : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return lstRes;
    }

    public T get(@NotNull String query, Object...params) {
        T res = null;
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(query);
            copySqlParamsToStatement(0, st, params);
            ResultSet rs = st.executeQuery();
            res = rs.next() ? rsReader.readData(rs) : null;
        }catch (SQLException e){
            Log.err("Get Failed : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return res;
    }

    public int updateOrDelete(@NotNull String query, Object...params) {
        int rows = -1;
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(query);
            copySqlParamsToStatement(0, st, params);
            rows = st.executeUpdate();

        }catch (SQLException e){
            Log.err("UpdateOrDelete Failed : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return rows;
    }

    public int insert(@NotNull String query, Object...params){
        int id = -1;
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            copySqlParamsToStatement(0, st, params);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            id =  (rs.next()) ? rs.getInt(1) : 0;
        }catch (SQLException e){
            Log.err("Insert Failed : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return id;
    }

}
