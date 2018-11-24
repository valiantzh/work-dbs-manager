package com.dcdzsoft.sms.impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

import java.net.*;
import java.io.*;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MsgProxyFrenchThomasOld implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory
			.getLog(MsgProxyFrenchThomasOld.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();

	public String sendMessage(SMSInfo smsInfo) throws EduException 
	{
		// 判断手机号
		if (smsInfo.CustomerMobile.length() == 9
				&& !smsInfo.CustomerMobile.startsWith("0")) {
			smsInfo.CustomerMobile = "33" + smsInfo.CustomerMobile;
		} else if (smsInfo.CustomerMobile.length() == 10
				&& smsInfo.CustomerMobile.startsWith("0")) {
			smsInfo.CustomerMobile = "33"
					+ smsInfo.CustomerMobile.substring(1);
		}
		System.out.println("发送手机号："+smsInfo.CustomerMobile);
		log.info("发送手机号："+smsInfo.CustomerMobile);
		String result = "";
		String AK = "YkC8ZyDRS24xo4Gw";
		String AS = "FWSYVQvKOEmJZ7STHJFgMKOKVvM5vANa";
		String CK = "XK6sVVsk5hYzCmKs3FWhRSIgzPiaK6Jg";

		String ServiceName = "sms-xx000000-1";
		String METHOD = "POST";
		try {
			URL QUERY = new URL("https://eu.api.ovh.com/1.0/sms/" + ServiceName
					+ "/jobs/");
			String BODY = "{\"receivers\":[\""+smsInfo.CustomerMobile+"\"],\"message\":\""+smsInfo.MsgContent+"\",\"priority\":\"high\",\"senderForResponse\":true}";

			long TSTAMP = new Date().getTime() / 1000;

			// Création de la signature
			String toSign = AS + "+" + CK + "+" + METHOD + "+" + QUERY + "+"
					+ BODY + "+" + TSTAMP;
			String signature = "$1$" + HashSHA1(toSign);
			System.out.println(signature);

			HttpURLConnection req = (HttpURLConnection) QUERY.openConnection();
			req.setRequestMethod(METHOD);
			req.setRequestProperty("Content-Type", "application/json");
			req.setRequestProperty("X-Ovh-Application", AK);
			req.setRequestProperty("X-Ovh-Consumer", CK);
			req.setRequestProperty("X-Ovh-Signature", signature);
			req.setRequestProperty("X-Ovh-Timestamp", "" + TSTAMP);

			if (!BODY.isEmpty()) {
				req.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(
						req.getOutputStream());
				wr.writeBytes(BODY);
				wr.flush();
				wr.close();
			}
			String inputLine;
			BufferedReader in;
			int responseCode = req.getResponseCode();
			if (responseCode == 200) {
				// Récupération du résultat de l'appel
				in = new BufferedReader(new InputStreamReader(
						req.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(
						req.getErrorStream()));
			}
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Affichage du résultat
			result = response.toString();
			System.out.println(result);			
			log.info("发送短信返回："+result);
		} catch (MalformedURLException e) {
			final String errmsg = "MalformedURLException: " + e;
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		} catch (IOException e) {
			final String errmsg = "IOException: " + e;
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
		return result;
	}

	public static String HashSHA1(String text) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] sha1hash = new byte[40];
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			sha1hash = md.digest();
			return convertToHex(sha1hash);
		} catch (NoSuchAlgorithmException e) {
			final String errmsg = "NoSuchAlgorithmException: " + text + " " + e;
			return errmsg;
		} catch (UnsupportedEncodingException e) {
			final String errmsg = "UnsupportedEncodingException: " + text + " "
					+ e;
			return errmsg;
		}
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

}
