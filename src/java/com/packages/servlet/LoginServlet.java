/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.servlet;

import com.packages.dao.ApplicationDao;
import com.packages.java.LoginBean;
import com.packages.java.UserBean;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Knightingal£
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//        if (request.getSession().getAttribute("email") != null) {
//
//            ApplicationDao userDao = new ApplicationDao();
//            List<UserBean> user = userDao.getUsers();
//
//            HttpSession session = request.getSession();
//            session.setAttribute("users", user);
//
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
//            requestDispatcher.forward(request, response);
//
//        } else {
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
//            requestDispatcher.forward(request, response);
//        }
//
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);

        String email = request.getParameter("Email");
        String password = request.getParameter("pwd");

        if (email.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
        LoginBean loginBean = new LoginBean();

        loginBean.setEmail(email);
        loginBean.setPassword(password);

        ApplicationDao loginDao = new ApplicationDao();

        try {

            String validateUSer = loginDao.authenticaticateUser(loginBean);
            System.err.println(validateUSer);

            switch (validateUSer) {
                case "admin": {
                    //            Creating a session....
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);

                    ApplicationDao userDao = new ApplicationDao();
                    UserBean user = userDao.getProfiledetails(email);

                    //       storing user details in the session Attribute
                    session.setAttribute("user", user);
                    session.setAttribute("userType", "admin");

                    response.sendRedirect("Administrator");
                   

//                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("AssertServlet");
//                    requestDispatcher.forward(request, response);
                    break;
                }
                case "success": {
                    //            Creating a session....
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);

                    email = ((String) request.getSession().getAttribute("email"));

                    ApplicationDao userDao = new ApplicationDao();
                    UserBean user = userDao.getProfiledetails(email);

//       storing user details in the request Attribute
                    session.setAttribute("user", user);
//                    System.err.println(userDao.);
                    
                    List <UserBean>  userAsset = userDao.getUserAsset(email);
                    session.setAttribute("userAsset", userAsset);
                    
//       forwarding the attribute to the jsp page 
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("user.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                default:
                    String errorMessage = "Invalid Credentials, please login again!";
                    request.setAttribute("error", errorMessage);
                    request.getRequestDispatcher("login.jsp").forward(request, response);

            }
        } catch (Exception e) {
            String errMsg = e.toString();
            System.err.println(errMsg);
        }
    }

}
