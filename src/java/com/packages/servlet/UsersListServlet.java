/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.servlet;

import com.google.gson.Gson;
import com.packages.dao.ApplicationDao;
import com.packages.java.AssetBean;
import com.packages.java.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Knightingal£
 */
public class UsersListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try {
            ApplicationDao getUser = new ApplicationDao();
   
            String json = new Gson().toJson(getUser.getUsers());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            System.err.println(e.toString());

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        int assetID = Integer.parseInt(request.getParameter("assetID"));
        int userID  = Integer.parseInt(request.getParameter("userID"));
        
         ApplicationDao assetMap = new ApplicationDao();
      
         if( userID == 0){
            int delAsset = assetMap.deleteAsset(assetID);
            
            String json = new Gson().toJson(delAsset);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
                
         else{
        
        
        
        int assetInfo = assetMap.getAssetInfo(assetID, userID);
        
        String json = new Gson().toJson(assetInfo);
        
         response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
         }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
