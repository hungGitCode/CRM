package com.cybersoft.crm.repository;

import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.model.UsersRoles;

import java.util.List;

public interface UsersRepository {
    public List<UsersModel> getUsersByEmailAndPassword(String email, String password);
    public List<UsersRoles> getAllUsersAndRoleDescription();
    public List<UsersModel> getAllUsers();

    public int insertUserByParameter(String email, String password, String fullname,  String avatar, String phone, String country, int roleID);

    public int deleteUsersById(int id);

    public UsersModel getUserById(int id);

    public int updateUserByParameter(String email, String fullname, String phone, String country, int roleID, int id);
    public  List<UsersModel> getUsersByJobId(int id);

    public UsersModel getUserModelByEmail(String email);


}
