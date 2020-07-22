/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packages.java;

import java.sql.Date;



/**
 *
 * @author Knightingal£
 */
public class AssetBean {
    private String assetName;
    private String serialNo;
    private String assetType;
    private String assetStatus;
    private Date purchasedDate;  
    private int assetID;
    private String userFirstName;
    private String userLastName;
    
    
    public AssetBean(String assetName, String serialNo, String assetType, String assetStatus, Date purchasedDate){
        this.assetName=assetName;
        this.serialNo = serialNo;
        this.assetType = assetType;
        this.assetStatus = assetStatus;
        this.purchasedDate = purchasedDate;
    }

    public AssetBean() {
        
    }

    /**
     * @return the assetName
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * @param assetName the assetName to set
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * @return the serialNo
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return the assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * @param assetType the assetType to set
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * @return the assetStatus
     */
    public String getAssetStatus() {
        return assetStatus;
    }

    /**
     * @param assetStatus the assetStatus to set
     */
    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    /**
     * @return the purchasedDate
     */
    public Date getPurchasedDate() {
        return purchasedDate;
    }

    /**
     * @param purchasedDate the purchasedDate to set
     */
    public void setPurchasedString(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    /**
     * @return the assetID
     */
    public int getAssetID() {
        return assetID;
    }

    /**
     * @param assetID the assetID to set
     */
    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }

    /**
     * @return the userFirstName
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * @param userFirstName the userFirstName to set
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * @return the userLastName
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * @param userLastName the userLastName to set
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * @return the purchasedDate
     */

    
}
