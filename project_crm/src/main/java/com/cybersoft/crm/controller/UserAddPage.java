package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
import com.cybersoft.crm.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "useradd",urlPatterns =  {"/user-add"})
public class UserAddPage extends HttpServlet {
    RoleRepository roleRepository = new RoleRepositoryImp();
    RoleService roleService = new RoleService(roleRepository);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> listRoles = roleService.getAllRoles();
        req.setAttribute("listRoles",listRoles);
        req.getRequestDispatcher("user-add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> listRoles = roleService.getAllRoles();
        req.setAttribute("listRoles",listRoles);
        req.getRequestDispatcher("user-add.jsp").forward(req,resp);
    }
}
