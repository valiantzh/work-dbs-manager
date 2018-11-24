package com.dcdzsoft.dto.business;

/**
* 柜格口费用查询
*/

public class OutParamTBTerminalHireQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String Location = ""; //设备安装地址
	public String ChargeType = ""; //收费类型
	public String ChargeTypeName = ""; //收费类型
	public String ChargeMode = ""; //计费模式
	public String ChargeModeName = ""; //计费模式
	public int Num; //计费数量
	public double Amount; //收费金额（单位：元）
	public String BoxType = ""; //箱类型编号
	public String BoxTypeName = ""; //箱类型名称
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

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

	public String getChargeType()
	{
		return ChargeType;
	}
	public void setChargeType(String ChargeType)
	{
		this.ChargeType = ChargeType;
	}

	public String getChargeTypeName()
	{
		return ChargeTypeName;
	}
	public void setChargeTypeName(String ChargeTypeName)
	{
		this.ChargeTypeName = ChargeTypeName;
	}

	public String getChargeMode()
	{
		return ChargeMode;
	}
	public void setChargeMode(String ChargeMode)
	{
		this.ChargeMode = ChargeMode;
	}

	public String getChargeModeName()
	{
		return ChargeModeName;
	}
	public void setChargeModeName(String ChargeModeName)
	{
		this.ChargeModeName = ChargeModeName;
	}

	public int getNum()
	{
		return Num;
	}
	public void setNum(int Num)
	{
		this.Num = Num;
	}

	public double getAmount()
	{
		return Amount;
	}
	public void setAmount(double Amount)
	{
		this.Amount = Amount;
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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}