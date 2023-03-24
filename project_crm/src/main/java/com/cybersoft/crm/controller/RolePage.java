package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
import com.cybersoft.crm.service.RoleService;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "rolePage",urlPatterns = {"/role-page"})
public class RolePage extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleRepository roleRepository = new RoleRepositoryImp();
        RoleService roleService = new RoleService(roleRepository);
        List<RoleModel> listRoles = roleService.getAllRoles();
        req.setAttribute("roles",listRoles);
        req.getRequestDispatcher("/role-table.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
