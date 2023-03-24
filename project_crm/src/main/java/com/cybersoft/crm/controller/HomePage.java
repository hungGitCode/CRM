package com.cybersoft.crm.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "homePage",urlPatterns = {"/home"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        System.out.println("test..." +session.getAttribute("RoleId") );
//        System.out.println("test..." +session.getAttribute("UserId") );
//        System.out.println("test..." +session.getAttribute("UserName") );
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
