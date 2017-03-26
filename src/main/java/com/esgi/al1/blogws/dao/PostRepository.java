package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.dbconnector.MySqlConnector;
import com.esgi.al1.blogws.models.Post;
import com.esgi.al1.blogws.utils.DBUtils;
import com.esgi.al1.blogws.utils.PostTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Romaaan on 26/03/2017.
 */
@Repository
public class PostRepository extends AbstractRepository<Post> {

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
        p.setFileName(rs.getString(PostTable.Columns.FileName.getOrdinal()));

        return p;
    }
    @Autowired
    public PostRepository(MySqlConnector connector) {
        super(connector);
        this.setRsReader(this::getPostData);
    }
}
