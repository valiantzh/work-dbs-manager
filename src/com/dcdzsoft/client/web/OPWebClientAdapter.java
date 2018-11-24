package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class  OPWebClientAdapter
{
	protected OPWebClientAdapter() {}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPDelSpotOperator p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPDelSpotOperator bean = ( com.dcdzsoft.business.op.OPDelSpotOperator)aop.bind(com.dcdzsoft.business.op.OPDelSpotOperator.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPFunctionQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPFunctionQry bean = ( com.dcdzsoft.business.op.OPFunctionQry)aop.bind(com.dcdzsoft.business.op.OPFunctionQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPModSpotOperPwd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPModSpotOperPwd bean = ( com.dcdzsoft.business.op.OPModSpotOperPwd)aop.bind(com.dcdzsoft.business.op.OPModSpotOperPwd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperAllLimitAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperAllLimitAdd bean = ( com.dcdzsoft.business.op.OPOperAllLimitAdd)aop.bind(com.dcdzsoft.business.op.OPOperAllLimitAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperAllLimitDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperAllLimitDel bean = ( com.dcdzsoft.business.op.OPOperAllLimitDel)aop.bind(com.dcdzsoft.business.op.OPOperAllLimitDel.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperAllLimitQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperAllLimitQry bean = ( com.dcdzsoft.business.op.OPOperAllLimitQry)aop.bind(com.dcdzsoft.business.op.OPOperAllLimitQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperClearPwd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperClearPwd bean = ( com.dcdzsoft.business.op.OPOperClearPwd)aop.bind(com.dcdzsoft.business.op.OPOperClearPwd.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperLogQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperLogQry bean = ( com.dcdzsoft.business.op.OPOperLogQry)aop.bind(com.dcdzsoft.business.op.OPOperLogQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperLogQryCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperLogQryCount bean = ( com.dcdzsoft.business.op.OPOperLogQryCount)aop.bind(com.dcdzsoft.business.op.OPOperLogQryCount.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperLogin p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperLogin bean = ( com.dcdzsoft.business.op.OPOperLogin)aop.bind(com.dcdzsoft.business.op.OPOperLogin.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperLogout p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperLogout bean = ( com.dcdzsoft.business.op.OPOperLogout)aop.bind(com.dcdzsoft.business.op.OPOperLogout.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperModPwd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperModPwd bean = ( com.dcdzsoft.business.op.OPOperModPwd)aop.bind(com.dcdzsoft.business.op.OPOperModPwd.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperOnlineQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperOnlineQry bean = ( com.dcdzsoft.business.op.OPOperOnlineQry)aop.bind(com.dcdzsoft.business.op.OPOperOnlineQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperOnlineQryCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperOnlineQryCount bean = ( com.dcdzsoft.business.op.OPOperOnlineQryCount)aop.bind(com.dcdzsoft.business.op.OPOperOnlineQryCount.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperRoleAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperRoleAdd bean = ( com.dcdzsoft.business.op.OPOperRoleAdd)aop.bind(com.dcdzsoft.business.op.OPOperRoleAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperRoleDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperRoleDel bean = ( com.dcdzsoft.business.op.OPOperRoleDel)aop.bind(com.dcdzsoft.business.op.OPOperRoleDel.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperRoleQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperRoleQry bean = ( com.dcdzsoft.business.op.OPOperRoleQry)aop.bind(com.dcdzsoft.business.op.OPOperRoleQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperSpeRightAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperSpeRightAdd bean = ( com.dcdzsoft.business.op.OPOperSpeRightAdd)aop.bind(com.dcdzsoft.business.op.OPOperSpeRightAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperSpeRightDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperSpeRightDel bean = ( com.dcdzsoft.business.op.OPOperSpeRightDel)aop.bind(com.dcdzsoft.business.op.OPOperSpeRightDel.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperSpeRightQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperSpeRightQry bean = ( com.dcdzsoft.business.op.OPOperSpeRightQry)aop.bind(com.dcdzsoft.business.op.OPOperSpeRightQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperToMenuAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperToMenuAdd bean = ( com.dcdzsoft.business.op.OPOperToMenuAdd)aop.bind(com.dcdzsoft.business.op.OPOperToMenuAdd.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperToMenuQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperToMenuQry bean = ( com.dcdzsoft.business.op.OPOperToMenuQry)aop.bind(com.dcdzsoft.business.op.OPOperToMenuQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperatorAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperatorAdd bean = ( com.dcdzsoft.business.op.OPOperatorAdd)aop.bind(com.dcdzsoft.business.op.OPOperatorAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperatorMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperatorMod bean = ( com.dcdzsoft.business.op.OPOperatorMod)aop.bind(com.dcdzsoft.business.op.OPOperatorMod.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperatorModStatus p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperatorModStatus bean = ( com.dcdzsoft.business.op.OPOperatorModStatus)aop.bind(com.dcdzsoft.business.op.OPOperatorModStatus.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPOperatorQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperatorQry bean = ( com.dcdzsoft.business.op.OPOperatorQry)aop.bind(com.dcdzsoft.business.op.OPOperatorQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperatorQryCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPOperatorQryCount bean = ( com.dcdzsoft.business.op.OPOperatorQryCount)aop.bind(com.dcdzsoft.business.op.OPOperatorQryCount.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPPushSpotOperator p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPPushSpotOperator bean = ( com.dcdzsoft.business.op.OPPushSpotOperator)aop.bind(com.dcdzsoft.business.op.OPPushSpotOperator.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleAdd bean = ( com.dcdzsoft.business.op.OPRoleAdd)aop.bind(com.dcdzsoft.business.op.OPRoleAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleAllLimitAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleAllLimitAdd bean = ( com.dcdzsoft.business.op.OPRoleAllLimitAdd)aop.bind(com.dcdzsoft.business.op.OPRoleAllLimitAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleAllLimitDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleAllLimitDel bean = ( com.dcdzsoft.business.op.OPRoleAllLimitDel)aop.bind(com.dcdzsoft.business.op.OPRoleAllLimitDel.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPRoleAllLimitQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleAllLimitQry bean = ( com.dcdzsoft.business.op.OPRoleAllLimitQry)aop.bind(com.dcdzsoft.business.op.OPRoleAllLimitQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleDel bean = ( com.dcdzsoft.business.op.OPRoleDel)aop.bind(com.dcdzsoft.business.op.OPRoleDel.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleMod bean = ( com.dcdzsoft.business.op.OPRoleMod)aop.bind(com.dcdzsoft.business.op.OPRoleMod.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPRoleQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleQry bean = ( com.dcdzsoft.business.op.OPRoleQry)aop.bind(com.dcdzsoft.business.op.OPRoleQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleSpeRightAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleSpeRightAdd bean = ( com.dcdzsoft.business.op.OPRoleSpeRightAdd)aop.bind(com.dcdzsoft.business.op.OPRoleSpeRightAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleSpeRightDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleSpeRightDel bean = ( com.dcdzsoft.business.op.OPRoleSpeRightDel)aop.bind(com.dcdzsoft.business.op.OPRoleSpeRightDel.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPRoleSpeRightQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleSpeRightQry bean = ( com.dcdzsoft.business.op.OPRoleSpeRightQry)aop.bind(com.dcdzsoft.business.op.OPRoleSpeRightQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPRoleToMenuAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleToMenuAdd bean = ( com.dcdzsoft.business.op.OPRoleToMenuAdd)aop.bind(com.dcdzsoft.business.op.OPRoleToMenuAdd.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPRoleToMenuQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPRoleToMenuQry bean = ( com.dcdzsoft.business.op.OPRoleToMenuQry)aop.bind(com.dcdzsoft.business.op.OPRoleToMenuQry.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPSpotOperQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.op.OPSpotOperQry bean = ( com.dcdzsoft.business.op.OPSpotOperQry)aop.bind(com.dcdzsoft.business.op.OPSpotOperQry.class);
		return bean.doBusiness(p1);
	}
	
	public static int doBusiness(com.dcdzsoft.dto.business.InParamOPOperatorDel p1)
			  throws com.dcdzsoft.EduException
		{
			BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
			com.dcdzsoft.business.op.OPOperatorDel bean = ( com.dcdzsoft.business.op.OPOperatorDel)aop.bind(com.dcdzsoft.business.op.OPOperatorDel.class);
			return bean.doBusiness(p1);
		}
}
