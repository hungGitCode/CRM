package com.cybersoft.crm.service;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;

import java.util.List;

public class RoleService {
//    private RoleRepositoryImp roleRepositoryImp = new RoleRepositoryImp();
//
//    public List<RoleModel> getAllRoles(){
//        return roleRepositoryImp.getRoles();
//    }
//
//    public boolean deleteRolseById(int id){
//        int result = roleRepositoryImp.deleteRolesById(id);
//        return result>0?true:false;
//    }
//
//    public boolean insertRolseByParameter(String name, String description){
//        int result = roleRepositoryImp.insertRoleByParameter(name,description);
//        return result>0?true:false;
//    }
    private RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public List<RoleModel> getAllRoles(){
        return roleRepository.getRoles();
    }
    public boolean deleteRolseById(int id){
        int result = roleRepository.deleteRolesById(id);
        return result>0?true:false;
    }
    public boolean insertRolseByParameter(String name, String description){
        int result = roleRepository.insertRoleByParameter(name,description);
//        System.out.println(result);
        return result>0?true:false;
    }
    public RoleModel getRoleById(int id){
        return roleRepository.getRoleById(id);
    }

    public boolean updateRoleByParameter(String name, String description, int id){
        int result = roleRepository.updateRoleByParameter(name,description,id);
        return result>0?true:false;
    }

}
