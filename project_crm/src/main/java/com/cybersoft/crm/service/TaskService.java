package com.cybersoft.crm.service;

import com.cybersoft.crm.DTO.TaskDetailDTO;
import com.cybersoft.crm.DTO.UserGroupworkDetailDTO;
import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<TaskDetailDTO> getAllTask(){
        return taskRepository.getAllTask();
    }
    public boolean updateTask(String name, String startDate, String endDate, int userId, int jobId, int statusId,int id){
        int result = taskRepository.updateTask(name,startDate,endDate,userId,jobId,statusId,id);
        return result>0?true:false;
    }

    public TaskModel getTaskModelById(int id){
        return taskRepository.getTaskModelById(id);
    }
    public boolean deleteTaskById(int id) {
        int result = taskRepository.deleteTaskById(id);
        return result>0?true:false;
    }
    public boolean addTask(String name, String startDate, String endDate, int userId, int jobId, int statusId) {
        int result= taskRepository.addTask(name, startDate, endDate, userId, jobId, statusId);
        return result>0?true:false;
    }
    public List<TaskModel> getTaskModelsByJobId(int id) {
        return taskRepository.getTaskModelsByJobId(id);
    }
    public List<UserGroupworkDetailDTO> getTaskDetailByJobIdAndUserIdAndStatusId(int jobId, int userId){
        return taskRepository.getTaskDetailByJobIdAndUserIdAndStatusId(jobId,userId);
    }
}
