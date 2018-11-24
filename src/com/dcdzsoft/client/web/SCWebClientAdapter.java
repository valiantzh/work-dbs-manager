package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class  SCWebClientAdapter
{
	protected SCWebClientAdapter() {}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamSCCheckFtpLog p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCCheckFtpLog bean = ( com.dcdzsoft.business.sc.SCCheckFtpLog)aop.bind(com.dcdzsoft.business.sc.SCCheckFtpLog.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamSCInsertFtpLog p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCInsertFtpLog bean = ( com.dcdzsoft.business.sc.SCInsertFtpLog)aop.bind(com.dcdzsoft.business.sc.SCInsertFtpLog.class);
		return bean.doBusiness(p1);
	}

	public static com.dcdzsoft.dto.business.OutParamSCSyncServerTime doBusiness(com.dcdzsoft.dto.business.InParamSCSyncServerTime p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncServerTime bean = ( com.dcdzsoft.business.sc.SCSyncServerTime)aop.bind(com.dcdzsoft.business.sc.SCSyncServerTime.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamSCSyncTerminalInfo p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncTerminalInfo bean = ( com.dcdzsoft.business.sc.SCSyncTerminalInfo)aop.bind(com.dcdzsoft.business.sc.SCSyncTerminalInfo.class);
		return bean.doBusiness(p1);
	}
	
	public static String doBusiness(com.dcdzsoft.dto.business.InParamSCSyncManagerLog p1)
			  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncManagerLog bean = ( com.dcdzsoft.business.sc.SCSyncManagerLog)aop.bind(com.dcdzsoft.business.sc.SCSyncManagerLog.class);
		return bean.doBusiness(p1);
	}
	
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSCPushDataQueueQry p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sc.SCPushDataQueueQry bean = 
                ( com.dcdzsoft.business.sc.SCPushDataQueueQry)aop.bind(com.dcdzsoft.business.sc.SCPushDataQueueQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSCPushDataQueueQryCount p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sc.SCPushDataQueueQryCount bean = 
                ( com.dcdzsoft.business.sc.SCPushDataQueueQryCount)aop.bind(com.dcdzsoft.business.sc.SCPushDataQueueQryCount.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamSCPushDataQueueAdd p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sc.SCPushDataQueueAdd bean = 
                ( com.dcdzsoft.business.sc.SCPushDataQueueAdd)aop.bind(com.dcdzsoft.business.sc.SCPushDataQueueAdd.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamSCPushDataQueueDel p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sc.SCPushDataQueueDel bean = 
                ( com.dcdzsoft.business.sc.SCPushDataQueueDel)aop.bind(com.dcdzsoft.business.sc.SCPushDataQueueDel.class);
        return bean.doBusiness(p1);
    }
    public static int doBusiness(com.dcdzsoft.dto.business.InParamSCPushDataQueueMod p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sc.SCPushDataQueueMod bean = 
                ( com.dcdzsoft.business.sc.SCPushDataQueueMod)aop.bind(com.dcdzsoft.business.sc.SCPushDataQueueMod.class);
        return bean.doBusiness(p1);
    }
    public static int doBusiness(com.dcdzsoft.dto.business.InParamSCPushDataQueueResend p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sc.SCPushDataQueueResend bean = 
                ( com.dcdzsoft.business.sc.SCPushDataQueueResend)aop.bind(com.dcdzsoft.business.sc.SCPushDataQueueResend.class);
        return bean.doBusiness(p1);
    }
}
