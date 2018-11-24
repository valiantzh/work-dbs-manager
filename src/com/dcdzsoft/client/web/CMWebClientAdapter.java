package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class CMWebClientAdapter {
    protected CMWebClientAdapter() {
    }
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMDeliverUserAdd p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMDeliverUserAdd bean = 
                ( com.dcdzsoft.business.cm.CMDeliverUserAdd)aop.bind(com.dcdzsoft.business.cm.CMDeliverUserAdd.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMDeliverUserDel p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMDeliverUserDel bean = 
                ( com.dcdzsoft.business.cm.CMDeliverUserDel)aop.bind(com.dcdzsoft.business.cm.CMDeliverUserDel.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMDeliverUserMod p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMDeliverUserMod bean = 
                ( com.dcdzsoft.business.cm.CMDeliverUserMod)aop.bind(com.dcdzsoft.business.cm.CMDeliverUserMod.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamCMDeliverUserQry p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMDeliverUserQry bean = 
                ( com.dcdzsoft.business.cm.CMDeliverUserQry)aop.bind(com.dcdzsoft.business.cm.CMDeliverUserQry.class);
        return bean.doBusiness(p1);
    }
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMDeliverUserQryCount p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMDeliverUserQryCount bean = 
                ( com.dcdzsoft.business.cm.CMDeliverUserQryCount)aop.bind(com.dcdzsoft.business.cm.CMDeliverUserQryCount.class);
        return bean.doBusiness(p1);
    }
    
    
    
  
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerAdd p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerAdd bean = 
                ( com.dcdzsoft.business.cm.CMCustomerAdd)aop.bind(com.dcdzsoft.business.cm.CMCustomerAdd.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerDel p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerDel bean = 
                ( com.dcdzsoft.business.cm.CMCustomerDel)aop.bind(com.dcdzsoft.business.cm.CMCustomerDel.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerMod p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerMod bean = 
                ( com.dcdzsoft.business.cm.CMCustomerMod)aop.bind(com.dcdzsoft.business.cm.CMCustomerMod.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerQry p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerQry bean = 
                ( com.dcdzsoft.business.cm.CMCustomerQry)aop.bind(com.dcdzsoft.business.cm.CMCustomerQry.class);
        return bean.doBusiness(p1);
    }
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerQryCount p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerQryCount bean = 
                ( com.dcdzsoft.business.cm.CMCustomerQryCount)aop.bind(com.dcdzsoft.business.cm.CMCustomerQryCount.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerListQry p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerListQry bean = 
                ( com.dcdzsoft.business.cm.CMCustomerListQry)aop.bind(com.dcdzsoft.business.cm.CMCustomerListQry.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerTopup p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerTopup bean = 
                ( com.dcdzsoft.business.cm.CMCustomerTopup)aop.bind(com.dcdzsoft.business.cm.CMCustomerTopup.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerTopupCancel p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerTopupCancel bean = 
                ( com.dcdzsoft.business.cm.CMCustomerTopupCancel)aop.bind(com.dcdzsoft.business.cm.CMCustomerTopupCancel.class);
        return bean.doBusiness(p1);
    }
    
    public static com.dcdzsoft.dto.business.OutParamCMCustomerBalanceQry doBusiness(com.dcdzsoft.dto.business.InParamCMCustomerBalanceQry p1)
            throws com.dcdzsoft.EduException
    {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.cm.CMCustomerBalanceQry bean = 
                ( com.dcdzsoft.business.cm.CMCustomerBalanceQry)aop.bind(com.dcdzsoft.business.cm.CMCustomerBalanceQry.class);
        return bean.doBusiness(p1);
    }
}
