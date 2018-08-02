/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mnzit.dao.UserDAO;
import com.mnzit.dao.daoImpl.UserDAOImpl;
import com.mnzit.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "UserUpdateController", urlPatterns = {"/UserUpdateController"})
public class UserUpdateController extends HttpServlet {

    User u = new User();
    UserDAO uDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("userId"));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(uDAO.getUserById(id)));
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            response.sendRedirect("404.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phoneno = request.getParameter("phone_no");
            u.setName(name);
            u.setAddress(address);
            u.setEmail(email);
            u.setPhoneNo(phoneno);
            int i = uDAO.createUser(u);
            if (i != 0) {
                map.put("id", i);
                map.put("name", name);
                map.put("address", address);
                map.put("email", email);
                map.put("phoneno", phoneno);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(map));
            } else {
                response.sendRedirect("404.jsp");
            }
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
