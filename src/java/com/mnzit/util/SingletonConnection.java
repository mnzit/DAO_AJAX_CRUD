/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.naming.java.javaURLContextFactory;

/**
 *
 * @author Dell
 */
public class SingletonConnection {

    private static SingletonConnection instance;
    private Connection con = null;
    private final ConnectionConstants conCons = new ConnectionConstants();
    private PreparedStatement pstm = null;

    public SingletonConnection() throws ClassNotFoundException, SQLException {
        Class.forName(conCons.DRIVER_NAME);
        con = DriverManager.getConnection(conCons.DB_URL, conCons.DB_USERNAME, conCons.DB_PASSWORD);
    }

    public Connection getConnection() {
        return con;
    }

    public static SingletonConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SingletonConnection();
            System.out.println("New connection made because no connection as yet instantiated");
        } else if (instance.getConnection().isClosed()) {
            instance = new SingletonConnection();
            System.out.println("New connection made because connection was closed");
        }
        return instance;
    }

    public void close() throws ClassNotFoundException, SQLException {
        if (getInstance().getConnection() != null && !getInstance().getConnection().isClosed()) {
            getInstance().getConnection().close();
        }
    }

    public PreparedStatement preparedStm(String query) throws SQLException, ClassNotFoundException {
        pstm = getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return pstm;
    }

    public int update() throws SQLException {
        return pstm.executeUpdate();
    }

    public ResultSet retrieve() throws SQLException {
        return pstm.executeQuery();
    }

    public int generatedId() throws SQLException {
        ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()) {
            int key = rs.getInt(1);
            return key;
        }
        return -1;
    }
}
