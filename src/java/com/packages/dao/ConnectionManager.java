/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.dao;
import java.sql.*;

/**
 *
 * @author Knightingal£
 */
public class ConnectionManager {

   static Connection con = null;
    
    public static Connection createConnection() {
       
        String host = "localhost:1433";
        String dbName = "databaseName=softworks";
        String url = "jdbc:sqlserver://" + host + ";" + dbName;
        String userName = "sa";
        String password = "password123$";
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        
        

        try {

            Class.forName(driverName);
            
            try {
                con = DriverManager.getConnection(url, userName, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }
}
