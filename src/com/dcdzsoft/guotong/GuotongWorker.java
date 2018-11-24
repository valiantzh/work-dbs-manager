package com.dcdzsoft.guotong;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.Callable;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.businessproxy.MonitorProxy;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.constant.SysDict;
import com.dcdzsoft.outerproxy.SendInfo;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.dto.business.InParamMBModDeliveryStatus;
import com.dcdzsoft.dto.business.InParamMBModSMSSentStatus;
import com.dcdzsoft.dto.business.InParamPTModExpiredTime;
import com.dcdzsoft.dto.business.InParamPTModPackageStatus;
import com.dcdzsoft.dto.function.TBTerminal;

public class GuotongWorker implements Callable<String> {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(GuotongWorker.class);
	
	private SMSInfo smsInfo = null;
	private TBTerminal terminal = null;
	private SendInfo  sendInfo = null;
	public GuotongWorker(SMSInfo smsInfo,TBTerminal terminal, SendInfo  sendInfo) {
		this.smsInfo = smsInfo;
		this.terminal = terminal;
		this.sendInfo = sendInfo;
	}

	// @Override
	public String call() throws Exception {
		if(this.smsInfo != null)
		{
			doSentDeliveryInfo();
		}
		
		if(this.terminal != null)
		{
			
		}
		
		if(this.sendInfo != null)
        {
		    doSentCustomerInfo();
        }
		return "";
	}
	
	/**
	 * 发送投递情况反馈
	 */
	private void doSentDeliveryInfo()
	{
		//发送反馈信息
		try
		{
			int isSentOK = 0;
			isSentOK = HttpClient4Guotong.doSentDeliveryInfo(smsInfo);
			
	        //结果入库
			InParamMBModDeliveryStatus inParam = new InParamMBModDeliveryStatus();
			inParam.PackageID = smsInfo.PackageID;
			inParam.TerminalNo = smsInfo.TerminalNo;
			inParam.StoredTime = smsInfo.StoredTime;
			inParam.PackageStatus = smsInfo.PackageStatus;
			
			inParam.TradeWaterNo = smsInfo.TradeWaterNo;
			inParam.OfBureau = smsInfo.OfBureau;
			inParam.BoxNo = smsInfo.BoxNo;
			inParam.PostmanID = smsInfo.PostmanID;
			inParam.CompanyID = smsInfo.CompanyID;
			inParam.CustomerMobile = smsInfo.CustomerMobile;
			inParam.OfLogisticsID = smsInfo.OfLogisticsID;
			inParam.OfLogisticsName = smsInfo.OfLogisticsName;
			inParam.StaOrderID = smsInfo.StaOrderID;
			inParam.OpenBoxKey = smsInfo.OpenBoxKey;
			inParam.HandleFlag = "1";
			inParam.Remark     = smsInfo.Remark;
			
			if(isSentOK == 0)
				inParam.UploadFlag = "1"; //发送成功
			else
				inParam.UploadFlag = "2"; //发送失败
			
			MonitorProxy.doBusiness(inParam);
			
			if("P4".equalsIgnoreCase(smsInfo.SendBZ)){
			    if("0".equals(smsInfo.result)){
			        //设置新的逾期时间
			        if(smsInfo.ExpiredTime != null){
			            
			            InParamPTModExpiredTime in = new InParamPTModExpiredTime();
	                    in.PackageID = smsInfo.PackageID;
	                    in.TerminalNo = smsInfo.TerminalNo;
	                    in.ExpiredTime = smsInfo.ExpiredTime;
	                    try {
	                        //修改订单逾期时间
	                    	MonitorProxy.doBusiness(in);
	                        
	                    } catch (EduException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
			        }
			        //修改订单状态
			        if(!smsInfo.PackageStatus.equals(smsInfo.NewStatus)){
			            InParamPTModPackageStatus in = new InParamPTModPackageStatus();
	                    in.PackageID = smsInfo.PackageID;
	                    in.TerminalNo = smsInfo.TerminalNo;
	                    in.PackageStatus = smsInfo.NewStatus;
	                    try {
	                    	MonitorProxy.doBusiness(in);
	                    } catch (EduException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
			        }
			    }
			}
		}catch(Exception e)
		{
			log.error("[doSentDeliveryInfo error1] = " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
     * 发送会员信息（包括同步充值流水）
     */
    private void doSentCustomerInfo()
    {
        //发送反馈信息
        try
        {
            int isSentOK = 0;
            
            isSentOK = HttpClient4Guotong.doSentCustomerInfo(sendInfo);
            
            //结果入库
            
        }catch(Exception e)
        {
            log.error("[doSentCustomerInfo error1] = " + e.getMessage());
            e.printStackTrace();
        }
    }
}
