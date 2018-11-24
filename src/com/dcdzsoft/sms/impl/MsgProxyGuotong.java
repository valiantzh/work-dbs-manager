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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

public class MsgProxyGuotong implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyGuotong.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static String postUrl = "";
	static
	{
		postUrl = "http://" + apCfg.getSmsServerIp() + ":" + apCfg.getSmsServerPort() + "/guotongservice";
		
		//postUrl = "http://" + apCfg.getDbsAipsIp() + ":" + apCfg.getDbsAipsPort() + "/guotongservice";
	}
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		//注册短信
		if(smsInfo.MsgType == SMSInfo.MSG_TYPE_REGISTER)
			return this.doSendRegisterMessage(smsInfo);
		
		//投递短信
		String result = "";
		
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
        		                new NameValuePair("ACTION", "600071"),
        		                new NameValuePair("MsgType",String.valueOf(smsInfo.MsgType)),
        		                new NameValuePair("MsgTel",ControlParam.getInstance().msgTel),
        		                new NameValuePair("ExpiredDays",ControlParam.getInstance().recoveryTimeout),
        		                new NameValuePair("TerminalNo",String.valueOf(smsInfo.TerminalNo)),
        		                new NameValuePair("Location",smsInfo.Location),
        		                new NameValuePair("BoxNo",smsInfo.BoxNo),
        		                new NameValuePair("PackageID",smsInfo.PackageID),
        		                new NameValuePair("CustomerMobile",smsInfo.CustomerMobile),
        		                new NameValuePair("OpenBoxKey",smsInfo.OpenBoxKey),
        		                new NameValuePair("PostmanID",smsInfo.PostmanID)
        		                };

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
	
	private String doSendRegisterMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		
		String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";

		HttpClient client = new HttpClient();
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