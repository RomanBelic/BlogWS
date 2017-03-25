package com.esgi.al1.blogws.models;

/**
 * Created by Romaaan on 25/03/2017.
 */
public enum UserType {
    Admin(1, "Admin"),
    User (2, "User"),
    Visitor(3, "Visitor"),
    Default(-1, "");

    public int getId() {
        return id;
    }

    public String getTypeStr() {
        return typeStr;
    }

    private final int id;
    private final String typeStr;

    UserType(int idType, String typeStr) {
        this.id = idType;
        this.typeStr = typeStr;
    }

    public UserType getUserTypeById(int id){
        switch (id){
            case 1 :
                return Admin;
            case 2 :
                return User;
            case 3 :
                return Visitor;
            default: return Default;
        }
    }

}
