package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.repository.JobsRepository;
import com.cybersoft.crm.repository.JobsRepositoryImp;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "groupworkEditApi",urlPatterns = {"/api/groupwork-edit"})
public class GroupWorkEditApi extends HttpServlet {

    private JobsRepository jobsRepository = new JobsRepositoryImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("Job Edit Api is running..." );
        JobsService jobsService = new JobsService(jobsRepository);
        ResponseData jobUpdateResponseData = new ResponseData();
        boolean isSuccess = false;
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        isSuccess = jobsService.updateJob(req.getParameter("name"), startDate, endDate, Integer.parseInt(req.getParameter("id")));
        System.out.println("Done if method");

        jobUpdateResponseData.setSuccess(isSuccess);
        jobUpdateResponseData.setStatus(isSuccess ?200:404);
        jobUpdateResponseData.setDescription(isSuccess ? "Update thành công" : "Update thất bại");
//        Cookie cookie = new Cookie("message", roleUpdateResponseData.getDescription());
//        cookie.setMaxAge(60);
//        resp.addCookie(cookie);
        System.out.println(jobUpdateResponseData.getDescription());
        Gson gson = new Gson();
        String json = gson.toJson(jobUpdateResponseData);
        out.print(json);
        out.flush();
    }

}
