package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.StatusModel;
import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.*;
import com.cybersoft.crm.service.JobsService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "taskEdit",urlPatterns = {"/task-edit"})
public class TaskEditPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
//        System.out.println("getting id : " +id);
        if(id!=null&&id.trim().length()!=0) {
            TaskRepository taskRepository = new TaskRepositoryImp();
            TaskService taskService = new TaskService(taskRepository);
            TaskModel taskModel = taskService.getTaskModelById(Integer.parseInt(id));

            req.setAttribute("taskModel", taskModel);

            UsersRepository usersRepository = new UsersRepositoryImp();
            UserService userService = new UserService(usersRepository);
            List<UsersModel> userModelList = userService.getAllUsers();
            req.setAttribute("userModelList",userModelList);

            JobsRepository jobsRepository = new JobsRepositoryImp();
            JobsService jobsService = new JobsService(jobsRepository);
            List<JobsModel> jobsModelList = jobsService.getJobs();
            req.setAttribute("jobsModelList",jobsModelList);

            StatusRepository statusRepository = new StatusRepositoryImp();
            List<StatusModel> statusModelList = statusRepository.getAllStatus();
            req.setAttribute("statusModelList",statusModelList);

            System.out.println("Send attribute task success");
        }
            req.getRequestDispatcher("task-edit.jsp").forward(req, resp);

    }


}

