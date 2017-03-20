package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.interfaces.IPostRepository;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.DataBase;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.PostTable;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romaaan on 19/03/2017.
 */

@Repository
public class PostDAO implements IPostRepository {

    private Post getPostData(ResultSet rs) throws SQLException {
        Post p = new Post();
        p.setId(rs.getInt(PostTable.Columns.Id.getOrdinal()));
        p.setAuthorId(rs.getInt(PostTable.Columns.AuthorId.getOrdinal()));
        p.setDate(rs.getDate(PostTable.Columns.Date.getOrdinal()));
        p.setDescription(rs.getString(PostTable.Columns.Description.getOrdinal()));
        p.setTitle(rs.getString(PostTable.Columns.Title.getOrdinal()));
        p.setTags(rs.getString(PostTable.Columns.Tags.getOrdinal()));
        p.setText(rs.getString(PostTable.Columns.Text.getOrdinal()));
        p.setBinaryContent(DataBase.ConvertBlob(rs.getBlob(PostTable.Columns.BinaryContent.getOrdinal())));

        return p;
    }

    @Override
    public List<Post> getAll() {
        List<Post> lstp = new ArrayList<Post>(64);
        try(Connection cn = MySqlConnector.getNewConnection()){
            PreparedStatement st = cn.prepareStatement(DataBase.Queries.getAllPosts);
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
}
