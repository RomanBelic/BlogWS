package com.esgi.al1.blogws.models;

import java.io.Serializable;

/**
 * Created by Romaaan on 01/04/2017.
 */
public class ServerInfo implements Serializable {

    private String daysUp;
    private String hoursUp;
    private String ipAdress;
    private String portAdress;
    private String serverName;

    public ServerInfo() {
    }

    public String getDaysUp() {
        return daysUp;
    }

    public void setDaysUp(String daysUp) {
        this.daysUp = daysUp;
    }

    public String getHoursUp() {
        return hoursUp;
    }

    public void setHoursUp(String hoursUp) {
        this.hoursUp = hoursUp;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getPortAdress() {
        return portAdress;
    }

    public void setPortAdress(String portAdress) {
        this.portAdress = portAdress;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
