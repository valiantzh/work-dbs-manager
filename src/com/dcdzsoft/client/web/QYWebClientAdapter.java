package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class  QYWebClientAdapter
{
	protected QYWebClientAdapter() {}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYSetupStat4Shanghai p1)
			  throws com.dcdzsoft.EduException
		{
			BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
			com.dcdzsoft.business.qy.QYSetupStat4Shanghai bean = ( com.dcdzsoft.business.qy.QYSetupStat4Shanghai)aop.bind(com.dcdzsoft.business.qy.QYSetupStat4Shanghai.class);
			return bean.doBusiness(p1);
		}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYBoxUsedStat p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.qy.QYBoxUsedStat bean = ( com.dcdzsoft.business.qy.QYBoxUsedStat)aop.bind(com.dcdzsoft.business.qy.QYBoxUsedStat.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamQYBoxUsedStatCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.qy.QYBoxUsedStatCount bean = ( com.dcdzsoft.business.qy.QYBoxUsedStatCount)aop.bind(com.dcdzsoft.business.qy.QYBoxUsedStatCount.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYStat4Company p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.qy.QYStat4Company bean = ( com.dcdzsoft.business.qy.QYStat4Company)aop.bind(com.dcdzsoft.business.qy.QYStat4Company.class);
		return bean.doBusiness(p1);
	}

	public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYStat4Terminal p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.qy.QYStat4Terminal bean = ( com.dcdzsoft.business.qy.QYStat4Terminal)aop.bind(com.dcdzsoft.business.qy.QYStat4Terminal.class);
		return bean.doBusiness(p1);
	}

	public static int doBusiness(com.dcdzsoft.dto.business.InParamQYStat4TerminalCount p1)
		  throws com.dcdzsoft.EduException
	{
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.qy.QYStat4TerminalCount bean = ( com.dcdzsoft.business.qy.QYStat4TerminalCount)aop.bind(com.dcdzsoft.business.qy.QYStat4TerminalCount.class);
		return bean.doBusiness(p1);
	}
	
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYStat4SMS p1)
			  throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYStat4SMS bean = ( com.dcdzsoft.business.qy.QYStat4SMS)aop.bind(com.dcdzsoft.business.qy.QYStat4SMS.class);
        return bean.doBusiness(p1);
    }
	
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYFeeStat4Terminal p1)
			  throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYFeeStat4Terminal bean = ( com.dcdzsoft.business.qy.QYFeeStat4Terminal)aop.bind(com.dcdzsoft.business.qy.QYFeeStat4Terminal.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamQYFeeStat4TerminalCount p1)
			  throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYFeeStat4TerminalCount bean = ( com.dcdzsoft.business.qy.QYFeeStat4TerminalCount)aop.bind(com.dcdzsoft.business.qy.QYFeeStat4TerminalCount.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYBoxUsageDailyStat p1)
	            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYBoxUsageDailyStat bean = 
                ( com.dcdzsoft.business.qy.QYBoxUsageDailyStat)aop.bind(com.dcdzsoft.business.qy.QYBoxUsageDailyStat.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamQYBoxUsageDailyStatCount p1)
	            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYBoxUsageDailyStatCount bean = 
                ( com.dcdzsoft.business.qy.QYBoxUsageDailyStatCount)aop.bind(com.dcdzsoft.business.qy.QYBoxUsageDailyStatCount.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYOrderInboxTimeStat p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYOrderInboxTimeStat bean = 
                ( com.dcdzsoft.business.qy.QYOrderInboxTimeStat)aop.bind(com.dcdzsoft.business.qy.QYOrderInboxTimeStat.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamQYOrderInboxTimeStatCount p1)
                throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYOrderInboxTimeStatCount bean = 
                ( com.dcdzsoft.business.qy.QYOrderInboxTimeStatCount)aop.bind(com.dcdzsoft.business.qy.QYOrderInboxTimeStatCount.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYTerminalUsageMonthly p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYTerminalUsageMonthly bean = 
                ( com.dcdzsoft.business.qy.QYTerminalUsageMonthly)aop.bind(com.dcdzsoft.business.qy.QYTerminalUsageMonthly.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamQYTerminalUsageMonthlyCount p1)
                throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYTerminalUsageMonthlyCount bean = 
                ( com.dcdzsoft.business.qy.QYTerminalUsageMonthlyCount)aop.bind(com.dcdzsoft.business.qy.QYTerminalUsageMonthlyCount.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamQYStatDeliverOrder p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYStatDeliverOrder bean = 
                ( com.dcdzsoft.business.qy.QYStatDeliverOrder)aop.bind(com.dcdzsoft.business.qy.QYStatDeliverOrder.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamQYStatDeliverOrderCount p1)
                throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.qy.QYStatDeliverOrderCount bean = 
                ( com.dcdzsoft.business.qy.QYStatDeliverOrderCount)aop.bind(com.dcdzsoft.business.qy.QYStatDeliverOrderCount.class);
        return bean.doBusiness(p1);
    }
}
