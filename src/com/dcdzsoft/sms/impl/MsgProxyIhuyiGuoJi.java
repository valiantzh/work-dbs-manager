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
//接口类型：互亿无线国际短信接口。
//账户注册：请通过该地址开通账户http://sms.ihuyi.com/register.html
//注意事项：
//（1）调试期间，请仔细阅读接口文档；
//（2）请使用APIID（查看APIID请登录用户中心->国际短信->帐户及签名设置->APIID）及 APIkey来调用接口；
//（3）该代码仅供接入互亿无线短信接口参考使用，客户可根据实际需要自行编写；

public class MsgProxyIhuyiGuoJi implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyIhuyiGuoJi.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	private static String Url = "http://api.isms.ihuyi.com/webservice/isms.php?method=Submit";
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";

		StringBuffer mobileBuff = new StringBuffer(25);
		if(StringUtils.isNotEmpty(smsInfo.OfBureau)){//国际版 OfBureau=国家号
			mobileBuff.append(smsInfo.OfBureau).append(" ");
		}else if(StringUtils.isNotEmpty(apCfg.getSmsMobilePrefix())){//
			mobileBuff.append(apCfg.getSmsMobilePrefix()).append(" ");//国家号+空格
		}
		mobileBuff.append(smsInfo.CustomerMobile);//国家号+空格+手机号0014165674636
		log.info("sendMessage "+smsInfo.PackageID+","+smsInfo.CustomerMobile+","+smsInfo.OfBureau);
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
		String mobile = mobileBuff.toString();
		
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

        NameValuePair[] data ={ new NameValuePair("account", apCfg.getGatewayUser()),//
        		                new NameValuePair("password", apCfg.getGatewayPwd()),////查看密码请登录用户中心->国际短信->帐户参数设置->APIKEY
        		                new NameValuePair("mobile", mobile),
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
			log.debug(mobile+","+smsInfo.MsgContent+","+code+","+msg+","+smsid);
			System.out.println(mobile+","+smsInfo.MsgContent+","+code+","+msg+","+smsid);
			
			result = SubmitResult;
			log.info("MsgProxyIhuyiGuoJi-->result:" +SubmitResult);
			
        }catch(Exception e)
        {
        	//e.printStackTrace();
        	throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        }
        finally
        {
        	method.releaseConnection();
        }
        
        
        return result;
	}
}
