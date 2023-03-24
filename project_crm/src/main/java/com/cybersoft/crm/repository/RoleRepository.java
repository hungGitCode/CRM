package com.cybersoft.crm.repository;

import com.cybersoft.crm.model.RoleModel;

import java.util.List;

public interface RoleRepository {
    public List<RoleModel> getRoles();
    public RoleModel getRoleById(int id);
    public int deleteRolesById(int id);
    public int insertRoleByParameter(String name, String description);
    public int updateRoleByParameter(String name, String description,int id);
}
