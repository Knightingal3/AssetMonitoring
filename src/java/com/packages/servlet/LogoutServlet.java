/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LogoutServlet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if(session!=null) //If session is not null
 {
 session.invalidate(); //removes all session attributes bound to the session
 request.setAttribute("errMessage", "You have logged out successfully");
 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
 requestDispatcher.forward(request, response);
 }
    }

    
}
