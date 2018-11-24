package com.dcdzsoft.dto.business;

/**
* 物流公司增加
*/

public class InParamPMLogisticsAdd implements java.io.Serializable
{
	public String FunctionID = "310011"; //功能编号

	public String OperID = ""; //操作员编号
	public String LogisticsName = ""; //物流公司名称
	public String PinyinCode = ""; //拼音代码
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "310011";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "310011";
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

	public String getLogisticsName()
	{
		return LogisticsName;
	}
	public void setLogisticsName(String LogisticsName)
	{
		this.LogisticsName = LogisticsName;
	}

	public String getPinyinCode()
	{
		return PinyinCode;
	}
	public void setPinyinCode(String PinyinCode)
	{
		this.PinyinCode = PinyinCode;
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