package com.luxshare.nohttp;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class TempBean {
    private String EmpCode;
    private String ProfitceterCode;
    private String Line;
    private String WorkOrderCode;
    private List<OrderBean> List;

    public String getEmpCode() {
        return EmpCode;
    }

    public void setEmpCode(String empCode) {
        EmpCode = empCode;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public java.util.List<OrderBean> getList() {
        return List;
    }

    public void setList(java.util.List<OrderBean> list) {
        List = list;
    }

    public String getProfitceterCode() {
        return ProfitceterCode;
    }

    public void setProfitceterCode(String profitceterCode) {
        ProfitceterCode = profitceterCode;
    }

    public String getWorkOrderCode() {
        return WorkOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        WorkOrderCode = workOrderCode;
    }
}
