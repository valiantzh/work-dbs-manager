package com.dcdzsoft.dto.business;

/**
* 开箱流水信息查询
*/

public class OutParamMBOpenBoxWaterQry implements java.io.Serializable
{
	public String TradeWaterNo = ""; //流水号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String Location = ""; //安装地址
	public String PackageID = ""; //订单号
	public String BoxNo = ""; //箱号
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String OpenBoxUser = ""; //开箱用户
	public String OpenBoxType = ""; //开箱类型
	public String OpenBoxTypeName = ""; //开箱类型名称
	public String UploadFlag = ""; //上传状态
	public String UploadFlagName = ""; //上传状态名称
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public String PackageStatus = ""; //包裹状态
	public String PackageStatusName = ""; //包裹状态名称
	public String Remark = ""; //备注

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

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

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
	}

	public String getOpenBoxUser()
	{
		return OpenBoxUser;
	}
	public void setOpenBoxUser(String OpenBoxUser)
	{
		this.OpenBoxUser = OpenBoxUser;
	}

	public String getOpenBoxType()
	{
		return OpenBoxType;
	}
	public void setOpenBoxType(String OpenBoxType)
	{
		this.OpenBoxType = OpenBoxType;
	}

	public String getOpenBoxTypeName()
	{
		return OpenBoxTypeName;
	}
	public void setOpenBoxTypeName(String OpenBoxTypeName)
	{
		this.OpenBoxTypeName = OpenBoxTypeName;
	}

	public String getUploadFlag()
	{
		return UploadFlag;
	}
	public void setUploadFlag(String UploadFlag)
	{
		this.UploadFlag = UploadFlag;
	}

	public String getUploadFlagName()
	{
		return UploadFlagName;
	}
	public void setUploadFlagName(String UploadFlagName)
	{
		this.UploadFlagName = UploadFlagName;
	}

	public java.sql.Timestamp getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(java.sql.Timestamp LastModifyTime)
	{
		this.LastModifyTime = LastModifyTime;
	}

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
	}

	public String getPackageStatusName()
	{
		return PackageStatusName;
	}
	public void setPackageStatusName(String PackageStatusName)
	{
		this.PackageStatusName = PackageStatusName;
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