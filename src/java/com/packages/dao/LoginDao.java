/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.dao;

import com.packages.java.LoginBean;
import java.sql.*;
 /*
 * @author Knightingal£
 */
public class LoginDao {

    public String authenticaticateUser(LoginBean loginBean) {

        String email = loginBean.getEmail();
        String password = loginBean.getPassword();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from users where email='" + email + "' and password='" + password + "'";

        String emailDB = "";
        String passwordDB = "";
        String userType = "";
       

        try {
            con = ConnectionManager.createConnection();
            preparedStatement = con.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                emailDB = resultSet.getString("email");
                passwordDB = resultSet.getString("password");
                userType = resultSet.getString("usertype");
                
                if (email.equals(emailDB) && password.equals(passwordDB)) {

                    if (userType.equalsIgnoreCase("administrator")) {
                        return "admin";
                    } else {
                        return "success";
                    }
                }
            }

        } catch (SQLException e) {

            System.err.println(e);
        }

        return "ERROR";
    }

}
