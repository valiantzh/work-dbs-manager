package com.dcdzsoft.dto.business;

/**
* 推送投递员增加
*/

public class InParamPMPushPostmanAdd implements java.io.Serializable
{
	public String FunctionID = "311201"; //功能编号

	public String OperID = ""; //操作员编号
	public String PostmanID = ""; //投递员编号
	public String CompanyID = ""; //投递公司编号
	public String PostmanName = ""; //投递员名称
	public String PostmanType = ""; //投递员类型
	public String Password = ""; //投递员密码
	public String BindCardID = ""; //绑定卡号
	public String BindMobile = ""; //绑定手机
	public String InputMobileFlag = ""; //转入标志
	public String Remark = ""; //备注
	public String BoxList = ""; //可开箱号列表
	public String RemoteFlag = ""; //远程操作标志
	public String TerminalNoStr = ""; //设备列表

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "311201";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "311201";
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

	public String getPostmanName()
	{
		return PostmanName;
	}
	public void setPostmanName(String PostmanName)
	{
		this.PostmanName = PostmanName;
	}

	public String getPostmanType()
	{
		return PostmanType;
	}
	public void setPostmanType(String PostmanType)
	{
		this.PostmanType = PostmanType;
	}

	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
	}

	public String getBindMobile()
	{
		return BindMobile;
	}
	public void setBindMobile(String BindMobile)
	{
		this.BindMobile = BindMobile;
	}

	public String getInputMobileFlag()
	{
		return InputMobileFlag;
	}
	public void setInputMobileFlag(String InputMobileFlag)
	{
		this.InputMobileFlag = InputMobileFlag;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

	public String getBoxList()
	{
		return BoxList;
	}
	public void setBoxList(String BoxList)
	{
		this.BoxList = BoxList;
	}

	public String getRemoteFlag()
	{
		return RemoteFlag;
	}
	public void setRemoteFlag(String RemoteFlag)
	{
		this.RemoteFlag = RemoteFlag;
	}

	public String getTerminalNoStr()
	{
		return TerminalNoStr;
	}
	public void setTerminalNoStr(String TerminalNoStr)
	{
		this.TerminalNoStr = TerminalNoStr;
	}

}