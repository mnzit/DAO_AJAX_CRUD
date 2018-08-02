/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.dao.daoImpl;

import com.mnzit.dao.UserDAO;
import com.mnzit.model.User;
import com.mnzit.util.SingletonConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class UserDAOImpl implements UserDAO {

    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    public SingletonConnection con() throws SQLException, ClassNotFoundException {
        SingletonConnection con = SingletonConnection.getInstance();
        return con;
    }

    @Override
    public int createUser(User u) throws ClassNotFoundException, SQLException {
        con().getConnection();
        pstm = con().preparedStm("INSERT INTO user(name,address,phone_no,email) VALUES(?,?,?,?)");
        pstm.setString(1, u.getName());
        pstm.setString(2, u.getAddress());
        pstm.setString(3, u.getPhoneNo());
        pstm.setString(4, u.getEmail());
        if (con().update() == 1) {
            return con().generatedId();
        }
        con().close();
        return 0;

    }

    @Override
    public int updateUser(User u) throws ClassNotFoundException, SQLException {
        con().getConnection();
        pstm = con().preparedStm("UPDATE user SET name=?,address=?,phone_no=?,email=? WHERE user_id=?");
        pstm.setString(1, u.getName());
        pstm.setString(2, u.getAddress());
        pstm.setString(3, u.getPhoneNo());
        pstm.setString(4, u.getEmail());
        pstm.setInt(5, u.getUserId());
        if (con().update() == 1) {
            return 1;
        }
        con().close();
        return 0;
    }

    @Override
    public int deleteUser(User u) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> allUsers() throws ClassNotFoundException, SQLException {
        List<User> allUsers = new ArrayList<>();
        pstm = con().preparedStm("SELECT * FROM user");
        rs = con().retrieve();
        while (rs.next()) {
            User u = new User();
            u.setUserId(rs.getInt(1));
            u.setName(rs.getString(2));
            u.setAddress(rs.getString(3));
            u.setPhoneNo(rs.getString(4));
            u.setEmail(rs.getString(5));
            allUsers.add(u);
        }
        return allUsers;

    }

    @Override
    public List<User> getUserById(int id) throws ClassNotFoundException, SQLException {
        List<User> allUsers = new ArrayList<>();
        pstm = con().preparedStm("SELECT * FROM user WHERE user_id=?");
        pstm.setInt(1, id);
        rs = con().retrieve();
        while (rs.next()) {
            User u = new User();
            u.setUserId(rs.getInt(1));
            u.setName(rs.getString(2));
            u.setAddress(rs.getString(3));
            u.setPhoneNo(rs.getString(4));
            u.setEmail(rs.getString(5));
            allUsers.add(u);
        }
        return allUsers;
    }

}
