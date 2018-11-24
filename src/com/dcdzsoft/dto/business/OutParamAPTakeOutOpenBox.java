package com.dcdzsoft.dto.business;

/**
* 取件开箱（新接口-PDA）
*/

public class OutParamAPTakeOutOpenBox implements java.io.Serializable
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