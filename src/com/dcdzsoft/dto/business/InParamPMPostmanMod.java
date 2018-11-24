package com.dcdzsoft.dto.business;

/**
* 投递员修改
*/

public class InParamPMPostmanMod implements java.io.Serializable
{
	public String FunctionID = "311002"; //功能编号

	public String OperID = ""; //操作员编号
	public String PostmanID = ""; //投递员编号
	public String CompanyID = ""; //投递公司编号
	public String PostmanName = ""; //投递员名称
	public String PostmanType = ""; //投递员类型
	public String BindCardID = ""; //绑定卡号
	public String BindMobile = ""; //绑定手机
	public String OfficeTel = ""; //办公电话
	public String Mobile = ""; //手机
	public String Email = ""; //电子邮件
	public String ContactAddress = ""; //联系地址
	public String InputMobileFlag = ""; //转入标志
	public String PostmanStatus = ""; //投递员状态
	public String DepartmentID = ""; //运营网点编号
	public String IDCard = ""; //身份证号
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "311002";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "311002";
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

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getPostmanName()
	{
		return PostmanName;
	}
	public void setPostmanName(String PostmanName)
	{
		this.PostmanName = PostmanName;
	}

	public String getPostmanType()
	{
		return PostmanType;
	}
	public void setPostmanType(String PostmanType)
	{
		this.PostmanType = PostmanType;
	}

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
	}

	public String getBindMobile()
	{
		return BindMobile;
	}
	public void setBindMobile(String BindMobile)
	{
		this.BindMobile = BindMobile;
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

	public String getContactAddress()
	{
		return ContactAddress;
	}
	public void setContactAddress(String ContactAddress)
	{
		this.ContactAddress = ContactAddress;
	}

	public String getInputMobileFlag()
	{
		return InputMobileFlag;
	}
	public void setInputMobileFlag(String InputMobileFlag)
	{
		this.InputMobileFlag = InputMobileFlag;
	}

	public String getPostmanStatus()
	{
		return PostmanStatus;
	}
	public void setPostmanStatus(String PostmanStatus)
	{
		this.PostmanStatus = PostmanStatus;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getIDCard()
	{
		return IDCard;
	}
	public void setIDCard(String IDCard)
	{
		this.IDCard = IDCard;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}
    @Override
    public String toString() {
        return "InParamPMPostmanMod [FunctionID=" + FunctionID + ", OperID=" + OperID + ", PostmanID=" + PostmanID
                + ", CompanyID=" + CompanyID + ", PostmanName=" + PostmanName + ", PostmanType=" + PostmanType
                + ", BindCardID=" + BindCardID + ", BindMobile=" + BindMobile + ", OfficeTel=" + OfficeTel + ", Mobile="
                + Mobile + ", Email=" + Email + ", ContactAddress=" + ContactAddress + ", InputMobileFlag="
                + InputMobileFlag + ", PostmanStatus=" + PostmanStatus + ", DepartmentID=" + DepartmentID + ", IDCard="
                + IDCard + ", Remark=" + Remark + "]";
    }

}