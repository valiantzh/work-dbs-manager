package com.dcdzsoft.dto.business;

/**
* 投递记录查询
*/

public class InParamPTDeliveryRecordQry implements java.io.Serializable
{
	public String FunctionID = "330044"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String BoxNo = ""; //箱门编号
	public String PackageID = ""; //订单号
	public String PackageStatus = ""; //包裹状态
	public String PostmanID = ""; //投递员编号
	public String CompanyID = ""; //投递公司编号
	public String DepartmentID = ""; //运营网点编号
	public String CustomerMobile = ""; //取件人手机
	public String UploadFlag = ""; //上传标志
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期
	public String InboxFlag = ""; //在箱标志
	public String PosPayFlag = "";//支付标识
	public String LeftFlag = "";//包裹状态
	public String Remark = "";//洗衣方式
	

	public String getLeftFlag() {
		return LeftFlag;
	}
	public void setLeftFlag(String leftFlag) {
		LeftFlag = leftFlag;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330044";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330044";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getUploadFlag()
	{
		return UploadFlag;
	}
	public void setUploadFlag(String UploadFlag)
	{
		this.UploadFlag = UploadFlag;
	}

	public java.sql.Date getBeginDate()
	{
		return BeginDate;
	}
	public void setBeginDate(java.sql.Date BeginDate)
	{
		this.BeginDate = BeginDate;
	}

	public java.sql.Date getEndDate()
	{
		return EndDate;
	}
	public void setEndDate(java.sql.Date EndDate)
	{
		this.EndDate = EndDate;
	}

	public String getInboxFlag()
	{
		return InboxFlag;
	}
	public void setInboxFlag(String InboxFlag)
	{
		this.InboxFlag = InboxFlag;
	}
    public String getPosPayFlag() {
        return PosPayFlag;
    }
    public void setPosPayFlag(String posPayFlag) {
        PosPayFlag = posPayFlag;
    }

}