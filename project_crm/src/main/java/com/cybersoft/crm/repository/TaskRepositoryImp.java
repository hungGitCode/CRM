package com.cybersoft.crm.repository;

import com.cybersoft.crm.DTO.TaskDetailDTO;
import com.cybersoft.crm.DTO.UserGroupworkDetailDTO;
import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.StatusModel;
import com.cybersoft.crm.model.TaskModel;
import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.service.JobsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImp implements TaskRepository {
    @Override
    public List<TaskDetailDTO> getAllTask() {
        List<TaskDetailDTO> taskDetailDTOList = new ArrayList<>();
        try {
            String query = "select tasks.id,tasks.name,tasks.start_date,tasks.end_date,users.fullname as user_name,job_id,jobs.name as job_name,status.name as status_name\n" +
                    "from (tasks left join users on tasks.user_id = users.id)\n" +
                    "left join jobs \n" +
                    "on tasks.job_id = jobs.id\n" +
                    "left join status\n" +
                    "on tasks.status_id = status.id;";

            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            System.out.println("running query...");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
                taskDetailDTO.setId(resultSet.getInt("id"));
                taskDetailDTO.setName(resultSet.getString("name"));
                taskDetailDTO.setStartDate(resultSet.getString("start_date"));
                taskDetailDTO.setEndDate(resultSet.getString("end_date"));
                taskDetailDTO.setUserName(resultSet.getString("user_name"));
                taskDetailDTO.setJobName(resultSet.getString("job_name"));
                taskDetailDTO.setJobId(resultSet.getInt("job_id"));
                taskDetailDTO.setStatusName(resultSet.getString("status_name"));
//                System.out.println(resultSet.getInt("id") + " + " +
//                        resultSet.getString("name") + " + " +
//                        resultSet.getString("start_date") + " + " +
//                        resultSet.getString("end_date") + " + " +
//                        resultSet.getString("user_name") + " + " +
//                        resultSet.getString("job_name") + " + " +
//                        resultSet.getString("status_name"));
                taskDetailDTOList.add(taskDetailDTO);
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error TaskDetailDTO " + e.getMessage());
        }

        return taskDetailDTOList;
    }

    @Override
    public int updateTask(String name, String startDate, String endDate, int userId, int jobId, int statusId,int id) {
        int result = 0;
        if(JobsRepositoryImp.checkDate(startDate)&&JobsRepositoryImp.checkDate(endDate)&&
                JobsRepositoryImp.compareDate(startDate,endDate)<=0) {
            JobsRepository jobsRepository = new JobsRepositoryImp();
            JobsService jobsService = new JobsService(jobsRepository);
            JobsModel jobsModel = jobsService.getJobById(jobId);
            if (JobsRepositoryImp.compareDate(startDate, jobsModel.getStartDate()) >= 0 && JobsRepositoryImp.compareDate(endDate, jobsModel.getEndDate()) <= 0) {
                try {
                    String query = "update tasks set name = ?,start_date = ?, end_date = ?,user_id= ?,job_id= ?,status_id= ? where id = ?";
                    Connection connection = MysqlConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, startDate);
                    preparedStatement.setString(3, endDate);
                    preparedStatement.setInt(4, userId);
                    preparedStatement.setInt(5, jobId);
                    preparedStatement.setInt(6, statusId);
                    preparedStatement.setInt(7, id);

                    result = preparedStatement.executeUpdate();
//            System.out.println(" Update User Success");
                    connection.close();

                } catch (Exception e) {
                    System.out.println("Error Update Job " + e.getMessage());
                }
            }else{
                System.out.println(" Ngày bắt đầu (hoặc Ngày kết thúc) của Task không hợp lý với Ngày bắt đầu (hoặc Ngày kết thúc) của Dự Án. Vui lòng Kiểm Tra lại");
            }
        }
        return result;
    }

    @Override
    public TaskModel getTaskModelById(int id) {
        TaskModel taskModel = new TaskModel();
        try {
            String query = "select * from tasks where id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                taskModel.setId(id);
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStartDate(resultSet.getString("start_date"));
                taskModel.setEndDate(resultSet.getString("end_date"));
                taskModel.setUserId(resultSet.getInt("user_id"));
                taskModel.setJobId(resultSet.getInt("job_id"));
            }
            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error getTask " + e.getMessage());
        }
        return taskModel;
    }

    @Override
    public int deleteTaskById(int id) {
        int result = 0;
        try {
            String query = "delete from tasks t where t.id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            result = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error deleteTasksById " + e.getMessage());
        }
        return result;
    }

    @Override
    public int addTask(String name, String startDate, String endDate, int userId, int jobId, int statusId) {
        int result = 0;
        if(JobsRepositoryImp.checkDate(startDate)&&JobsRepositoryImp.checkDate(endDate)&&
                JobsRepositoryImp.compareDate(startDate,endDate)<=0) {

            JobsRepository jobsRepository = new JobsRepositoryImp();
            JobsService jobsService = new JobsService(jobsRepository);
            JobsModel jobsModel = jobsService.getJobById(jobId);
            if (JobsRepositoryImp.compareDate(startDate, jobsModel.getStartDate()) >= 0 && JobsRepositoryImp.compareDate(endDate, jobsModel.getEndDate()) <= 0) {

                try {
                    String query = "INSERT INTO tasks(name,start_date, end_date,user_id,job_id,status_id) VALUES (?,?,?,?,?,?);";
                    Connection connection = MysqlConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, startDate);
                    preparedStatement.setString(3, endDate);
                    preparedStatement.setInt(4, userId);
                    preparedStatement.setInt(5, jobId);
                    preparedStatement.setInt(6, statusId);
                    result = preparedStatement.executeUpdate();
                    System.out.println(" Add task Success");
                    connection.close();

                } catch (Exception e) {
                    System.out.println("Error task Job : " + e.getMessage());
                }
            }else{
                System.out.println(" Ngày bắt đầu (hoặc Ngày kết thúc) của Task không hợp lý với Ngày bắt đầu (hoặc Ngày kết thúc) của Dự Án. Vui lòng Kiểm Tra lại");
            }
        }
        return result;
    }

    @Override
    public List<TaskModel> getTaskModelsByJobId(int id) {
        List<TaskModel> taskModelList = new ArrayList<>();
        try {
            String query = "select * from tasks where job_id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TaskModel taskModel = new TaskModel();
            while (resultSet.next()) {
                taskModel.setId(id);
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStartDate(resultSet.getString("start_date"));
                taskModel.setEndDate(resultSet.getString("end_date"));
                taskModel.setUserId(resultSet.getInt("user_id"));
                taskModel.setJobId(resultSet.getInt("job_id"));
                taskModel.setJobId(resultSet.getInt("status_id"));
            }
            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error getTask " + e.getMessage());
        }
        return taskModelList;
    }

    @Override
    public List<UserGroupworkDetailDTO> getTaskDetailByJobIdAndUserIdAndStatusId(int jobId, int userId){
        List<UserGroupworkDetailDTO> userGroupworkDetailDTOList = new ArrayList<>();

        try {
            String query = "select tasks.id,tasks.name,tasks.start_date,tasks.end_date,users.fullname as user_name,job_id,jobs.name as job_name,status.name as status_name\n" +
                    "from (tasks left join users on tasks.user_id = users.id)\n" +
                    "left join jobs \n" +
                    "on tasks.job_id = jobs.id\n" +
                    "left join status\n" +
                    "on tasks.status_id = status.id\n" +
                    "where job_id =? and user_id=? and status_id=?";

            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setInt(2, userId);

            StatusRepository statusRepository = new StatusRepositoryImp();
            List<StatusModel> statusModelList = statusRepository.getAllStatus();

            for(StatusModel statusModel : statusModelList) {
                List<TaskDetailDTO> taskDetailDTOList = new ArrayList<>();
                UserGroupworkDetailDTO userGroupworkDetailDTO = new UserGroupworkDetailDTO();
                userGroupworkDetailDTO.setStatusName(statusModel.getName());


                preparedStatement.setInt(3, statusModel.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) { // Nếu query trả rỗng
                    userGroupworkDetailDTO.setTaskDetailDTOList(null);
                    userGroupworkDetailDTOList.add(userGroupworkDetailDTO);
                 continue;
                } else {
                    while (resultSet.next()) {

                        TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
                        taskDetailDTO.setId(resultSet.getInt("id"));
                        taskDetailDTO.setName(resultSet.getString("name"));
                        taskDetailDTO.setStartDate(resultSet.getString("start_date"));
                        taskDetailDTO.setEndDate(resultSet.getString("end_date"));
                        taskDetailDTO.setUserName(resultSet.getString("user_name"));
                        taskDetailDTO.setJobName(resultSet.getString("job_name"));
                        taskDetailDTO.setJobId(resultSet.getInt("job_id"));
                        taskDetailDTO.setStatusName(resultSet.getString("status_name"));

                        taskDetailDTOList.add(taskDetailDTO);
                    }

                    userGroupworkDetailDTO.setTaskDetailDTOList(taskDetailDTOList);
                    userGroupworkDetailDTOList.add(userGroupworkDetailDTO);
                }
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error getTaskDetailByJobIdAndUserIdAndStatusId " + e.getMessage());
        }
        return userGroupworkDetailDTOList;
    }
//    @Override
//    public List<List<TaskDetailDTO>> getTaskDetailByJobIdAndUserIdAndStatusId(int jobId, int userId){
//        List<List<TaskDetailDTO>> listOfList = new ArrayList<>();
//        List<TaskDetailDTO> taskDetailDTOList = new ArrayList<>();
//        try {
//            String query = "select tasks.id,tasks.name,tasks.start_date,tasks.end_date,users.fullname as user_name,jobs.name as job_name,status.name as status_name\n" +
//                    "from (tasks left join users on tasks.user_id = users.id)\n" +
//                    "left join jobs \n" +
//                    "on tasks.job_id = jobs.id\n" +
//                    "left join status\n" +
//                    "on tasks.status_id = status.id\n" +
//                    "where job_id =? and user_id=? and status_id=?";
//
//            Connection connection = MysqlConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, jobId);
//            preparedStatement.setInt(2, userId);
//
//            StatusRepository statusRepository = new StatusRepositoryImp();
//            List<StatusModel> statusModelList = statusRepository.getAllStatus();
//            for(StatusModel statusModel : statusModelList){
//                preparedStatement.setInt(3, statusModel.getId());
//                ResultSet resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
//                    taskDetailDTO.setId(resultSet.getInt("id"));
//                    taskDetailDTO.setName(resultSet.getString("name"));
//                    taskDetailDTO.setStartDate(resultSet.getString("start_date"));
//                    taskDetailDTO.setEndDate(resultSet.getString("end_date"));
//                    taskDetailDTO.setUserName(resultSet.getString("user_name"));
//                    taskDetailDTO.setJobName(resultSet.getString("job_name"));
//                    taskDetailDTO.setStatusName(resultSet.getString("status_name"));
////                System.out.println(resultSet.getInt("id") + " + " +
////                        resultSet.getString("name") + " + " +
////                        resultSet.getString("start_date") + " + " +
////                        resultSet.getString("end_date") + " + " +
////                        resultSet.getString("user_name") + " + " +
////                        resultSet.getString("job_name") + " + " +
////                        resultSet.getString("status_name"));
//                    taskDetailDTOList.add(taskDetailDTO);
//                }
//                listOfList.add(taskDetailDTOList);
//            }
//            connection.close();
//
//        } catch (Exception e) {
//            System.out.println("Error getTaskDetailByJobIdAndUserIdAndStatusId " + e.getMessage());
//        }
//        return listOfList;
//    }
}
