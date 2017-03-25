package com.esgi.al1.blogws.models;

import javax.websocket.Decoder;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Romaaan on 18/03/2017.
 */
public class Post implements Serializable {

     private Date date;
     private int authorId;
     private int id;
     private String title;
     private String description;
     private String tags;
     private byte[] binaryContent;
     private String text;
     private String FileName;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public Post (){}

    public byte[] getBinaryContent() {
        return binaryContent;
    }

    public void setBinaryContent(byte[] binaryContent) {
        this.binaryContent = binaryContent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode() + new Integer(authorId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Post && ((Post)obj).id == this.id);
    }
}
