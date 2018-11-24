package com.dcdzsoft.constant;

import java.io.*;

/**
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 *
 * <p>
 * Description:
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
public final class ControlParam implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 私有默认构造函数
     */
    private ControlParam() {

    }

    private static class SingletonHolder {
        private static final ControlParam instance = new ControlParam();
    }

    /**
     * 静态工厂方法，返还此类的惟一实例
     * 
     * @return a ControlParam instance
     */
    public static ControlParam getInstance() {
        return SingletonHolder.instance;
    }

    public String appclientId = ""; // 客户端Id
    public String appKey = ""; // 应用的app_key
    private String appToken = ""; // 应用的appToken

    public String remoteRestartPwd = ""; // 远程重启密码

    public String pushserviceCtrl = ""; // 推送业务控制

    public String msgTel = ""; // 短信咨询电话
    public String headerDepartmentID = ""; // 运营网点根节点
    private String serviceTel = "";// 客服电话

    public String currentVersion = ""; // 当前系统版本号
    public String buinessModel = ""; // 业务处理模式(STD 标准版 XIANGYOU 湘邮 JINGDONG 京东
    public String serverIP = ""; // 服务器IP地址
    public String serverPort = ""; // 服务器端口
    public String serverSSL = ""; // 通讯是否采用SSL加密 false:不采用 true:采用
    public String serverConnectTimeout = ""; // 超时时间(秒)

    public String screenProtectTime = ""; // 屏保启用时间（单位：分钟）
    public String screensoundFlag = ""; // 语音提示（0关闭；1开启）

    public String terminalSoftUpgrade = "";// 终端软件升级管理：v1.2.3后版本支持20180717

    public String deviceBoardPort = ""; // 驱动板端口
    public String deviceBoardModel = ""; // 控制模式(1:事件通知 2:轮询)'

    public String cardReaderPort = ""; // 读卡器端口
    public String cardReaderModel = ""; // 控制模式(1:事件通知 2:轮询)
    public String cardReaderNeedFlag = ""; // 是否需要读卡器(0:不需要 1:需要)

    public String printPort = ""; // 打印端口
    public String printModel = ""; // 控制模式(1:事件通知 2:轮询 3:按需同步)
    public String printNeedFlag = ""; // 是否需要打印机(0:不需要 1:需要)

    public String barCodePort = ""; // 条码端口
    public String barCodeModel = ""; // 控制模式(1:事件通知 2:轮询)
    public String barNeedFlag = ""; // 是否需要条码扫描(0:不需要 1:需要)

    public String shortMsgPort = ""; // 短信端口
    public String shortMsgModel = ""; // 控制模式(1:事件通知 2:轮询 3:按需同步)
    public String shortMsgNeedFlag = ""; // 是否需要短信猫(0:不需要 1:需要)

    public String ledScreenPort = ""; // LED条屏端口
    public String ledSreenModel = ""; // 控制模式(1:事件通知 2:轮询 3:按需同步)
    public String ledSreenNeedFlag = ""; // 是否需要LED条屏(0:不需要 1:需要)

    public String posPort = ""; // POS机端口
    public String posModel = ""; // 控制模式(1:事件通知 2:轮询 4:按需异步)
    public String posNeedFlag = ""; // 是否需要POS机(0:不需要 1:需要)

    public String coinPort = ""; // 投币机端口
    public String coinModel = ""; // 控制模式(1:事件通知 2:轮询)
    public String coinNeedFlag = ""; // 是否需要投币机(0:不需要 1:需要)

    public String bankCardPort = ""; // 银行卡端口
    public String bankCardModel = ""; // 控制模式(1:事件通知 2:轮询)
    public String bankCardNeedFlag = ""; // 是否需要银行卡(0:不需要 1:需要)

    public String cameraPort = ""; // 摄像端口
    public String cameraModel = ""; // 控制模式(1:事件通知 2:轮询 4:按需异步)
    public String cameraNeedFlag = ""; // 是否需要摄像头(0:不需要 1:需要)

    public String systemHTPWD = ""; // 前台转后台密码
    public String spotAdminID = ""; // 现场管理员编号
    public String spotAdminPWD = ""; // 现场管理员密码

    public String stdSendMessage = ""; // 标准的派件信息内容
    public String stdRetrieveMessage = ""; // 标准的回收信息内容

    public String articleInspectFlag = ""; // 物品检测标志(0:关闭 1:打开)
    public String doorInspectFlag = ""; // 箱门检测标志(0:关闭 1:打开)
    public String powerInspectFlag = ""; // 电源检测标志(0:关闭 1:打开)
    public String shockInspectFlag = ""; // 震动防撬检测标志(0:关闭 1:打开)
    public String boxWarnTime = ""; // 箱门异常报警时间参数(0:关闭 30秒)

    public String deskDefaultHeight = ""; // 副柜默认高度

    public String postmanCheckSource = ""; // 投递员身份验证地点 0:本地 1:网络
    public String postmanCheckModel = ""; // 投递员身份认证方式 0:获取动态码 1:身份认证
    public String manTerminalRightCheck = ""; // 投递员柜体权限验证模式 0:不需要 1:验证公司 2:验证本人
    public String manBoxRightCheck = ""; // 投递员格口权限验证模式 0:不需要 1:验证公司 2:验证本人
    public String companyQryModel = ""; // 投递公司查询模式(1:同级别或以下 2:同级别或以下或上级)
    public String regSentPwdSms = ""; // 注册发送短信密码(0:不发送 1:发送)

    public String postmanMD5Flag = ""; // 密码存储使用MD5加密 投递员(0:不使用 1:使用)
    public String managerMD5Flag = ""; // 密码存储使用MD5加密 管理员(0:不使用 1:使用)
    public String passwdCheckModel = ""; // 密码验证方式(0:普通MD5 1:京东MD5 2:邮政)

    public String orderDeliverySource = ""; // 投递订单来源 0:无 1:网络 2:本地
    public String orderCheckFlag = ""; // 订单验证标志 0:不验证 1:验证
    public String ordersNotInList = ""; // 列表外订单处理方式 0:不允许投递 1:允许投递
    public String orderNeedNetCheck = ""; // 订单需要网络验证 0:不需要 1:需要
    public String orderFormatHandleWay = ""; // 订单号格式处理方式 0:无 1:京东

    public String orderDeliveryMode = "";// 订单投递模式：0：标准 1：一单多箱
    private String cancelDeliveryOffline = "";//后台离线清箱 0:不允许 1:允许

    public String recoverySource = ""; // 回收来源 1从服务器下载；2从本地库验证
    public String recoveryTimeout = ""; // 回收超时时间（单位：天数）
    public String recoveryTimeSet = "";// 回收时间设置 0：按柜设置 1：按投递公司设置

    public String recoveryScope = ""; // 回收范围 1投递员；2投递公司
    public String recoveryPower = ""; // 回收力度 1强制回收，2非强制回收

    public String reminderDays = ""; // 催领天数
    public String reminderDateTime = "";// 催领时间：比如16点

    public String takeOutPWDSource = ""; // 取件密码来源 0:本地不生成 1:本地生成
    public String takeOutPwdLen = ""; // 取件密码长度
    public String takeOutPwdFormat = ""; // 取件密码组成格式 0:数字 1:字母 2:字母数字组合

    public String takeOutCheckModel = ""; // 取件验证模式 1本地验证，2网络验证
    public String takeOutCheckType = ""; // 取件验证方式
                                         // 1单号+密码，2手机号+密码，3单号或手机号+密码，4提货码+会员卡
    public String takeOutPwdMD5Type = ""; // 取件密码MD5加密类型 0:标准 1:京东
    public String refuseCloseDoor = ""; // 拒关箱门（单位：次数）
    public String autoLockOrder = "";
    public String autoExpiredOrder = "";// 自动过期订单

    public String firstWrongPwdCount = ""; // 初次取件密码出错次数上限'
    public String secondWrongPwdCount = ""; // 再次取件密码出错次数上限
    public String temporaryLockTime = ""; // 暂时锁定时间限制(分钟)'

    public String inBoxInfoCompare = ""; // 异常在箱信息比对，1需要比对，0不需要
    public String boxInfoCompare = ""; // 异常箱体信息比对，1需要比对，0不需要

    public String contactTel = ""; // 求助联系人电话
    public String sendToPartner = ""; //

    // 短信通知控制
    public String sendDeliverSMS = "";// 发送投递通知 0:不发送,1:发送给收件人
    public String sendPickupSMS = "";// 发送已取件通知 0:不发送,1:发送给收件人
    public String sendReminderSMS = "";// 发送催领通知 0:不发送,1:发送给收件人
    public String sendExpiredSMS = "";// 发送逾期通知 0:不发送,1:发送给收件人

    public String sendDeliverSMS2Post = "";// 发送投递通知
                                           // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司
    public String sendPickupSMS2Post = "";// 发送已取件通知
                                          // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司
    public String sendReminderSMS2Post = "";// 发送催领通知
                                            // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司
    public String sendExpiredSMS2Post = "";// 发送逾期通知
                                           // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司

    // 邮件通知控制
    public String sendDeliverEmail = "";// 发送投递邮件 0:不发送,1:发送给收件人
    public String sendPickupEmail = "";// 发送取件邮件 0:不发送,1:发送给收件人
    public String sendReminderEmail = "";// 发送催领通知 0:不发送,1:发送给收件人
    public String sendExpiredEmail = "";// 发送逾期通知 0:不发送,1:发送给收件人

    public String sendDeliverEmail2Post = "";// 发送投递通知
                                             // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司
    public String sendPickupEmail2Post = "";// 发送已取件通知
                                            // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司
    public String sendReminderEmail2Post = "";// 发送催领通知
                                              // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司
    public String sendExpiredEmail2Post = "";// 发送逾期通知
                                             // 0-不发送，1-发送投递员,2-发送给投递公司,3-发送给投递员&投递公司

    public String appScanLogin = "";
    public String checkSMSAccount = "";// 检查短信充值账户 0:不开通 1:开通
    public String bytePerSMS = "";// 每条短信长度
    public String sendWarningPerDays = "";// 间隔发送账户余额不足的天数，即每3天发送一次通知
    public String partnerSendSMS = "";// 由合作方发送短信： 0:不开通 1:开通
    public String sendPwdSMSFirstTime = "";// 短信初始发送时间：7点之前不发送，7点以后即时发送
    public String reSendDeliveryInfo = ""; // 重发投递上传失败信息 0:不发送 1:发送
    public String scanTimeout = "";// 用户扫码超时时间：分钟，超时重新扫码

    private String cardNoTopupMod = "";// 卡号不存在是否允许充值 0:不允许 1：允许(自动创建会员卡信息)
    private String uploadTopupRecord = "";// 上传充值记录 0：不上传 1：上传

    private String lockerChargeUser = "";// 收费对象设置：0 不收费 1：投递员 2：收件人

    public String autoClearOpenKey = "";// 逾期件自动清除密码0:不使用,1:使用

    public String sendNoCloseDoor = "";// 是否上传箱门未关状态；0:不上传，1:上传 //菜鸟，是否将箱门未关状态上传
    // 发送邮件
    public String sendRegisterEmailToPM = "";// 发送注册密码邮件给投投递员，0:不使用,1:使用
    public String sendRegisterEmailToCM = "";// 发送注册密码邮件给投客户，0:不使用,1:使用

    public String sendBoxUsedEmailToOP = "";// 发送柜体使用状况给管理员，0:不使用,1:使用
    public String sendAlertEmail = "";// 发送柜体异常邮件控制，0:不使用,1:使用
    public String emailServerHost = "";// 邮箱发件服务器地址
    public String emailServerPort = "";// 邮箱服务器端口
    public String emailUser = "";// 邮箱用户
    public String emailPwd = "";// 邮箱密码
    public String emailFromAddress = "";// 发件人地址
    public String emailToAddress = "";// 收件人地址
    public String interTimeDifferent = "";// 国际时差；

    //
    public String shortMsgFromPhone = "";// 美国短信发送平台需要添加发件人；

    public String getShortMsgFromPhone() {
        return shortMsgFromPhone;
    }

    public void setShortMsgFromPhone(String shortMsgFromPhone) {
        this.shortMsgFromPhone = shortMsgFromPhone;
    }

    public String getInterTimeDifferent() {
        return interTimeDifferent;
    }

    public void setInterTimeDifferent(String interTimeDifferent) {
        interTimeDifferent = interTimeDifferent;
    }

    public String getSendDeliverSMS() {
        return sendDeliverSMS;
    }

    public void setSendDeliverSMS(String sendDeliverSMS) {
        this.sendDeliverSMS = sendDeliverSMS;
    }

    public String getSendRegisterEmailToPM() {
        return sendRegisterEmailToPM;
    }

    public void setSendRegisterEmailToPM(String sendRegisterEmailToPM) {
        this.sendRegisterEmailToPM = sendRegisterEmailToPM;
    }

    public String getSendRegisterEmailToCM() {
        return sendRegisterEmailToCM;
    }

    public void setSendRegisterEmailToCM(String sendRegisterEmailToCM) {
        this.sendRegisterEmailToCM = sendRegisterEmailToCM;
    }

    public String getSendBoxUsedEmailToOP() {
        return sendBoxUsedEmailToOP;
    }

    public void setSendBoxUsedEmailToOP(String sendBoxUsedEmailToOP) {
        this.sendBoxUsedEmailToOP = sendBoxUsedEmailToOP;
    }

    public String getSendDeliverEmail() {
        return sendDeliverEmail;
    }

    public void setSendDeliverEmail(String sendDeliverEmail) {
        this.sendDeliverEmail = sendDeliverEmail;
    }

    public String getSendPickupEmail() {
        return sendPickupEmail;
    }

    public void setSendPickupEmail(String sendPickupEmail) {
        this.sendPickupEmail = sendPickupEmail;
    }

    public String getSendAlertEmail() {
        return sendAlertEmail;
    }

    public void setSendAlertEmail(String sendAlertEmail) {
        this.sendAlertEmail = sendAlertEmail;
    }

    public String getEmailFromAddress() {
        return emailFromAddress;
    }

    public void setEmailFromAddress(String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }

    public String getEmailToAddress() {
        return emailToAddress;
    }

    public void setEmailToAddress(String emailToAddress) {
        this.emailToAddress = emailToAddress;
    }

    public String getCompanyQryModel() {
        return companyQryModel;
    }

    public void setCompanyQryModel(String companyQryModel) {
        this.companyQryModel = companyQryModel;
    }

    public String getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(String reminderDays) {
        this.reminderDays = reminderDays;
    }

    public String getEmailServerHost() {
        return emailServerHost;
    }

    public void setEmailServerHost(String emailServerHost) {
        this.emailServerHost = emailServerHost;
    }

    public String getEmailServerPort() {
        return emailServerPort;
    }

    public void setEmailServerPort(String emailServerPort) {
        this.emailServerPort = emailServerPort;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEmailPwd() {
        return emailPwd;
    }

    public void setEmailPwd(String emailPwd) {
        this.emailPwd = emailPwd;
    }

    public String getSendNoCloseDoor() {
        return sendNoCloseDoor;
    }

    public void setSendNoCloseDoor(String sendNoCloseDoor) {
        this.sendNoCloseDoor = sendNoCloseDoor;
    }

    public String getAutoClearOpenKey() {
        return autoClearOpenKey;
    }

    public void setAutoClearOpenKey(String autoClearOpenKey) {
        this.autoClearOpenKey = autoClearOpenKey;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    public String getReSendDeliveryInfo() {
        return reSendDeliveryInfo;
    }

    public void setReSendDeliveryInfo(String reSendDeliveryInfo) {
        this.reSendDeliveryInfo = reSendDeliveryInfo;
    }

    public String getAppScanLogin() {
        return appScanLogin;
    }

    public void setAppScanLogin(String appScanLogin) {
        this.appScanLogin = appScanLogin;
    }

    public String getSendPickupSMS() {
        return sendPickupSMS;
    }

    public void setSendPickupSMS(String sendPickupSMS) {
        this.sendPickupSMS = sendPickupSMS;
    }

    public String getAutoLockOrder() {
        return autoLockOrder;
    }

    public void setAutoLockOrder(String autoLockOrder) {
        this.autoLockOrder = autoLockOrder;
    }

    public String getSendToPartner() {
        return sendToPartner;
    }

    public String getAppclientId() {
        return appclientId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getRemoteRestartPwd() {
        return remoteRestartPwd;
    }

    public String getMsgTel() {
        return msgTel;
    }

    public String getArticleInspectFlag() {
        return articleInspectFlag;
    }

    public String getBankCardModel() {
        return bankCardModel;
    }

    public String getBankCardNeedFlag() {
        return bankCardNeedFlag;
    }

    public String getBankCardPort() {
        return bankCardPort;
    }

    public String getBarCodeModel() {
        return barCodeModel;
    }

    public String getBarCodePort() {
        return barCodePort;
    }

    public String getBarNeedFlag() {
        return barNeedFlag;
    }

    public String getBoxInfoCompare() {
        return boxInfoCompare;
    }

    public String getBoxWarnTime() {
        return boxWarnTime;
    }

    public String getBuinessModel() {
        return buinessModel;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public String getCameraNeedFlag() {
        return cameraNeedFlag;
    }

    public String getCameraPort() {
        return cameraPort;
    }

    public String getCardReaderModel() {
        return cardReaderModel;
    }

    public String getCardReaderNeedFlag() {
        return cardReaderNeedFlag;
    }

    public String getCardReaderPort() {
        return cardReaderPort;
    }

    public String getCoinModel() {
        return coinModel;
    }

    public String getCoinNeedFlag() {
        return coinNeedFlag;
    }

    public String getCoinPort() {
        return coinPort;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public String getDeskDefaultHeight() {
        return deskDefaultHeight;
    }

    public String getDeviceBoardModel() {
        return deviceBoardModel;
    }

    public String getDeviceBoardPort() {
        return deviceBoardPort;
    }

    public String getDoorInspectFlag() {
        return doorInspectFlag;
    }

    public String getFirstWrongPwdCount() {
        return firstWrongPwdCount;
    }

    public String getInBoxInfoCompare() {
        return inBoxInfoCompare;
    }

    public String getLedScreenPort() {
        return ledScreenPort;
    }

    public String getLedSreenModel() {
        return ledSreenModel;
    }

    public String getLedSreenNeedFlag() {
        return ledSreenNeedFlag;
    }

    public String getManagerMD5Flag() {
        return managerMD5Flag;
    }

    public String getManBoxRightCheck() {
        return manBoxRightCheck;
    }

    public String getManTerminalRightCheck() {
        return manTerminalRightCheck;
    }

    public String getOrderCheckFlag() {
        return orderCheckFlag;
    }

    public String getOrderDeliverySource() {
        return orderDeliverySource;
    }

    public String getOrderFormatHandleWay() {
        return orderFormatHandleWay;
    }

    public String getOrderNeedNetCheck() {
        return orderNeedNetCheck;
    }

    public String getOrdersNotInList() {
        return ordersNotInList;
    }

    public String getPasswdCheckModel() {
        return passwdCheckModel;
    }

    public String getPosModel() {
        return posModel;
    }

    public String getPosNeedFlag() {
        return posNeedFlag;
    }

    public String getPosPort() {
        return posPort;
    }

    public String getPostmanCheckModel() {
        return postmanCheckModel;
    }

    public String getPostmanCheckSource() {
        return postmanCheckSource;
    }

    public String getPostmanMD5Flag() {
        return postmanMD5Flag;
    }

    public String getPowerInspectFlag() {
        return powerInspectFlag;
    }

    public String getPrintModel() {
        return printModel;
    }

    public String getPrintNeedFlag() {
        return printNeedFlag;
    }

    public String getPrintPort() {
        return printPort;
    }

    public String getRecoveryPower() {
        return recoveryPower;
    }

    public String getRecoveryScope() {
        return recoveryScope;
    }

    public String getRecoverySource() {
        return recoverySource;
    }

    public String getRecoveryTimeout() {
        return recoveryTimeout;
    }

    public String getRefuseCloseDoor() {
        return refuseCloseDoor;
    }

    public String getScreenProtectTime() {
        return screenProtectTime;
    }

    public String getScreensoundFlag() {
        return screensoundFlag;
    }

    public String getSecondWrongPwdCount() {
        return secondWrongPwdCount;
    }

    public String getServerConnectTimeout() {
        return serverConnectTimeout;
    }

    public String getServerIP() {
        return serverIP;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getServerSSL() {
        return serverSSL;
    }

    public String getShockInspectFlag() {
        return shockInspectFlag;
    }

    public String getShortMsgModel() {
        return shortMsgModel;
    }

    public String getShortMsgNeedFlag() {
        return shortMsgNeedFlag;
    }

    public String getShortMsgPort() {
        return shortMsgPort;
    }

    public String getSpotAdminID() {
        return spotAdminID;
    }

    public String getSpotAdminPWD() {
        return spotAdminPWD;
    }

    public String getStdRetrieveMessage() {
        return stdRetrieveMessage;
    }

    public String getStdSendMessage() {
        return stdSendMessage;
    }

    public String getTakeOutCheckModel() {
        return takeOutCheckModel;
    }

    public String getSystemHTPWD() {
        return systemHTPWD;
    }

    public String getTakeOutCheckType() {
        return takeOutCheckType;
    }

    public String getTakeOutPwdFormat() {
        return takeOutPwdFormat;
    }

    public String getTakeOutPwdLen() {
        return takeOutPwdLen;
    }

    public String getTakeOutPwdMD5Type() {
        return takeOutPwdMD5Type;
    }

    public String getTakeOutPWDSource() {
        return takeOutPWDSource;
    }

    public String getTemporaryLockTime() {
        return temporaryLockTime;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setSendToPartner(String sendToPartner) {
        this.sendToPartner = sendToPartner;
    }

    public void setAppclientId(String appclientId) {
        this.appclientId = appclientId;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setArticleInspectFlag(String articleInspectFlag) {
        this.articleInspectFlag = articleInspectFlag;
    }

    public void setBankCardModel(String bankCardModel) {
        this.bankCardModel = bankCardModel;
    }

    public void setBankCardNeedFlag(String bankCardNeedFlag) {
        this.bankCardNeedFlag = bankCardNeedFlag;
    }

    public void setBankCardPort(String bankCardPort) {
        this.bankCardPort = bankCardPort;
    }

    public void setBarCodeModel(String barCodeModel) {
        this.barCodeModel = barCodeModel;
    }

    public void setBarCodePort(String barCodePort) {
        this.barCodePort = barCodePort;
    }

    public void setBarNeedFlag(String barNeedFlag) {
        this.barNeedFlag = barNeedFlag;
    }

    public void setBoxInfoCompare(String boxInfoCompare) {
        this.boxInfoCompare = boxInfoCompare;
    }

    public void setBoxWarnTime(String boxWarnTime) {
        this.boxWarnTime = boxWarnTime;
    }

    public void setBuinessModel(String buinessModel) {
        this.buinessModel = buinessModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public void setCameraNeedFlag(String cameraNeedFlag) {
        this.cameraNeedFlag = cameraNeedFlag;
    }

    public void setCameraPort(String cameraPort) {
        this.cameraPort = cameraPort;
    }

    public void setCardReaderModel(String cardReaderModel) {
        this.cardReaderModel = cardReaderModel;
    }

    public void setCardReaderNeedFlag(String cardReaderNeedFlag) {
        this.cardReaderNeedFlag = cardReaderNeedFlag;
    }

    public void setCardReaderPort(String cardReaderPort) {
        this.cardReaderPort = cardReaderPort;
    }

    public void setCoinModel(String coinModel) {
        this.coinModel = coinModel;
    }

    public void setCoinNeedFlag(String coinNeedFlag) {
        this.coinNeedFlag = coinNeedFlag;
    }

    public void setCoinPort(String coinPort) {
        this.coinPort = coinPort;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public void setDeskDefaultHeight(String deskDefaultHeight) {
        this.deskDefaultHeight = deskDefaultHeight;
    }

    public void setDeviceBoardModel(String deviceBoardModel) {
        this.deviceBoardModel = deviceBoardModel;
    }

    public void setDeviceBoardPort(String deviceBoardPort) {
        this.deviceBoardPort = deviceBoardPort;
    }

    public void setDoorInspectFlag(String doorInspectFlag) {
        this.doorInspectFlag = doorInspectFlag;
    }

    public void setFirstWrongPwdCount(String firstWrongPwdCount) {
        this.firstWrongPwdCount = firstWrongPwdCount;
    }

    public void setInBoxInfoCompare(String inBoxInfoCompare) {
        this.inBoxInfoCompare = inBoxInfoCompare;
    }

    public void setLedScreenPort(String ledScreenPort) {
        this.ledScreenPort = ledScreenPort;
    }

    public void setLedSreenModel(String ledSreenModel) {
        this.ledSreenModel = ledSreenModel;
    }

    public void setLedSreenNeedFlag(String ledSreenNeedFlag) {
        this.ledSreenNeedFlag = ledSreenNeedFlag;
    }

    public void setManagerMD5Flag(String managerMD5Flag) {
        this.managerMD5Flag = managerMD5Flag;
    }

    public void setManBoxRightCheck(String manBoxRightCheck) {
        this.manBoxRightCheck = manBoxRightCheck;
    }

    public void setManTerminalRightCheck(String manTerminalRightCheck) {
        this.manTerminalRightCheck = manTerminalRightCheck;
    }

    public void setOrderCheckFlag(String orderCheckFlag) {
        this.orderCheckFlag = orderCheckFlag;
    }

    public void setOrderDeliverySource(String orderDeliverySource) {
        this.orderDeliverySource = orderDeliverySource;
    }

    public void setOrderFormatHandleWay(String orderFormatHandleWay) {
        this.orderFormatHandleWay = orderFormatHandleWay;
    }

    public void setOrderNeedNetCheck(String orderNeedNetCheck) {
        this.orderNeedNetCheck = orderNeedNetCheck;
    }

    public void setOrdersNotInList(String ordersNotInList) {
        this.ordersNotInList = ordersNotInList;
    }

    public void setPasswdCheckModel(String passwdCheckModel) {
        this.passwdCheckModel = passwdCheckModel;
    }

    public void setPosModel(String posModel) {
        this.posModel = posModel;
    }

    public void setPosNeedFlag(String posNeedFlag) {
        this.posNeedFlag = posNeedFlag;
    }

    public void setPosPort(String posPort) {
        this.posPort = posPort;
    }

    public void setPostmanCheckModel(String postmanCheckModel) {
        this.postmanCheckModel = postmanCheckModel;
    }

    public void setPostmanCheckSource(String postmanCheckSource) {
        this.postmanCheckSource = postmanCheckSource;
    }

    public void setPostmanMD5Flag(String postmanMD5Flag) {
        this.postmanMD5Flag = postmanMD5Flag;
    }

    public void setPowerInspectFlag(String powerInspectFlag) {
        this.powerInspectFlag = powerInspectFlag;
    }

    public void setPrintModel(String printModel) {
        this.printModel = printModel;
    }

    public void setPrintNeedFlag(String printNeedFlag) {
        this.printNeedFlag = printNeedFlag;
    }

    public void setPrintPort(String printPort) {
        this.printPort = printPort;
    }

    public void setRecoveryPower(String recoveryPower) {
        this.recoveryPower = recoveryPower;
    }

    public void setRecoveryScope(String recoveryScope) {
        this.recoveryScope = recoveryScope;
    }

    public void setRecoverySource(String recoverySource) {
        this.recoverySource = recoverySource;
    }

    public void setRecoveryTimeout(String recoveryTimeout) {
        this.recoveryTimeout = recoveryTimeout;
    }

    public void setRefuseCloseDoor(String refuseCloseDoor) {
        this.refuseCloseDoor = refuseCloseDoor;
    }

    public void setScreenProtectTime(String screenProtectTime) {
        this.screenProtectTime = screenProtectTime;
    }

    public void setScreensoundFlag(String screensoundFlag) {
        this.screensoundFlag = screensoundFlag;
    }

    public void setSecondWrongPwdCount(String secondWrongPwdCount) {
        this.secondWrongPwdCount = secondWrongPwdCount;
    }

    public void setServerConnectTimeout(String serverConnectTimeout) {
        this.serverConnectTimeout = serverConnectTimeout;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public void setServerSSL(String serverSSL) {
        this.serverSSL = serverSSL;
    }

    public void setShockInspectFlag(String shockInspectFlag) {
        this.shockInspectFlag = shockInspectFlag;
    }

    public void setShortMsgModel(String shortMsgModel) {
        this.shortMsgModel = shortMsgModel;
    }

    public void setShortMsgNeedFlag(String shortMsgNeedFlag) {
        this.shortMsgNeedFlag = shortMsgNeedFlag;
    }

    public void setShortMsgPort(String shortMsgPort) {
        this.shortMsgPort = shortMsgPort;
    }

    public void setSpotAdminID(String spotAdminID) {
        this.spotAdminID = spotAdminID;
    }

    public void setSpotAdminPWD(String spotAdminPWD) {
        this.spotAdminPWD = spotAdminPWD;
    }

    public void setStdRetrieveMessage(String stdRetrieveMessage) {
        this.stdRetrieveMessage = stdRetrieveMessage;
    }

    public void setStdSendMessage(String stdSendMessage) {
        this.stdSendMessage = stdSendMessage;
    }

    public void setSystemHTPWD(String systemHTPWD) {
        this.systemHTPWD = systemHTPWD;
    }

    public void setTakeOutCheckModel(String takeOutCheckModel) {
        this.takeOutCheckModel = takeOutCheckModel;
    }

    public void setTakeOutCheckType(String takeOutCheckType) {
        this.takeOutCheckType = takeOutCheckType;
    }

    public void setTakeOutPwdFormat(String takeOutPwdFormat) {
        this.takeOutPwdFormat = takeOutPwdFormat;
    }

    public void setTakeOutPwdLen(String takeOutPwdLen) {
        this.takeOutPwdLen = takeOutPwdLen;
    }

    public void setTakeOutPwdMD5Type(String takeOutPwdMD5Type) {
        this.takeOutPwdMD5Type = takeOutPwdMD5Type;
    }

    public void setTakeOutPWDSource(String takeOutPWDSource) {
        this.takeOutPWDSource = takeOutPWDSource;
    }

    public void setTemporaryLockTime(String temporaryLockTime) {
        this.temporaryLockTime = temporaryLockTime;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public void setMsgTel(String msgTel) {
        this.msgTel = msgTel;
    }

    public String getHeaderDepartmentID() {
        return headerDepartmentID;
    }

    public void setHeaderDepartmentID(String headerDepartmentID) {
        this.headerDepartmentID = headerDepartmentID;
    }

    public String getRegSentPwdSms() {
        return regSentPwdSms;
    }

    public void setRegSentPwdSms(String regSentPwdSms) {
        this.regSentPwdSms = regSentPwdSms;
    }

    public String getPushserviceCtrl() {
        return pushserviceCtrl;
    }

    public void setPushserviceCtrl(String pushserviceCtrl) {
        this.pushserviceCtrl = pushserviceCtrl;
    }

    public String getSendReminderSMS() {
        return sendReminderSMS;
    }

    public void setSendReminderSMS(String sendReminderSMS) {
        this.sendReminderSMS = sendReminderSMS;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getCheckSMSAccount() {
        return checkSMSAccount;
    }

    public void setCheckSMSAccount(String checkSMSAccount) {
        this.checkSMSAccount = checkSMSAccount;
    }

    public String getBytePerSMS() {
        return bytePerSMS;
    }

    public void setBytePerSMS(String bytePerSMS) {
        this.bytePerSMS = bytePerSMS;
    }

    public void setRemoteRestartPwd(String remoteRestartPwd) {
        this.remoteRestartPwd = remoteRestartPwd;
    }

    public String getPartnerSendSMS() {
        return partnerSendSMS;
    }

    public void setPartnerSendSMS(String partnerSendSMS) {
        this.partnerSendSMS = partnerSendSMS;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getSendPwdSMSFirstTime() {
        return sendPwdSMSFirstTime;
    }

    public void setSendPwdSMSFirstTime(String sendPwdSMSFirstTime) {
        this.sendPwdSMSFirstTime = sendPwdSMSFirstTime;
    }

    public String getSendWarningPerDays() {
        return sendWarningPerDays;
    }

    public void setSendWarningPerDays(String sendWarningPerDays) {
        this.sendWarningPerDays = sendWarningPerDays;
    }

    public String getAutoExpiredOrder() {
        return autoExpiredOrder;
    }

    public void setAutoExpiredOrder(String autoExpiredOrder) {
        this.autoExpiredOrder = autoExpiredOrder;
    }

    public String getScanTimeout() {
        return scanTimeout;
    }

    public void setScanTimeout(String scanTimeout) {
        this.scanTimeout = scanTimeout;
    }

    public String getOrderDeliveryMode() {
        return orderDeliveryMode;
    }

    public void setOrderDeliveryMode(String orderDeliveryMode) {
        this.orderDeliveryMode = orderDeliveryMode;
    }

    public String getCardNoTopupMod() {
        return cardNoTopupMod;
    }

    public void setCardNoTopupMod(String cardNoTopupMod) {
        this.cardNoTopupMod = cardNoTopupMod;
    }

    public String getUploadTopupRecord() {
        return uploadTopupRecord;
    }

    public void setUploadTopupRecord(String uploadTopupRecord) {
        this.uploadTopupRecord = uploadTopupRecord;
    }

    public String getLockerChargeUser() {
        return lockerChargeUser;
    }

    public void setLockerChargeUser(String lockerChargeUser) {
        this.lockerChargeUser = lockerChargeUser;
    }

    /**
     * @return the reminderDateTime
     */
    public String getReminderDateTime() {
        return reminderDateTime;
    }

    /**
     * @param reminderDateTime
     *            the reminderDateTime to set
     */
    public void setReminderDateTime(String reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public String getSendExpiredSMS() {
        return sendExpiredSMS;
    }

    public void setSendExpiredSMS(String sendExpiredSMS) {
        this.sendExpiredSMS = sendExpiredSMS;
    }

    public String getRecoveryTimeSet() {
        return recoveryTimeSet;
    }

    public void setRecoveryTimeSet(String recoveryTimeSet) {
        this.recoveryTimeSet = recoveryTimeSet;
    }

    public String getSendDeliverSMS2Post() {
        return sendDeliverSMS2Post;
    }

    public void setSendDeliverSMS2Post(String sendDeliverSMS2Post) {
        this.sendDeliverSMS2Post = sendDeliverSMS2Post;
    }

    public String getSendPickupSMS2Post() {
        return sendPickupSMS2Post;
    }

    public void setSendPickupSMS2Post(String sendPickupSMS2Post) {
        this.sendPickupSMS2Post = sendPickupSMS2Post;
    }

    public String getSendReminderSMS2Post() {
        return sendReminderSMS2Post;
    }

    public void setSendReminderSMS2Post(String sendReminderSMS2Post) {
        this.sendReminderSMS2Post = sendReminderSMS2Post;
    }

    public String getSendExpiredSMS2Post() {
        return sendExpiredSMS2Post;
    }

    public void setSendExpiredSMS2Post(String sendExpiredSMS2Post) {
        this.sendExpiredSMS2Post = sendExpiredSMS2Post;
    }

    public String getSendReminderEmail() {
        return sendReminderEmail;
    }

    public void setSendReminderEmail(String sendReminderEmail) {
        this.sendReminderEmail = sendReminderEmail;
    }

    public String getSendExpiredEmail() {
        return sendExpiredEmail;
    }

    public void setSendExpiredEmail(String sendExpiredEmail) {
        this.sendExpiredEmail = sendExpiredEmail;
    }

    public String getSendDeliverEmail2Post() {
        return sendDeliverEmail2Post;
    }

    public void setSendDeliverEmail2Post(String sendDeliverEmail2Post) {
        this.sendDeliverEmail2Post = sendDeliverEmail2Post;
    }

    public String getSendPickupEmail2Post() {
        return sendPickupEmail2Post;
    }

    public void setSendPickupEmail2Post(String sendPickupEmail2Post) {
        this.sendPickupEmail2Post = sendPickupEmail2Post;
    }

    public String getSendReminderEmail2Post() {
        return sendReminderEmail2Post;
    }

    public void setSendReminderEmail2Post(String sendReminderEmail2Post) {
        this.sendReminderEmail2Post = sendReminderEmail2Post;
    }

    public String getSendExpiredEmail2Post() {
        return sendExpiredEmail2Post;
    }

    public void setSendExpiredEmail2Post(String sendExpiredEmail2Post) {
        this.sendExpiredEmail2Post = sendExpiredEmail2Post;
    }

    public String getTerminalSoftUpgrade() {
        return terminalSoftUpgrade;
    }

    public void setTerminalSoftUpgrade(String terminalSoftUpgrade) {
        this.terminalSoftUpgrade = terminalSoftUpgrade;
    }

    public String getCancelDeliveryOffline() {
        return cancelDeliveryOffline;
    }

    public void setCancelDeliveryOffline(String cancelDeliveryOffline) {
        this.cancelDeliveryOffline = cancelDeliveryOffline;
    }

}
