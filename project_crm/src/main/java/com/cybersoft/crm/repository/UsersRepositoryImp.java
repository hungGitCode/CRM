package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.model.UsersRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryImp implements UsersRepository {
    //Đối với câu lấy giá trị : Select => excuteQuery
//            insert,Update,Delete => excuteUpte
    @Override
    public List<UsersModel> getUsersByEmailAndPassword(String email, String password) {
        List<UsersModel> list = new ArrayList<>();
        try {
            String query = "select * from users u where u.email = ? and u.password = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setRoleId(resultSet.getInt("role_id"));

                list.add(usersModel);
            }

            connection.close();

        } catch (Exception e) {
            System.out.println("Error getUsersByEmailAndPassword " + e.getMessage());
        }

        return list;
    }

    @Override
    public List<UsersRoles> getAllUsersAndRoleDescription() {
        List<UsersRoles> list = new ArrayList<>();
        try {
            String query = "select users.id, email, fullname, role_id, description from users inner join roles on users.role_id = roles.id;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UsersRoles usersRoles = new UsersRoles();
                usersRoles.setId(resultSet.getInt("id"));
                usersRoles.setEmail(resultSet.getString("email"));
                usersRoles.setFullname(resultSet.getString("fullname"));
                usersRoles.setRoleId(resultSet.getInt("role_id"));
                usersRoles.setDescription(resultSet.getString("description"));
                list.add(usersRoles);
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error getAllUser " + e.getMessage());
        }
//        if(list.size()>0){
//            System.out.println("Ket noi va lay du lieu thanh cong");
//        }
        return list;
    }
    public List<UsersModel> getAllUsers() {
        List<UsersModel> list = new ArrayList<>();
        try {
            String query = "select * from users;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setRoleId(resultSet.getInt("role_id"));
                usersModel.setPhone(resultSet.getString("phone"));
                usersModel.setCountry(resultSet.getString("country"));
                list.add(usersModel);
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error getAllUser " + e.getMessage());
        }
//        if(list.size()>0){
//            System.out.println("Ket noi va lay du lieu thanh cong");
//        }
        return list;
    }

    @Override
    public int insertUserByParameter(String email, String password, String fullname, String avatar, String phone, String country, int roleID) {
        int result = 0;
        try {
            String query = "INSERT INTO users(email, password,fullname, avatar, phone, country,role_id ) VALUES (?,?,?,?,?,?,?);";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,fullname);
            preparedStatement.setString(4,avatar);
            preparedStatement.setString(5,phone);
            preparedStatement.setString(6,country);
            preparedStatement.setInt(7,roleID);
            result = preparedStatement.executeUpdate();
            System.out.println("Insert User Success");
            connection.close();

        } catch (Exception e) {
            System.out.println("Error InsertRoleByParameter " + e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteUsersById(int id) {

            int result = 0;
            try {
                String query = "delete from users u where u.id = ?";
                Connection connection = MysqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,id);
                result = preparedStatement.executeUpdate();

                connection.close();

            } catch (Exception e) {
                System.out.println("Error deleteUsersById " + e.getMessage());
            }
            return result;
        }

    @Override
    public UsersModel getUserById(int id) {
        UsersModel usersModel = new UsersModel();
        try {
            String query = "select * from users where id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                usersModel.setId(resultSet.getInt("id"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setRoleId(resultSet.getInt("role_id"));
                usersModel.setPhone(resultSet.getString("phone"));
                usersModel.setCountry(resultSet.getString("country"));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error getUserById " + e.getMessage());
        }

        return usersModel;
    }

    @Override
    public int updateUserByParameter(String email, String fullname, String phone, String country, int roleID,int id) {
        int result = 0;
        try {
            String query = "update users set email = ?,fullname = ?,phone = ?,country = ?,role_id = ? where id = ?;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,fullname);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,country);
            preparedStatement.setInt(5,roleID);
            preparedStatement.setInt(6,id);

            result = preparedStatement.executeUpdate();
//            System.out.println(" Update User Success");
            connection.close();

        } catch (Exception e) {
            System.out.println("Error UpdateUserByParameter " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<UsersModel> getUsersByJobId(int id) {
        List<UsersModel> list = new ArrayList<>();

        try {
            String query = "select users.id,email,password,fullname,phone,country,avatar, role_id\n" +
                    "from users\n" +
                    "inner join tasks \n" +
                    "on tasks.user_id = users.id\n" +
                    "where tasks.job_id=?;";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> listAvailableUserId = new ArrayList<>();
            while (resultSet.next()) {  // Loại bỏ user trùng nhau
                boolean flag = false;
                if(listAvailableUserId.size()>0) {
                    for (Integer userId : listAvailableUserId) {
                        if (resultSet.getInt("id") == userId) {
                            flag = true;
                            break;
                        }
                    }
                }

                if(!flag) {

                    UsersModel usersModel = new UsersModel();
                    usersModel.setId(resultSet.getInt("id"));
                    usersModel.setEmail(resultSet.getString("email"));
                    usersModel.setAvatar(resultSet.getString("avatar"));
                    usersModel.setFullName(resultSet.getString("fullname"));
                    usersModel.setRoleId(resultSet.getInt("role_id"));
                    usersModel.setPhone(resultSet.getString("phone"));
                    usersModel.setCountry(resultSet.getString("country"));
                    listAvailableUserId.add(resultSet.getInt("id"));
                    list.add(usersModel);
                }
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error getAllUser " + e.getMessage());
        }

        return list;
    }

    @Override
    public UsersModel getUserModelByEmail(String email) {
        UsersModel usersModel = new UsersModel();
        try {
            String query = "select * from users where email = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                usersModel.setId(resultSet.getInt("id"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setRoleId(resultSet.getInt("role_id"));
                usersModel.setPhone(resultSet.getString("phone"));
                usersModel.setCountry(resultSet.getString("country"));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error getUserId By Email" + e.getMessage());
        }

        return usersModel;

    }


}


