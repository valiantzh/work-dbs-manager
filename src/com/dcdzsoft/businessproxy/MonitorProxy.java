package com.dcdzsoft.businessproxy;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;

/**
 *
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: 监控业务 </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zhengxy
 * @version 1.0
 */
public class MonitorProxy {
    protected MonitorProxy() {
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamTBTerminalListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.tb.TBTerminalListQry bean = (com.dcdzsoft.business.tb.TBTerminalListQry) aop
                .bind(com.dcdzsoft.business.tb.TBTerminalListQry.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPAControlParamQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pa.PAControlParamQry bean = (com.dcdzsoft.business.pa.PAControlParamQry) aop
                .bind(com.dcdzsoft.business.pa.PAControlParamQry.class);
        return bean.doBusiness(p1);
    }

    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamOPFunctionQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.op.OPFunctionQry bean = (com.dcdzsoft.business.op.OPFunctionQry) aop
                .bind(com.dcdzsoft.business.op.OPFunctionQry.class);
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
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamMBReminderMsgQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBReminderMsgQry bean = (com.dcdzsoft.business.mb.MBReminderMsgQry) aop
                .bind(com.dcdzsoft.business.mb.MBReminderMsgQry.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBSendReminderMsg p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBSendReminderMsg bean = (com.dcdzsoft.business.mb.MBSendReminderMsg) aop
                .bind(com.dcdzsoft.business.mb.MBSendReminderMsg.class);
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
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBModDeliveryStatus p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBModDeliveryStatus bean = (com.dcdzsoft.business.mb.MBModDeliveryStatus) aop
                .bind(com.dcdzsoft.business.mb.MBModDeliveryStatus.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBModSMSSentStatus p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBModSMSSentStatus bean = (com.dcdzsoft.business.mb.MBModSMSSentStatus) aop
                .bind(com.dcdzsoft.business.mb.MBModSMSSentStatus.class);
        return bean.doBusiness(p1);
    }
    
    public static javax.sql.RowSet doBusiness(com.dcdzsoft.dto.business.InParamPYSMSAccountListQry p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSAccountListQry bean = (com.dcdzsoft.business.py.PYSMSAccountListQry) aop
                .bind(com.dcdzsoft.business.py.PYSMSAccountListQry.class);
        return bean.doBusiness(p1);
    }
    
    public static boolean doBusiness(com.dcdzsoft.dto.business.InParamPYSMSNumCheck p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSNumCheck bean = (com.dcdzsoft.business.py.PYSMSNumCheck) aop
                .bind(com.dcdzsoft.business.py.PYSMSNumCheck.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSConsumeBillAdd p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSConsumeBillAdd bean = (com.dcdzsoft.business.py.PYSMSConsumeBillAdd) aop
                .bind(com.dcdzsoft.business.py.PYSMSConsumeBillAdd.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamPYSMSOwingReportSend p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.py.PYSMSOwingReportSend bean = (com.dcdzsoft.business.py.PYSMSOwingReportSend) aop
                .bind(com.dcdzsoft.business.py.PYSMSOwingReportSend.class);
        return bean.doBusiness(p1);
    }
    
    /***/
    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTAutoLockOrder p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTAutoLockOrder bean = (com.dcdzsoft.business.pt.PTAutoLockOrder) aop
                .bind(com.dcdzsoft.business.pt.PTAutoLockOrder.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTAutoExpiredOrder p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTAutoExpiredOrder bean = (com.dcdzsoft.business.pt.PTAutoExpiredOrder) aop
                .bind(com.dcdzsoft.business.pt.PTAutoExpiredOrder.class);
        return bean.doBusiness(p1);
    }
    //逾期之后清除密码
    public static int doBusiness(com.dcdzsoft.dto.business.InParamAutoClearOpenKey p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTAutoClearOpenKey bean = (com.dcdzsoft.business.pt.PTAutoClearOpenKey) aop
                .bind(com.dcdzsoft.business.pt.PTAutoClearOpenKey.class);
        return bean.doBusiness(p1);
    }
    
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTAutoSendPwdSMS p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTAutoSendPwdSMS bean = (com.dcdzsoft.business.pt.PTAutoSendPwdSMS) aop
                .bind(com.dcdzsoft.business.pt.PTAutoSendPwdSMS.class);
        return bean.doBusiness(p1);
    }

    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTModPackageStatus p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTModPackageStatus bean = (com.dcdzsoft.business.pt.PTModPackageStatus) aop
                .bind(com.dcdzsoft.business.pt.PTModPackageStatus.class);
        return bean.doBusiness(p1);
    }
    
    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTModExpiredTime p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTModExpiredTime bean = (com.dcdzsoft.business.pt.PTModExpiredTime) aop
                .bind(com.dcdzsoft.business.pt.PTModExpiredTime.class);
        return bean.doBusiness(p1);
    }
    public static int doBusiness(com.dcdzsoft.dto.business.InParamSMSysCleanAuto p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMSysCleanAuto bean = (com.dcdzsoft.business.sm.SMSysCleanAuto) aop
                .bind(com.dcdzsoft.business.sm.SMSysCleanAuto.class);
        return bean.doBusiness(p1);
    }
    //离线或异常消息发送邮件
    public static int doBusiness(com.dcdzsoft.dto.business.InParamMBSendEmail p1)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBSendAlertEmail bean = (com.dcdzsoft.business.mb.MBSendAlertEmail) aop
                .bind(com.dcdzsoft.business.mb.MBSendAlertEmail.class);
        return bean.doBusiness(p1);
    }
    /**
     * 广播异常消息
     * 
     * @param TerminalNo
     * @param AlertType
     * @param AlertLevel
     * @param BoxNo
     * @param Remark
     */
    public static void broadcastAlert(String TerminalNo, String AlertType, String AlertLevel, String BoxNo,
            String Remark) {

    }
}
