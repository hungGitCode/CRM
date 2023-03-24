package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.repository.JobsRepository;
import com.cybersoft.crm.repository.JobsRepositoryImp;
import com.cybersoft.crm.repository.TaskRepository;
import com.cybersoft.crm.repository.TaskRepositoryImp;
import com.cybersoft.crm.service.JobsService;
import com.cybersoft.crm.service.TaskService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "taskAddApi",urlPatterns ={"/api/task-add"})
public class TaskAddApi extends HttpServlet {
    private TaskRepository taskRepository = new TaskRepositoryImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        TaskService taskService = new TaskService(taskRepository);
        ResponseData taskAddReponseData = new ResponseData();
        Boolean isSuccess = false;

        isSuccess = taskService.addTask(req.getParameter("name"),
                req.getParameter("startDate"),
                req.getParameter("endDate"),
                Integer.parseInt(req.getParameter("userId")),
                Integer.parseInt(req.getParameter("jobId")),
                Integer.parseInt(req.getParameter("statusId")));

        taskAddReponseData.setSuccess(isSuccess);
        taskAddReponseData.setStatus(isSuccess ?200:404);
        taskAddReponseData.setDescription(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        System.out.println(taskAddReponseData.getDescription());
        Gson gson = new Gson();
        String json = gson.toJson(taskAddReponseData);
        out.print(json);
        out.flush();
    }
}
