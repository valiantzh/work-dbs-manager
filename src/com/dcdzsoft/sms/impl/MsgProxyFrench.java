package com.dcdzsoft.sms.impl;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SslUtils;

public class MsgProxyFrench implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyFrench.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	// 错误代码
	public final static int OK = 200;// Everything went well!
	public final static int ERROR_NOAPIKEY = 1;// API密钥是必需的，并没有提供,API key is necessary and has not been provided
	public final static int ERROR_PHONENUMBER = 2;// 需要手机号码,Mobile phone number is required
	public final static int ERROR_MESSAGEID = 3;   // 消息ID是必需的，Message ID is required
	public final static int ERROR_UNRECOGNIZED = 4; // 无法识别所需的SMS，Unable to
													// identify the required SMS
	public final static int ERROR_CORRECT = 9;
	// 至少有一个参数不正确，发件人的名字不在3到11个字符之间。
	// 手机号码无效。
	// 如果scheduledDeliveryDate已经：
	// 设置日期（年/月/日）在当前日期之前。
	// 设置小时尚未设置。
	// 设置分钟没有设置。
	public final static int ERROR_APIKEY = 10;      // API密钥无效,API key is not valid
	public final static int ERROR_BALANCE = 11;     // 您的信用余额不足以发送短信,Your credit balance is not sufficient to send SMS
	
	public String sendMessage(SMSInfo smsInfo) throws EduException {
		//判断手机号
		if(smsInfo.CustomerMobile.length()==9 && !smsInfo.CustomerMobile.startsWith("0")){
			smsInfo.CustomerMobile = "0"+smsInfo.CustomerMobile;
		}else if(smsInfo.CustomerMobile.length()==10 && smsInfo.CustomerMobile.startsWith("0")){
			smsInfo.CustomerMobile = "0033"+smsInfo.CustomerMobile.substring(1);
		}
		String result = "";		
		PrintWriter out = null;
		HttpURLConnection connection = null;
		StringBuffer respBuff = new StringBuffer();
		try {
			String[] msgContent = new String[1];
			msgContent[0] = java.net.URLEncoder.encode(smsInfo.MsgContent,"utf-8");
			JSONObject msgBody = new JSONObject();
			msgBody.put("apiKey", apCfg.getSmsServerPort());
			msgBody.put("phoneNumbers", smsInfo.CustomerMobile);
			msgBody.put("message", smsInfo.MsgContent);
			System.out.println("msgBody:" + msgBody.toString());
			System.out.println("msgContent[0]:"	+ java.net.URLDecoder.decode(msgContent[0], "utf-8"));

			connection = getConnection("https://api.smspartner.fr/v1/send");
			connection.setRequestMethod("POST");
			out = new PrintWriter(connection.getOutputStream());
			out.print(msgBody.toString());
			out.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));// 流
			String line;
			while ((line = reader.readLine()) != null) {
				respBuff.append(line);
			}
			result = respBuff.toString();
			// 返回數據：{"success":true,"code":200,"message_id":1223109,"nb_sms":1,"cost":0.045,"currency":"EUR"}
			// 錯誤的返回：{"success":false,"code":55,"message":"Aucun prix par default n'a \u00e9t\u00e9 trouv\u00e9 pour les num\u00e9ros suivant (15957135105)","errors":[{"success":false,"code":"9","message":"Le t\u00e9l\u00e9phone (15957135105) n'est pas \u00eatre un num\u00e9ro de mobile"}]}

			System.out.println("result:" + result);
			JSONObject respJson = JSONObject.fromObject(result);
			if (respJson != null) {
				boolean success = respJson.optBoolean("success");
				int code = respJson.optInt("code");
				if (success && code == 200) {
					result = respJson.optString("message_id");// 正常返回message_id
				} else {
					log.error("[statusCode]" + code + "," + respJson.optString("error"));//错误返回整条信息
					throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
				}
			} else {
				result = "SMSResult is null";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
			// isNeedSendAgain = true;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (out != null) {
				out.close();
			}
		}
		return result;
	}
	public String messageStatus(SMSInfo smsInfo) throws EduException {
		//判断手机号
		if(smsInfo.CustomerMobile.length()==9 && !smsInfo.CustomerMobile.startsWith("0")){
			smsInfo.CustomerMobile = "0"+smsInfo.CustomerMobile;
		}else if(smsInfo.CustomerMobile.length()==10 && smsInfo.CustomerMobile.startsWith("0")){
			smsInfo.CustomerMobile = "0033"+smsInfo.CustomerMobile.substring(1);
		}
		
		String result = "";		
		PrintWriter out = null;
		HttpURLConnection connection = null;
		StringBuffer respBuff = new StringBuffer();
		try {
			String[] msgContent = new String[1];
			msgContent[0] = java.net.URLEncoder.encode(smsInfo.MsgContent,"utf-8");
			JSONObject msgBody = new JSONObject();
			msgBody.put("apiKey", apCfg.getSmsServerPort());
			msgBody.put("phoneNumbers", smsInfo.CustomerMobile);
			msgBody.put("message", smsInfo.MsgContent);
			System.out.println("msgBody:" + msgBody.toString());
			System.out.println("msgContent[0]:"	+ java.net.URLDecoder.decode(msgContent[0], "utf-8"));
			//https://api.smspartner.fr/v1/message-status?
			connection = getConnection("https://api.smspartner.fr/v1/send");
			connection.setRequestMethod("POST");
			out = new PrintWriter(connection.getOutputStream());
			out.print(msgBody.toString());
			out.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));// 流
			String line;
			while ((line = reader.readLine()) != null) {
				respBuff.append(line);
			}
			result = respBuff.toString();
			// 返回數據：{"success":true,"code":200,"message_id":1223109,"nb_sms":1,"cost":0.045,"currency":"EUR"}
			// 錯誤的返回：{"success":false,"code":55,"message":"Aucun prix par default n'a \u00e9t\u00e9 trouv\u00e9 pour les num\u00e9ros suivant (15957135105)","errors":[{"success":false,"code":"9","message":"Le t\u00e9l\u00e9phone (15957135105) n'est pas \u00eatre un num\u00e9ro de mobile"}]}

			System.out.println("result:" + result);
			JSONObject respJson = JSONObject.fromObject(result);
			if (respJson != null) {
				boolean success = respJson.optBoolean("success");
				int code = respJson.optInt("code");
				if (success && code == 200) {
					result = respJson.optString("message_id");// 正常返回message_id
				} else {
					log.error("[statusCode]" + code + "," + respJson.optString("error"));//错误返回整条信息
					throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
				}
			} else {
				result = "SMSResult is null";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
			// isNeedSendAgain = true;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (out != null) {
				out.close();
			}
		}
		return result;
	}
	private static HttpURLConnection getConnection(String url)
			throws IOException {
		URL postUrl = new URL(url);// stringת��URL����
		if ("https".equalsIgnoreCase(postUrl.getProtocol())) {
			try {
				SslUtils.ignoreSsl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();//
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Charset", "utf-8");
		// connection.setRequestProperty("Accept",
		// "application/x-www-form-urlencoded");
		connection.setRequestProperty("ContentType","application/x-www-form-urlencoded;charset=utf-8");// x-www-form-urlencoded
		return connection;
	}

}
