package com.inatec.pgw.indepotance.storage.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Anatoly Chernysh
 */
public class DataSource {
//    public static final String HOST = "192.168.20.187";
    public static final String HOST = "127.0.0.1";
//    public static final String PORT = "3308";
    public static final String PORT = "3306";

    private static DataSource datasource;

    private BasicDataSource ds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setUsername("inatec");
        ds.setPassword("inatec");
        ds.setUrl("jdbc:mariadb://" + HOST + ":" + PORT + "/inatec");

        // the settings below are optional -- dbcp can work with defaults
        ds.setMinIdle(5);
        ds.setMaxIdle(50);
        ds.setMaxOpenPreparedStatements(180);
    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
}