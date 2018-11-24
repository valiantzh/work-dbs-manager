package com.dcdzsoft.dto.business;

/**
* 柜体信息查询
*/

public class InParamTBTerminalQry implements java.io.Serializable
{
	public String FunctionID = "210014"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String RegisterFlag = ""; //注册标志
	public String TerminalStatus = ""; //柜体状态
	public String QryFlag = ""; //查询标志(v-动态信息查询;s-静态信息查询;其他-默认查询)
	public String Bounds = ""; //查询坐标范围（西南经纬度坐标，东北经纬度坐标）
	public String Location = ""; //安装地址

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210014";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210014";
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getRegisterFlag()
	{
		return RegisterFlag;
	}
	public void setRegisterFlag(String RegisterFlag)
	{
		this.RegisterFlag = RegisterFlag;
	}

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}

	public String getQryFlag()
	{
		return QryFlag;
	}
	public void setQryFlag(String QryFlag)
	{
		this.QryFlag = QryFlag;
	}

	public String getBounds()
	{
		return Bounds;
	}
	public void setBounds(String Bounds)
	{
		this.Bounds = Bounds;
	}

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

}