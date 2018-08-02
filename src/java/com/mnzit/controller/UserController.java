/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.controller;

import com.google.gson.Gson;
import com.mnzit.dao.UserDAO;
import com.mnzit.dao.daoImpl.UserDAOImpl;
import com.mnzit.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    User u = new User();
    UserDAO uDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("userlist", uDAO.allUsers());
            RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
            rd.forward(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            response.sendRedirect("404.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String userId = request.getParameter("userId");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phoneno = request.getParameter("phone_no");
            u.setName(name);
            u.setAddress(address);
            u.setEmail(email);
            u.setPhoneNo(phoneno);
            if (userId == null || userId.isEmpty()) {
                int i = uDAO.createUser(u);
                Map<String, Object> map = new HashMap<String, Object>();
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
            } else {
                int id = Integer.parseInt(userId);
                u.setUserId(id);
                if (uDAO.updateUser(u) == 1) {

                } else {
                    response.sendRedirect("404.jsp");
                }
            }
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
