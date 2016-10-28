package com.luxshare.textokhttp.test;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class PersonBean implements Serializable {


    /**
     * IsSuccess : true
     * ErrMsg : null
     * Data : [{"Code":"P000329","Model":"HP","SaveBy":"C044981","SavedByName":"安明禹","CompanyName":"东莞讯滔","SavedByDeptName":"资讯开发课"}]
     */

    private boolean IsSuccess;
    private Object ErrMsg;
    /**
     * Code : P000329
     * Model : HP
     * SaveBy : C044981
     * SavedByName : 安明禹
     * CompanyName : 东莞讯滔
     * SavedByDeptName : 资讯开发课
     */

    private List<DataBean> Data;

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public Object getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(Object ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String Code;
        private String Model;
        private String SaveBy;
        private String SavedByName;
        private String CompanyName;
        private String SavedByDeptName;

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String Model) {
            this.Model = Model;
        }

        public String getSaveBy() {
            return SaveBy;
        }

        public void setSaveBy(String SaveBy) {
            this.SaveBy = SaveBy;
        }

        public String getSavedByName() {
            return SavedByName;
        }

        public void setSavedByName(String SavedByName) {
            this.SavedByName = SavedByName;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getSavedByDeptName() {
            return SavedByDeptName;
        }

        public void setSavedByDeptName(String SavedByDeptName) {
            this.SavedByDeptName = SavedByDeptName;
        }
    }
}
