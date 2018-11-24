package com.dcdzsoft.dto.business;

/**
* 物流公司查询
*/

public class OutParamPMLogisticsQry implements java.io.Serializable
{
	public String LogisticsID = ""; //物流公司编号
	public String LogisticsName = ""; //物流公司名称
	public String PinyinCode = ""; //拼音代码
	public String Remark = ""; //备注

	public String getLogisticsID()
	{
		return LogisticsID;
	}
	public void setLogisticsID(String LogisticsID)
	{
		this.LogisticsID = LogisticsID;
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