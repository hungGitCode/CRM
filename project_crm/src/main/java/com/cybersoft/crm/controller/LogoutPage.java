package com.cybersoft.crm.controller;

import com.cybersoft.crm.repository.UsersRepository;
import com.cybersoft.crm.repository.UsersRepositoryImp;
import com.cybersoft.crm.service.LoginService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logoutPage", urlPatterns = {"/logout"})
public class LogoutPage extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

}

