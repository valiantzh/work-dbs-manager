package com.dcdzsoft.client.web;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;
import com.dcdzsoft.dto.business.OutParamSMUpdate;

public class SMWebClientAdapter {
    protected SMWebClientAdapter() {
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMDbCfgQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMDbCfgQry bean = (com.dcdzsoft.business.sm.SMDbCfgQry) aop
                .bind(com.dcdzsoft.business.sm.SMDbCfgQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMDbCfgSet p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMDbCfgSet bean = (com.dcdzsoft.business.sm.SMDbCfgSet) aop
                .bind(com.dcdzsoft.business.sm.SMDbCfgSet.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMGlobalCfgQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMGlobalCfgQry bean = (com.dcdzsoft.business.sm.SMGlobalCfgQry) aop
                .bind(com.dcdzsoft.business.sm.SMGlobalCfgQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMGlobalCfgSet p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMGlobalCfgSet bean = (com.dcdzsoft.business.sm.SMGlobalCfgSet) aop
                .bind(com.dcdzsoft.business.sm.SMGlobalCfgSet.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMLoadMailVm p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMLoadMailVm bean = (com.dcdzsoft.business.sm.SMLoadMailVm) aop
                .bind(com.dcdzsoft.business.sm.SMLoadMailVm.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMModServerCfg p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMModServerCfg bean = (com.dcdzsoft.business.sm.SMModServerCfg) aop
                .bind(com.dcdzsoft.business.sm.SMModServerCfg.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMModSignPwd p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMModSignPwd bean = (com.dcdzsoft.business.sm.SMModSignPwd) aop
                .bind(com.dcdzsoft.business.sm.SMModSignPwd.class);
        return bean.doBusiness(p1);
    }

    public static OutParamSMUpdate doBusiness(com.dcdzsoft.dto.business.InParamSMUpdate p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpdate bean = (com.dcdzsoft.business.sm.SMUpdate) aop
                .bind(com.dcdzsoft.business.sm.SMUpdate.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMModSysVersion p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMModSysVersion bean = (com.dcdzsoft.business.sm.SMModSysVersion) aop
                .bind(com.dcdzsoft.business.sm.SMModSysVersion.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMModWelcomeInfo p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMModWelcomeInfo bean = (com.dcdzsoft.business.sm.SMModWelcomeInfo) aop
                .bind(com.dcdzsoft.business.sm.SMModWelcomeInfo.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMReloadCtrlParam p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMReloadCtrlParam bean = (com.dcdzsoft.business.sm.SMReloadCtrlParam) aop
                .bind(com.dcdzsoft.business.sm.SMReloadCtrlParam.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMReloadFuncData p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMReloadFuncData bean = (com.dcdzsoft.business.sm.SMReloadFuncData) aop
                .bind(com.dcdzsoft.business.sm.SMReloadFuncData.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMReloadLogCfg p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMReloadLogCfg bean = (com.dcdzsoft.business.sm.SMReloadLogCfg) aop
                .bind(com.dcdzsoft.business.sm.SMReloadLogCfg.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMSysInfoQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMSysInfoQry bean = (com.dcdzsoft.business.sm.SMSysInfoQry) aop
                .bind(com.dcdzsoft.business.sm.SMSysInfoQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMSystemInfoMod p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMSystemInfoMod bean = (com.dcdzsoft.business.sm.SMSystemInfoMod) aop
                .bind(com.dcdzsoft.business.sm.SMSystemInfoMod.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMSystemInfoQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMSystemInfoQry bean = (com.dcdzsoft.business.sm.SMSystemInfoQry) aop
                .bind(com.dcdzsoft.business.sm.SMSystemInfoQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMSystemInfoQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMSystemInfoQryCount bean = (com.dcdzsoft.business.sm.SMSystemInfoQryCount) aop
                .bind(com.dcdzsoft.business.sm.SMSystemInfoQryCount.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMSystemInfoListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMSystemInfoListQry bean = (com.dcdzsoft.business.sm.SMSystemInfoListQry) aop
                .bind(com.dcdzsoft.business.sm.SMSystemInfoListQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradePackAdd p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradePackAdd bean = (com.dcdzsoft.business.sm.SMUpgradePackAdd) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradePackAdd.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradePackMod p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradePackMod bean = (com.dcdzsoft.business.sm.SMUpgradePackMod) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradePackMod.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradePackDel p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradePackDel bean = (com.dcdzsoft.business.sm.SMUpgradePackDel) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradePackDel.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradePackQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradePackQry bean = (com.dcdzsoft.business.sm.SMUpgradePackQry) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradePackQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradePackQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradePackQryCount bean = (com.dcdzsoft.business.sm.SMUpgradePackQryCount) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradePackQryCount.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradePackListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradePackListQry bean = (com.dcdzsoft.business.sm.SMUpgradePackListQry) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradePackListQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMTerminalSoftMod p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMTerminalSoftMod bean = (com.dcdzsoft.business.sm.SMTerminalSoftMod) aop
                .bind(com.dcdzsoft.business.sm.SMTerminalSoftMod.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMTerminalSoftQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMTerminalSoftQry bean = (com.dcdzsoft.business.sm.SMTerminalSoftQry) aop
                .bind(com.dcdzsoft.business.sm.SMTerminalSoftQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMTerminalSoftQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMTerminalSoftQryCount bean = (com.dcdzsoft.business.sm.SMTerminalSoftQryCount) aop
                .bind(com.dcdzsoft.business.sm.SMTerminalSoftQryCount.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradeWaterQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradeWaterQry bean = (com.dcdzsoft.business.sm.SMUpgradeWaterQry) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradeWaterQry.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMUpgradeWaterQryCount p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMUpgradeWaterQryCount bean = (com.dcdzsoft.business.sm.SMUpgradeWaterQryCount) aop
                .bind(com.dcdzsoft.business.sm.SMUpgradeWaterQryCount.class);
        return bean.doBusiness(p1);
    }

}
