package com.esgi.al1.blogws.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Romaaan on 18/03/2017.
 */
@Configuration
@EnableCaching
@PropertySource("application.properties")
@ComponentScan(basePackages = {"com.esgi.al1.blogws"})
public class Settings {

    public static final String ProductionProfile = "Production";
    public static final String TestProfile = "Test";
    public static final Calendar startTime = Calendar.getInstance();

    private final Environment env;

    private SqlConfig sqlconf;

    private DataBase dataBase;
    private DataBase dataBaseTest;

    private static final HashMap<String,String> srvProperties = new HashMap<>(16);

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
    private void initServerSettings(){
        srvProperties.put("LogEnabled","1");
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

    public static HashMap<String,String> getServerProperties(){
        return srvProperties;
    }
}
