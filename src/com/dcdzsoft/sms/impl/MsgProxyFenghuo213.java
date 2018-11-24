package com.dcdzsoft.sms.impl;



import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;



import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
/**
 * 烽火  -阿尔及利亚，手机号为10位，0开头，国家代码00213
 */
public class MsgProxyFenghuo213 implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyFenghuo213.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	//private static final String SMS_ACCOUNT = "sms-ls148311-1";
	//private static final String SMS_FROM    = "ELECREPLAY";
	//private static final String SMS_CONTENTTYPE = "text/json";
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		
		//StringBuffer url = new StringBuffer();
		
		//String Url = "http://172.30.1.204:13002/cgi-bin/sendsms?";
		String mobile = smsInfo.CustomerMobile;
        if(mobile.startsWith("0")&&smsInfo.CustomerMobile.length()==10){
            mobile = mobile.substring(1);
        }
        
		StringBuffer sb = new StringBuffer();
		sb.append("http://")
        .append(apCfg.getSmsServerIp())
        .append(":")
        .append(apCfg.getSmsServerPort())
        .append("/cgi-bin/sendsms?");
        sb.append("username=" + apCfg.getGatewayUser());
        sb.append("&password=" + apCfg.getGatewayPwd());
        if(mobile.startsWith("6")){
            sb.append("&smsc=" + "mobilis");
        }else if(mobile.startsWith("7")){
            sb.append("&smsc=" + "djezzy");
        }else{
            sb.append("&smsc=" + "ooredoo");
        }
        
        sb.append("&charset=utf8");
        sb.append("&from=").append(encoderURL("ALG Poste"));
        sb.append("&coding=2");
        
        if(mobile.length()==9){
            mobile = "+213"+mobile;
        }
		sb.append("&to=" + mobile);
		sb.append("&text=" + encoderURL(smsInfo.MsgContent));//
System.out.println("MsgProxyFenghuo213:"+smsInfo.MsgContent);
		//短信完整请求URL
        String smsurl =  sb.toString();
        System.out.println("MsgProxyFenghuo213:"+smsurl);
		HttpClient client = new HttpClient();
		
		GetMethod method = new GetMethod(smsurl);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;"); 
		method.setRequestHeader("Accept-Charset", "utf-8");
		method.setRequestHeader("Accept-Language", "en-US,en");
		method.setRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
		method.setRequestHeader("Keep-Alive", "300"); 
		method.setRequestHeader("Connection", "Keep-Alive"); 
		method.setRequestHeader("Cache-Control", "no-cache"); 
		try
        {
            int statusCode = client.executeMethod(method);
            if(statusCode == HttpStatus.SC_OK || statusCode==202 )
            {
                result = new String(method.getResponseBodyAsString().getBytes("utf8"));
                System.out.println("33333MsgProxyFenghuo213:result="+result);
                if(result!=null &&(result.startsWith("0") ||result.indexOf("success") >= 0)){
                    result = "0";
                }else{
                    //result =  json.getString("msg");
                    //result = json.optString("message");
                    result = "-444";
                }
                    
            }else
            {
                log.error("[statusCode]" + statusCode );
                System.out.println("33333MsgProxyFenghuo213:statusCode="+statusCode);
                throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
            }
            System.out.println("MsgProxyFenghuo213:"+result);
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
