package com.zarebcn.api.movies.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Util methods for using the database
 */
public class DbUtil {

    /** Opens a {@link Connection} using the configuration in jdbc.properties */
    public static Connection getConnection() throws SQLException {

        Properties props = loadJdbcProperties("jdbc.properties");

        String host = props.getProperty("localhost");
        String schema = props.getProperty("movies");
        String user = props.getProperty("root");
        String pwd = props.getProperty("root");

        return DriverManager.getConnection(
                "jdbc:mysql://" + host + "/" + schema + "?user=" + user + "&password=" + pwd);
    }

    /** Creates a {@link DataSource} using the configuration in jdbc.properties */
    public static DataSource getDataSource() throws SQLException {

        Properties props = loadJdbcProperties("jdbc.properties");

        String host = props.getProperty("localhost");
        String schema = props.getProperty("movies");
        String user = props.getProperty("root");
        String pwd = props.getProperty("root");

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + host + "/" + schema);
        dataSource.setUser(user);
        dataSource.setPassword(pwd);

        return dataSource;
    }

    /** Loads JDBC properties from a file in the specified file */
    private static Properties loadJdbcProperties(String fileName) {

        try {
            Properties props = new Properties();
            props.load(new FileReader(fileName));
            return props;
        } catch (IOException e) {
            throw new RuntimeException("JDBC properties could no be loaded from " + fileName, e);
        }
    }
}
