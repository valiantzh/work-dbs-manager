package com.dcdzsoft.dto.business;

/**
* 用户取件身份认证
*/

public class OutParamPTVerfiyUser implements java.io.Serializable
{
	public String PackageID = ""; //订单号
	public String BoxNo = ""; //箱门编号
	public String PosPayFlag = ""; //支付标志
	public String OverPayFlag = ""; //逾期取件付费
	public double ExpiredAmt; //超时付费金额
	public String CustomerTel = "";//收件人手机号
	public String PostmanID = "";// 投递员ID
	public String Company = "";//快递公司
	public String Remark = "";


	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getPosPayFlag()
	{
		return PosPayFlag;
	}
	public void setPosPayFlag(String PosPayFlag)
	{
		this.PosPayFlag = PosPayFlag;
	}

	public String getOverPayFlag()
	{
		return OverPayFlag;
	}
	public void setOverPayFlag(String OverPayFlag)
	{
		this.OverPayFlag = OverPayFlag;
	}

	public double getExpiredAmt()
	{
		return ExpiredAmt;
	}
	public void setExpiredAmt(double ExpiredAmt)
	{
		this.ExpiredAmt = ExpiredAmt;
	}
    public String getCustomerTel() {
        return CustomerTel;
    }
    public void setCustomerTel(String customerTel) {
        CustomerTel = customerTel;
    }
    public String getPostmanID() {
        return PostmanID;
    }
    public void setPostmanID(String postmanID) {
        PostmanID = postmanID;
    }
    public String getCompany() {
        return Company;
    }
    public void setCompany(String company) {
        Company = company;
    }
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String remark) {
        Remark = remark;
    }

}