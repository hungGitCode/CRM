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

@WebServlet(name = "taskEditApi",urlPatterns = {"/api/task-edit"})
public class TaskEditApi extends HttpServlet {

    private TaskRepository taskRepository = new TaskRepositoryImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("Task Edit Api is running..." );
        TaskService taskService = new TaskService(taskRepository);
        ResponseData taskUpdateResponseData = new ResponseData();
        boolean isSuccess = false;
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
//       if(JobsRepositoryImp.checkDate(startDate)&&JobsRepositoryImp.checkDate(endDate)&&
//               JobsRepositoryImp.compareDate(startDate,endDate)<=0) {
               isSuccess = taskService.updateTask(req.getParameter("name"),startDate,endDate,
                                                 Integer.parseInt(req.getParameter("userId")),
                                                 Integer.parseInt(req.getParameter("jobId")),
                                                 Integer.parseInt(req.getParameter("statusId")),
                                                 Integer.parseInt(req.getParameter("id")));
//               System.out.println("Done if method : + name : " + req.getParameter("name") + " ;start : " +
//                       startDate + " ; end : " + endDate + " ; userId: " +  Integer.parseInt(req.getParameter("userId")) + " ;JobId : " + Integer.parseInt(req.getParameter("jobId"))
//                       + " ; statusID " + Integer.parseInt(req.getParameter("statusId"))  + " ; task id " + Integer.parseInt(req.getParameter("id")));
//       }
        taskUpdateResponseData.setSuccess(isSuccess);
        taskUpdateResponseData.setStatus(isSuccess ?200:404);
        taskUpdateResponseData.setDescription(isSuccess ? "Update thành công" : "Update thất bại");
//        Cookie cookie = new Cookie("message", roleUpdateResponseData.getDescription());
//        cookie.setMaxAge(60);
//        resp.addCookie(cookie);
        System.out.println(taskUpdateResponseData.getDescription());
        Gson gson = new Gson();
        String json = gson.toJson(taskUpdateResponseData);
        out.print(json);
        out.flush();
    }

}
