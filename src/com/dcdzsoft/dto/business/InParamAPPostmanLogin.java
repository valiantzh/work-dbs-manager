package com.dcdzsoft.dto.business;

/**
* 投递员APP扫码登录
*/

public class InParamAPPostmanLogin implements java.io.Serializable
{
	public String FunctionID = "650008"; //功能编号

	public String PostmanID = ""; //投递员编号
	public String TerminalNo = ""; //设备号
	public String RandomCode = ""; //验证码
	public String Password = ""; //密码
	public String PostmanName = ""; //投递员名称
	public String CompanyID = ""; //投递公司编号
	public String InputMobileFlag = ""; //转入标志
	public String BoxList = ""; //格口号列表
	public String VerifyFlag = ""; //认证标志
	public int ExpiredCount; //逾期包裹数
	public int ExpiredDay; //逾期天数

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650008";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650008";
		else
			this.FunctionID = FunctionID;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getRandomCode()
	{
		return RandomCode;
	}
	public void setRandomCode(String RandomCode)
	{
		this.RandomCode = RandomCode;
	}
	
	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}

	public String getPostmanName()
	{
		return PostmanName;
	}
	public void setPostmanName(String PostmanName)
	{
		this.PostmanName = PostmanName;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getInputMobileFlag()
	{
		return InputMobileFlag;
	}
	public void setInputMobileFlag(String InputMobileFlag)
	{
		this.InputMobileFlag = InputMobileFlag;
	}

	public String getBoxList()
	{
		return BoxList;
	}
	public void setBoxList(String BoxList)
	{
		this.BoxList = BoxList;
	}

	public String getVerifyFlag()
	{
		return VerifyFlag;
	}
	public void setVerifyFlag(String VerifyFlag)
	{
		this.VerifyFlag = VerifyFlag;
	}

	public int getExpiredDay()
	{
		return ExpiredDay;
	}
	public void setExpiredDay(int ExpiredDay)
	{
		this.ExpiredDay = ExpiredDay;
	}
	
	public int getExpiredCount()
	{
		return ExpiredCount;
	}
	public void setExpiredCount(int ExpiredCount)
	{
		this.ExpiredCount = ExpiredCount;
	}

}