package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class  RMWebClientAdapter
{
	protected RMWebClientAdapter() {}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamRMAppealQryCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.rm.RMAppealQryCount bean = ( com.dcdzsoft.business.rm.RMAppealQryCount)aop.bind(com.dcdzsoft.business.rm.RMAppealQryCount.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamRMAppealQuery p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.rm.RMAppealQuery bean = ( com.dcdzsoft.business.rm.RMAppealQuery)aop.bind(com.dcdzsoft.business.rm.RMAppealQuery.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamRMOpenBox p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.rm.RMOpenBox bean = ( com.dcdzsoft.business.rm.RMOpenBox)aop.bind(com.dcdzsoft.business.rm.RMOpenBox.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamRMRemoteOpenBox p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.rm.RMRemoteOpenBox bean = ( com.dcdzsoft.business.rm.RMRemoteOpenBox)aop.bind(com.dcdzsoft.business.rm.RMRemoteOpenBox.class);
		return bean.doBusiness(p1);
	}

	public static com.dcdzsoft.dto.business.OutParamRMRequestOpenBoxKey doBusiness(com.dcdzsoft.dto.business.InParamRMRequestOpenBoxKey p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.rm.RMRequestOpenBoxKey bean = ( com.dcdzsoft.business.rm.RMRequestOpenBoxKey)aop.bind(com.dcdzsoft.business.rm.RMRequestOpenBoxKey.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamRMSetFaultReason p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.rm.RMSetFaultReason bean = ( com.dcdzsoft.business.rm.RMSetFaultReason)aop.bind(com.dcdzsoft.business.rm.RMSetFaultReason.class);
		return bean.doBusiness(p1);
	}

}
