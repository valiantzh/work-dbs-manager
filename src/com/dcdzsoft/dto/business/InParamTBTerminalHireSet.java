package com.dcdzsoft.dto.business;

/**
* 柜格口费用设置
*/

public class InParamTBTerminalHireSet implements java.io.Serializable
{
	public String FunctionID = "210042"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String ChargeType = ""; //收费类型：1-预收费用；2-正常收费；3-逾期收费；
	public String ChargeMode = ""; //计费模式:1-按次计费；2-按时计费；3-按天计费
	public int Num; //计费数量
	public double Amount; //收费金额（单位：元）
	public int Discount; //计费折扣（100%）
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210042";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210042";
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getChargeType()
	{
		return ChargeType;
	}
	public void setChargeType(String ChargeType)
	{
		this.ChargeType = ChargeType;
	}

	public String getChargeMode()
	{
		return ChargeMode;
	}
	public void setChargeMode(String ChargeMode)
	{
		this.ChargeMode = ChargeMode;
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

	public int getDiscount()
	{
		return Discount;
	}
	public void setDiscount(int Discount)
	{
		this.Discount = Discount;
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