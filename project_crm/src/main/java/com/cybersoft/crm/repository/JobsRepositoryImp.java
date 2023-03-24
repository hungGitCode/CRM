package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.*;
import com.cybersoft.crm.service.TaskService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JobsRepositoryImp implements JobsRepository {
    @Override
    public List<JobsModel> getJobs() {
        List<JobsModel> list = new ArrayList<>();
        try {
            String query = "select * from jobs";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JobsModel jobsModel = new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStartDate(resultSet.getString("start_date"));
                jobsModel.setEndDate(resultSet.getString("end_date"));
                list.add(jobsModel);
            }
            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error getJobs " + e.getMessage());
        }
        return list;
    }

    @Override
    public JobsModel getJobById(int id) {
        JobsModel jobsModel = new JobsModel();
        try {
            String query = "select * from jobs where id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStartDate(resultSet.getString("start_date"));
                jobsModel.setEndDate(resultSet.getString("end_date"));
            }
            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error getJobs " + e.getMessage());
        }
        return jobsModel;
    }

    @Override
    public List<QualitiesModel> getQuantitiesByJobId(int id) {
        System.out.println("running quentites by jobid");
        TaskRepository taskRepository = new TaskRepositoryImp();
        TaskService taskService = new TaskService(taskRepository);

        StatusRepository statusRepository = new StatusRepositoryImp();
        List<StatusModel> statusModelList = statusRepository.getAllStatus();

        List<QualitiesModel> qualitiesModelList = new ArrayList<>();
        List<TaskModel> taskModelList = taskService.getTaskModelsByJobId(id);
// Tính số lượng mỗi status xuất hiện
        try {
            String query = "select status.name, count(status_id) as status_count\n" +
                    "from tasks\n" +
                    "inner join status\n" +
                    "on tasks.status_id = status.id\n" +
                    "where job_id = ? \n" +
                    "group by status_id;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            for(StatusModel statusModel : statusModelList){
                QualitiesModel qualitiesModel = new QualitiesModel();
                qualitiesModel.setStatusName(statusModel.getName());
                qualitiesModelList.add(qualitiesModel);
            }

            while (resultSet.next()) {
//                QualitiesModel qualitiesModel = new QualitiesModel();
//                qualitiesModel.setStatusName(resultSet.getString("name"));
//                qualitiesModel.setStatusCount(resultSet.getFloat("status_count"));
//                qualitiesModelList.add(qualitiesModel);
                for (QualitiesModel qualitiesModel : qualitiesModelList) {

                    if (resultSet.getString("name").equals(qualitiesModel.getStatusName())) {
                        qualitiesModel.setStatusCount(resultSet.getFloat("status_count"));
                        break;
                    }
                }
            }

            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error get statusCount " + e.getMessage());
        }
        // Tính số % tương ứng
        int sumCount = 0;
        for (QualitiesModel qualitiesModel : qualitiesModelList) {
//            System.out.println("statusname : + " + qualitiesModel.getStatusName());
//            System.out.println("statuscount : + " + qualitiesModel.getStatusCount());
            sumCount += qualitiesModel.getStatusCount();
//            System.out.println(sumCount);
        }
        for (QualitiesModel qualitiesModel : qualitiesModelList) {
            qualitiesModel.setStatusPercent(Math.round(qualitiesModel.getStatusCount() / sumCount * 100));
        }
        return qualitiesModelList;
    }

    @Override
    public List<QualitiesModel> getQuantitiesByUserId(int id) {
        TaskRepository taskRepository = new TaskRepositoryImp();
        TaskService taskService = new TaskService(taskRepository);

        StatusRepository statusRepository = new StatusRepositoryImp();
        List<StatusModel> statusModelList = statusRepository.getAllStatus();

        List<QualitiesModel> qualitiesModelList = new ArrayList<>();
        List<TaskModel> taskModelList = taskService.getTaskModelsByJobId(id);
// Tính số lượng mỗi status xuất hiện
        try {
            String query = "select status.name, count(status_id) as status_count\n" +
                    "from tasks\n" +
                    "inner join status\n" +
                    "on tasks.status_id = status.id\n" +
                    "where user_id = ?\n" +
                    "group by status_id;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            for(StatusModel statusModel : statusModelList){
                QualitiesModel qualitiesModel = new QualitiesModel();
                qualitiesModel.setStatusName(statusModel.getName());
                qualitiesModelList.add(qualitiesModel);
            }

            while (resultSet.next()) {
//                QualitiesModel qualitiesModel = new QualitiesModel();
//                qualitiesModel.setStatusName(resultSet.getString("name"));
//                qualitiesModel.setStatusCount(resultSet.getFloat("status_count"));
//                qualitiesModelList.add(qualitiesModel);
                for (QualitiesModel qualitiesModel : qualitiesModelList) {

                    if (resultSet.getString("name").equals(qualitiesModel.getStatusName())) {
                        qualitiesModel.setStatusCount(resultSet.getFloat("status_count"));
                        break;
                    }
                }
            }

            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error get statusCount " + e.getMessage());
        }
        // Tính số % tương ứng
        int sumCount = 0;
        for (QualitiesModel qualitiesModel : qualitiesModelList) {
//            System.out.println("statusname : + " + qualitiesModel.getStatusName());
//            System.out.println("statuscount : + " + qualitiesModel.getStatusCount());
            sumCount += qualitiesModel.getStatusCount();
            System.out.println(sumCount);
        }
        for (QualitiesModel qualitiesModel : qualitiesModelList) {
            qualitiesModel.setStatusPercent(Math.round(qualitiesModel.getStatusCount() / sumCount * 100));
        }
        return qualitiesModelList;
    }

    @Override
    public List<JobsModel> getJobsByUserId(int id) {
        List<JobsModel> list = new ArrayList<>();
        try {
            String query = "select jobs.id,jobs.name,jobs.start_date,jobs.end_date\n" +
                    "from jobs\n" +
                    "inner join tasks\n" +
                    "on tasks.job_id = jobs.id \n" +
                    "where tasks.user_id=?\n" +
                    "group by jobs.id,jobs.name,jobs.start_date,jobs.end_date;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JobsModel jobsModel = new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStartDate(resultSet.getString("start_date"));
                jobsModel.setEndDate(resultSet.getString("end_date"));
                list.add(jobsModel);
            }
            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error getJobsByUserId " + e.getMessage());
        }
        return list;
    }

    public static boolean checkDate(String date){
        boolean isDate = false;
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
//            Date parseToDate = formatter.parse(date);
            LocalDate parseDate = LocalDate.parse(date,dateTimeFormatter);
            isDate = true;
        }catch (Exception e){
            System.out.println("Error Date format : " + e);
            isDate = false;
        }

        return isDate;
    }

    public static int compareDate(String date1, String date2){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parseDate1 = LocalDate.parse(date1,dateTimeFormatter);
        LocalDate parseDate2 = LocalDate.parse(date2,dateTimeFormatter);
        int result = parseDate1.compareTo(parseDate2);
        System.out.println("Compare Date Result : " + result); // result = 0 : ngày bằng nhau , >0 date1 sau ngày date2
        return result;
    }


    @Override
    public int addJob(String name, String startDate, String endDate) {
        int result = 0;
        if(checkDate(startDate)&&checkDate(endDate)&&compareDate(startDate,endDate)<=0) {

            try {
                String query = "INSERT INTO jobs(name,start_date, end_date) VALUES (?,?,?);";
                Connection connection = MysqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, startDate);
                preparedStatement.setString(3, endDate);
                result = preparedStatement.executeUpdate();
                System.out.println(" Add Job Success");
                connection.close();

            } catch (Exception e) {
                System.out.println("Error Add Job : " + e.getMessage());
            }
        }
        return result;
    }

    @Override
    public int deleteJobById(int id) {
        int result = 0;
        try {
            String query = "delete from jobs j where j.id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            result = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error deleteJobsById " + e.getMessage());
        }
        return result;
    }

    @Override
    public int updateJob(String name, String startDate, String endDate, int id) {
        int result = 0;
        if(checkDate(startDate)&&checkDate(endDate)&&compareDate(startDate,endDate)<=0) {
            try {
                String query = "update jobs set name = ? , start_date = ?,end_date = ? where id = ?;";
                Connection connection = MysqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, startDate);
                preparedStatement.setString(3, endDate);
                preparedStatement.setInt(4, id);
                result = preparedStatement.executeUpdate();
                System.out.println(" Update Jobs Success");
                connection.close();

            } catch (Exception e) {
                System.out.println("Error Update Job " + e.getMessage());
            }
        }
        return result;
    }



}
