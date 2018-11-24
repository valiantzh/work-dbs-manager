package com.dcdzsoft.sms;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SMSInfo {
	public static final int MSG_TYPE_DELIVERY = 1; //投递
	public static final int MSG_TYPE_EXPIRED = 2; //逾期
	public static final int MSG_TYPE_REMINDER = 3; //催领
	public static final int MSG_TYPE_TAKEDOUT = 4; //取件
	public static final int MSG_TYPE_RESENT = 5;   //重发投递
	public static final int MSG_TYPE_CHECKCODE = 7; //注册验证码
	public static final int MSG_TYPE_REGISTER = 8; //投递员注册
	public static final int MSG_TYPE_SENDURGENTSMS = 9; //紧急取件短信
	public static final int MSG_TYPE_SMSOWING = 10; //欠费通知短信
	public static final int MSG_TYPE_DELIVERYTOPOSTMAN = 11; //发送投递短信给投递员
	public static final int MSG_TYPE_DELIVERYTOCOMPANY = 12; //发送投递短信给投递公司
	public static final int MSG_TYPE_TAKEDOUTTOPOSTMAN = 21; //发送取件短信给投递员
	public static final int MSG_TYPE_TAKEDOUTTOCOMPANY = 22; //发送取件短信给投递公司
	
	public static final int MSG_TYPE_EXPIREDTOPOSTMAN = 41; //发送逾期取件短信给投递员
	public static final int MSG_TYPE_EXPIREDTOCOMPANY = 42; //发送逾期取件短信给投递公司
	
	public static final int MSG_TYPE_MOUTH=50; //投件时没关格口通知给仓管
    public static final int MSG_TYPE_TAKE=51; //取件柜快件取出通知给用户
    public static final int MSG_TYPE_RETENTION=52;//取件柜滞留30小时通知给用户
    public static final int MSG_TYPE_ITWRITER=53; //取件柜滞留30小时通知快递员
    public static final int MSG_TYPE_DETENTION=54;//取件柜滞留24小时通知用户
	
	public long WaterID = 0L;
	public String PackageID = "";
	public java.sql.Timestamp StoredTime;
	public java.sql.Date StoredDate; 
	public java.sql.Timestamp TakedTime;
	public java.sql.Timestamp sysDateTime;
	public java.sql.Timestamp ExpiredTime;
	public String TerminalNo = "";
	public String TerminalName = "";
	public String MBDeviceNo = "";
	public String BoxNo = "";
	public String OfBureau = ""; //所属投递段
	public String Location = "";
	public String DepartmentID = "";
	public String CustomerID = "";
	public String CustomerMobile = "";
	public String CustomerName = "";
	public String OpenBoxKey = "";
	public String DynamicCode = "";
	public String PackageStatus = "";
	public String PostmanID = "";
	public String PostmanName = "";
	public String PostmanMobile = "";
	public String PostmanEmail = "";
	public String PostmanPwd = "";
	public String CompanyID = "";
	public String CompanyName = "";
	public String CompanyMobile = "";//投递公司联系人电话
	public String LogisticsName = "";
	public String MsgContent = "";
	public String Remark = "";
	
	public String Brand = "";//品牌、供应商
	
	public String SenderMobile = "";//
	public String TradeWaterNo = "";//
	public String OfLogisticsID = ""; //运单所属物流公司
	public String OfLogisticsName = ""; //运单所属物流公司名称
	public String StaOrderID = ""; //电商运单号
	public String BoxType = "";
	
	public int MsgType = 0;
	public String msgTel = "";//客服电话
	
	public String Email = "";//邮件地址
	public String FromEamilAddress = "";//发件人地址
	public String UserName = "";//发件用户（一般和发件人地址相同）
	public String EmailPwd = "";//邮箱密码
	//发送反馈订单信息
	/**
	 * 反馈标志 P1-包裹已投箱;P2-用户已领取;P3-包裹逾期未领,投递员收回;P4-逾期或锁定（获取新的逾期时间）
	 */
    public String SendBZ = "";//
    public String NewStatus = "";//新的订单状态
    public String result = "";
    
    public SMSInfo(){}  
    //增加了属性之后在形参构造方法中要加入赋值语句。
	public SMSInfo(SMSInfo smsInfo) {
		WaterID          = smsInfo.WaterID;
		PackageID        = smsInfo.PackageID;
		StoredTime       = smsInfo.StoredTime;
		StoredDate       = smsInfo.StoredDate;
		TakedTime        = smsInfo.TakedTime;
		ExpiredTime      = smsInfo.ExpiredTime;
		TerminalNo       = smsInfo.TerminalNo;
		TerminalName     = smsInfo.TerminalName;
		MBDeviceNo       = smsInfo.MBDeviceNo;
		BoxNo 		     = smsInfo.BoxNo;
		OfBureau         = smsInfo.OfBureau;
		Location         = smsInfo.Location;
		DepartmentID     = smsInfo.DepartmentID;
		CustomerID       = smsInfo.CustomerID;
		CustomerMobile   = smsInfo.CustomerMobile;
		CustomerName     = smsInfo.CustomerName;
		OpenBoxKey       = smsInfo.OpenBoxKey;
		DynamicCode      = smsInfo.DynamicCode;
		PackageStatus    = smsInfo.PackageStatus;
		PostmanID        = smsInfo.PostmanID;
		PostmanName      = smsInfo.PostmanName;
		PostmanMobile    = smsInfo.PostmanMobile;
		PostmanEmail     = smsInfo.PostmanEmail;
		PostmanPwd       = smsInfo.PostmanPwd;
		CompanyID        = smsInfo.CompanyID;
		CompanyName      = smsInfo.CompanyName;
		CompanyMobile    = smsInfo.CompanyMobile;
		LogisticsName    = smsInfo.LogisticsName;
		MsgContent       = smsInfo.MsgContent;
		Remark           = smsInfo.Remark;
		Brand            = smsInfo.Brand;
		SenderMobile     = smsInfo.SenderMobile;
		TradeWaterNo     = smsInfo.TradeWaterNo;
		OfLogisticsID    = smsInfo.OfLogisticsID;
		OfLogisticsName  = smsInfo.OfLogisticsName;
		StaOrderID       = smsInfo.StaOrderID;
		BoxType          = smsInfo.BoxType;
		MsgType          = smsInfo.MsgType;
		Email            = smsInfo.Email;
		FromEamilAddress = smsInfo.FromEamilAddress;
		UserName 	     = smsInfo.UserName;
		EmailPwd         = smsInfo.EmailPwd;
		SendBZ           = smsInfo.SendBZ;
		NewStatus        = smsInfo.NewStatus;
		
		msgTel           = smsInfo.msgTel;
		sysDateTime      = smsInfo.sysDateTime;
		result           = smsInfo.result;
	}
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
