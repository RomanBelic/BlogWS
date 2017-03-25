package com.esgi.al1.blogws.utils;

/**
 * Created by Romaaan on 25/03/2017.
 */
public class UserTable extends DataTable{

    public UserTable(String name, String alias) {
        super(name, alias);
    }

    public enum Columns {
        Id("Id",1),
        IdType("IdType",2),
        Name("Name",3),
        LastName("LastName",4),
        FileName("FileName",5),
        DateCreated("DateCreated",6),
        BinaryContent("BinaryContent",7)
        ;

        private final String columnName;
        private final int ordinal;

        Columns(String colName, int ordinal){
            this.columnName = colName;
            this.ordinal = ordinal;
        }

        public String getName(){
            return this.columnName;
        }

        public int getOrdinal(){
            return this.ordinal;
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }
}
