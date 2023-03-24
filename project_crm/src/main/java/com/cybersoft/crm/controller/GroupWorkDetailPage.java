package com.cybersoft.crm.controller;

import com.cybersoft.crm.DTO.UserGroupworkDetailDTO;
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

@WebServlet(name = "groupWorkDetail",urlPatterns = {"/groupwork-details"})
public class GroupWorkDetailPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println("getting id : " +id);
        if(id!=null&&id.trim().length()!=0) {
            JobsRepository jobsRepository = new JobsRepositoryImp();
            JobsService jobsService = new JobsService(jobsRepository);
            TaskRepository taskRepository = new TaskRepositoryImp();
            TaskService taskService = new TaskService( taskRepository);
            UsersRepository usersRepository = new UsersRepositoryImp();
            UserService userService = new UserService(usersRepository);


            List<QualitiesModel> qualitiesModelList = jobsService.getQuantitiesByJobId(Integer.parseInt(id));
            req.setAttribute("qualitiesModelList",qualitiesModelList);

            List<UsersModel> usersModelList = userService.getUsersByJobId(Integer.parseInt(id));
            req.setAttribute("usersModelList",usersModelList);
            List<List<UserGroupworkDetailDTO>> listList = new ArrayList<>();

            for(UsersModel usersModel : usersModelList){
//                System.out.print(usersModel.getId());
                List<UserGroupworkDetailDTO> userGroupworkDetailDTOList = taskService.getTaskDetailByJobIdAndUserIdAndStatusId(Integer.parseInt(id),usersModel.getId())  ;
                listList.add(userGroupworkDetailDTOList);
//                listList.get(usersModelList.indexOf(usersModel));
            }
            req.setAttribute("listList",listList);

        }
    req.getRequestDispatcher("/groupwork-details.jsp").forward(req,resp);
    }
}
