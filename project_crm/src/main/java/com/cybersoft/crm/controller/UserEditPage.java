package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
import com.cybersoft.crm.repository.UsersRepository;
import com.cybersoft.crm.repository.UsersRepositoryImp;
import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userEdit",urlPatterns = {"/user-edit"})
public class UserEditPage extends HttpServlet {
    RoleRepository roleRepository = new RoleRepositoryImp();
    RoleService roleService = new RoleService(roleRepository);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id!=null&&id.trim().length()!=0) {

            UsersRepository usersRepository = new UsersRepositoryImp();
            UserService userService = new UserService(usersRepository);
            UsersModel usersModel = userService.getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", usersModel);
        }
        List<RoleModel> listRoles = roleService.getAllRoles();
        req.setAttribute("listRoles",listRoles);

        req.getRequestDispatcher("user-edit.jsp").forward(req,resp);
    }
}
