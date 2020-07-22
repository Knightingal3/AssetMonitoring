/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.servlet;

import com.packages.dao.ApplicationDao;
import com.packages.java.AssetBean;
import com.packages.java.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AssetServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

            ApplicationDao getAsset = new ApplicationDao();
            List<AssetBean> assets = getAsset.getAssets();

            HttpSession session = request.getSession();
//            request.setAttribute("assets", assets);

            session.setAttribute("assets", assets);
            response.sendRedirect("admin.jsp");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
//            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.err.println(e.toString());

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            if (request.getSession().getAttribute("email") != null){
        try {
            processRequest(request, response);

            int value = 0;
            String assetName = request.getParameter("assetName");
            String serialNo = request.getParameter("serialNo");
            String date = request.getParameter("purchasedDate");
//Date formating....................................................................

            Date purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            java.sql.Date purchasedDate = new java.sql.Date(purchaseDate.getTime());

//Date formating....................................................................
            String assetStatus = request.getParameter("assetStatus");
            String assetType = request.getParameter("assetType");

            AssetBean addAsset = new AssetBean(assetName, serialNo, assetType, assetStatus, purchasedDate);

            value = ApplicationDao.addAsset(addAsset);

            switch (value) {

                case 1: {
                    ApplicationDao getAsset = new ApplicationDao();
                    List<AssetBean> assets = getAsset.getAssets();

                    HttpSession session = request.getSession();
                    session.setAttribute("assets", assets);

                    response.sendRedirect("admin.jsp");

//                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
//                    requestDispatcher.forward(request, response);
                    break;
                }

            }
        } catch (ParseException ex) {
            Logger.getLogger(AssetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            }else
            {
               RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                    requestDispatcher.forward(request, response);
            }
    }
}