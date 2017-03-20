package com.esgi.al1.blogws.utils;

import org.springframework.context.annotation.Bean;

/**
 * Created by Romaaan on 20/03/2017.
 */

public class PostTable extends DataTable {

    public PostTable(String name, String alias) {
        super(name, alias);
    }

    public enum Columns {
        Id("Id",1),
        AuthorId("AuthorId",2),
        Date("Date",3),
        Title("Title",4),
        Description("Description",5),
        Tags("Tags",6),
        Text("Text",7),
        BinaryContent("BinaryContent",8);

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
