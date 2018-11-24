package com.dcdzsoft.sms.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

public class MsgProxyCommzGate implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory
			.getLog(MsgProxyCommzGate.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();

	
	public String sendMessage(SMSInfo smsInfo) throws EduException {
		String result = "";

		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); // 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000); // 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);

		PostMethod post = new PostMethod("http://www.commzgate.net/gateway/SendMsg");
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");// 在头文件中设置转码
		NameValuePair[] data = {
				new NameValuePair("ID", apCfg.getGatewayUser()),
				new NameValuePair("Password", apCfg.getGatewayPwd()),
				new NameValuePair("Mobile", smsInfo.CustomerMobile),
				new NameValuePair("Type", "A"),
				new NameValuePair("Message", smsInfo.MsgContent) };

		try { // System.out.println(msgContent);

			post.setRequestBody(data);
			int statusCode = client.executeMethod(post);

			if (statusCode == HttpStatus.SC_OK) {
				result = new String(post.getResponseBodyAsString().getBytes("utf8"));

				if (StringUtils.isNotEmpty(result)&& result.trim().indexOf("01010") != -1) {
					System.out.println("sms result ok = " + result);
					result = "0";
				} else {
					System.out.println("sms result fail = " + result + ","
							+ smsInfo.MsgContent);
					result = "-222";
				}
			} else {
				log.error("[statusCode]" + statusCode);
				throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		} finally {
			post.releaseConnection();
		}

		return result;
	}
	 

	/*public String sendMessage(SMSInfo smsInfo) throws EduException {
		String result = "";

		// String gatewayURL = "http://www.commzgate.net/gateway/SendMsg";
		String gatewayURL = "https://www.commzgate.net/gateway/SendMsg";
		String[] response = new String[2]; // Array with 2 parts. Part 1 is what
											// YOU receive from CG. Part 2 is
											// what YOU send

		try {
			HttpURLConnection httpURLConnector = null;
			DataOutputStream out = null;
			BufferedReader in = null;

			URL theURL = new URL(gatewayURL + "?");
			httpURLConnector = (HttpURLConnection) theURL.openConnection();
			httpURLConnector.setRequestMethod("POST");
			httpURLConnector.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			httpURLConnector.setDoInput(true);
			httpURLConnector.setDoOutput(true);
			httpURLConnector.setReadTimeout(30000);// 30000
			httpURLConnector.connect();

			out = new DataOutputStream(httpURLConnector.getOutputStream());

			StringBuffer sb = new StringBuffer();
			//sb.append("ID=").append(URLEncoder.encode(apCfg.getGatewayUser(), "UTF-8"));
			//sb.append("&").append("Password=").append(URLEncoder.encode(apCfg.getGatewayPwd(), "UTF-8"));
			sb.append("ID=").append(apCfg.getGatewayUser());
			sb.append("&").append("Password=").append(apCfg.getGatewayPwd());
			
			sb.append("&").append("Mobile=").append(URLEncoder.encode(smsInfo.CustomerMobile, "UTF-8"));
			sb.append("&").append("Message=").append(URLEncoder.encode(smsInfo.MsgContent, "UTF-8"));
			//sb.append("&").append("Message=").append(URLEncoder.encode("Test", "UTF-8"));
			//sb.append("&").append("Type=").append(URLEncoder.encode("A", "UTF-8"));
			sb.append("&").append("Type=").append("A");

			response[1] = "Posting: " + gatewayURL + "?" + sb.toString();
			System.out.println("source = " + response[1]);

			out.writeBytes(sb.toString());
			out.close();

			in = new BufferedReader(new InputStreamReader(httpURLConnector.getInputStream()));
			response[0] = in.readLine();

			in.close();
			httpURLConnector.disconnect();

			if (StringUtils.isNotEmpty(response[0])&& response[0].trim().indexOf("01010") != -1) {
				System.out.println("sms result ok = " + response[0]);
				result = "0";
			} else {
				System.out.println("sms result fail = " + response[0]);

				result = "-222";
			}
		} catch (Exception e) {
			response[0] = "Server Error in posting message (" + e.getMessage() + ")";
			response[1] = "Unknown -- error in http post! (" + e.getMessage() + ")";

			System.out.println("send failure = " + e.getMessage());

			result = "-333";
		}

		return result;
	}*/
}
