package com.dcdzsoft.sms.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

public class MsgProxyZhiLi implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyZhiLi.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
	
		String Url = "https://rti-sms.com/bulksms/submitsms.go";

		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
		client.getParams().setContentCharset("UTF-8");
		
		String username = apCfg.getGatewayUser();
		String password = apCfg.getGatewayPwd();
		String originator = "7180";//smsInfo.DynamicCode
		String phone = smsInfo.CustomerMobile;
		if(!smsInfo.CustomerMobile.startsWith("56")){//56 国家代码
			phone = "56"+smsInfo.CustomerMobile;
		}
		String msgtext = "";
		try {
			msgtext = URLEncoder.encode(smsInfo.MsgContent,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuffer urlBuffer = new StringBuffer(Url);
		
		urlBuffer.append("?username=").append(username)
		.append("&password=").append(password)
		.append("&originator=").append(originator)
		.append("&phone=").append(phone)
		.append("&msgtext=").append(msgtext);
		System.out.println(urlBuffer);
		GetMethod method = new GetMethod(urlBuffer.toString());
		
		//method.addRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
       
        try
        {
        	client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();
			//System.out.println(SubmitResult);
			
        	if("OK".equals(SubmitResult))
        	{
        	    result = "0";
        	}else
        	{
       		    log.error("Result="+SubmitResult);
        	    throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
            }
        }catch(Exception e)
        {
        	e.printStackTrace();
        	throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        }
        finally
        {
        	method.releaseConnection();
        }
        return result;
	}
	
}
