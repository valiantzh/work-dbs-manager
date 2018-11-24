package com.dcdzsoft.dto.business;

/**
* 设备格口状态报告
*/

public class OutParamMBReportBoxStatus implements java.io.Serializable
{
	public String Remark = ""; //备注

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}