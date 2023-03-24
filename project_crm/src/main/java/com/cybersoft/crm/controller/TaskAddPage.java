package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.JobsRepository;
import com.cybersoft.crm.repository.JobsRepositoryImp;
import com.cybersoft.crm.repository.UsersRepository;
import com.cybersoft.crm.repository.UsersRepositoryImp;
import com.cybersoft.crm.service.JobsService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "taskAdd",urlPatterns = {"/task-add"})
public class TaskAddPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobsRepository jobsRepository = new JobsRepositoryImp();
        JobsService jobsService = new JobsService(jobsRepository);
        List<JobsModel> jobsModelList = jobsService.getJobs();
        req.setAttribute("jobsModelList",jobsModelList);

        UsersRepository usersRepository = new UsersRepositoryImp();
        UserService userService = new UserService(usersRepository);
        List<UsersModel> userModelList = userService.getAllUsers();
        req.setAttribute("userModelList",userModelList);

    req.getRequestDispatcher("/task-add.jsp").forward(req,resp);
    }
}
