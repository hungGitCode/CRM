package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.repository.JobsRepository;
import com.cybersoft.crm.repository.JobsRepositoryImp;
import com.cybersoft.crm.service.JobsService;
import com.cybersoft.crm.service.RoleService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "groupWorkAddApi",urlPatterns ={"/api/groupwork-add"})
public class GroupWorkAddApi extends HttpServlet {
    private JobsRepository jobsRepository  = new JobsRepositoryImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JobsService jobsService = new JobsService(jobsRepository);
        ResponseData jobAddReponseData = new ResponseData();
        Boolean isSuccess = false;

        isSuccess = jobsService.addJob(req.getParameter("name"),req.getParameter("startDate"),req.getParameter("endDate"));

        jobAddReponseData.setSuccess(isSuccess);
        jobAddReponseData.setStatus(isSuccess ?200:404);
        jobAddReponseData.setDescription(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        System.out.println(jobAddReponseData.getDescription());
        Gson gson = new Gson();
        String json = gson.toJson(jobAddReponseData);
        out.print(json);
        out.flush();
    }
}
