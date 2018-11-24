package com.dcdzsoft.sms.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

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

public class MsgProxyElecreplayOVH implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyElecreplayOVH.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static final String SMS_ACCOUNT = "sms-ls148311-1";
	private static final String SMS_FROM    = "ELECREPLAY";
	private static final String SMS_CONTENTTYPE = "text/json";//text/plain
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		
		String Url = "https://www.ovh.com/cgi-bin/sms/http2sms.cgi?";
		StringBuffer sb = new StringBuffer();
		sb.append("account=" + SMS_ACCOUNT);
        sb.append("&login=" + apCfg.getGatewayUser());
        sb.append("&password=" + apCfg.getGatewayPwd());
        //sb.append("&login=casierselecreplay");
        //sb.append("&password=casier93");
        
        sb.append("&from=" + SMS_FROM);
        sb.append("&contentType=" + SMS_CONTENTTYPE);
        String mobile = smsInfo.CustomerMobile;
        if(smsInfo.CustomerMobile.length()==9){
            mobile = "0033"+smsInfo.CustomerMobile;
        }
		sb.append("&to=" + mobile);
		sb.append("&message=" + encoderURL(smsInfo.MsgContent));//
		System.out.println("ElecreplayOVH:"+smsInfo.MsgContent);
		//短信完整请求URL
        String sms = Url + sb.toString();
        System.out.println("ElecreplayOVH:"+sms);
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(sms);
        try
        {
            int statusCode = client.executeMethod(method);
            if(statusCode == HttpStatus.SC_OK)
            {
                result = new String(method.getResponseBodyAsString().getBytes("utf8"));
                System.out.println("ElecreplayOVH:"+result);
                JSONObject json = JSONObject.fromObject(result);
                if(json!=null && "100".equals(json.optString("status")))
                    result = "0";
                else{
                    //result =  json.getString("msg");
                    //result = json.optString("message");
                    result = "-444";
                }
                    
            }else
            {
                log.error("[statusCode]" + statusCode );
                throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
            }
            System.out.println("ElecreplayOVH:"+result);
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
			result = java.net.URLEncoder.encode(MsgContent,"UTF-8");//URLEncoder.encode(MsgContent,"UTF8");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
