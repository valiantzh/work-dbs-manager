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
import com.dcdzsoft.util.DateUtils;
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
public class MsgProxyTaiHe implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyTaiHe.class);
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
            String[] msgContent = null;
            
            JSONArray msgList = new JSONArray();
            JSONObject msgObj = new JSONObject();
            
            switch(smsInfo.MsgType){
            case SMSInfo.MSG_TYPE_DELIVERY:
                msgContent = new String[2];
                StringBuffer msg =new StringBuffer();
                msg.append(smsInfo.TerminalName).append(smsInfo.BoxNo).append("号箱");//smsInfo.Location
                msgContent[0] = java.net.URLEncoder.encode(msg.toString(),"utf-8");//
                msgContent[1] = java.net.URLEncoder.encode(smsInfo.OpenBoxKey,"utf-8");//
                msgObj.put("templateId", "32");
                break;
            case SMSInfo.MSG_TYPE_REMINDER:
                /*msgContent = new String[3];
                msgContent[0] = java.net.URLEncoder.encode(smsInfo.OpenBoxKey,"utf-8");//
                msgContent[1] = java.net.URLEncoder.encode(smsInfo.Location,"utf-8");//
                msgContent[2] = java.net.URLEncoder.encode(smsInfo.msgTel,"utf-8");//
                msgObj.put("templateId", "33");*/
                msgContent = new String[5];
                msgContent[0] = java.net.URLEncoder.encode(""+DateUtils.diffHour(smsInfo.sysDateTime, smsInfo.StoredTime),"utf-8");//
                msgContent[1] = java.net.URLEncoder.encode("凌晨0:00","utf-8");//
                msgContent[2] = java.net.URLEncoder.encode(smsInfo.OpenBoxKey,"utf-8");//
                msgContent[3] = java.net.URLEncoder.encode(smsInfo.Location,"utf-8");//
                msgContent[4] = java.net.URLEncoder.encode(smsInfo.msgTel,"utf-8");//
                msgObj.put("templateId", "140");
                break;
            case SMSInfo.MSG_TYPE_EXPIRED:
                msgContent = new String[4];
                
                msgContent[0] = java.net.URLEncoder.encode(smsInfo.Location,"utf-8");//
                msgContent[1] = java.net.URLEncoder.encode(smsInfo.PackageID,"utf-8");//
                msgContent[2] = "";//
                msgContent[3] = java.net.URLEncoder.encode(smsInfo.msgTel,"utf-8");//
                msgObj.put("templateId", "34");
                break;
            case SMSInfo.MSG_TYPE_TAKEDOUT:
                msgContent = new String[2];
                msgContent[0] = java.net.URLEncoder.encode(smsInfo.PackageID,"utf-8");//
                msgContent[1] = java.net.URLEncoder.encode(smsInfo.msgTel,"utf-8");//
                msgObj.put("templateId", "35");
                break;
            case SMSInfo.MSG_TYPE_REGISTER:
                msgContent = new String[1];
                msgContent[0] = java.net.URLEncoder.encode(smsInfo.DynamicCode,"utf-8");//
                msgObj.put("templateId", "36");
                break;
            }
            if(msgContent==null){
                throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
            }
            msgObj.put("mobiles", smsInfo.CustomerMobile);
            msgObj.put("contentArr", msgContent);
            msgObj.put("sendTime", "");
            msgList.add(msgObj);
            JSONObject msgBody = new JSONObject();
            msgBody.put("username", apCfg.getGatewayUser());
            msgBody.put("password", apCfg.getGatewayPwd());
            msgBody.put("subID", "");
            msgBody.put("msgList", msgList);
            //System.out.println("msgBody:"+msgBody.toString());
            //System.out.println("msgContent[0]:"+java.net.URLDecoder.decode(msgContent[0],"utf-8"));

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
