package com.esgi.al1.blogws.utils;

import com.esgi.al1.blogws.models.SqlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

import static com.esgi.al1.blogws.utils.Settings.ProductionProfile;

/**
 * Created by Romaaan on 18/03/2017.
 */
@Configuration
@PropertySource("application.properties")
public class Settings {

    public static final String ProductionProfile = "Production";
    public static final String TestProfile = "Test";

    private final Environment env;

    private SqlConfig sqlconf;

    @Autowired
    public Settings(Environment env) {
        this.env = env;
    }

    @PostConstruct
    @Profile(ProductionProfile)
    private void initSetting() {
        sqlconf = new SqlConfigBuilder().
                buildDriver(env.getProperty("connectionstring.driver", "jdbc:mysql")).
                buildHost(env.getProperty("connectionstring.host","localhost")).
                buildPort(env.getProperty("connectionstring.sqlport","3306")).
                buildLogin(env.getProperty("connectionstring.login","root")).
                buildPass(env.getProperty("connectionstring.pass","")).
                buildConnectionParams(env.getProperty("connectionstring.dbparams")).build();
    }

    @Bean
    public SqlConfig getSqlConfig(){
        return sqlconf;
    }

}
