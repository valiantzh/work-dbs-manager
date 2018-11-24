package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

public class MBWebClientAdapter {
    protected MBWebClientAdapter() {
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBDepartListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDepartListQry bean = (com.dcdzsoft.business.mb.MBDepartListQry) aop
                .bind(com.dcdzsoft.business.mb.MBDepartListQry.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBDepartTreeQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDepartTreeQry bean = (com.dcdzsoft.business.mb.MBDepartTreeQry) aop
                .bind(com.dcdzsoft.business.mb.MBDepartTreeQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBDepartmentAdd p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDepartmentAdd bean = (com.dcdzsoft.business.mb.MBDepartmentAdd) aop
                .bind(com.dcdzsoft.business.mb.MBDepartmentAdd.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBDepartmentDel p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDepartmentDel bean = (com.dcdzsoft.business.mb.MBDepartmentDel) aop
                .bind(com.dcdzsoft.business.mb.MBDepartmentDel.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBDepartmentMod p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDepartmentMod bean = (com.dcdzsoft.business.mb.MBDepartmentMod) aop
                .bind(com.dcdzsoft.business.mb.MBDepartmentMod.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBDepartmentQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDepartmentQry bean = (com.dcdzsoft.business.mb.MBDepartmentQry) aop
                .bind(com.dcdzsoft.business.mb.MBDepartmentQry.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBDeviceAlertQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceAlertQry bean = (com.dcdzsoft.business.mb.MBDeviceAlertQry) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceAlertQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBDeviceAlertQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceAlertQryCount bean = (com.dcdzsoft.business.mb.MBDeviceAlertQryCount) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceAlertQryCount.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBDeviceOffline p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceOffline bean = (com.dcdzsoft.business.mb.MBDeviceOffline) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceOffline.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBDeviceSign doBusiness(
            com.dcdzsoft.dto.business.InParamMBDeviceSign p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceSign bean = (com.dcdzsoft.business.mb.MBDeviceSign) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceSign.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBDeviceSign0 doBusiness(
            com.dcdzsoft.dto.business.InParamMBDeviceSign0 p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceSign0 bean = (com.dcdzsoft.business.mb.MBDeviceSign0) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceSign0.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBDeviceSignQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceSignQry bean = (com.dcdzsoft.business.mb.MBDeviceSignQry) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceSignQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBDeviceSignQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceSignQryCount bean = (com.dcdzsoft.business.mb.MBDeviceSignQryCount) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceSignQryCount.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBGetAdvertisePic doBusiness(
            com.dcdzsoft.dto.business.InParamMBGetAdvertisePic p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetAdvertisePic bean = (com.dcdzsoft.business.mb.MBGetAdvertisePic) aop
                .bind(com.dcdzsoft.business.mb.MBGetAdvertisePic.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBGetBoxDectionResult p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetBoxDectionResult bean = (com.dcdzsoft.business.mb.MBGetBoxDectionResult) aop
                .bind(com.dcdzsoft.business.mb.MBGetBoxDectionResult.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBGetControlParam p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetControlParam bean = (com.dcdzsoft.business.mb.MBGetControlParam) aop
                .bind(com.dcdzsoft.business.mb.MBGetControlParam.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBGetDeviceSysLog p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetDeviceSysLog bean = (com.dcdzsoft.business.mb.MBGetDeviceSysLog) aop
                .bind(com.dcdzsoft.business.mb.MBGetDeviceSysLog.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBGetHistoryPackage p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetHistoryPackage bean = (com.dcdzsoft.business.mb.MBGetHistoryPackage) aop
                .bind(com.dcdzsoft.business.mb.MBGetHistoryPackage.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBGetInboxPackage p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetInboxPackage bean = (com.dcdzsoft.business.mb.MBGetInboxPackage) aop
                .bind(com.dcdzsoft.business.mb.MBGetInboxPackage.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBGetManagerLog p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetManagerLog bean = (com.dcdzsoft.business.mb.MBGetManagerLog) aop
                .bind(com.dcdzsoft.business.mb.MBGetManagerLog.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBGetUpFailureData p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetUpFailureData bean = (com.dcdzsoft.business.mb.MBGetUpFailureData) aop
                .bind(com.dcdzsoft.business.mb.MBGetUpFailureData.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBHeartBeat doBusiness(
            com.dcdzsoft.dto.business.InParamMBHeartBeat p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBHeartBeat bean = (com.dcdzsoft.business.mb.MBHeartBeat) aop
                .bind(com.dcdzsoft.business.mb.MBHeartBeat.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBInitialization doBusiness(
            com.dcdzsoft.dto.business.InParamMBInitialization p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBInitialization bean = (com.dcdzsoft.business.mb.MBInitialization) aop
                .bind(com.dcdzsoft.business.mb.MBInitialization.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBMobileBlackListAdd p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBMobileBlackListAdd bean = (com.dcdzsoft.business.mb.MBMobileBlackListAdd) aop
                .bind(com.dcdzsoft.business.mb.MBMobileBlackListAdd.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBMobileBlackListDel p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBMobileBlackListDel bean = (com.dcdzsoft.business.mb.MBMobileBlackListDel) aop
                .bind(com.dcdzsoft.business.mb.MBMobileBlackListDel.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBMobileBlackListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBMobileBlackListQry bean = (com.dcdzsoft.business.mb.MBMobileBlackListQry) aop
                .bind(com.dcdzsoft.business.mb.MBMobileBlackListQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBMobileBlackListQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBMobileBlackListQryCount bean = (com.dcdzsoft.business.mb.MBMobileBlackListQryCount) aop
                .bind(com.dcdzsoft.business.mb.MBMobileBlackListQryCount.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBPwdShortMsgQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBPwdShortMsgQry bean = (com.dcdzsoft.business.mb.MBPwdShortMsgQry) aop
                .bind(com.dcdzsoft.business.mb.MBPwdShortMsgQry.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBPwdShortMsgQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBPwdShortMsgQryCount bean = (com.dcdzsoft.business.mb.MBPwdShortMsgQryCount) aop
                .bind(com.dcdzsoft.business.mb.MBPwdShortMsgQryCount.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBPickupPwdQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBPickupPwdQry bean = (com.dcdzsoft.business.mb.MBPickupPwdQry) aop
                .bind(com.dcdzsoft.business.mb.MBPickupPwdQry.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBPwdShortMsgReSend p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBPwdShortMsgReSend bean = (com.dcdzsoft.business.mb.MBPwdShortMsgReSend) aop
                .bind(com.dcdzsoft.business.mb.MBPwdShortMsgReSend.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBReminderWaterQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBReminderWaterQry bean = (com.dcdzsoft.business.mb.MBReminderWaterQry) aop
                .bind(com.dcdzsoft.business.mb.MBReminderWaterQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBReminderWaterQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBReminderWaterQryCount bean = (com.dcdzsoft.business.mb.MBReminderWaterQryCount) aop
                .bind(com.dcdzsoft.business.mb.MBReminderWaterQryCount.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBReportBoxStatus doBusiness(
            com.dcdzsoft.dto.business.InParamMBReportBoxStatus p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBReportBoxStatus bean = (com.dcdzsoft.business.mb.MBReportBoxStatus) aop
                .bind(com.dcdzsoft.business.mb.MBReportBoxStatus.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBReportPeripheralStatus doBusiness(
            com.dcdzsoft.dto.business.InParamMBReportPeripheralStatus p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBReportPeripheralStatus bean = (com.dcdzsoft.business.mb.MBReportPeripheralStatus) aop
                .bind(com.dcdzsoft.business.mb.MBReportPeripheralStatus.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBSendUpgradeInform p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBSendUpgradeInform bean = (com.dcdzsoft.business.mb.MBSendUpgradeInform) aop
                .bind(com.dcdzsoft.business.mb.MBSendUpgradeInform.class);
        return bean.doBusiness(p1);
    }

    public static com.dcdzsoft.dto.business.OutParamMBSpotAdminLogin doBusiness(
            com.dcdzsoft.dto.business.InParamMBSpotAdminLogin p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBSpotAdminLogin bean = (com.dcdzsoft.business.mb.MBSpotAdminLogin) aop
                .bind(com.dcdzsoft.business.mb.MBSpotAdminLogin.class);
        return bean.doBusiness(p1);
    }

    public static java.lang.String doBusiness(com.dcdzsoft.dto.business.InParamMBUploadDeviceAlert p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBUploadDeviceAlert bean = (com.dcdzsoft.business.mb.MBUploadDeviceAlert) aop
                .bind(com.dcdzsoft.business.mb.MBUploadDeviceAlert.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBUploadInboxPack p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBUploadInboxPack bean = (com.dcdzsoft.business.mb.MBUploadInboxPack) aop
                .bind(com.dcdzsoft.business.mb.MBUploadInboxPack.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBTerminalLedMsgQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBTerminalLedMsgQry bean = (com.dcdzsoft.business.mb.MBTerminalLedMsgQry) aop
                .bind(com.dcdzsoft.business.mb.MBTerminalLedMsgQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBTerminalLedMsgQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBTerminalLedMsgQryCount bean = (com.dcdzsoft.business.mb.MBTerminalLedMsgQryCount) aop
                .bind(com.dcdzsoft.business.mb.MBTerminalLedMsgQryCount.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBPushTerminalLedMsg p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBPushTerminalLedMsg bean = (com.dcdzsoft.business.mb.MBPushTerminalLedMsg) aop
                .bind(com.dcdzsoft.business.mb.MBPushTerminalLedMsg.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBDeliverInboxPac p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeliverInboxPac bean = (com.dcdzsoft.business.mb.MBDeliverInboxPac) aop
                .bind(com.dcdzsoft.business.mb.MBDeliverInboxPac.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBFeedbackFailQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBFeedbackFailQry bean = (com.dcdzsoft.business.mb.MBFeedbackFailQry) aop
                .bind(com.dcdzsoft.business.mb.MBFeedbackFailQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBFeedbackFailReSend p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBFeedbackFailReSend bean = (com.dcdzsoft.business.mb.MBFeedbackFailReSend) aop
                .bind(com.dcdzsoft.business.mb.MBFeedbackFailReSend.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBFeedbackFailDel p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBFeedbackFailDel bean = (com.dcdzsoft.business.mb.MBFeedbackFailDel) aop
                .bind(com.dcdzsoft.business.mb.MBFeedbackFailDel.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBOpenBoxWaterQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBOpenBoxWaterQry bean = (com.dcdzsoft.business.mb.MBOpenBoxWaterQry) aop
                .bind(com.dcdzsoft.business.mb.MBOpenBoxWaterQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBOpenBoxWaterQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBOpenBoxWaterQryCount bean = (com.dcdzsoft.business.mb.MBOpenBoxWaterQryCount) aop
                .bind(com.dcdzsoft.business.mb.MBOpenBoxWaterQryCount.class);
        return bean.doBusiness(p1);
    }
}
