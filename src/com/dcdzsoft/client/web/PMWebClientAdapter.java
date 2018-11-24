package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class  PMWebClientAdapter
{
	protected PMWebClientAdapter() {}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMCompanyAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCompanyAdd bean = ( com.dcdzsoft.business.pm.PMCompanyAdd)aop.bind(com.dcdzsoft.business.pm.PMCompanyAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMCompanyDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCompanyDel bean = ( com.dcdzsoft.business.pm.PMCompanyDel)aop.bind(com.dcdzsoft.business.pm.PMCompanyDel.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPMCompanyListQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCompanyListQry bean = ( com.dcdzsoft.business.pm.PMCompanyListQry)aop.bind(com.dcdzsoft.business.pm.PMCompanyListQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMCompanyMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCompanyMod bean = ( com.dcdzsoft.business.pm.PMCompanyMod)aop.bind(com.dcdzsoft.business.pm.PMCompanyMod.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMCompanyModDep p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCompanyModDep bean = ( com.dcdzsoft.business.pm.PMCompanyModDep)aop.bind(com.dcdzsoft.business.pm.PMCompanyModDep.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPMCompanyQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCompanyQry bean = ( com.dcdzsoft.business.pm.PMCompanyQry)aop.bind(com.dcdzsoft.business.pm.PMCompanyQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMCompanyQryCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCompanyQryCount bean = ( com.dcdzsoft.business.pm.PMCompanyQryCount)aop.bind(com.dcdzsoft.business.pm.PMCompanyQryCount.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMCorpTerminalRightAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCorpTerminalRightAdd bean = ( com.dcdzsoft.business.pm.PMCorpTerminalRightAdd)aop.bind(com.dcdzsoft.business.pm.PMCorpTerminalRightAdd.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPMCorpTerminalRightQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMCorpTerminalRightQry bean = ( com.dcdzsoft.business.pm.PMCorpTerminalRightQry)aop.bind(com.dcdzsoft.business.pm.PMCorpTerminalRightQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMLogisticsAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMLogisticsAdd bean = ( com.dcdzsoft.business.pm.PMLogisticsAdd)aop.bind(com.dcdzsoft.business.pm.PMLogisticsAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMLogisticsDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMLogisticsDel bean = ( com.dcdzsoft.business.pm.PMLogisticsDel)aop.bind(com.dcdzsoft.business.pm.PMLogisticsDel.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMLogisticsMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMLogisticsMod bean = ( com.dcdzsoft.business.pm.PMLogisticsMod)aop.bind(com.dcdzsoft.business.pm.PMLogisticsMod.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPMLogisticsQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMLogisticsQry bean = ( com.dcdzsoft.business.pm.PMLogisticsQry)aop.bind(com.dcdzsoft.business.pm.PMLogisticsQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMManTerminalRightAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMManTerminalRightAdd bean = ( com.dcdzsoft.business.pm.PMManTerminalRightAdd)aop.bind(com.dcdzsoft.business.pm.PMManTerminalRightAdd.class);
		return bean.doBusiness(p1);
	}
	
	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMManTerminalRightDel p1)
			  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMManTerminalRightDel  bean = ( com.dcdzsoft.business.pm.PMManTerminalRightDel)aop.bind(com.dcdzsoft.business.pm.PMManTerminalRightDel.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPMManTerminalRightQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMManTerminalRightQry bean = ( com.dcdzsoft.business.pm.PMManTerminalRightQry)aop.bind(com.dcdzsoft.business.pm.PMManTerminalRightQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanAdd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanAdd bean = ( com.dcdzsoft.business.pm.PMPostmanAdd)aop.bind(com.dcdzsoft.business.pm.PMPostmanAdd.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanBindCard p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanBindCard bean = ( com.dcdzsoft.business.pm.PMPostmanBindCard)aop.bind(com.dcdzsoft.business.pm.PMPostmanBindCard.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanDel p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanDel bean = ( com.dcdzsoft.business.pm.PMPostmanDel)aop.bind(com.dcdzsoft.business.pm.PMPostmanDel.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanMod bean = ( com.dcdzsoft.business.pm.PMPostmanMod)aop.bind(com.dcdzsoft.business.pm.PMPostmanMod.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanModComp p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanModComp bean = ( com.dcdzsoft.business.pm.PMPostmanModComp)aop.bind(com.dcdzsoft.business.pm.PMPostmanModComp.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanModDep p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanModDep bean = ( com.dcdzsoft.business.pm.PMPostmanModDep)aop.bind(com.dcdzsoft.business.pm.PMPostmanModDep.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanModPwd p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanModPwd bean = ( com.dcdzsoft.business.pm.PMPostmanModPwd)aop.bind(com.dcdzsoft.business.pm.PMPostmanModPwd.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanQry bean = ( com.dcdzsoft.business.pm.PMPostmanQry)aop.bind(com.dcdzsoft.business.pm.PMPostmanQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanQryCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanQryCount bean = ( com.dcdzsoft.business.pm.PMPostmanQryCount)aop.bind(com.dcdzsoft.business.pm.PMPostmanQryCount.class);
		return bean.doBusiness(p1);
	}

	public static com.dcdzsoft.dto.business.OutParamPMPostmanRegister doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanRegister p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanRegister bean = ( com.dcdzsoft.business.pm.PMPostmanRegister)aop.bind(com.dcdzsoft.business.pm.PMPostmanRegister.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPMPostmanUnBindCard p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMPostmanUnBindCard bean = ( com.dcdzsoft.business.pm.PMPostmanUnBindCard)aop.bind(com.dcdzsoft.business.pm.PMPostmanUnBindCard.class);
		return bean.doBusiness(p1);
	}

	public static com.dcdzsoft.dto.business.OutParamPMReGetCheckCode doBusiness(com.dcdzsoft.dto.business.InParamPMReGetCheckCode p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pm.PMReGetCheckCode bean = ( com.dcdzsoft.business.pm.PMReGetCheckCode)aop.bind(com.dcdzsoft.business.pm.PMReGetCheckCode.class);
		return bean.doBusiness(p1);
	}

}
