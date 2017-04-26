package com.esgi.al1.blogws.models;

import com.esgi.al1.blogws.utils.ServiceModelBuilder;

import java.io.Serializable;

/**
 * Created by Romaaan on 25/04/2017.
 */
public class ServiceModel<T> implements Serializable{

   private int httpCode;

   public int getHttpCode() {
      return httpCode;
   }

   public void setHttpCode(int httpCode) {
      this.httpCode = httpCode;
   }

   public T getContent() {
      return content;
   }

   public void setContent(T content) {
      this.content = content;
   }

   private T content;

   public ServiceModel (){

   }

   public ServiceModel (ServiceModelBuilder<T> builder){
      this.content = builder.getContent();
      this.httpCode = builder.getHtppCode();
   }
}
