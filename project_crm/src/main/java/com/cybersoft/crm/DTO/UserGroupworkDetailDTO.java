package com.cybersoft.crm.DTO;

import com.cybersoft.crm.model.TaskModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGroupworkDetailDTO {
    private String statusName;
    //private List<Map<String, List<TaskDetailDTO>>> groupworkdetail;
    private List<TaskDetailDTO> taskDetailDTOList;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<TaskDetailDTO> getTaskDetailDTOList() {
        return taskDetailDTOList;
    }

    public void setTaskDetailDTOList(List<TaskDetailDTO> taskDetailDTOList) {
        this.taskDetailDTOList = taskDetailDTOList;
    }
}


