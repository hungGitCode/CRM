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

@WebServlet(name = "roleEdit",urlPatterns = {"/role-edit"})
public class RoleEditPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Running edit api role....");
        String id = req.getParameter("id");
//        System.out.println("getting id : " +id);
        if(id!=null&&id.trim().length()!=0) {
            RoleRepository roleRepository = new RoleRepositoryImp();
            RoleService roleService = new RoleService(roleRepository);
            RoleModel roleModel = roleService.getRoleById(Integer.parseInt(req.getParameter("id")));
//        RoleModel roleModel = roleService.getRoleById(11);
            req.setAttribute("role", roleModel);
//            System.out.println("Running edit api role....2");
        }
            req.getRequestDispatcher("role-edit.jsp").forward(req, resp);

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Running edit api role....");
//        String id = req.getParameter("id");
//        System.out.println("getting id : " +id);
//        if(id!=null&&id.trim().length()!=0) {
//            RoleRepository roleRepository = new RoleRepositoryImp();
//            RoleService roleService = new RoleService(roleRepository);
////            RoleModel roleModel = roleService.getRoleById(Integer.parseInt(req.getParameter("id")));
//        RoleModel roleModel = roleService.getRoleById(11);
//            req.setAttribute("role", roleModel);
//            System.out.println("Running edit api role....2");
//        }
//
//    }

}

