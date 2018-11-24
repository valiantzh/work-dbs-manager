package com.dcdzsoft.client.locker.businessproxy;

import com.dcdzsoft.EduException;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.aop.BusiBeanAOPBaseClass;
import com.dcdzsoft.businessproxy.BusinessProxy;
import com.dcdzsoft.client.pda.PDAManager;
import com.dcdzsoft.constant.SysDict;

/**
 * 
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * 
 * <p>
 * Description: 暴露给设备端所有业务
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * 
 * <p>
 * Company: 杭州东城电子有限公司
 * </p>
 * 
 * @author wangzl
 * @version 1.0
 */
public class ProxyDcdzsoft implements BusinessProxy {
    /**
     * 投递员登陆验证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTPostmanLogin doBusiness(InParamPTPostmanLogin p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
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
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public int doBusiness(InParamPMPostmanModPwd p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pm.PMPostmanModPwd bean = (com.dcdzsoft.business.pm.PMPostmanModPwd) aop
                .bind(com.dcdzsoft.business.pm.PMPostmanModPwd.class);
        return bean.doBusiness(p1);
    }

    /**
     * 投递员绑定卡
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public int doBusiness(InParamPMPostmanBindCard p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pm.PMPostmanBindCard bean = (com.dcdzsoft.business.pm.PMPostmanBindCard) aop
                .bind(com.dcdzsoft.business.pm.PMPostmanBindCard.class);
        return bean.doBusiness(p1);
    }

    /**
     * 投递员解绑定卡
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public int doBusiness(InParamPMPostmanUnBindCard p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pm.PMPostmanUnBindCard bean = (com.dcdzsoft.business.pm.PMPostmanUnBindCard) aop
                .bind(com.dcdzsoft.business.pm.PMPostmanUnBindCard.class);
        return bean.doBusiness(p1);
    }

    /**
     * 投递员注册
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
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
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
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
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTDeliveryPackage doBusiness(InParamPTDeliveryPackage p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliveryPackage bean = (com.dcdzsoft.business.pt.PTDeliveryPackage) aop
                .bind(com.dcdzsoft.business.pt.PTDeliveryPackage.class);
        return bean.doBusiness(p1);
    }

    /**
     * 本次投递汇总
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTDeliverySum doBusiness(InParamPTDeliverySum p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliverySum bean = (com.dcdzsoft.business.pt.PTDeliverySum) aop
                .bind(com.dcdzsoft.business.pt.PTDeliverySum.class);
        return bean.doBusiness(p1);
    }

    /**
     * 取回逾期包裹
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTWithdrawExpiredPack doBusiness(InParamPTWithdrawExpiredPack p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTWithdrawExpiredPack bean = (com.dcdzsoft.business.pt.PTWithdrawExpiredPack) aop
                .bind(com.dcdzsoft.business.pt.PTWithdrawExpiredPack.class);
        return bean.doBusiness(p1);
    }

    /**
     * 下载待投递订单列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public java.util.List<OutParamPTReadPackageQry> doBusiness(InParamPTReadPackageQry p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTReadPackageQry bean = (com.dcdzsoft.business.pt.PTReadPackageQry) aop
                .bind(com.dcdzsoft.business.pt.PTReadPackageQry.class);
        return bean.doBusiness(p1);
    }

    /**
     * 下载逾期包裹单列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public java.util.List<OutParamPTExpiredPackQry> doBusiness(InParamPTExpiredPackQry p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTExpiredPackQry bean = (com.dcdzsoft.business.pt.PTExpiredPackQry) aop
                .bind(com.dcdzsoft.business.pt.PTExpiredPackQry.class);
        return bean.doBusiness(p1);
    }

    /**
     * 单个包裹查询验证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTPackageDetail doBusiness(InParamPTPackageDetail p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPackageDetail bean = (com.dcdzsoft.business.pt.PTPackageDetail) aop
                .bind(com.dcdzsoft.business.pt.PTPackageDetail.class);
        return bean.doBusiness(p1);
    }

    /**
     * 用户取件身份认证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTVerfiyUser doBusiness(InParamPTVerfiyUser p1, String TerminalNo) throws com.dcdzsoft.EduException {

        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTVerfiyUser bean = (com.dcdzsoft.business.pt.PTVerfiyUser) aop
                .bind(com.dcdzsoft.business.pt.PTVerfiyUser.class);
        return bean.doBusiness(p1);
    }

    /**
     * 用户取件
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTPickupPackage doBusiness(InParamPTPickupPackage p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPickupPackage bean = (com.dcdzsoft.business.pt.PTPickupPackage) aop
                .bind(com.dcdzsoft.business.pt.PTPickupPackage.class);
        return bean.doBusiness(p1);
    }

    /**
     * 管理员取件
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTManagerPickupPack doBusiness(InParamPTManagerPickupPack p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTManagerPickupPack bean = (com.dcdzsoft.business.pt.PTManagerPickupPack) aop
                .bind(com.dcdzsoft.business.pt.PTManagerPickupPack.class);
        return bean.doBusiness(p1);
    }

    /**
     * 验证手机是否在黑名单列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTMobileBlackList doBusiness(InParamPTMobileBlackList p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTMobileBlackList bean = (com.dcdzsoft.business.pt.PTMobileBlackList) aop
                .bind(com.dcdzsoft.business.pt.PTMobileBlackList.class);
        return bean.doBusiness(p1);
    }

    /**
     * 下载在箱包裹信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public java.util.List<OutParamPTDownloadInboxInfo> doBusiness(InParamPTDownloadInboxInfo p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDownloadInboxInfo bean = (com.dcdzsoft.business.pt.PTDownloadInboxInfo) aop
                .bind(com.dcdzsoft.business.pt.PTDownloadInboxInfo.class);
        return bean.doBusiness(p1);

    }

    /**
     * 用户取件关门状态上传
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public String doBusiness(InParamPTUploadDoorStatus p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTUploadDoorStatus bean = (com.dcdzsoft.business.pt.PTUploadDoorStatus) aop
                .bind(com.dcdzsoft.business.pt.PTUploadDoorStatus.class);
        return bean.doBusiness(p1);
    }

    /**
     * 重新发送用户取件密码
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public String doBusiness(InParamPTReSentOpenBoxKey p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTReSentOpenBoxKey bean = (com.dcdzsoft.business.pt.PTReSentOpenBoxKey) aop
                .bind(com.dcdzsoft.business.pt.PTReSentOpenBoxKey.class);
        return bean.doBusiness(p1);
    }

    /**
     * 上传取消投递记录
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTDeliveryCancel doBusiness(InParamPTDeliveryCancel p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliveryCancel bean = (com.dcdzsoft.business.pt.PTDeliveryCancel) aop
                .bind(com.dcdzsoft.business.pt.PTDeliveryCancel.class);
        return bean.doBusiness(p1);
    }

    /**
     * 请求服务器分配可投递箱门
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public OutParamPTAllocateeBox doBusiness(InParamPTAllocateeBox p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTAllocateeBox bean = (com.dcdzsoft.business.pt.PTAllocateeBox) aop
                .bind(com.dcdzsoft.business.pt.PTAllocateeBox.class);
        return bean.doBusiness(p1);
    }

    /**
     * 下载投递员列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public java.util.List<OutParamPTDownloadPostmanList> doBusiness(InParamPTDownloadPostmanList p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDownloadPostmanList bean = (com.dcdzsoft.business.pt.PTDownloadPostmanList) aop
                .bind(com.dcdzsoft.business.pt.PTDownloadPostmanList.class);
        return bean.doBusiness(p1);
    }
    
    /***************************** 箱体相关 ********************************************************/
    /***
     * 修改柜体状态
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    @Override
    public String doBusiness(InParamTBTerminalModStatus p, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.tb.TBTerminalModStatus bean = (com.dcdzsoft.business.tb.TBTerminalModStatus) aop
                .bind(com.dcdzsoft.business.tb.TBTerminalModStatus.class);
        return bean.doBusiness(p);
    }

    /***
     * 修改箱体状态
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    @Override
    public String doBusiness(InParamTBBoxStatusMod p, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.tb.TBBoxStatusMod bean = (com.dcdzsoft.business.tb.TBBoxStatusMod) aop
                .bind(com.dcdzsoft.business.tb.TBBoxStatusMod.class);
        return bean.doBusiness(p);
    }

    /***
     * 箱体锁定状态维护
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    @Override
    public String doBusiness(InParamTBLockStatusMod p, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.tb.TBLockStatusMod bean = (com.dcdzsoft.business.tb.TBLockStatusMod) aop
                .bind(com.dcdzsoft.business.tb.TBLockStatusMod.class);
        return bean.doBusiness(p);
    }

    /***
     * 箱体故障状态维护
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    @Override
    public String doBusiness(InParamTBFaultStatusMod p, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.tb.TBFaultStatusMod bean = (com.dcdzsoft.business.tb.TBFaultStatusMod) aop
                .bind(com.dcdzsoft.business.tb.TBFaultStatusMod.class);
        return bean.doBusiness(p);
    }

    /***
     * 修改箱体类型
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    @Override
    public String doBusiness(InParamTBBoxTypeMod p, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.tb.TBBoxTypeMod bean = (com.dcdzsoft.business.tb.TBBoxTypeMod) aop
                .bind(com.dcdzsoft.business.tb.TBBoxTypeMod.class);
        return bean.doBusiness(p);
    }

    /***
     * 同步柜体信息
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    @Override
    public String doBusiness(InParamSCSyncTerminalInfo p, String TerminalNo) throws com.dcdzsoft.EduException {
        boolean isDone = false;
        if (isDone)
            throw new EduException("dummy error.");

        return "";
    }
    
    /**
     * 设备注册
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBDeviceRegister doBusiness(InParamMBDeviceRegister p1,String TerminalNo) throws com.dcdzsoft.EduException{
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceRegister bean = (com.dcdzsoft.business.mb.MBDeviceRegister) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceRegister.class);
        return bean.doBusiness(p1);
    }

    /**
     * 设备签到
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBDeviceSign doBusiness(InParamMBDeviceSign p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceSign bean = (com.dcdzsoft.business.mb.MBDeviceSign) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceSign.class);
        return bean.doBusiness(p1);
    }

    /**
     * 设备离线
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public int doBusiness(InParamMBDeviceOffline p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBDeviceOffline bean = (com.dcdzsoft.business.mb.MBDeviceOffline) aop
                .bind(com.dcdzsoft.business.mb.MBDeviceOffline.class);
        return bean.doBusiness(p1);
    }

    /**
     * 上传设备警报信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public String doBusiness(InParamMBUploadDeviceAlert p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBUploadDeviceAlert bean = (com.dcdzsoft.business.mb.MBUploadDeviceAlert) aop
                .bind(com.dcdzsoft.business.mb.MBUploadDeviceAlert.class);
        return bean.doBusiness(p1);
    }

    /**
     * 同步服务器时间
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamSCSyncServerTime doBusiness(InParamSCSyncServerTime p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        // BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        // com.dcdzsoft.business.sc.SCSyncServerTime bean =
        // (com.dcdzsoft.business.sc.SCSyncServerTime) aop
        // .bind(com.dcdzsoft.business.sc.SCSyncServerTime.class);
        // return bean.doBusiness(p1);

        OutParamSCSyncServerTime outParam = new OutParamSCSyncServerTime();

        java.util.Date nowDate = new java.util.Date();
        outParam.ServerTime = new java.sql.Timestamp(nowDate.getTime());

        return outParam;
    }

    /**
     * 设备心跳包检测
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBHeartBeat doBusiness(InParamMBHeartBeat p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBHeartBeat bean = (com.dcdzsoft.business.mb.MBHeartBeat) aop
                .bind(com.dcdzsoft.business.mb.MBHeartBeat.class);
        return bean.doBusiness(p1);
    }

    /**
     * 设备安装初始化
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBInitialization doBusiness(InParamMBInitialization p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBInitialization bean = (com.dcdzsoft.business.mb.MBInitialization) aop
                .bind(com.dcdzsoft.business.mb.MBInitialization.class);
        return bean.doBusiness(p1);
    }

    /**
     * 设备格口状态报告
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBReportBoxStatus doBusiness(InParamMBReportBoxStatus p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBReportBoxStatus bean = (com.dcdzsoft.business.mb.MBReportBoxStatus) aop
                .bind(com.dcdzsoft.business.mb.MBReportBoxStatus.class);
        return bean.doBusiness(p1);
    }

    /**
     * 设备外设状态报告
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBReportPeripheralStatus doBusiness(InParamMBReportPeripheralStatus p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBReportPeripheralStatus bean = (com.dcdzsoft.business.mb.MBReportPeripheralStatus) aop
                .bind(com.dcdzsoft.business.mb.MBReportPeripheralStatus.class);
        return bean.doBusiness(p1);
    }

    /**
     * 现场管理员登录
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBSpotAdminLogin doBusiness(InParamMBSpotAdminLogin p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBSpotAdminLogin bean = (com.dcdzsoft.business.mb.MBSpotAdminLogin) aop
                .bind(com.dcdzsoft.business.mb.MBSpotAdminLogin.class);
        return bean.doBusiness(p1);
    }

    /**
     * 获取广告列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBGetAdvertisePic doBusiness(InParamMBGetAdvertisePic p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetAdvertisePic bean = (com.dcdzsoft.business.mb.MBGetAdvertisePic) aop
                .bind(com.dcdzsoft.business.mb.MBGetAdvertisePic.class);
        return bean.doBusiness(p1);
    }

    /**
     * 同步管理员日志
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public String doBusiness(InParamSCSyncManagerLog p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sc.SCSyncManagerLog bean = (com.dcdzsoft.business.sc.SCSyncManagerLog) aop
                .bind(com.dcdzsoft.business.sc.SCSyncManagerLog.class);
        return bean.doBusiness(p1);
    }
    /**
     * 上传开箱消息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBUploadOpenBox doBusiness(InParamMBUploadOpenBox p1, String TerminalNo)throws com.dcdzsoft.EduException{
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBUploadOpenBox bean = (com.dcdzsoft.business.mb.MBUploadOpenBox) aop
                .bind(com.dcdzsoft.business.mb.MBUploadOpenBox.class);
        return bean.doBusiness(p1);
    }
    
    /**
     * 上传关箱消息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBUploadCloseBox doBusiness(InParamMBUploadCloseBox p1, String TerminalNo)throws com.dcdzsoft.EduException{
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBUploadCloseBox bean = (com.dcdzsoft.business.mb.MBUploadCloseBox) aop
                .bind(com.dcdzsoft.business.mb.MBUploadCloseBox.class);
        return bean.doBusiness(p1);
    }
    /**
     * 获取最新软件版本信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamMBGetNewVersion doBusiness(InParamMBGetNewVersion p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.mb.MBGetNewVersion bean = (com.dcdzsoft.business.mb.MBGetNewVersion) aop
                .bind(com.dcdzsoft.business.mb.MBGetNewVersion.class);
        return bean.doBusiness(p1);
    }

    /***************************** 远程求助 ********************************************************/
    /**
     * 申请开箱密码
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamRMRequestOpenBoxKey doBusiness(InParamRMRequestOpenBoxKey p1, String TerminalNo)
            throws EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.rm.RMRequestOpenBoxKey bean = (com.dcdzsoft.business.rm.RMRequestOpenBoxKey) aop
                .bind(com.dcdzsoft.business.rm.RMRequestOpenBoxKey.class);
        return bean.doBusiness(p1);
    }

    /**
     * 远程求助开箱
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public int doBusiness(InParamRMOpenBox p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.rm.RMOpenBox bean = (com.dcdzsoft.business.rm.RMOpenBox) aop
                .bind(com.dcdzsoft.business.rm.RMOpenBox.class);
        return bean.doBusiness(p1);
    }

    /**
     * 发送紧急取件短信
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public String doBusiness(InParamPTSendUrgentSMS p1, String TerminalNo) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTSendUrgentSMS bean = (com.dcdzsoft.business.pt.PTSendUrgentSMS) aop
                .bind(com.dcdzsoft.business.pt.PTSendUrgentSMS.class);
        return bean.doBusiness(p1);

    }
    
    /**
     * 获取软件升级信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamSMGetUpgradeInfo doBusiness(InParamSMGetUpgradeInfo p1, String TerminalNo) throws EduException {
        // TODO Auto-generated method stub
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.sm.SMGetUpgradeInfo bean = (com.dcdzsoft.business.sm.SMGetUpgradeInfo) aop
                .bind(com.dcdzsoft.business.sm.SMGetUpgradeInfo.class);
        return bean.doBusiness(p1);
    }

    /************************************** PDA 业务  ********************************************/
    /**
     * 柜端给PDA投递授权，并上传当前可用箱列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    @Override
    public OutParamPTDeliveryAuth4PDA doBusiness(InParamPTDeliveryAuth4PDA p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTDeliveryAuth4PDA bean = (com.dcdzsoft.business.pt.PTDeliveryAuth4PDA) aop
                .bind(com.dcdzsoft.business.pt.PTDeliveryAuth4PDA.class);

        OutParamPTDeliveryAuth4PDA out = bean.doBusiness(p1);
        if (SysDict.TERMINAL_REGISTERFLAG_YES.equals(out.RegisterFlag)) {
            // 可用箱列表保存到内存中
            String authToken = p1.TradeWaterNo;
            PDAManager.getInstance().bindingPDA(p1.TerminalNo, p1.PDANo, p1.FreeBoxList, authToken, false);
        }
        return out;
    }
    
    /************************************** 新业务  ********************************************/
    /**
     * 用户验证-投递账号验证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamPTVerifyDeliver doBusiness(InParamPTVerifyDeliver p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTVerifyDeliver bean = (com.dcdzsoft.business.pt.PTVerifyDeliver) aop
                .bind(com.dcdzsoft.business.pt.PTVerifyDeliver.class);
        return bean.doBusiness(p1);
    }
    /**
     * 用户验证-住户账号验证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamPTVerifyTenant doBusiness(InParamPTVerifyTenant p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTVerifyTenant bean = (com.dcdzsoft.business.pt.PTVerifyTenant) aop
                .bind(com.dcdzsoft.business.pt.PTVerifyTenant.class);
        return bean.doBusiness(p1);
    }
    /**
     * 下载用户信息详情
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public java.util.List<OutParamPTTenantDetail> doBusiness(InParamPTTenantDetail p1, String TerminalNo)
            throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTTenantDetail bean = (com.dcdzsoft.business.pt.PTTenantDetail) aop
                .bind(com.dcdzsoft.business.pt.PTTenantDetail.class);
        return bean.doBusiness(p1);
    }
    
}
