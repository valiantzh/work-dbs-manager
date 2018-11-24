package com.dcdzsoft.dto.business;

/**
* 上传取消投递记录
*/

public class OutParamPTDeliveryCancel implements java.io.Serializable
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