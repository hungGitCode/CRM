package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImp implements RoleRepository  {
    @Override
    public List<RoleModel> getRoles() {
        List<RoleModel> list = new ArrayList<>();
        try {
            String query = "select * from roles";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoleModel roleModel = new RoleModel();
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));

                list.add(roleModel);
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error getUsersByEmailAndPassword " + e.getMessage());
        }

        return list;
    }

    @Override
    public RoleModel getRoleById(int id) {
        RoleModel roleModel = new RoleModel();
        try {
            String query = "select * from roles where id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error getRoleById " + e.getMessage());
        }

        return roleModel;
    }

    @Override
    public int deleteRolesById(int id) {
        int result = 0;
        try {
            String query = "delete from roles r where r.id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            result = preparedStatement.executeUpdate();

            connection.close();

        } catch (Exception e) {
            System.out.println("Error deleteRolesById " + e.getMessage());
        }
        return result;
    }
    @Override
    public int insertRoleByParameter(String name, String description) {
        int result = 0;
        try {
            String query = "INSERT INTO roles( name, description ) VALUES (?,?);";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            result = preparedStatement.executeUpdate();
            System.out.println("Success");
            connection.close();

        } catch (Exception e) {
            System.out.println("Error InsertRoleByParameter " + e.getMessage());
        }
        return result;
    }

    @Override
    public int updateRoleByParameter(String name, String description, int id) {
        int result = 0;
        try {
            String query = "update roles set name = ? , description = ? where id = ?;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,id);
            result = preparedStatement.executeUpdate();
            System.out.println(" Update Role Success");
            connection.close();

        } catch (Exception e) {
            System.out.println("Error UpdateRoleByParameter " + e.getMessage());
        }
        return result;
    }
}



