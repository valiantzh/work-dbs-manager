package com.dcdzsoft.dto.business;

/**
* 查询管理员信息
*/

public class OutParamOPOperatorQry implements java.io.Serializable
{
	public String OperID = ""; //管理员内部编号
	public String UserID = ""; //管理员编号
	public String OperName = ""; //管理员姓名
	public int OperType; //管理员类型
	public String OperTypeName = ""; //管理员类型
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String OfficeTel = ""; //办公电话
	public String Mobile = ""; //手机
	public String Email = ""; //电子邮件
	public java.sql.Timestamp CreateTime; //创建时间
	public String OperStatus = ""; //管理员状态
	public String OperStatusName = ""; //管理员状态名称
	public String Remark = ""; //备注
	public int RoleID; //角色编号

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getUserID()
	{
		return UserID;
	}
	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public String getOperName()
	{
		return OperName;
	}
	public void setOperName(String OperName)
	{
		this.OperName = OperName;
	}

	public int getOperType()
	{
		return OperType;
	}
	public void setOperType(int OperType)
	{
		this.OperType = OperType;
	}

	public String getOperTypeName()
	{
		return OperTypeName;
	}
	public void setOperTypeName(String OperTypeName)
	{
		this.OperTypeName = OperTypeName;
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

	public String getOfficeTel()
	{
		return OfficeTel;
	}
	public void setOfficeTel(String OfficeTel)
	{
		this.OfficeTel = OfficeTel;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}

	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}

	public java.sql.Timestamp getCreateTime()
	{
		return CreateTime;
	}
	public void setCreateTime(java.sql.Timestamp CreateTime)
	{
		this.CreateTime = CreateTime;
	}

	public String getOperStatus()
	{
		return OperStatus;
	}
	public void setOperStatus(String OperStatus)
	{
		this.OperStatus = OperStatus;
	}

	public String getOperStatusName()
	{
		return OperStatusName;
	}
	public void setOperStatusName(String OperStatusName)
	{
		this.OperStatusName = OperStatusName;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

	public int getRoleID()
	{
		return RoleID;
	}
	public void setRoleID(int RoleID)
	{
		this.RoleID = RoleID;
	}

}