package com.esgi.al1.blogws.utils;

import ch.qos.logback.core.net.LoginAuthenticator;
import com.esgi.al1.blogws.models.SqlConfig;

/**
 * Created by Romaaan on 24/03/2017.
 */
public class SqlConfigBuilder {
    
    private String connectionParams;
    private String login;
    private String pass;
    private String driver;
    private String connectionString;
    private String host;
    private String port;

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

    public SqlConfigBuilder buildConnectionParams(String connectionParams) {
        this.connectionParams = connectionParams;
        return this;
    }

    public SqlConfigBuilder buildLogin(String login) {
        this.login = login;
        return this;
    }

    public SqlConfigBuilder buildPass(String pass) {
        this.pass = pass;
        return this;
    }

    public SqlConfigBuilder buildDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public SqlConfigBuilder buildHost(String host) {
        this.host = host;
        return this;
    }

    public SqlConfigBuilder buildPort(String port) {
        this.port = port;
        return this;
    }
    
    public SqlConfig build () {
        this.connectionString = driver + "://" + host + ":" + port + "?user=" + login + "&password=" + pass + "&" + connectionParams;
        return new SqlConfig(this);
    }
}
