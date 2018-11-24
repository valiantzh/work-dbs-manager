package com.dcdzsoft.client.pda.businessproxy;


import com.dcdzsoft.EduException;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.aop.BusiBeanAOPBaseClass;
import com.dcdzsoft.client.pda.PDAManager;
import com.dcdzsoft.constant.Constant;

/**
 * 
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * 
 * <p>
 * Description: 暴露给PDA所有业务
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * 
 * <p>
 * Company: 杭州东城电子有限公司
 * </p>
 * 
 * @author zhengxy
 * @version 1.0
 */
public class ProxyDcdzPDA {
    /**
     * 投递员登陆验证
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTPostmanLogin doBusiness(InParamPTPostmanLogin p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        PDAManager.getInstance().updatePdaLastSignTime(TerminalNo);
        p1.LoginType = Constant.POSTMAN_LOGIN_TYPE_PDA;
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPostmanLogin bean = (com.dcdzsoft.business.pt.PTPostmanLogin) aop
                .bind(com.dcdzsoft.business.pt.PTPostmanLogin.class);
        return bean.doBusiness(p1);
    }

    /**
     * 投递员修改密码
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public int doBusiness(InParamPMPostmanModPwd p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pm.PMPostmanModPwd bean = (com.dcdzsoft.business.pm.PMPostmanModPwd) aop
                .bind(com.dcdzsoft.business.pm.PMPostmanModPwd.class);
        return bean.doBusiness(p1);
    }

    /**
     * 投递员注册
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPMPostmanRegister doBusiness(InParamPMPostmanRegister p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pm.PMPostmanRegister bean = (com.dcdzsoft.business.pm.PMPostmanRegister) aop
                .bind(com.dcdzsoft.business.pm.PMPostmanRegister.class);
        return bean.doBusiness(p1);
    }

    /**
     * 重新获取验证码
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPMReGetCheckCode doBusiness(InParamPMReGetCheckCode p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pm.PMReGetCheckCode bean = (com.dcdzsoft.business.pm.PMReGetCheckCode) aop
                .bind(com.dcdzsoft.business.pm.PMReGetCheckCode.class);
        return bean.doBusiness(p1);
    }

    /**
     * 投递包裹
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTDeliveryPackage doBusiness(InParamPTDeliveryPackage p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliveryPackage bean = (com.dcdzsoft.business.pt.PTDeliveryPackage) aop
                .bind(com.dcdzsoft.business.pt.PTDeliveryPackage.class);
        return bean.doBusiness(p1);
    }

    /**
     * 单个包裹查询验证
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTPackageDetail doBusiness(InParamPTPackageDetail p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPackageDetail bean = (com.dcdzsoft.business.pt.PTPackageDetail) aop
                .bind(com.dcdzsoft.business.pt.PTPackageDetail.class);
        return bean.doBusiness(p1);
    }

    /**
     * 验证手机是否在黑名单列表
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTMobileBlackList doBusiness(InParamPTMobileBlackList p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        PDAManager.getInstance().updatePdaLastSignTime(TerminalNo);
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTMobileBlackList bean = (com.dcdzsoft.business.pt.PTMobileBlackList) aop
                .bind(com.dcdzsoft.business.pt.PTMobileBlackList.class);
        return bean.doBusiness(p1);
    }

    /**
     * 同步服务器时间
     * 
     * @param p1
     * @param TerminalNo
     *            设备号（PDA编号）
     * @return
     * @throws EduException
     */
    public OutParamSCSyncServerTime doBusiness(InParamSCSyncServerTime p1, String TerminalNo)
            throws com.dcdzsoft.EduException {

        OutParamSCSyncServerTime outParam = new OutParamSCSyncServerTime();

        java.util.Date nowDate = new java.util.Date();
        outParam.ServerTime = new java.sql.Timestamp(nowDate.getTime());

        return outParam;
    }

    /**
     * 获取最新软件版本信息
     * 
     * @param p1
     * @param TerminalNo
     *            设备号（PDA编号）
     * @return
     * @throws EduException
     */
    public OutParamMBGetNewVersion doBusiness(InParamMBGetNewVersion p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetNewVersion bean = (com.dcdzsoft.business.mb.MBGetNewVersion) aop
                .bind(com.dcdzsoft.business.mb.MBGetNewVersion.class);
        return bean.doBusiness(p1);
    }

    /************************************** PDA 业务  ********************************************/
    /**
     * PDA设备签到
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws EduException
     */
    public OutParamMBDeviceSignPDA doBusiness(InParamMBDeviceSignPDA p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceSignPDA bean = (com.dcdzsoft.business.mb.MBDeviceSignPDA) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceSignPDA.class);
        return bean.doBusiness(p1);
    }

    /**
     * PDA获取可用箱，
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamAPPostmanFreeBoxList doBusiness(InParamAPPostmanFreeBoxList p1, String TerminalNo)
            throws com.dcdzsoft.EduException {

        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APPostmanFreeBoxList bean = (com.dcdzsoft.business.ap.APPostmanFreeBoxList) aop
                .bind(com.dcdzsoft.business.ap.APPostmanFreeBoxList.class);
        return bean.doBusiness(p1);

    }

    /**
     * PDA投递开箱
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamAPPostmanAppOpenBox doBusiness(InParamAPPostmanAppOpenBox p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        PDAManager.getInstance().updatePdaLastSignTime(TerminalNo);
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APPostmanAppOpenBox bean = (com.dcdzsoft.business.ap.APPostmanAppOpenBox) aop
                .bind(com.dcdzsoft.business.ap.APPostmanAppOpenBox.class);
        return bean.doBusiness(p1);
    }


    /**
     * PDA取消投递
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamAPPostmanDeliveryCancel doBusiness(InParamAPPostmanDeliveryCancel p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        PDAManager.getInstance().updatePdaLastSignTime(TerminalNo);

        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APPostmanDeliveryCancel bean = (com.dcdzsoft.business.ap.APPostmanDeliveryCancel) aop
                .bind(com.dcdzsoft.business.ap.APPostmanDeliveryCancel.class);
        return bean.doBusiness(p1);
    }

    /**
     * 
     * PDA确认投递
     * 
     * @param p1
     * @param TerminalNo
     *            PDA编号
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamAPPostmanDeliveryPackage doBusiness(InParamAPPostmanDeliveryPackage p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        PDAManager.getInstance().updatePdaLastSignTime(TerminalNo);
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APPostmanDeliveryPackage bean = (com.dcdzsoft.business.ap.APPostmanDeliveryPackage) aop
                .bind(com.dcdzsoft.business.ap.APPostmanDeliveryPackage.class);
        return bean.doBusiness(p1);
    }

}
