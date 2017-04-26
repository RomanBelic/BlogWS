package com.esgi.al1.blogws.models;

import java.util.Date;

/**
 * Created by Romaaan on 25/03/2017.
 */
public class User {

    private int id;
    private int idType;
    private Date dateCreated;
    private String name;
    private String lastName;
    private byte[] binaryContent;
    private String fileName;
    private UserType type;
    private String login;
    private String password;

    public User (){}

    public User(int id, int idType, Date dateCreated, String name, String lastName, byte[] arr, String filrName, UserType userType) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getBinaryContent() {
        return binaryContent;
    }

    public void setBinaryContent(byte[] binaryContent) {
        this.binaryContent = binaryContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode() + new Integer(idType).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof User && ((User)obj).id == this.id);
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
