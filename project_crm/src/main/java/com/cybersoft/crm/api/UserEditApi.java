package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.repository.UsersRepository;
import com.cybersoft.crm.repository.UsersRepositoryImp;
import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userEditApi",urlPatterns = {"/api/user-edit"})
public class UserEditApi extends HttpServlet {
    private UsersRepository usersRepository = new UsersRepositoryImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("User Edit Api is running..." );
        System.out.println(req.getParameter("email"));
        System.out.println(req.getParameter("fullName"));
        System.out.println(req.getParameter("phone"));
        System.out.println(req.getParameter("country"));
        System.out.println(req.getParameter("roleId"));
        System.out.println(req.getParameter("id"));
        UserService userService = new UserService(usersRepository);
        ResponseData userUpdateResponseData = new ResponseData();
        boolean isSuccess = false;
//        updateUserByParameter(String email, String fullname, String phone, String country, int roleID,int id)
        if(req.getParameter("email")!=""&&req.getParameter("fullName")!=""&&Integer.parseInt(req.getParameter("roleId"))>0){
            isSuccess=userService.updateUserByParameter(req.getParameter("email"),req.getParameter("fullName"),
                    req.getParameter("phone"),req.getParameter("country"),Integer.parseInt(req.getParameter("roleId")),
                            Integer.parseInt(req.getParameter("id")));
            System.out.println("Done if method");
        }
        userUpdateResponseData.setSuccess(isSuccess);
        userUpdateResponseData.setStatus(isSuccess ?200:404);
        userUpdateResponseData.setDescription(isSuccess ? "Update thành công" : "Update thất bại");

        System.out.println(userUpdateResponseData.getDescription());
        Gson gson = new Gson();
        String json = gson.toJson(userUpdateResponseData);
        out.print(json);
        out.flush();
    }

}
