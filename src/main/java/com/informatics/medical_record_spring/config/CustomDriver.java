package com.informatics.medical_record_spring.config;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;


import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class CustomDriver implements Driver {

    static {
        try {
            DriverManager.registerDriver(new CustomDriver());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register CustomDriver", e);
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (acceptsURL(url)) {
            String username = info.getProperty("user");
            String password = info.getProperty("password");
            return createConnection(url, username, password);
        }
        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith("jdbc:customdb:");
    }

    private Connection createConnection(String url, String username, String password) throws SQLException {
        System.out.println("Connecting to database...");
        return DriverManager.getConnection(url.replace("customdb", "mysql"), username, password);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 1;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
}