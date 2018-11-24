package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class  PAWebClientAdapter
{
	protected PAWebClientAdapter() {}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPAAllDictQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PAAllDictQry bean = ( com.dcdzsoft.business.pa.PAAllDictQry)aop.bind(com.dcdzsoft.business.pa.PAAllDictQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPAControlParamMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PAControlParamMod bean = ( com.dcdzsoft.business.pa.PAControlParamMod)aop.bind(com.dcdzsoft.business.pa.PAControlParamMod.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPAControlParamQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PAControlParamQry bean = ( com.dcdzsoft.business.pa.PAControlParamQry)aop.bind(com.dcdzsoft.business.pa.PAControlParamQry.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPAControlTypeQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PAControlTypeQry bean = ( com.dcdzsoft.business.pa.PAControlTypeQry)aop.bind(com.dcdzsoft.business.pa.PAControlTypeQry.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPADictTypeQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PADictTypeQry bean = ( com.dcdzsoft.business.pa.PADictTypeQry)aop.bind(com.dcdzsoft.business.pa.PADictTypeQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPADictionaryMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PADictionaryMod bean = ( com.dcdzsoft.business.pa.PADictionaryMod)aop.bind(com.dcdzsoft.business.pa.PADictionaryMod.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPADictionaryQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PADictionaryQry bean = ( com.dcdzsoft.business.pa.PADictionaryQry)aop.bind(com.dcdzsoft.business.pa.PADictionaryQry.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamPATerminalCtrlParamMod p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PATerminalCtrlParamMod bean = ( com.dcdzsoft.business.pa.PATerminalCtrlParamMod)aop.bind(com.dcdzsoft.business.pa.PATerminalCtrlParamMod.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPATerminalCtrlParamQry p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.pa.PATerminalCtrlParamQry bean = ( com.dcdzsoft.business.pa.PATerminalCtrlParamQry)aop.bind(com.dcdzsoft.business.pa.PATerminalCtrlParamQry.class);
		return bean.doBusiness(p1);
	}

}
