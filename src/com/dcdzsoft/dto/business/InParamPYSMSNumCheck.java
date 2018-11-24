package com.dcdzsoft.dto.business;

/**
* 检测短信发送量
*/

public class InParamPYSMSNumCheck implements java.io.Serializable
{
	public String FunctionID = "410102"; //功能编号

	public String OperID = ""; //管理员编号
	public String AccountID = ""; //充值账户编号
	public String DepartmentID = ""; //运营网点编号
	public int MsgType; //消息类型
	public int MsgSize; //消息长度
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410102";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410102";
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

	public String getAccountID()
	{
		return AccountID;
	}
	public void setAccountID(String AccountID)
	{
		this.AccountID = AccountID;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public int getMsgType()
	{
		return MsgType;
	}
	public void setMsgType(int MsgType)
	{
		this.MsgType = MsgType;
	}

	public int getMsgSize()
	{
		return MsgSize;
	}
	public void setMsgSize(int MsgSize)
	{
		this.MsgSize = MsgSize;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}