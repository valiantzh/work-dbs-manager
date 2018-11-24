package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class PYWebClientAdapter {
    protected PYWebClientAdapter() {
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSServicesAdd p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSServicesAdd bean = (com.dcdzsoft.business.py.PYSMSServicesAdd) aop
                .bind(com.dcdzsoft.business.py.PYSMSServicesAdd.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSServicesDel p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSServicesDel bean = (com.dcdzsoft.business.py.PYSMSServicesDel) aop
                .bind(com.dcdzsoft.business.py.PYSMSServicesDel.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSServicesMod p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSServicesMod bean = (com.dcdzsoft.business.py.PYSMSServicesMod) aop
                .bind(com.dcdzsoft.business.py.PYSMSServicesMod.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYSMSServicesQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSServicesQry bean = (com.dcdzsoft.business.py.PYSMSServicesQry) aop
                .bind(com.dcdzsoft.business.py.PYSMSServicesQry.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYSMSServicesListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSServicesListQry bean = (com.dcdzsoft.business.py.PYSMSServicesListQry) aop
                .bind(com.dcdzsoft.business.py.PYSMSServicesListQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountAdd p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountAdd bean = (com.dcdzsoft.business.py.PYSMSAccountAdd) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountAdd.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountDel p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountDel bean = (com.dcdzsoft.business.py.PYSMSAccountDel) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountDel.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountMod p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountMod bean = (com.dcdzsoft.business.py.PYSMSAccountMod) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountMod.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountQry bean = (com.dcdzsoft.business.py.PYSMSAccountQry) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountQryCount bean = (com.dcdzsoft.business.py.PYSMSAccountQryCount) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountQryCount.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountListQry bean = (com.dcdzsoft.business.py.PYSMSAccountListQry) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountListQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountTopup p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountTopup bean = (com.dcdzsoft.business.py.PYSMSAccountTopup) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountTopup.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountTopupCancel p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountTopupCancel bean = (com.dcdzsoft.business.py.PYSMSAccountTopupCancel) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountTopupCancel.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYSMSBillWaterQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSBillWaterQry bean = (com.dcdzsoft.business.py.PYSMSBillWaterQry) aop
                .bind(com.dcdzsoft.business.py.PYSMSBillWaterQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSBillWaterQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSBillWaterQryCount bean = (com.dcdzsoft.business.py.PYSMSBillWaterQryCount) aop
                .bind(com.dcdzsoft.business.py.PYSMSBillWaterQryCount.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYCustomerCardQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCustomerCardQry bean = (com.dcdzsoft.business.py.PYCustomerCardQry) aop
                .bind(com.dcdzsoft.business.py.PYCustomerCardQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYCustomerCardQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCustomerCardQryCount bean = (com.dcdzsoft.business.py.PYCustomerCardQryCount) aop
                .bind(com.dcdzsoft.business.py.PYCustomerCardQryCount.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYCustomerCardWaterSync p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCustomerCardWaterSync bean = (com.dcdzsoft.business.py.PYCustomerCardWaterSync) aop
                .bind(com.dcdzsoft.business.py.PYCustomerCardWaterSync.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYCustomerCardWaterMod p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCustomerCardWaterMod bean = (com.dcdzsoft.business.py.PYCustomerCardWaterMod) aop
                .bind(com.dcdzsoft.business.py.PYCustomerCardWaterMod.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYCustomerCardWaterQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCustomerCardWaterQry bean = (com.dcdzsoft.business.py.PYCustomerCardWaterQry) aop
                .bind(com.dcdzsoft.business.py.PYCustomerCardWaterQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYCustomerCardWaterQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCustomerCardWaterQryCount bean = (com.dcdzsoft.business.py.PYCustomerCardWaterQryCount) aop
                .bind(com.dcdzsoft.business.py.PYCustomerCardWaterQryCount.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYCardBillReport4MonthlyQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCardBillReport4MonthlyQry bean = (com.dcdzsoft.business.py.PYCardBillReport4MonthlyQry) aop
                .bind(com.dcdzsoft.business.py.PYCardBillReport4MonthlyQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYCardBillReport4MonthlyQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYCardBillReport4MonthlyQryCount bean = (com.dcdzsoft.business.py.PYCardBillReport4MonthlyQryCount) aop
                .bind(com.dcdzsoft.business.py.PYCardBillReport4MonthlyQryCount.class);
        return bean.doBusiness(p1);
    }
}