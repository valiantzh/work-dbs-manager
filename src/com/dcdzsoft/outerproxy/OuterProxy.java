package com.dcdzsoft.outerproxy;

import javax.servlet.http.HttpServletRequest;

import com.dcdzsoft.sms.SMSInfo;

/**
 * 
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * 
 * <p>
 * Description: 面向合作方业务接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * 
 * <p>
 * Company: 杭州东城电子有限公司
 * </p>
 * 
 * @author zhengxy
 * @version 1.0
 */
public interface  OuterProxy {
    /**
     * 查询收件人信息
     * @param terminalNo
     * @param message
     * @return
     */
    public String packageDetail(String terminalNo,String message);
    
    /**
     * 从合作方获取信息
     * @param terminalNo
     * @param message
     * @return
     */
    public String getInfoFormPartner(String terminalNo,String message);

    /**
     * 发送投递反馈信息
     * @param request
     * @return
     */
    public  String sendDeliverInfo(HttpServletRequest request);
    /**
     * 发送投递反馈信息
     * @param smsInfo
     * @return
     */
    public  int sendDeliveryInfo(SMSInfo smsInfo);
    
    /**
     * 智能包裹柜信息接口6000-73
     * @param request
     * @return
     */
    public String sendTerminalInfo(HttpServletRequest request);
    
    /**
     * 发送会员信息
     * @param request
     * @return
     */
    public String sendCustomerInfo(HttpServletRequest request);
    /**
     * 发送会员信息
     * @param sendInfo
     * @return
     */
    public String sendCustomerInfo(SendInfo sendInfo);
    
}
