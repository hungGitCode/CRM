package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.JobsRepository;
import com.cybersoft.crm.repository.JobsRepositoryImp;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
import com.cybersoft.crm.service.JobsService;
import com.cybersoft.crm.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "groupWorkEdit",urlPatterns = {"/groupwork-edit"})
public class GroupWorkEditJobPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        System.out.println("getting id : " +id);
        if(id!=null&&id.trim().length()!=0) {
            JobsRepository jobsRepository = new JobsRepositoryImp();
            JobsService jobsService = new JobsService(jobsRepository);
            JobsModel jobsModel = jobsService.getJobById(Integer.parseInt(id));
//        RoleModel roleModel = roleService.getRoleById(11);
            req.setAttribute("job", jobsModel);
            System.out.println("Send attribute job success");
        }
            req.getRequestDispatcher("groupwork-edit.jsp").forward(req, resp);

    }


}

