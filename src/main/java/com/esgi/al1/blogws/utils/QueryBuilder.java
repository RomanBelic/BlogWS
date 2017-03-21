package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 21/03/2017.
 */
public class QueryBuilder  {

    private String table;
    private String database;
    private String where;
    private String command;
    private String updatearguments;
    private String orderBy;

    public QueryBuilder (){
        this.table = "";
        this.database = "";
        this.where = "";
        this.command = "";
        this.updatearguments = "";
        this.orderBy = "";
    }

    public QueryBuilder setTable (String table){
        this.table = table;
        return this;
    }

    public QueryBuilder setDatabase (String database){
        this.database = database;
        return this;
    }

    public QueryBuilder setWhere (String where){
        this.where = where;
        return this;
    }

    public QueryBuilder setCommand (String command){
        this.command = command;
        return this;
    }

    public QueryBuilder setUpdateArguments (String arguments){
        this.updatearguments = arguments;
        return this;
    }

    public QueryBuilder setOrderBy (String order){
        this.orderBy = order;
        return this;
    }

    public String build(){
        return command.concat(" ").
                concat(database).concat(".").concat(table).concat(" ").
                concat(updatearguments).concat(" ").concat(where).concat(" ").
                concat(orderBy);
    }



}
