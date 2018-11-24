package com.dcdzsoft.dto.business;

/**
* PDA投递授权
*/

public class OutParamPTDeliveryAuth4PDA implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	public String RegisterFlag = ""; //PDA注册标志

	public String getRegisterFlag()
	{
		return RegisterFlag;
	}
	public void setRegisterFlag(String RegisterFlag)
	{
		this.RegisterFlag = RegisterFlag;
	}
}