package com.esgi.al1.blogws.models;

import java.io.Serializable;

/**
 * Created by Romaaan on 30/03/2017.
 */
public class SystemInfo implements Serializable {

    private String os;
    private String osVersion;
    private String osArch;
    private String country;
    private String locale;

    public SystemInfo() {
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
