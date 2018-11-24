package com.dcdzsoft.sms.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: 东信短信接口</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zhengxy
 * @version 1.0
 */
public class MsgProxyEastcom implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyEastcom.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static final String APPLICATION_JSON = "application/json";
    
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
        PostMethod post = new PostMethod("http://120.55.101.39:8090/smsplatform/sms/sendMsg");
        post.addRequestHeader("Content-Type","application/json; charset=utf-8");//在头文件中设置转码
        try
        {
            //smsInfo.MsgContent = "Dear Customer,\nYour shipment \nReady to collect at: \nPIN code: 123456";
            System.out.println(smsInfo.MsgContent);
            String[] msgContent = new String[1];
            msgContent[0] = java.net.URLEncoder.encode(smsInfo.MsgContent,"utf-8");//
            JSONArray msgList = new JSONArray();
            JSONObject msgObj = new JSONObject();
            msgObj.put("mobiles", smsInfo.CustomerMobile);
            msgObj.put("templateId", "3");
            msgObj.put("contentArr", msgContent);
            msgObj.put("sendTime", "");
            msgList.add(msgObj);
            JSONObject msgBody = new JSONObject();
            msgBody.put("username", apCfg.getGatewayUser());
            msgBody.put("password", apCfg.getGatewayPwd());
            msgBody.put("subID", "");
            msgBody.put("msgList", msgList);
            System.out.println("msgBody:"+msgBody.toString());
            System.out.println("msgContent[0]:"+java.net.URLDecoder.decode(msgContent[0],"utf-8"));

            RequestEntity requestEntity = new StringRequestEntity(msgBody.toString(), APPLICATION_JSON, "UTF-8");
            post.setRequestEntity(requestEntity);
            
            int statusCode = client.executeMethod(post);

            if(statusCode == HttpStatus.SC_OK){
        		result = new String(post.getResponseBodyAsString().getBytes("utf8"));
        		
        		System.out.println("result:"+result);
        		JSONObject respJson = JSONObject.fromObject(result);
        		if(respJson!=null){
        		    String code = respJson.optString("code");
        		    if(!"0".equals(code)){
        		        result = "-333";
        		    }
        		}else{
        		    result = "-333";
        		}
        		//大于零短信数量
        		System.out.println("sms result = " + result);
        	}else{
        	    result = new String(post.getResponseBodyAsString().getBytes("utf8"));
                
                System.out.println("result:"+result);
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
