package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.model.UsersRoles;
import com.cybersoft.crm.repository.UsersRepository;


import java.util.List;

public class UserService {
    private UsersRepository usersRepository;
    public UserService(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    public List<UsersModel> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    public List<UsersRoles> getAllUsersAndRoleDescription(){
        return usersRepository.getAllUsersAndRoleDescription();
    }

    public boolean insertUserByParameter(String email, String password, String fullname, String avatar, String phone, String country, int roleID) {
        int result = usersRepository.insertUserByParameter(email, password, fullname, avatar, phone, country, roleID);
        System.out.println(result);
        return result>0?true:false;
    }

    public boolean deleteUsersById(int id){
        int result = usersRepository.deleteUsersById(id);
        return result>0?true:false;
    }

    public UsersModel getUserById(int id){
       return usersRepository.getUserById(id);
    }
    public boolean updateUserByParameter(String email, String fullname, String phone, String country, int roleID,int id){
        int result = usersRepository.updateUserByParameter(email,fullname,phone,country,roleID,id);
        return result>0?true:false;
    }
    public List<UsersModel> getUsersByJobId(int id) {
        return usersRepository.getUsersByJobId(id);
    }
    public UsersModel getUserModelByEmail(String email) {
        return usersRepository.getUserModelByEmail(email);
    }

}
