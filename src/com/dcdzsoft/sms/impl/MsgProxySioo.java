package com.dcdzsoft.sms.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: 上海希奥</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zhengxy
 * @version 1.0
 */
public class MsgProxySioo implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxySioo.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		
		String result = "";
		
		String Url = "http://sms.10690221.com:9011/hy/";

		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
		
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		String message = smsInfo.MsgContent;   //内容

        try {
            message=URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
		String auth = SecurityUtils.md5("xacs"+apCfg.getGatewayPwd());
        NameValuePair[] data ={ new NameValuePair("uid", apCfg.getGatewayUser()),
                                new NameValuePair("auth", auth),
        		                new NameValuePair("expid", "0"),
        		                new NameValuePair("mobile",smsInfo.CustomerMobile),
        		                new NameValuePair("encode","utf-8"),
        		                new NameValuePair("msg",message)};
        
        method.setRequestBody(data);

        try
        {
        	int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                String code = method.getResponseBodyAsString();
                System.out.println("code="+code);
                if(code.startsWith("0")){
                    result = "0";
                }else{
                    result = "-333";
                }
            }else{
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
