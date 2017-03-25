package com.esgi.al1.blogws.utils;

import ch.qos.logback.classic.db.SQLBuilder;
import com.esgi.al1.blogws.utils.SqlConfigBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Romaaan on 23/03/2017.
 */
public class SqlConfig  {

    private String connectionParams;
    private String login;
    private String pass;
    private String driver;
    private String connectionString;
    private String host;
    private String port;

    public SqlConfig(){}

    public SqlConfig(SqlConfigBuilder build){
        this.login = build.getLogin();
        this.pass = build.getPass();
        this.driver = build.getDriver();
        this.connectionParams = build.getConnectionParams();
        this.connectionString = build.getConnectionString();
        this.host = build.getHost();
        this.port = build.getPort();
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getConnectionParams() {
        return connectionParams;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }

    public String getConnectionString() {
        return connectionString;
    }
}
