package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class PTWebClientAdapter {
    protected PTWebClientAdapter() {
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTDelPackage p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDelPackage bean = (com.dcdzsoft.business.pt.PTDelPackage) aop
                .bind(com.dcdzsoft.business.pt.PTDelPackage.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTDeliverPackage2DB p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliverPackage2DB bean = (com.dcdzsoft.business.pt.PTDeliverPackage2DB) aop
                .bind(com.dcdzsoft.business.pt.PTDeliverPackage2DB.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPTDeliveryRecordQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliveryRecordQry bean = (com.dcdzsoft.business.pt.PTDeliveryRecordQry) aop
                .bind(com.dcdzsoft.business.pt.PTDeliveryRecordQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTDeliveryRecordQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliveryRecordQryCount bean = (com.dcdzsoft.business.pt.PTDeliveryRecordQryCount) aop
                .bind(com.dcdzsoft.business.pt.PTDeliveryRecordQryCount.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamPTDeliverySum doBusiness(
            com.dcdzsoft.dto.business.InParamPTDeliverySum p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliverySum bean = (com.dcdzsoft.business.pt.PTDeliverySum) aop
                .bind(com.dcdzsoft.business.pt.PTDeliverySum.class);
        return bean.doBusiness(p1);
    }

    public static java.util.List<com.dcdzsoft.dto.business.OutParamPTExpiredPackQry> doBusiness(
            com.dcdzsoft.dto.business.InParamPTExpiredPackQry p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTExpiredPackQry bean = (com.dcdzsoft.business.pt.PTExpiredPackQry) aop
                .bind(com.dcdzsoft.business.pt.PTExpiredPackQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTInboxPackage2DB p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTInboxPackage2DB bean = (com.dcdzsoft.business.pt.PTInboxPackage2DB) aop
                .bind(com.dcdzsoft.business.pt.PTInboxPackage2DB.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamPTMobileBlackList doBusiness(
            com.dcdzsoft.dto.business.InParamPTMobileBlackList p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTMobileBlackList bean = (com.dcdzsoft.business.pt.PTMobileBlackList) aop
                .bind(com.dcdzsoft.business.pt.PTMobileBlackList.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTModExpiredTime p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTModExpiredTime bean = (com.dcdzsoft.business.pt.PTModExpiredTime) aop
                .bind(com.dcdzsoft.business.pt.PTModExpiredTime.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTModPackageStatus p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTModPackageStatus bean = (com.dcdzsoft.business.pt.PTModPackageStatus) aop
                .bind(com.dcdzsoft.business.pt.PTModPackageStatus.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamPTOneDeliveryRecordQry doBusiness(
            com.dcdzsoft.dto.business.InParamPTOneDeliveryRecordQry p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTOneDeliveryRecordQry bean = (com.dcdzsoft.business.pt.PTOneDeliveryRecordQry) aop
                .bind(com.dcdzsoft.business.pt.PTOneDeliveryRecordQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTPackIsDelivery p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPackIsDelivery bean = (com.dcdzsoft.business.pt.PTPackIsDelivery) aop
                .bind(com.dcdzsoft.business.pt.PTPackIsDelivery.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamPTPushSubscribeOrder doBusiness(
            com.dcdzsoft.dto.business.InParamPTPushSubscribeOrder p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPushSubscribeOrder bean = (com.dcdzsoft.business.pt.PTPushSubscribeOrder) aop
                .bind(com.dcdzsoft.business.pt.PTPushSubscribeOrder.class);
        return bean.doBusiness(p1);
    }

    public static java.util.List<com.dcdzsoft.dto.business.OutParamPTReadPackageQry> doBusiness(
            com.dcdzsoft.dto.business.InParamPTReadPackageQry p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTReadPackageQry bean = (com.dcdzsoft.business.pt.PTReadPackageQry) aop
                .bind(com.dcdzsoft.business.pt.PTReadPackageQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTResetOpenBoxKey p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTResetOpenBoxKey bean = (com.dcdzsoft.business.pt.PTResetOpenBoxKey) aop
                .bind(com.dcdzsoft.business.pt.PTResetOpenBoxKey.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTSyncDelivery p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTSyncDelivery bean = (com.dcdzsoft.business.pt.PTSyncDelivery) aop
                .bind(com.dcdzsoft.business.pt.PTSyncDelivery.class);
        return bean.doBusiness(p1);
    }


    public static com.dcdzsoft.dto.business.OutParamPTReadyPackageAdd doBusiness(
            com.dcdzsoft.dto.business.InParamPTReadyPackageAdd p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTReadyPackageAdd bean = (com.dcdzsoft.business.pt.PTReadyPackageAdd) aop
                .bind(com.dcdzsoft.business.pt.PTReadyPackageAdd.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamPTPackageCustomerAdd doBusiness(
            com.dcdzsoft.dto.business.InParamPTPackageCustomerAdd p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPackageCustomerAdd bean = (com.dcdzsoft.business.pt.PTPackageCustomerAdd) aop
                .bind(com.dcdzsoft.business.pt.PTPackageCustomerAdd.class);
        return bean.doBusiness(p1);
    }
}
