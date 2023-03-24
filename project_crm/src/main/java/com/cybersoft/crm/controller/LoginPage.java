package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.UsersRepository;
import com.cybersoft.crm.repository.UsersRepositoryImp;
import com.cybersoft.crm.service.LoginService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginPage", urlPatterns = {"/login"})
public class LoginPage extends HttpServlet {

    private LoginService loginService = new LoginService();
    private UsersRepository usersRepository = new UsersRepositoryImp();
    private UserService userService = new UserService(usersRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

//        Khai báo một cookie tên là email
//        Cookie cookie = new Cookie("email",email);
//        cookie.setMaxAge(5 * 60);
//        resp.addCookie(cookie);
//
//        Cookie cookie1 = new Cookie("password",password);
//        cookie1.setMaxAge(5 * 60);
//        resp.addCookie(cookie1);

//        Cookie[] cookies = req.getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println("Name cookie " + cookie.getName() + " - Value " + cookie.getValue());
//        }

//        Yêu cầu sử dụng session


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean isLogin = loginService.checkLogin(email,password);

        if(isLogin){
            UsersModel usersModel = userService.getUserModelByEmail(email);
            HttpSession session = req.getSession();
            session.setAttribute("isLogin",true);
            session.setAttribute("UserId",usersModel.getId());
            session.setAttribute("UserName",usersModel.getFullName());
            session.setAttribute("RoleId",(Integer)usersModel.getRoleId());

            session.setMaxInactiveInterval(8 * 60);
        }


        String message = "";
        if(isLogin){
            message = "Đăng nhập thành công!";
        }else{
            message = "Đăng nhập thất bại!";
        }
        req.setAttribute("message",message);

        System.out.println("Kiem tra " + isLogin);

        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
}

