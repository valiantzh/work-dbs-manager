package com.dcdzsoft.sms.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

public class MsgProxyDcdzsoft implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyDcdzsoft.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
        PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", apCfg.getGatewayUser()),
        		                new NameValuePair("Key", apCfg.getGatewayPwd()),
        		                new NameValuePair("smsMob",smsInfo.CustomerMobile),
        		                new NameValuePair("smsText",smsInfo.MsgContent)};
        
        try
        {
        	 //System.out.println(msgContent);

        	 post.setRequestBody(data);
        	 int statusCode = client.executeMethod(post);

        	 if(statusCode == HttpStatus.SC_OK)
        	 {
        		 result = new String(post.getResponseBodyAsString().getBytes("utf8"));
        		 
        		 //大于零短信数量
        		 System.out.println("sms result = " + result);
        	 }else
        	 {
        		 log.error("MsgProxyDcdzsoft[statusCode]" + statusCode);
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
