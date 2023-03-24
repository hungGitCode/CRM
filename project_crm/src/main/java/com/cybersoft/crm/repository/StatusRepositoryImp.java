package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.StatusModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatusRepositoryImp implements StatusRepository {
    @Override
    public List<StatusModel> getAllStatus() {
        List<StatusModel> list = new ArrayList<>();
        try {
            String query = "select * from status";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StatusModel statusModel = new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));
                list.add(statusModel);
            }
            connection.close();
//        System.out.println("getJobs done....");
        } catch (Exception e) {
            System.out.println("Error getJobs " + e.getMessage());
        }
        return list;
    }
}
