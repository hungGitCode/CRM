package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.UsersRepositoryImp;

import java.util.List;

public class LoginService {

    UsersRepositoryImp usersRepository = new UsersRepositoryImp();

    public boolean checkLogin(String email, String password){
        List<UsersModel> list = usersRepository.getUsersByEmailAndPassword(email,password);
        if(list.size() > 0){
            //Đăng nhập thành công
            return true;
        }else{
            //Đăng nhập thất bại
            return false;
        }

    }

}
