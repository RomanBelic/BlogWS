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

     public Post (){
     }

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

    private Post(PostBuilder builder) {
        this.date = builder.date;
        this.id = builder.id;
        this.authorId = builder.authorId;
        this.title = builder.title;
        this.description = builder.description;
        this.tags = builder.tags;
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode() + new Integer(authorId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Post && ((Post)obj).id == this.id);
    }

    public static class PostBuilder {
         private final int id;
         private final int authorId;
         private Date date;
         private String title;
         private String description;
         private String tags;

         public PostBuilder(int id, int authorId){
             this.id=id;
             this.authorId=authorId;
         }
         public PostBuilder date(Date date){
             this.date=date;
             return this;
         }

         public PostBuilder title (String title){
             this.title=title;
             return this;
         }

         public PostBuilder description (String desc){
             this.description=desc;
             return this;
         }

         public PostBuilder tag (String tag){
             this.tags=tags;
             return this;
         }

         public Post build(){
             return new Post(this);
         }
    }
}
