package com.luxshare.textokhttp.test;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class Test {

    /**
     * head : 8
     * g : [{"Id":262,"CompanyCode":"LXXT","CompanyName":"东莞讯滔","ProfitceterCode":"SEE-D","Line":"XT029","WorkOrderCode":"E511D-160900723","MaterialCode":"L56TH053-SD-R","MaterialName":"Other Cable:HSEC8 180 POS TO PCIE,DELL 4X00M","DemandQuantity":20,"ActualQuantity":null,"DemandTime":"2016-09-28 13:22:00","GetOrderTime":"2016-09-28 11:21:12","StockEmpCode":"L033712","StockEmpName":"付军","StockTime":"2016-09-28 11:21:20","SignEmpCode":null,"SignEmpName":null,"SignForTime":null,"Status":8,"ApplyEmpCode":"L033712","ApplyEmpName":"付军","ApplyTime":"2016-09-28 11:20:52","Reason":null,"Background":"#d9dfff"}]
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int head;
        /**
         * Id : 262
         * CompanyCode : LXXT
         * CompanyName : 东莞讯滔
         * ProfitceterCode : SEE-D
         * Line : XT029
         * WorkOrderCode : E511D-160900723
         * MaterialCode : L56TH053-SD-R
         * MaterialName : Other Cable:HSEC8 180 POS TO PCIE,DELL 4X00M
         * DemandQuantity : 20
         * ActualQuantity : null
         * DemandTime : 2016-09-28 13:22:00
         * GetOrderTime : 2016-09-28 11:21:12
         * StockEmpCode : L033712
         * StockEmpName : 付军
         * StockTime : 2016-09-28 11:21:20
         * SignEmpCode : null
         * SignEmpName : null
         * SignForTime : null
         * Status : 8
         * ApplyEmpCode : L033712
         * ApplyEmpName : 付军
         * ApplyTime : 2016-09-28 11:20:52
         * Reason : null
         * Background : #d9dfff
         */

        private List<GBean> g;

        public int getHead() {
            return head;
        }

        public void setHead(int head) {
            this.head = head;
        }

        public List<GBean> getG() {
            return g;
        }

        public void setG(List<GBean> g) {
            this.g = g;
        }

        public static class GBean {
            private int Id;
            private String CompanyCode;
            private String CompanyName;
            private String ProfitceterCode;
            private String Line;
            private String WorkOrderCode;
            private String MaterialCode;
            private String MaterialName;
            private int DemandQuantity;
            private Object ActualQuantity;
            private String DemandTime;
            private String GetOrderTime;
            private String StockEmpCode;
            private String StockEmpName;
            private String StockTime;
            private Object SignEmpCode;
            private Object SignEmpName;
            private Object SignForTime;
            private int Status;
            private String ApplyEmpCode;
            private String ApplyEmpName;
            private String ApplyTime;
            private Object Reason;
            private String Background;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getCompanyCode() {
                return CompanyCode;
            }

            public void setCompanyCode(String CompanyCode) {
                this.CompanyCode = CompanyCode;
            }

            public String getCompanyName() {
                return CompanyName;
            }

            public void setCompanyName(String CompanyName) {
                this.CompanyName = CompanyName;
            }

            public String getProfitceterCode() {
                return ProfitceterCode;
            }

            public void setProfitceterCode(String ProfitceterCode) {
                this.ProfitceterCode = ProfitceterCode;
            }

            public String getLine() {
                return Line;
            }

            public void setLine(String Line) {
                this.Line = Line;
            }

            public String getWorkOrderCode() {
                return WorkOrderCode;
            }

            public void setWorkOrderCode(String WorkOrderCode) {
                this.WorkOrderCode = WorkOrderCode;
            }

            public String getMaterialCode() {
                return MaterialCode;
            }

            public void setMaterialCode(String MaterialCode) {
                this.MaterialCode = MaterialCode;
            }

            public String getMaterialName() {
                return MaterialName;
            }

            public void setMaterialName(String MaterialName) {
                this.MaterialName = MaterialName;
            }

            public int getDemandQuantity() {
                return DemandQuantity;
            }

            public void setDemandQuantity(int DemandQuantity) {
                this.DemandQuantity = DemandQuantity;
            }

            public Object getActualQuantity() {
                return ActualQuantity;
            }

            public void setActualQuantity(Object ActualQuantity) {
                this.ActualQuantity = ActualQuantity;
            }

            public String getDemandTime() {
                return DemandTime;
            }

            public void setDemandTime(String DemandTime) {
                this.DemandTime = DemandTime;
            }

            public String getGetOrderTime() {
                return GetOrderTime;
            }

            public void setGetOrderTime(String GetOrderTime) {
                this.GetOrderTime = GetOrderTime;
            }

            public String getStockEmpCode() {
                return StockEmpCode;
            }

            public void setStockEmpCode(String StockEmpCode) {
                this.StockEmpCode = StockEmpCode;
            }

            public String getStockEmpName() {
                return StockEmpName;
            }

            public void setStockEmpName(String StockEmpName) {
                this.StockEmpName = StockEmpName;
            }

            public String getStockTime() {
                return StockTime;
            }

            public void setStockTime(String StockTime) {
                this.StockTime = StockTime;
            }

            public Object getSignEmpCode() {
                return SignEmpCode;
            }

            public void setSignEmpCode(Object SignEmpCode) {
                this.SignEmpCode = SignEmpCode;
            }

            public Object getSignEmpName() {
                return SignEmpName;
            }

            public void setSignEmpName(Object SignEmpName) {
                this.SignEmpName = SignEmpName;
            }

            public Object getSignForTime() {
                return SignForTime;
            }

            public void setSignForTime(Object SignForTime) {
                this.SignForTime = SignForTime;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public String getApplyEmpCode() {
                return ApplyEmpCode;
            }

            public void setApplyEmpCode(String ApplyEmpCode) {
                this.ApplyEmpCode = ApplyEmpCode;
            }

            public String getApplyEmpName() {
                return ApplyEmpName;
            }

            public void setApplyEmpName(String ApplyEmpName) {
                this.ApplyEmpName = ApplyEmpName;
            }

            public String getApplyTime() {
                return ApplyTime;
            }

            public void setApplyTime(String ApplyTime) {
                this.ApplyTime = ApplyTime;
            }

            public Object getReason() {
                return Reason;
            }

            public void setReason(Object Reason) {
                this.Reason = Reason;
            }

            public String getBackground() {
                return Background;
            }

            public void setBackground(String Background) {
                this.Background = Background;
            }
        }
    }
}
