package com.cybersoft.crm.controller;

import com.cybersoft.crm.DTO.UserGroupworkDetailDTO;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.QualitiesModel;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userDetail",urlPatterns = {"/user-details"})
public class UserDetailPage extends HttpServlet {
    private UsersRepository usersRepository = new UsersRepositoryImp();
    private UserService userService = new UserService(usersRepository);
    private JobsRepository jobsRepository = new JobsRepositoryImp();
    private JobsService jobsService = new JobsService(jobsRepository);
    private TaskRepository taskRepository = new TaskRepositoryImp();
    private TaskService taskService = new TaskService( taskRepository);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println("getting id : " +id);
        if(id!=null&&id.trim().length()!=0) {

            UsersModel usersModel = userService.getUserById(Integer.parseInt(id));
            req.setAttribute("usersModel",usersModel);

            List<QualitiesModel> qualitiesModelList = jobsService.getQuantitiesByUserId(Integer.parseInt(id));
            req.setAttribute("qualitiesModelList",qualitiesModelList);

            List<JobsModel> jobsModelList = jobsRepository.getJobsByUserId(Integer.parseInt(id));
            req.setAttribute("jobsModelList",jobsModelList);

            List<List<UserGroupworkDetailDTO>> listList = new ArrayList<>();

            for(JobsModel jobsModel : jobsModelList){
                List<UserGroupworkDetailDTO> userGroupworkDetailDTOList =
                        taskService.getTaskDetailByJobIdAndUserIdAndStatusId(jobsModel.getId(),Integer.parseInt(id));
                listList.add(userGroupworkDetailDTOList);
            }
            req.setAttribute("listList",listList);
        }
        req.getRequestDispatcher("/user-details.jsp").forward(req,resp);
    }
}
