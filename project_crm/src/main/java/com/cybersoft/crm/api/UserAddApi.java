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

@WebServlet(name = "userAddApi",urlPatterns = {"/api/user-add"})
public class UserAddApi extends HttpServlet {
    UsersRepository usersRepository = new UsersRepositoryImp();
    UserService userService = new UserService(usersRepository);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        UserService userService = new UserService(usersRepository);
        ResponseData userAddReponseData = new ResponseData();
        boolean isSuccess = false;
        if(req.getParameter("email")==""||req.getParameter("fullname")==""||req.getParameter("password")==""||req.getParameter("roleId")==""){

        }else{
            isSuccess = userService.insertUserByParameter(req.getParameter("email"),req.getParameter("password"),req.getParameter("fullname"),
                        "",req.getParameter("phoneNum"),req.getParameter("country"), Integer.parseInt(req.getParameter("roleID")));

        }
        userAddReponseData.setSuccess(isSuccess);
        userAddReponseData.setStatus(isSuccess ?200:404);
        userAddReponseData.setDescription(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        System.out.println(userAddReponseData.getDescription());
        Gson gson = new Gson();
        String json = gson.toJson(userAddReponseData);
        out.print(json);
        out.flush();

    }
}
