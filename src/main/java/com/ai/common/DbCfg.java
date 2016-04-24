package com.ai.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by Aaron on 2016/4/21.
 */
public class DbCfg {
    @XmlElement(name = "DBUser")
    private String dbUser;
    @XmlElement(name = "DBPassword")
    private String dbPassword;
    @XmlElement(name = "JDBCUrl")
    private String jdbcUrl;
    @XmlElement(name = "Driver")
    private String driver;

    public DbCfg() {
    }

    public DbCfg(String dbUser, String dbPassword, String jdbcUrl, String driver) {
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.jdbcUrl = jdbcUrl;
        this.driver = driver;
    }

    @XmlTransient
    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    @XmlTransient
    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @XmlTransient
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    @XmlTransient
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
