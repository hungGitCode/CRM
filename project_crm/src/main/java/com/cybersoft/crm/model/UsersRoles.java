package com.cybersoft.crm.model;

public class UsersRoles {
//      usersModel.setId(resultSet.getInt("id"));
//                usersModel.setEmail(resultSet.getString("email"));
//                usersModel.setFullName(resultSet.getString("fullname"));
//                usersModel.setRoleId(resultSet.getInt("role_id"));
//                usersModel.setFullName(resultSet.getString("description"));
    private int id;
    private int role_id;
    private String email;
    private String fullname;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
