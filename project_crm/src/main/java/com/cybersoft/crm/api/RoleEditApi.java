package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.RoleRepositoryImp;
import com.cybersoft.crm.service.RoleService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "roleEditApi",urlPatterns = {"/api/role-edit"})
public class RoleEditApi extends HttpServlet {
    private RoleRepository roleRepository = new RoleRepositoryImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("Role Edit Api is running..." );
        RoleService roleService = new RoleService(roleRepository);
        ResponseData roleUpdateResponseData = new ResponseData();
        boolean isSuccess = false;
        if(req.getParameter("name")!=""&&req.getParameter("description")!=""){
            isSuccess=roleService.updateRoleByParameter(
                    req.getParameter("name"),req.getParameter("description"), Integer.parseInt(req.getParameter("roleId")));
           System.out.println("Done if method");
        }
        roleUpdateResponseData.setSuccess(isSuccess);
        roleUpdateResponseData.setStatus(isSuccess ?200:404);
        roleUpdateResponseData.setDescription(isSuccess ? "Update thành công" : "Update thất bại");
//        Cookie cookie = new Cookie("message", roleUpdateResponseData.getDescription());
//        cookie.setMaxAge(60);
//        resp.addCookie(cookie);
        System.out.println(roleUpdateResponseData.getDescription());
        Gson gson = new Gson();
        String json = gson.toJson(roleUpdateResponseData);
        out.print(json);
        out.flush();

    }
}
