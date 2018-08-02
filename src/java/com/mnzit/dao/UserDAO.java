/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.dao;

import com.mnzit.model.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface UserDAO {

    int createUser(User u) throws ClassNotFoundException, SQLException;

    int updateUser(User u) throws ClassNotFoundException, SQLException;

    int deleteUser(User u) throws ClassNotFoundException, SQLException;

    List<User> allUsers() throws ClassNotFoundException, SQLException;

    List<User> getUserById(int id) throws ClassNotFoundException, SQLException;

}
