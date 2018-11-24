package com.dcdzsoft.sms.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SslUtils;

public class MsgProxyIhuyiShunfeng implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyIhuyiShunfeng.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		
		String mobile = smsInfo.CustomerMobile;
		switch(smsInfo.MsgType){
		case SMSInfo.MSG_TYPE_TAKEDOUTTOCOMPANY:{//发送给投递公司
			mobile = smsInfo.CompanyMobile;
			if(StringUtils.isEmpty(mobile)){
				return "";
			}			
		}break;
		case SMSInfo.MSG_TYPE_TAKEDOUTTOPOSTMAN:{//发送给投递员
			mobile = smsInfo.PostmanMobile;
			if(StringUtils.isEmpty(mobile)){
				return "";
			}			
		}break;			
		}
		log.info("处理手机号："+mobile);
		boolean isNeedSendAgain = false;
		
		String result = "";
		
		String Url = "https://unp-mail-sit.sit.sf-express.com:40086/unp/notice/single";
		
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
		
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

        NameValuePair[] data ={ new NameValuePair("account", apCfg.getGatewayUser()),
        		                new NameValuePair("password", apCfg.getGatewayPwd()),
        		                new NameValuePair("mobile",mobile),
        		                new NameValuePair("content",smsInfo.MsgContent)};
        method.setRequestBody(data);
        try
        {
        	
        	client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

        	 if("2".equals(code))
        	 {
        		 result = "0";
        	 }else
        	 {
        		 log.error("[statusCode]" + code + "," + msg);
        		 throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        	 }
        }catch(Exception e)
        {
        	e.printStackTrace();
        	//throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        	isNeedSendAgain = true;
        }
        finally
        {
        	method.releaseConnection();
        }
        
        if(isNeedSendAgain == true)
        	return _sendMessageAgain(smsInfo);
        
        
        return result;
	}
	
	public String _sendMessageAgain(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		
		String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";

		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
		
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

        NameValuePair[] data ={ new NameValuePair("account", apCfg.getGatewayUser()),
        		                new NameValuePair("password", apCfg.getGatewayPwd()),
        		                new NameValuePair("mobile",smsInfo.CustomerMobile),
        		                new NameValuePair("content",smsInfo.MsgContent)};
        
        method.setRequestBody(data);

        try
        {
        	
        	client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

        	 if("2".equals(code))
        	 {
        		 result = "0";
        	 }else
        	 {
        		 log.error("[statusCode]" + code + "," + msg);
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
