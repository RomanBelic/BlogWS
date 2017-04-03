package com.esgi.al1.blogws.dao;

import com.esgi.al1.blogws.interfaces.IInfoReader;
import com.esgi.al1.blogws.models.ServerInfo;
import com.esgi.al1.blogws.models.SystemInfo;
import com.esgi.al1.blogws.utils.Log;
import com.esgi.al1.blogws.utils.Settings;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romaaan on 30/03/2017.
 */
@Repository
public class SystemRepository {

    private final IInfoReader<SystemInfo> sysInfReader = () -> {
        SystemInfo info = new SystemInfo();
        info.setOsArch(System.getProperty("os.arch"));
        info.setOs(System.getProperty("os.name"));
        info.setOsVersion(System.getProperty("os.version"));
        info.setLocale(System.getProperty("user.language"));
        info.setCountry(System.getProperty("user.country"));
        return info;
    };

    private final IInfoReader<ServerInfo> srvInfReader = () -> {
        ServerInfo info = new ServerInfo();
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            int daysUp = now.get(Calendar.DAY_OF_YEAR) - Settings.startTime.get(Calendar.DAY_OF_YEAR);
            int hoursUp = (daysUp * 24) + now.get(Calendar.HOUR_OF_DAY);
            info.setDaysUp(String.valueOf(daysUp));
            info.setHoursUp(String.valueOf(hoursUp));
            info.setIpAdress(InetAddress.getLocalHost().getHostAddress());
            info.setServerName("Tomcat 9.0.0.M17");
            info.setPortAdress("8080");
            return info;
        }catch (UnknownHostException e){
            Log.err(e.getMessage());
            info.setIpAdress(e.getMessage());
        }
        return info;
    };


    public int setSystemProperties(HashMap<String,String> properties){
        try {
            for (Map.Entry<String, String> prop : properties.entrySet()) {
                System.setProperty(prop.getKey(), prop.getValue());
            }
            return HttpServletResponse.SC_OK;
        }catch(Exception e){
            Log.err("System Repository : " + e.getMessage());
            return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
    }

    public ServerInfo getServerInfo(){
        return srvInfReader.getInfo();
    }

    public SystemInfo getSystemInfo(){
        return sysInfReader.getInfo();
    }

}
