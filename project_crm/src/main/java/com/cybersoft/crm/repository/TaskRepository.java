package com.cybersoft.crm.repository;

import com.cybersoft.crm.DTO.TaskDetailDTO;
import com.cybersoft.crm.DTO.UserGroupworkDetailDTO;
import com.cybersoft.crm.model.TaskModel;

import java.util.List;

public interface TaskRepository {
    public List<TaskDetailDTO> getAllTask();
    public int updateTask(String name, String startDate,String endDate, int userId, int jobId, int statusId,int id);

    public TaskModel getTaskModelById(int id);
    public int deleteTaskById(int id);
    public int addTask(String name, String startDate,String endDate, int userId, int jobId, int statusId);
    public List<TaskModel> getTaskModelsByJobId(int id);

    public List<UserGroupworkDetailDTO> getTaskDetailByJobIdAndUserIdAndStatusId(int jobId, int userId);

}
