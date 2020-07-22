/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.java;

import java.io.Serializable;

/**
 *
 * @author Knightingal£
 */
public class UserBean implements Serializable{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String password;
    private String userType;
    private int userID;
    private String userAsset;
    private String assetSerialNo;

    public UserBean(String firstName, String lastName, String email, String phone, String gender, String password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.userType = userType;
    }

    public UserBean() {
    
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the userAsset
     */
    public String getUserAsset() {
        return userAsset;
    }

    /**
     * @param userAsset the userAsset to set
     */
    public void setUserAsset(String userAsset) {
        this.userAsset = userAsset;
    }

    /**
     * @return the assetSerialNo
     */
    public String getAssetSerialNo() {
        return assetSerialNo;
    }

    /**
     * @param assetSerialNo the assetSerialNo to set
     */
    public void setAssetSerialNo(String assetSerialNo) {
        this.assetSerialNo = assetSerialNo;
    }
}


