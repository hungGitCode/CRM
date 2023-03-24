package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
import com.cybersoft.crm.service.RoleService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "roleApi", urlPatterns = {"/api/deleteRoleById"})
public class RoleDeleteApi extends HttpServlet {
//    private RoleService roleService = new RoleService();
    private RoleRepository roleRepository = new RoleRepositoryImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        RoleService roleService = new RoleService(roleRepository);
        System.out.println("Running api delete role by ID");
        boolean isSuccess = roleService.deleteRolseById(id);
        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess?"Xoá thành công":"Xoá thất bại");
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        out.print(json);
        out.flush();

    }
}
