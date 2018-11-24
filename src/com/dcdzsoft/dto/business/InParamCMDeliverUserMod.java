package com.dcdzsoft.dto.business;

/**
* 投递服务用户修改
*/

public class InParamCMDeliverUserMod implements java.io.Serializable
{
	public String FunctionID = "170202"; //功能编号

	public String OperID = ""; //管理员编号
	public String CustomerID = ""; //用户编号
	public String FirstName = ""; //用户名
	public String LastName = ""; //用户姓
	public String CustomerMobile = ""; //用户手机
	public String ServiceStatus = ""; //服务状态：0-正常;1-注销;2-未激活;
	public String TerminalNo = ""; //设备号
	public String DepartmentID = ""; //网点编号
	public String SpecialFlag = ""; //特殊标志：0-正常
	public String DeliverFailHandle = ""; //投递失败处理：0-联系收件人
	public String Email= ""; //邮箱
	public String Address = ""; //地址
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "170202";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "170202";
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

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getFirstName()
	{
		return FirstName;
	}
	public void setFirstName(String FirstName)
	{
		this.FirstName = FirstName;
	}

	public String getLastName()
	{
		return LastName;
	}
	public void setLastName(String LastName)
	{
		this.LastName = LastName;
	}

	public String getServiceStatus()
	{
		return ServiceStatus;
	}
	public void setServiceStatus(String ServiceStatus)
	{
		this.ServiceStatus = ServiceStatus;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getSpecialFlag()
	{
		return SpecialFlag;
	}
	public void setSpecialFlag(String SpecialFlag)
	{
		this.SpecialFlag = SpecialFlag;
	}

	public String getDeliverFailHandle()
	{
		return DeliverFailHandle;
	}
	public void setDeliverFailHandle(String DeliverFailHandle)
	{
		this.DeliverFailHandle = DeliverFailHandle;
	}

	public String getAddress()
	{
		return Address;
	}
	public void setAddress(String Address)
	{
		this.Address = Address;
	}

	public String getRemark()
	{
		return Remark;
	}
	
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}
	
	public String getCustomerMobile() {
		return CustomerMobile;
	}
	
	public void setCustomerMobile(String customerMobile) {
		CustomerMobile = customerMobile;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	

}