package com.lrh.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/13 15:24
 */
public class DBUtils {

    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    public static Connection connection = null;

    public synchronized static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.error("load {} error", DBUtils.class.getClassLoader().getResource("").getPath() + "jdbc.properties not exits", e);
            throw new RuntimeException(e);
        }
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String driverClassName = properties.getProperty("jdbc.driver-class-name");
        Objects.requireNonNull(url, "jdbc.url must not be null");
        Objects.requireNonNull(username, "jdbc.username");
        Objects.requireNonNull(password, "jdbc.password");
        Objects.requireNonNull(driverClassName, "jdbc.driver-class-name");

        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("jdbc connect error. \n {}", e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection=getConnection();
        try {
            System.out.println(connection.getSchema());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
