package com.dcdzsoft.dto.business;

/**
 * 添加待投递订单
 */

public class InParamPTReadyPackageAdd implements java.io.Serializable {
    public String FunctionID = "330601"; // 功能编号

    public String TerminalNo = ""; // 设备号
    public String PackageID = ""; // 订单号
    public String BoxNo = ""; // 箱门编号
    public String PostmanID = ""; // 投递员编号
    public String CompanyID = ""; // 投递公司编号
    public java.sql.Timestamp OrderTime; // 存物时间
    public String CustomerID = ""; // 取件人编号
    public String CustomerMobile = ""; // 取件人手机
    public String CustomerName = "";// 取件人名称
    public String PosPayFlag = ""; // 支付标志
    public String Remark = ""; // 备注

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "330601";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "330601";
        else
            this.FunctionID = FunctionID;
    }

    public String getTerminalNo() {
        return TerminalNo;
    }

    public void setTerminalNo(String TerminalNo) {
        this.TerminalNo = TerminalNo;
    }

    public String getPackageID() {
        return PackageID;
    }

    public void setPackageID(String PackageID) {
        this.PackageID = PackageID;
    }

    public String getBoxNo() {
        return BoxNo;
    }

    public void setBoxNo(String BoxNo) {
        this.BoxNo = BoxNo;
    }

    public String getPostmanID() {
        return PostmanID;
    }

    public void setPostmanID(String PostmanID) {
        this.PostmanID = PostmanID;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String CompanyID) {
        this.CompanyID = CompanyID;
    }

    public java.sql.Timestamp getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(java.sql.Timestamp OrderTime) {
        this.OrderTime = OrderTime;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPosPayFlag() {
        return PosPayFlag;
    }

    public void setPosPayFlag(String PosPayFlag) {
        this.PosPayFlag = PosPayFlag;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

}