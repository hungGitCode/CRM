package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.model.UsersRoles;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
import com.cybersoft.crm.repository.UsersRepository;
import com.cybersoft.crm.repository.UsersRepositoryImp;

import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.UserService;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userpage",urlPatterns = "/user-page")
public class UserPage extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersRepository usersRepository = new UsersRepositoryImp();
        UserService userService = new UserService(usersRepository);
        List<UsersRoles> listUsersRoles =  userService.getAllUsersAndRoleDescription();
        req.setAttribute("AllUsersRoles", listUsersRoles);
        req.getRequestDispatcher("/user-table.jsp").forward(req,resp);
    }
}
