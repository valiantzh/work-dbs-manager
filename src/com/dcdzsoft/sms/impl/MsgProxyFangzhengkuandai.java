package com.dcdzsoft.sms.impl;

import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

public class MsgProxyFangzhengkuandai implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyFangzhengkuandai.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		String message = "";
		
		try{
			message = URLEncoder.encode(smsInfo.MsgContent, "GB2312");
		}catch(Exception e){

		}

		HttpClient client = new HttpClient();

		// Create a method instance.
		String url = "http://114.255.71.158:8061/?username=" + apCfg.getGatewayUser() + "&password=" + apCfg.getGatewayPwd();
		url = url + "&message=" + message;
		url = url + "&phone=" + smsInfo.CustomerMobile;
	    url = url + "&epid=109654";
	    url = url + "&linkid=" + "";
	    url = url + "&subcode=";

		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        try
        {
        	 // Execute the method.
             int statusCode = client.executeMethod(method);

             if(statusCode == HttpStatus.SC_OK)
        	 {
            	 result = new String(method.getResponseBodyAsString().getBytes("GB2312"));
            	 
            	 //System.out.println(result);
        	 }else
        	 {
        		 log.error("[statusCode]" + statusCode);
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
