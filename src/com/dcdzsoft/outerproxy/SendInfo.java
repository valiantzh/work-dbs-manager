package com.dcdzsoft.outerproxy;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;

import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.WebUtils;

public class SendInfo {
    public String Action = "";//
    //会员信息
    public String CardNo = "";//卡号
    public String CustomerID = "";//会员编号
    public String CustomerName = "";//会员名称
    public String CustomerMobile = "";//会员手机
    public int Balance; //余额
    //交易信息
    public String TradeWaterNo = "";//交易流水号
    public String TransactionType = "";//交易类型
    public int TransactionAmt;//交易金额(单位：分)
    public String TransactionDate = "";//交易日期
    public String ReferenceCode = "";//
    public String TradeUserID = "";//交易人员
    
    public String UploadFlag = "";
    public String TerminalNo = "";
    public String PackageID = "";
    public java.sql.Timestamp sysDateTime;//当前时间
    public String Remark = "";
    /**
     * 生成POST参数-用户信息同步，充值流水同步
     * @param action
     * @param sendInfo
     * @return
     */
    public static NameValuePair[] toNameValuePair(String action, SendInfo sendInfo){
        String sysDateTimeStr = "";
        if(sendInfo.sysDateTime != null){
            sysDateTimeStr = DateUtils.timestampToString(sendInfo.sysDateTime);
        }
        NameValuePair[] data ={ new NameValuePair("ACTION", action),
                new NameValuePair("CardNo",sendInfo.CardNo),
                new NameValuePair("CID",sendInfo.CustomerID),
                new NameValuePair("CNAME",sendInfo.CustomerName),
                new NameValuePair("CMOBI",sendInfo.CustomerMobile),
                new NameValuePair("BALANCE",Integer.toString(sendInfo.Balance)),
                new NameValuePair("TradeNo",sendInfo.TradeWaterNo),
                new NameValuePair("TradeDate",sendInfo.TransactionDate),
                new NameValuePair("TradeType",sendInfo.TransactionType),
                new NameValuePair("TradeRefe",sendInfo.ReferenceCode),
                new NameValuePair("TradeAmt",Integer.toString(sendInfo.TransactionAmt)),
                
                new NameValuePair("TNO", sendInfo.TerminalNo),
                new NameValuePair("PID",sendInfo.PackageID),
                new NameValuePair("sysDate",sysDateTimeStr),
                };
        return data;
    }
    /**
     * 提取POST参数-用户信息同步，充值流水同步
     * @param request
     * @return SendInfo
     */
    public static SendInfo toCustomerInfo(HttpServletRequest request){
        SendInfo sendInfo = new SendInfo();
        sendInfo.Action = WebUtils.getStringParameter("ACTION",request);
        sendInfo.CardNo = WebUtils.getStringParameter("CardNo",request);
        sendInfo.CustomerID = WebUtils.getStringParameter("CID",request);
        sendInfo.CustomerName = WebUtils.getStringParameter("CNAME",request);
        sendInfo.CustomerMobile = WebUtils.getStringParameter("CMOBI",request);
        sendInfo.Balance = WebUtils.getIntParameter("BALANCE",request);
        
        sendInfo.TradeWaterNo    = WebUtils.getStringParameter("TradeNo",request);
        sendInfo.TransactionDate = WebUtils.getStringParameter("TradeDate",request);
        sendInfo.TransactionType = WebUtils.getStringParameter("TradeType",request);
        sendInfo.ReferenceCode   = WebUtils.getStringParameter("TradeRefe",request);
        sendInfo.TransactionAmt  = WebUtils.getIntParameter("TradeAmt",request);
        
        sendInfo.TerminalNo     = WebUtils.getStringParameter("TNO", request);
        sendInfo.PackageID      = WebUtils.getStringParameter("PID",request);
        
        String sysDateTimeStr  = WebUtils.getStringParameter("XHRQ",request); 
        if(StringUtils.isNotEmpty(sysDateTimeStr)){
            sendInfo.sysDateTime = DateUtils.stringToTimestamp(sysDateTimeStr);
        }
        
        return sendInfo;
    }
    /**
     * 生成POST参数-订单状态同步
     * @param action
     * @param smsInfo
     * @return
     */
    public static NameValuePair[] toNameValuePair(String action, SMSInfo smsInfo){
        String sysDateTimeStr = "";
        String expiredTimeStr = "";
        String takedTimeStr   = "";
        String storedTimeStr   = "";
        if(smsInfo.sysDateTime != null){
            sysDateTimeStr = DateUtils.timestampToString(smsInfo.sysDateTime);
        }
        if(smsInfo.ExpiredTime != null){
            expiredTimeStr = DateUtils.timestampToString(smsInfo.ExpiredTime);
        }
        if(smsInfo.TakedTime != null){
            takedTimeStr = DateUtils.timestampToString(smsInfo.TakedTime);
        }
        if(smsInfo.StoredTime != null){
            storedTimeStr = DateUtils.timestampToString(smsInfo.StoredTime);
        }
        NameValuePair[] data ={ new NameValuePair("ACTION", action),
                new NameValuePair("TERMINALNO", smsInfo.TerminalNo),
                new NameValuePair("TNAME", smsInfo.TerminalName),
                new NameValuePair("MBDEVICENO", smsInfo.MBDeviceNo),
                new NameValuePair("LTN", smsInfo.Location),
                new NameValuePair("PTS", smsInfo.PackageStatus),
                new NameValuePair("BNO",smsInfo.BoxNo),
                new NameValuePair("BTP",smsInfo.BoxType),
                new NameValuePair("PID",smsInfo.PackageID),
                new NameValuePair("MOB",smsInfo.CustomerMobile),
                new NameValuePair("CID",smsInfo.CustomerID),
                new NameValuePair("PWD",smsInfo.OpenBoxKey),
                new NameValuePair("MID",smsInfo.PostmanID),
                new NameValuePair("MNE",smsInfo.PostmanName),
                new NameValuePair("MME",smsInfo.PostmanMobile),
                new NameValuePair("TDJH",smsInfo.OfBureau),
                new NameValuePair("XHRQ",sysDateTimeStr),
                new NameValuePair("EXRQ",expiredTimeStr),//逾期日期
                new NameValuePair("QJRQ",takedTimeStr),//取件日期
                new NameValuePair("CWRQ",storedTimeStr),//存物日期
                new NameValuePair("BZ",smsInfo.SendBZ),
                new NameValuePair("WID",smsInfo.TradeWaterNo),
                new NameValuePair("WLID",smsInfo.OfLogisticsID),
                new NameValuePair("WLMC",smsInfo.OfLogisticsName),
                new NameValuePair("COMN",smsInfo.CompanyName),
                new NameValuePair("ORDERID",smsInfo.StaOrderID),
                new NameValuePair("REMARK",smsInfo.Remark)
                };
        return data;
    }
    /**
     * 提取POST参数
     * @param request
     * @return SMSInfo
     */
    public static SMSInfo toSMSInfo(HttpServletRequest request){
        SMSInfo smsInfo = new SMSInfo();
        smsInfo.TerminalNo     = WebUtils.getStringParameter("TERMINALNO", request);
        smsInfo.TerminalName   = WebUtils.getStringParameter("TNAME", request);
        smsInfo.MBDeviceNo     = WebUtils.getStringParameter("MBDEVICENO", request);
        smsInfo.Location       = WebUtils.getStringParameter("LTN", request);
        smsInfo.PackageStatus  = WebUtils.getStringParameter("PTS", request);
        smsInfo.BoxNo          = WebUtils.getStringParameter("BNO",request);
        smsInfo.BoxType        = WebUtils.getStringParameter("BTP",request);
        smsInfo.PackageID      = WebUtils.getStringParameter("PID",request);
        smsInfo.CustomerID      = WebUtils.getStringParameter("CID",request);
        smsInfo.CustomerMobile = WebUtils.getStringParameter("MOB",request);
        smsInfo.OpenBoxKey     = WebUtils.getStringParameter("PWD",request);
        smsInfo.PostmanID      = WebUtils.getStringParameter("MID",request);
        smsInfo.PostmanName    = WebUtils.getStringParameter("MNE",request);
        smsInfo.PostmanMobile  = WebUtils.getStringParameter("MME",request);
        smsInfo.OfBureau       = WebUtils.getStringParameter("TDJH",request);
        String sysDateTimeStr  = WebUtils.getStringParameter("XHRQ",request); 
        if(StringUtils.isNotEmpty(sysDateTimeStr)){
            smsInfo.sysDateTime = DateUtils.stringToTimestamp(sysDateTimeStr);
        }
        String expiredDateTimeStr = WebUtils.getStringParameter("EXRQ",request); 
        if(StringUtils.isNotEmpty(expiredDateTimeStr)){
            smsInfo.ExpiredTime = DateUtils.stringToTimestamp(expiredDateTimeStr);
        }
        String takedTimeStr = WebUtils.getStringParameter("QJRQ",request); 
        if(StringUtils.isNotEmpty(takedTimeStr)){
            smsInfo.TakedTime = DateUtils.stringToTimestamp(takedTimeStr);
        }
        String storedTimeStr = WebUtils.getStringParameter("CWRQ",request); 
        if(StringUtils.isNotEmpty(storedTimeStr)){
            smsInfo.StoredTime = DateUtils.stringToTimestamp(storedTimeStr);
        }
        smsInfo.SendBZ          = WebUtils.getStringParameter("BZ",request);
        smsInfo.TradeWaterNo    = WebUtils.getStringParameter("WID",request);
        smsInfo.OfLogisticsID   = WebUtils.getStringParameter("WLID",request);
        smsInfo.OfLogisticsName = WebUtils.getStringParameter("WLMC",request);
        smsInfo.CompanyName     = WebUtils.getStringParameter("COMN",request);
        smsInfo.StaOrderID      = WebUtils.getStringParameter("ORDERID",request);
        smsInfo.Remark          = WebUtils.getStringParameter("REMARK",request);
        return smsInfo;
    }
}
