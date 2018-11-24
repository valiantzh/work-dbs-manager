package com.dcdzsoft.dto.business;

/**
* 箱租用金额查询
*/

public class InParamTBBoxHireQry implements java.io.Serializable
{
	public String FunctionID = "210298"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String BoxType = ""; //箱类型编号
	public String BoxTypeName = ""; //箱类型名称

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210298";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210298";
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

	public String getBoxType()
	{
		return BoxType;
	}
	public void setBoxType(String BoxType)
	{
		this.BoxType = BoxType;
	}

	public String getBoxTypeName()
	{
		return BoxTypeName;
	}
	public void setBoxTypeName(String BoxTypeName)
	{
		this.BoxTypeName = BoxTypeName;
	}

}