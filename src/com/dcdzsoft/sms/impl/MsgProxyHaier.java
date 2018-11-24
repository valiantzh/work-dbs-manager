package com.dcdzsoft.sms.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

public class MsgProxyHaier implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyHaier.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		
		//String Url = "http://221.179.180.158:9007/QxtSms/QxtFirewall?";
		
		StringBuffer sb = new StringBuffer();
        
        //sb.append("&OperID=" + apCfg.getGatewayUser());
        //sb.append("&OperPass=" + apCfg.getGatewayPwd());
		sb.append("&OperID=kuaids");
	    sb.append("&OperPass=123456");
	        
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");  
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		String startTime = format.format(c.getTime());
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 10);
		String endTime = format.format(c.getTime());
        
		sb.append("&SendTime=" + startTime);
		sb.append("&ValidTime=" + endTime);
		sb.append("&AppendID=1234");
		sb.append("&DesMobile=" + smsInfo.CustomerMobile);
		sb.append("&Content=" + encoderURL(smsInfo.MsgContent));
		sb.append("&ContentType=15");

		//短信完整请求URL
        String sms = "http://221.179.180.158:9007/QxtSms/QxtFirewall?" + sb.toString();

		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(sms);
		//PostMethod method = new PostMethod(Url);
		//client.getParams().setContentCharset("UTF-8");
		//method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

        try
        {
        	client.executeMethod(method);
			String SubmitResult = method.getResponseBodyAsString();

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			
        	 if("03".equals(code)) //单条短信提交成功
        	 {
        		 result = "0";
        	 }else
        	 {
        		 log.error("[statusCode]" + code );
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
	
	private String encoderURL(String MsgContent)
	{
		String result = "";
		try
		{
			result = URLEncoder.encode(MsgContent,"GBK");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
