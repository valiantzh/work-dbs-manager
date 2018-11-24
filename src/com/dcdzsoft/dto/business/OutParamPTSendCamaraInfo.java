package com.dcdzsoft.dto.business;

/**
* 发送拍照信息给监控设备
*/

public class OutParamPTSendCamaraInfo implements java.io.Serializable
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