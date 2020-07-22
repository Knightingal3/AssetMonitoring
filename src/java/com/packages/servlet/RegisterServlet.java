/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.servlet;

import com.packages.dao.ApplicationDao;
import com.packages.java.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Knightingal£
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//............Copying all the input parameters in to local variables
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("pnumb");
        String password = request.getParameter("pwd");
        String gender = request.getParameter("gender");
        String userType = request.getParameter("userType");

//............Using JavaBean Parameterized Constructor   to pass values to the DAO
        UserBean registerBean = new UserBean(firstName, lastName, email, phoneNumber, gender, password, userType);

//       Inistantiating the RegisterDao class
        ApplicationDao registerdao = new ApplicationDao();

//       inserting data into the the database using the RegisterDao ref object
        String userRegistered = registerdao.registerUser(registerBean);

        if (userRegistered.equals("success")) {
            response.sendRedirect("Administrator");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
//            requestDispatcher.forward(request, response);

        } //On Failure, display a meaningful message to the User.
        else {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/form.jsp").forward(request, response);

        }

    }

}
