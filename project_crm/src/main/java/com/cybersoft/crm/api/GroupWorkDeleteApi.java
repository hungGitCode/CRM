package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.repository.JobsRepository;
import com.cybersoft.crm.repository.JobsRepositoryImp;
import com.cybersoft.crm.service.JobsService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "groupworkdeleteapi", urlPatterns = {"/api/groupwork-delete"})
public class GroupWorkDeleteApi extends HttpServlet {

    private JobsRepository jobsRepository = new JobsRepositoryImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        JobsService jobsService = new JobsService(jobsRepository);
        System.out.println("Running api delete job by ID");
        boolean isSuccess = jobsService.deleteJobById(id);
        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess?"Xoá thành công":"Xoá thất bại");
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        out.print(json);
        out.flush();

    }
}
