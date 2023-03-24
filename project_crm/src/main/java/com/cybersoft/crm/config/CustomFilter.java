package com.cybersoft.crm.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//
@WebFilter(urlPatterns = {"/*"})
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("Kiem tra filter");
//        Cho phép đi vào link client mong muốn
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response  = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin") != null && !session.getAttribute("isLogin").equals("")){
            //Đã login
//            System.out.println("da co co session");
            boolean isLogin = (boolean) session.getAttribute("isLogin");
            if(isLogin){
                //chuyển về page chỉ định
                if(request.getServletPath().equals("/login")){
                    //Nếu là trang login
                    response.sendRedirect(request.getContextPath() + "/home");
                }else{
                    int roldId = (int) session.getAttribute("RoleId");
                    switch (request.getServletPath()) {
                        case "/user-edit":
                        case "/user-add":
                        case "/role-edit":
                        case "/role-add":
                        case "/groupwork-edit":
                        case "/groupwork-add":
                        case "/task-edit":
                        case "/task-add":
//                        case "/api/deleteRoleById":
                            if ((Integer)session.getAttribute("RoleId") == 3) { // User có quyền là "Nhân Viên" tương ứng rold_id = 3  không có quyền chỉnh sửa và add
                                request.getRequestDispatcher("/404.html").forward(request, response);
                            }

                    }

                    filterChain.doFilter(request,response);
                }
            }else{
                //chuyển về page login
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }else{
//            System.out.println("chua co session");
            //Chưa login
            //Chuyển về page login
            if(request.getServletPath().equals("/login")){
                //Nếu là trang login
                filterChain.doFilter(request,response);
            }else{
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}
