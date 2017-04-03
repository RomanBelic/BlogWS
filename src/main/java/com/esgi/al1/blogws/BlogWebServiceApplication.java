package com.esgi.al1.blogws;

import com.esgi.al1.blogws.utils.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import java.util.Date;

@SpringBootApplication
public class BlogWebServiceApplication {
	//test
	//Commit from Chris
    //Second commit from Chris
    //Commit de Roman

	public static void main(String[] args) {
		Settings.startTime.setTime(new Date());
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, Settings.ProductionProfile);
		SpringApplication.run(BlogWebServiceApplication.class, args);
	}
}
