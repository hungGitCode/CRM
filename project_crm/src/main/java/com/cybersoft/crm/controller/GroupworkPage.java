package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.JobsModel;
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
import java.util.List;

@WebServlet(name = "groupWorkPage",urlPatterns = {"/groupwork"})
public class GroupworkPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobsRepository jobsRepository = new JobsRepositoryImp();
        JobsService jobsService = new JobsService(jobsRepository);
        List<JobsModel> jobsModelList = jobsService.getJobs();
        req.setAttribute("ListJobs",jobsModelList);
//        for(JobsModel jobsModel : jobsModelList){
//            System.out.println(jobsModel.getStartDate());
//            System.out.println(jobsModel.getEndDate());
//            req.setAttribute("test",jobsModel.getStartDate());
//        }

        req.getRequestDispatcher("/groupwork.jsp").forward(req,resp);
    }
}
