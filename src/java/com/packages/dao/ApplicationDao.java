/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.dao;

import com.packages.dao.ConnectionManager;
import com.packages.java.AssetBean;
import com.packages.java.LoginBean;
import com.packages.java.UserBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Date;
import java.util.List;

/**
 *
 * @author Knightingal£
 */
public class ApplicationDao {

//    Begining of Asset method........................................................................
    public static int addAsset(AssetBean addAsset) {
        //To change body of generated methods, choose Tools | Templates.

        String assetName = addAsset.getAssetName();
        String serialNo = addAsset.getSerialNo();
        String assetStaus = addAsset.getAssetStatus();
        String assetType = addAsset.getAssetType();
        Date purchaseDate = addAsset.getPurchasedDate();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = ConnectionManager.createConnection();

            String query = "INSERT INTO Assets ( AssetName, AssetStatus, Type, SerialNo, purchasedDate)"
                    + "VALUES (?,?,?,?,?)";

            preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, assetName);
            preparedStatement.setString(2, assetStaus);
            preparedStatement.setString(3, assetType);
            preparedStatement.setString(4, serialNo);
            preparedStatement.setDate(5, purchaseDate);

            int i = preparedStatement.executeUpdate();

            System.err.println(i);

            if (i != 0) {
                return i;
            }

        } catch (SQLException e) {

            e.toString();
            System.err.println(e);

        }

        return 0;
    }

//    End of Asset method.........................................................................
//    Beginging of User Registration Method......................................................
    public String registerUser(UserBean registerBean) {
        String firstName = registerBean.getFirstName();
        String lastName = registerBean.getLastName();
        String email = registerBean.getEmail();
        String gender = registerBean.getGender();
        String phone = registerBean.getPhone();
        String password = registerBean.getPassword();
        String userType = registerBean.getUserType();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = ConnectionManager.createConnection();

            String query = "INSERT INTO users(firstName,lastName,email,phone,gender,password,userType)  VALUES (?,?,?,?,?,?,?)";

            preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, userType);
           
            int i = preparedStatement.executeUpdate();

            if (i != 0) {
                return "success";
            }

        } catch (SQLException e) {

            e.printStackTrace();
            System.err.print(e);
        }

        return "Oops form registration unsuccessful...";
    }

    //End of User Registration Method.......................................................................
// User Login Method..........................................................................................
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
            }

            if (email.equalsIgnoreCase(emailDB) && userType.equalsIgnoreCase("Administrator")) {
                return "admin";
            } else if (email.equals(emailDB) && password.equals(passwordDB)) {
                return "success";
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return "ERROR";
    }

//        End of User Login Method.........................................................................
    
    
    
// Fetching user profile details..........................................................................
    public UserBean getProfiledetails(String email) {
        UserBean users = null;
//        List<UserBean> user = new ArrayList<>();
        try {
            // get connection to database
            Connection con = ConnectionManager.createConnection();

            // write select query to get profile details
            String sql = "select * from users where email=?";
           
//            String sql = "Select users.firstName, users.lastName, users.email, users.gender, users.phone, users.usertype, Assets.AssetName,"
//                    + " Assets.SerialNo from [softworks].[dbo].[users] inner join [softworks].[dbo].[Assets]  on "
//            + "[softworks].[dbo].[users].id = [softworks].[dbo].[Assets].UserID where email=?";
           
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);

            // execute query, get resultset and return User info
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                users = new UserBean();
                users.setFirstName(resultset.getString("firstName"));
                users.setEmail(resultset.getString("email"));
                users.setGender(resultset.getString("gender"));
                users.setPhone(resultset.getString("phone"));
                users.setUserType(resultset.getString("usertype"));
                users.setUserAsset(resultset.getString("AssetName"));
                users.setAssetSerialNo(resultset.getString("SerialNo"));
                         }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return users;
    }

    
        public List <UserBean> getUserAsset(String email) {
        UserBean users = null;
        List<UserBean> userAsset = new ArrayList<>();
        try {
            // get connection to database
            Connection con = ConnectionManager.createConnection();

            // write select query to get profile details
//            String sql = "select * from users where email=?";
           
            String sql = "Select users.firstName, users.lastName, users.email, users.gender, users.phone, users.usertype, Assets.AssetName,"
                    + " Assets.SerialNo from [softworks].[dbo].[users] inner join [softworks].[dbo].[Assets]  on "
            + "[softworks].[dbo].[users].id = [softworks].[dbo].[Assets].UserID where email=?";
           
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);

            // execute query, get resultset and return User info
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                users = new UserBean();
                
                users.setUserAsset(resultset.getString("AssetName"));
                users.setAssetSerialNo(resultset.getString("SerialNo"));
               userAsset.add(users);            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userAsset;
    }
    @SuppressWarnings("empty-statement")
    
    //        Begining of fetching assets.........................................................................
    
    public List<AssetBean> getAssets() {
        AssetBean asset = null;

        List<AssetBean> assets = new ArrayList<>();

        try {
            Connection con = ConnectionManager.createConnection();

//            String sql = "select * from Assets";
            
         String sql = "    select Assets.AssetName,Assets.AssetID,Assets.AssetStatus,Assets.Type,Assets.PurchasedDate,Assets.SerialNo,"
                 + "users.firstName, users.lastName from [softworks].[dbo].[Assets] left join [softworks].[dbo].[users] on "
                 + "[softworks].[dbo].[Assets].USerID = [softworks].[dbo].[users].id";

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            System.err.println(resultSet);
            while (resultSet.next()) {
                asset = new AssetBean();
                asset.setAssetID(resultSet.getInt("AssetID"));
                asset.setAssetName(resultSet.getString("AssetName"));
                asset.setSerialNo(resultSet.getString("SerialNo"));
                asset.setAssetStatus(resultSet.getString("AssetStatus"));
                asset.setAssetType(resultSet.getString("Type"));
                asset.setPurchasedString(resultSet.getDate("PurchasedDate"));
                asset.setUserFirstName(resultSet.getString("firstName"));
                asset.setUserLastName(resultSet.getString("lastName"));
                assets.add(asset);

            }

        } catch (SQLException e) {
            String errMsg = "Error connecting to database";
            e.printStackTrace();
            System.err.println(errMsg);
        }

        return assets;

    }
    
 //        End of fetching assets.........................................................................
    
    public List <UserBean> getUsers(){
     
        List<UserBean> users = new ArrayList<>();
        
        try {
            Connection con = ConnectionManager.createConnection();

            String sql = "select * from users where usertype != 'administrator'";

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            System.err.println(resultSet);
            while (resultSet.next()) {
                   UserBean user = new UserBean();
                user.setUserID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                users.add(user);
            }
    
    } catch(Exception e){
        
    }
return users;
}
    
    public int getAssetInfo(int assetID, int userID){

        try{
             Connection con = ConnectionManager.createConnection();
                            
            String sql = "update Assets set UserID ="+userID+ "where AssetID ="+assetID+"";

            Statement statement = con.createStatement();

            statement.executeUpdate(sql);
            
//            System.err.println(resultSet);

        }
        catch (SQLException e){
            
        }
                
    return 1;
    }
    
    public int deleteAsset(int assetID){

        try{
             Connection con = ConnectionManager.createConnection();
                            
            String sql = "update Assets set UserID = null where AssetID ="+assetID+"";

            Statement statement = con.createStatement();

            statement.executeUpdate(sql);
            
//            System.err.println(resultSet);

        }
        catch (SQLException e){
            
        }
                
    return 1;
    }
}

