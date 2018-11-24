package com.dcdzsoft.sms.impl;

import java.io.*;
import java.net.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.w3c.dom.Document;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
public class MsgProxyFrenchThomas implements ISMSProxy{
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyFrenchThomas.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	public String sendMessage(SMSInfo smsInfo) throws EduException {
		String result = "";
		// 判断手机号
		if (smsInfo.CustomerMobile.length() == 9
				&& !smsInfo.CustomerMobile.startsWith("0")) {
			smsInfo.CustomerMobile = "33" + smsInfo.CustomerMobile;
		} else if (smsInfo.CustomerMobile.length() == 10
				&& smsInfo.CustomerMobile.startsWith("0")) {
			smsInfo.CustomerMobile = "33"
					+ smsInfo.CustomerMobile.substring(1);
		}
		System.out.println("发送手机号："+smsInfo.CustomerMobile);
		log.info("发送手机号："+smsInfo.CustomerMobile);
        String sender = apCfg.getGatewayUser();
        String message = "";
		try {
			message = URLEncoder.encode(smsInfo.MsgContent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
        String login = "gsisms";
        String apiKey = "938b929c9d7c458";
        String smsData = "<DATA><MESSAGE><![CDATA[["+message+"]]></MESSAGE><TPOA>"+sender+"</TPOA><SMS><MOBILEPHONE>"+smsInfo.CustomerMobile+"</MOBILEPHONE></SMS></DATA>";
        String url = "https://api.allmysms.com/http/9.0/sendSms/?login=" + login + "&apiKey=" + apiKey + "&smsData=" + smsData;
        // Send GET request
        try{
	        URL client = new URL(url);
	        URLConnection conn = client.openConnection();
	        InputStream responseBody = conn.getInputStream();	  
	        byte[] contents = new byte[1024];	  
	        int bytesRead=0;
	        String strFileContents = null;
	        while( (bytesRead = responseBody.read(contents)) != -1){
	           strFileContents = new String(contents, 0, bytesRead);
	        }
	        responseBody.close();
	        System.out.println(strFileContents);
        }catch(Exception e){
        	e.printStackTrace();
        	throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        }
        log.info("发送短信返回："+result);
        return result;
     }
 }