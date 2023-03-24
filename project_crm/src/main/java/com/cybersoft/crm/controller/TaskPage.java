package com.cybersoft.crm.controller;

import com.cybersoft.crm.DTO.TaskDetailDTO;
import com.cybersoft.crm.repository.TaskRepository;
import com.cybersoft.crm.repository.TaskRepositoryImp;
import com.cybersoft.crm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "taskPage",urlPatterns = {"/task-page"})
public class TaskPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskRepository taskRepository = new TaskRepositoryImp();
        TaskService taskService = new TaskService(taskRepository);
        List<TaskDetailDTO> taskDetailDTOList = taskService.getAllTask();
        req.setAttribute("taskList",taskDetailDTOList);
        req.getRequestDispatcher("task.jsp").forward(req,resp);
    }
}
