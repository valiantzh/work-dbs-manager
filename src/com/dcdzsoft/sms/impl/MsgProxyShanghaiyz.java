package com.dcdzsoft.sms.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

public class MsgProxyShanghaiyz implements ISMSProxy {
	private static String DEFAULT_UID = "shangyz";
	private static String DEFAULT_KEY = "Wzl129";
	
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyShanghaiyz.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static String postUrl = "";
	static
	{
		postUrl = "http://" + apCfg.getSmsServerIp() + ":" + apCfg.getSmsServerPort() + "/smsservice";
	}
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		
		//if(smsInfo.MsgType == SMSInfo.MSG_TYPE_REGISTER)
		//	return result;
		
		HttpClient client = new HttpClient();
		//HttpClient client = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true));
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(20000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(20000);
		
        PostMethod post = new PostMethod(postUrl);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        NameValuePair[] data ={ //new NameValuePair("Uid", DEFAULT_UID),
        		                //new NameValuePair("Key", DEFAULT_KEY),
        		                new NameValuePair("smsType",String.valueOf(smsInfo.MsgType)),
        		                new NameValuePair("smsMob",smsInfo.CustomerMobile),
        		                new NameValuePair("smsText",smsInfo.MsgContent),
        		                new NameValuePair("depid",smsInfo.DepartmentID)};

        try
        {
        	 //System.out.println(msgContent);

        	 post.setRequestBody(data);
        	 int statusCode = client.executeMethod(post);

        	 if(statusCode == HttpStatus.SC_OK)
        	 {
        		 result = post.getResponseBodyAsString();
        		 result = StringUtils.trim(result);
        		 //System.out.println("result = " + result);
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
        	post.releaseConnection();
        }
        
        return result;
	}
}
