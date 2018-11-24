package com.dcdzsoft.dto.business;

/**
* 本次投递汇总
*/

public class OutParamPTDeliverySum implements java.io.Serializable
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