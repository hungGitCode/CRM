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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(name = "roleAddApi", urlPatterns = {"/api/role-add"})
public class RoleAddApi extends HttpServlet {
    private RoleRepository roleRepository = new RoleRepositoryImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        RoleService roleService = new RoleService(roleRepository);
            ResponseData roleAddReponseData = new ResponseData();
            Boolean isSuccess = false;

        if(req.getParameter("rolename")!=""&&req.getParameter("roledescription")!="") {
            isSuccess = roleService.insertRolseByParameter(req.getParameter("rolename"), req.getParameter("roledescription"));

        }
            roleAddReponseData.setSuccess(isSuccess);
            roleAddReponseData.setStatus(isSuccess ?200:404);
            roleAddReponseData.setDescription(isSuccess ? "Thêm thành công" : "Thêm thất bại");
            System.out.println(roleAddReponseData.getDescription());
            Gson gson = new Gson();
            String json = gson.toJson(roleAddReponseData);
            out.print(json);
            out.flush();

    }
}
