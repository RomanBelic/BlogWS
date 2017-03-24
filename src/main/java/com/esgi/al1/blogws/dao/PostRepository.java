package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.interfaces.IPostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.*;
import com.esgi.al1.blogws.utils.DataBase.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romaaan on 19/03/2017.
 */

@Repository
public class PostRepository  implements IPostRepository {

    private final MySqlConnector connector;

    @Autowired
    public PostRepository(MySqlConnector connector) {
        this.connector = connector;
    }

    private Post getPostData(ResultSet rs) throws SQLException {
        Post p = new Post();
        p.setId(rs.getInt(PostTable.Columns.Id.getOrdinal()));
        p.setAuthorId(rs.getInt(PostTable.Columns.AuthorId.getOrdinal()));
        p.setDate(rs.getDate(PostTable.Columns.Date.getOrdinal()));
        p.setDescription(rs.getString(PostTable.Columns.Description.getOrdinal()));
        p.setTitle(rs.getString(PostTable.Columns.Title.getOrdinal()));
        p.setTags(rs.getString(PostTable.Columns.Tags.getOrdinal()));
        p.setText(rs.getString(PostTable.Columns.Text.getOrdinal()));
        p.setBinaryContent(DBUtils.ConvertBlob(rs.getBlob(PostTable.Columns.BinaryContent.getOrdinal())));

        return p;
    }

    @Override
    public List<Post> getAll() {
        List<Post> lstp = new ArrayList<>(64);
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(Queries.getAllPosts);
            ResultSet rs = st.executeQuery();
            Post p;
            while (rs.next()){
                p = getPostData(rs);
                lstp.add(p);
            }
        }catch (SQLException e){
            Log.err("Post dao : " + e.getMessage());
        }
        return lstp;
    }

    @Override
    public int updatePost(GeneratedStatement gst, int id) {
        int rows = -1;
        String query = null;
        try(Connection cn = connector.getNewConnection()){
            query = Queries.updatePost.concat(gst.getParamStr()).concat(Queries.wherePostId);
            PreparedStatement st = cn.prepareStatement(query);
            DBUtils.copySqlParamsToStatement(gst.getLstParams(), st);
            st.setInt(gst.getLastParam().getIndex() + 1, id);
            rows = st.executeUpdate();

        }catch (SQLException e){
            Log.err("Post dao : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return rows;
    }

    @Override
    public int deletePost(int id) {
        int rows = -1;
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(Queries.deletePost);
            st.setInt(1, id);
            rows = st.executeUpdate();
        }catch (SQLException e){
            Log.err("Post dao : " + e.getMessage());
        }
        return rows;
    }

    @Override
    public int insertPost(GeneratedStatement gst){
        int id = -1;
        String query = null;
        try(Connection cn = connector.getNewConnection()){
            query = Queries.insertPost.concat(gst.getParamStr());
            PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            DBUtils.copySqlParamsToStatement(gst.getLstParams(), st);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            id =  (rs.next()) ? rs.getInt(1) : 0;
        }catch (SQLException e){
            Log.err("Post dao : " + e.getMessage());
            Log.err("Query : " + query);
        }
        return id;
    }

    @Override
    public List<Post> getAllByLimit(int start, int end) {
        List<Post> lstp = new ArrayList<>(64);
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(Queries.getAllPostsLimit);
            st.setInt(1, start);
            st.setInt(2, end);
            ResultSet rs = st.executeQuery();
            Post p;
            while (rs.next()){
                p = getPostData(rs);
                lstp.add(p);
            }
        }catch (SQLException e){
            Log.err("Post dao : " + e.getMessage());
        }
        return lstp;
    }

    @Override
    public Post getPostById(int id) {
        Post p = null;
        try(Connection cn = connector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(Queries.getPost);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            p = rs.next() ? getPostData(rs) : null;
        }catch (SQLException e){
            Log.err("Post dao : " + e.getMessage());
        }
        return p;
    }
}
