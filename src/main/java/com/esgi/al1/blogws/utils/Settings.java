package com.esgi.al1.blogws.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

/**
 * Created by Romaaan on 18/03/2017.
 */
@Configuration
@EnableCaching
@PropertySource("application.properties")
public class Settings {

    public static final String ProductionProfile = "Production";
    public static final String TestProfile = "Test";

    private final Environment env;

    private SqlConfig sqlconf;

    private DataBase dataBase;
    private DataBase dataBaseTest;

    @Autowired
    public Settings(Environment env) {
        this.env = env;
    }

    @PostConstruct
    private void initSqlSettings() {
        sqlconf = new SqlConfigBuilder().
                buildDriver(env.getProperty("connectionstring.driver", "jdbc:mysql")).
                buildHost(env.getProperty("connectionstring.host","localhost")).
                buildPort(env.getProperty("connectionstring.sqlport","3306")).
                buildLogin(env.getProperty("connectionstring.login","root")).
                buildPass(env.getProperty("connectionstring.pass","")).
                buildConnectionParams(env.getProperty("connectionstring.dbparams")).build();
    }

    @PostConstruct
    @Profile(ProductionProfile)
    private void initDBProd (){
        dataBase = new DataBase(env.getProperty("database.prod","blogws"));
    }

    @PostConstruct
    @Profile(TestProfile)
    private void initDBTest (){
        dataBaseTest = new DataBase(env.getProperty("database.test","blogws_test"));
    }

    @Bean
    public SqlConfig getSqlConfig(){
        return sqlconf;
    }

    @Bean
    @Profile(ProductionProfile)
    public DataBase getDataBase(){
        return dataBase;
    }

    @Bean
    @Profile(TestProfile)
    public DataBase getDataBaseTest(){
        return dataBaseTest;
    }
}
