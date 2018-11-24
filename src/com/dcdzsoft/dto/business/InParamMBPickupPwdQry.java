package com.dcdzsoft.dto.business;

/**
 * 取件密码短消息查询
 */

public class InParamMBPickupPwdQry implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "150114"; // 功能编号

    public String OperID = ""; // 管理员编号
    public String TerminalNo = ""; // 设备号
    public String PackageID = ""; // 订单号
    public String CustomerMobile = ""; // 取件人手机
    public String OperPwd = "";// 管理员密码

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "150114";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "150114";
        else
            this.FunctionID = FunctionID;
    }

    public String getOperID() {
        return OperID;
    }

    public void setOperID(String OperID) {
        this.OperID = OperID;
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

    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }

    public String getOperPwd() {
        return OperPwd;
    }

    public void setOperPwd(String operPwd) {
        OperPwd = operPwd;
    }

}