package com.dcdzsoft.sms.impl;

import java.util.UUID;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: 阿联酋 http://www.smartsmsgateway.com/</p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author wangzl
 * @version 1.0
 */
public class MsgProxySmartSMSGateway implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxySmartSMSGateway.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	@Override
	public String sendMessage(SMSInfo smsInfo) throws EduException 
	{
		String result = "";
		if(smsInfo.CustomerMobile.startsWith("1")&&smsInfo.CustomerMobile.length()==11){
			smsInfo.CustomerMobile = smsInfo.CustomerMobile.substring(1);
		}
		if(smsInfo.CustomerMobile.startsWith("0")&& smsInfo.CustomerMobile.length()==10){//
			smsInfo.CustomerMobile = smsInfo.CustomerMobile.substring(1);
		}
		if(!smsInfo.CustomerMobile.startsWith("00971")){//国际编码
			smsInfo.CustomerMobile = "00971"+smsInfo.CustomerMobile;
		}
		//发送短信
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
        PostMethod post  = new PostMethod("http://www.smartsmsgateway.com/api/api_http.php");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//utf8在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("username", apCfg.getGatewayUser()),//decan
        						new NameValuePair("senderid", "SMS Alert"),//SMS Alert
        						new NameValuePair("password", apCfg.getGatewayPwd()),//dec01
        						new NameValuePair("to", smsInfo.CustomerMobile),
        		                new NameValuePair("text",smsInfo.MsgContent),
        		                new NameValuePair("type","unicode"),//
        		                };

        try
        {
        	 //System.out.println(msgContent);

        	 post.setRequestBody(data);
        	 int statusCode = client.executeMethod(post);

        	 if(statusCode == HttpStatus.SC_OK)
        	 {
        		 result = new String(post.getResponseBodyAsString().getBytes("utf8"));
        		 
        		 log.info("SendSMS:"+smsInfo.CustomerMobile+"["+smsInfo.PackageID+"]"+",Result="+result);
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
