package com.dcdzsoft.sms;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;

import com.dcdzsoft.businessproxy.MonitorProxy;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.dto.business.InParamMBModSMSSentStatus;
import com.dcdzsoft.dto.business.InParamPYSMSNumCheck;
import com.dcdzsoft.util.DateUtils;

public class SMSWorker implements Callable<String> {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(SMSWorker.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	private static final String PROXY_PACKAGE_PREFIX = "com.dcdzsoft.sms.impl.";
	private static final String BUSINESS_METHOD_NAME = "sendMessage";
	
	private static Class proxyClass = null;
	private static ISMSProxy smsProxy = null;
	private static Method method = null; 
	
	 static{
		 try
		 {
			 proxyClass = Class.forName(PROXY_PACKAGE_PREFIX + apCfg.getSendShortMsg());
			 smsProxy = (ISMSProxy)proxyClass.newInstance();
			 
			 method = proxyClass.getMethod(BUSINESS_METHOD_NAME, new Class[]{SMSInfo.class});
		 }catch(Exception e)
		 {
		     System.err.println("SMSWorker proxyClass="+PROXY_PACKAGE_PREFIX + apCfg.getSendShortMsg());
			 e.printStackTrace();
		 }
	 }
	
	private SMSInfo smsInfo = null;

	public SMSWorker(SMSInfo smsInfo) {
		this.smsInfo = smsInfo;
	}

	// @Override
	public String call() throws Exception {

	    boolean isSentOK = false;
	    int isNeedSend = checkIsSent();//检查是否需要发送&是否允许发送
		//判断是否需要发送短消息
		if(isNeedSend>0)
		{
			isSentOK = sentSMS();
			
			//重发两次
			/*if(!isSentOK)
			{
				//休眠10000毫秒
				Thread.sleep(1000*10);
				
				isSentOK = sentSMS();
			}
			
			if(!isSentOK)
			{
				//休眠200000毫秒
				Thread.sleep(1000*20);
				
				isSentOK = sentSMS();
			}*/
		}else{
		    log.info(DateUtils.nowDate() + " " + DateUtils.nowTime() + " [SentSMSFail]" + this.smsInfo.CustomerMobile+":" +this.smsInfo.MsgContent);
		}
		//结果入库
        InParamMBModSMSSentStatus inParam = new InParamMBModSMSSentStatus();
        inParam.WaterID = smsInfo.WaterID;
        inParam.Remark = smsInfo.Remark;
        if(smsInfo.MsgType == SMSInfo.MSG_TYPE_DELIVERY 
        	|| smsInfo.MsgType == SMSInfo.MSG_TYPE_RESENT 
        	//|| smsInfo.MsgType == SMSInfo.MSG_TYPE_DELIVERYTOPOSTMAN
        	|| smsInfo.MsgType == SMSInfo.MSG_TYPE_REMINDER
        	)
        {
            if(isNeedSend>0){
                if(isSentOK)
                    inParam.SendStatus = "2"; //发送成功
                else
                    inParam.SendStatus = "4"; //发送失败
            }else if(isNeedSend == 0){
                inParam.SendStatus = "5"; //不需要发送，短信由合作方发送
            }else{
                //
                inParam.SendStatus = "6"; //不让发送：发送失败(联系客户充值)
            }
            MonitorProxy.doBusiness(inParam);
        }
        
		return "";
	}
	/**
	 * 
	 * @return  1-发送，0-不用发，-1-不让发
	 */
	private int checkIsSent(){
	    
	    if("1".equals(ControlParam.getInstance().getPartnerSendSMS())){//由合作方平台发送短信
            if(smsInfo.MsgType == SMSInfo.MSG_TYPE_DELIVERY 
                || smsInfo.MsgType == SMSInfo.MSG_TYPE_EXPIRED
                || smsInfo.MsgType == SMSInfo.MSG_TYPE_REMINDER
                || smsInfo.MsgType == SMSInfo.MSG_TYPE_TAKEDOUT
                || smsInfo.MsgType == SMSInfo.MSG_TYPE_RESENT
                || smsInfo.MsgType == SMSInfo.MSG_TYPE_DELIVERYTOPOSTMAN)
            {
                return 0;//不用发
            }
        }
	    boolean isSent = true;
	    try{
            //更新短信发送统计&查询短信账号
            InParamPYSMSNumCheck inParamC = new InParamPYSMSNumCheck();
            inParamC.DepartmentID = this.smsInfo.DepartmentID;
            inParamC.MsgType      = this.smsInfo.MsgType;
            inParamC.MsgSize      = this.smsInfo.MsgContent.length();
            //System.out.println(""+inParamC.DepartmentID+","+inParamC.MsgType+","+inParamC.MsgSize);
            isSent = MonitorProxy.doBusiness(inParamC);
        }catch(Exception e){
            e.printStackTrace();
        }
	    if(isSent){
	        return 1;
	    }else{
	        return -1;//不给发
	    }
	}
	private boolean sentSMS()
	{
		boolean isSentOK = true;
		
		
		//具体发送短消息
		try{
			Object result = method.invoke(smsProxy, new Object[]{smsInfo});
	        
	        if(result != null && ("-222" .equals(result) || "-333".equals(result) || "-444".equals(result) || "-555".equals(result) || "-666".equals(result)))
	        	isSentOK = false;
	        
		}catch(Exception e)
		{
			e.printStackTrace();
			
			isSentOK = false;
		}
		
		return isSentOK;
	}
}
