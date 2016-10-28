package com.luxshare.nohttp;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/27.
 */
public class OrderBean implements Serializable{

    private String MaterialCode;
    private String MaterialName;
    private String Quantity;
    private String DemandQuantity;
    private String DemandTime;

    public String getDemandQuantity() {
        return DemandQuantity;
    }

    public void setDemandQuantity(String demandQuantity) {
        DemandQuantity = demandQuantity;
    }

    public String getDemandTime() {
        return DemandTime;
    }

    public void setDemandTime(String demandTime) {
        DemandTime = demandTime;
    }

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
