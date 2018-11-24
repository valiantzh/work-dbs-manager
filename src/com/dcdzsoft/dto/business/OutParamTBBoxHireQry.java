package com.dcdzsoft.dto.business;

/**
* 箱租用金额查询
*/

public class OutParamTBBoxHireQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String BoxType = ""; //箱类型编号
	public String BoxTypeName = ""; //箱类型名称
	public double ChargeAmt; //收费金额（单位：元）
	public String Remark = ""; //备注

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

	public double getChargeAmt()
	{
		return ChargeAmt;
	}
	public void setChargeAmt(double ChargeAmt)
	{
		this.ChargeAmt = ChargeAmt;
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