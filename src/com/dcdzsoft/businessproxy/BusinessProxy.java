package com.dcdzsoft.businessproxy;

import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.EduException;

/**
 * 
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * 
 * <p>
 * Description: 暴露给设备端所有业务接口
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * <p>
 * Company: 杭州东城电子有限公司
 * </p>
 * 
 * @author wangzl
 * @version 1.0
 */
public interface BusinessProxy {
    /**
     * 投递员登陆验证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTPostmanLogin doBusiness(InParamPTPostmanLogin p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 投递员修改密码
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public int doBusiness(InParamPMPostmanModPwd p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 投递员绑定卡
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public int doBusiness(InParamPMPostmanBindCard p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 投递员解绑定卡
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public int doBusiness(InParamPMPostmanUnBindCard p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 投递员注册
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPMPostmanRegister doBusiness(InParamPMPostmanRegister p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 重新获取验证码
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPMReGetCheckCode doBusiness(InParamPMReGetCheckCode p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 投递包裹
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTDeliveryPackage doBusiness(InParamPTDeliveryPackage p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 本次投递汇总
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTDeliverySum doBusiness(InParamPTDeliverySum p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 取回逾期包裹
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTWithdrawExpiredPack doBusiness(InParamPTWithdrawExpiredPack p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 下载待投递订单列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public java.util.List<OutParamPTReadPackageQry> doBusiness(InParamPTReadPackageQry p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 下载逾期包裹单列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public java.util.List<OutParamPTExpiredPackQry> doBusiness(InParamPTExpiredPackQry p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 单个包裹查询验证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTPackageDetail doBusiness(InParamPTPackageDetail p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 用户取件身份认证
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTVerfiyUser doBusiness(InParamPTVerfiyUser p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 用户取件
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTPickupPackage doBusiness(InParamPTPickupPackage p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 管理员取件
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTManagerPickupPack doBusiness(InParamPTManagerPickupPack p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 验证手机是否在黑名单列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTMobileBlackList doBusiness(InParamPTMobileBlackList p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 下载在箱包裹信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public java.util.List<OutParamPTDownloadInboxInfo> doBusiness(InParamPTDownloadInboxInfo p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 用户取件关门状态上传
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public String doBusiness(InParamPTUploadDoorStatus p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 上传取消投递记录
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTDeliveryCancel doBusiness(InParamPTDeliveryCancel p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 请求服务器分配可投递箱门
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public OutParamPTAllocateeBox doBusiness(InParamPTAllocateeBox p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 重新发送用户取件密码
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public String doBusiness(InParamPTReSentOpenBoxKey p1, String TerminalNo) throws com.dcdzsoft.EduException;
    
    /**
     * 下载投递员列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public java.util.List<OutParamPTDownloadPostmanList> doBusiness(InParamPTDownloadPostmanList p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /***************************** 箱体相关 ********************************************************/
    /***
     * 修改柜体状态
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    public String doBusiness(InParamTBTerminalModStatus p, String TerminalNo) throws com.dcdzsoft.EduException;

    /***
     * 修改箱体状态
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    public String doBusiness(InParamTBBoxStatusMod p, String TerminalNo) throws com.dcdzsoft.EduException;

    /***
     * 箱体锁定状态维护
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    public String doBusiness(InParamTBLockStatusMod p, String TerminalNo) throws com.dcdzsoft.EduException;

    /***
     * 箱体故障状态维护
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    public String doBusiness(InParamTBFaultStatusMod p, String TerminalNo) throws com.dcdzsoft.EduException;

    /***
     * 修改箱体类型
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws Exception
     */
    public String doBusiness(InParamTBBoxTypeMod p, String TerminalNo) throws com.dcdzsoft.EduException;

    /***
     * 同步柜体信息
     * 
     * @param p
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public String doBusiness(InParamSCSyncTerminalInfo p, String TerminalNo) throws com.dcdzsoft.EduException;

    /***************************** 运营相关 ********************************************************/
    /**
     * 设备注册
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBDeviceRegister doBusiness(InParamMBDeviceRegister p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 设备签到
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBDeviceSign doBusiness(InParamMBDeviceSign p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 设备离线
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public int doBusiness(InParamMBDeviceOffline p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 上传设备警报信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public String doBusiness(InParamMBUploadDeviceAlert p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 同步服务器时间
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamSCSyncServerTime doBusiness(InParamSCSyncServerTime p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 设备心跳包检测
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBHeartBeat doBusiness(InParamMBHeartBeat p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 设备安装初始化
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBInitialization doBusiness(InParamMBInitialization p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 设备格口状态报告(对账)
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBReportBoxStatus doBusiness(InParamMBReportBoxStatus p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 设备外设状态报告
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBReportPeripheralStatus doBusiness(InParamMBReportPeripheralStatus p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 现场管理员登录
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBSpotAdminLogin doBusiness(InParamMBSpotAdminLogin p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 获取广告列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBGetAdvertisePic doBusiness(InParamMBGetAdvertisePic p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 获取最新软件版本信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBGetNewVersion doBusiness(InParamMBGetNewVersion p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 同步管理员日志
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public String doBusiness(InParamSCSyncManagerLog p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 上传开箱消息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBUploadOpenBox doBusiness(InParamMBUploadOpenBox p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /**
     * 上传关箱消息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamMBUploadCloseBox doBusiness(InParamMBUploadCloseBox p1, String TerminalNo)
            throws com.dcdzsoft.EduException;

    /***************************** 远程求助 ********************************************************/
    /**
     * 申请开箱密码
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamRMRequestOpenBoxKey doBusiness(InParamRMRequestOpenBoxKey p1, String TerminalNo) throws EduException;

    /**
     * 远程求助开箱
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public int doBusiness(InParamRMOpenBox p1, String TerminalNo) throws com.dcdzsoft.EduException;

    /**
     * 发送紧急取件短信
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public String doBusiness(InParamPTSendUrgentSMS p1, String TerminalNo) throws com.dcdzsoft.EduException;
    
    

    /**
     * 获取软件升级信息
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamSMGetUpgradeInfo doBusiness(InParamSMGetUpgradeInfo p1, String TerminalNo)
            throws com.dcdzsoft.EduException;


    /***************************** PDA 业务 ********************************************************/
    /**
     * 柜端给PDA投递授权，并上传当前可用箱列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws EduException
     */
    public OutParamPTDeliveryAuth4PDA doBusiness(InParamPTDeliveryAuth4PDA p1, String TerminalNo)
            throws com.dcdzsoft.EduException;
}
