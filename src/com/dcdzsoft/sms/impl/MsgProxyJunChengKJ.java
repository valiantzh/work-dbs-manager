package com.dcdzsoft.sms.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;

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
 * <p>Description: 君诚科技</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zhengxy
 * @version 1.0
 */
public class MsgProxyJunChengKJ implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyJunChengKJ.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		
		String result = "";
		String Url = "http://www1.jc-chn.cn/";

		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
		
		
		String message = smsInfo.MsgContent;   //内容

        try {
            message=URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String password = SecurityUtils.md5(apCfg.getGatewayUser()+SecurityUtils.md5(apCfg.getGatewayPwd()));
        
        StringBuilder params = new StringBuilder();
        params.append("username=").append(apCfg.getGatewayUser())
                .append("&password=").append(password)
                .append("&mobile=").append(smsInfo.CustomerMobile)
                .append("&content=").append(message)
                .append("&dstime=").append("")
                .append("&ext=").append("")
                .append("&msgid=").append("")                
                .append("&msgfmt=").append("utf-8");
        URLConnection conn = null;
        try
        {
            URL realUrl = new URL(Url + "smsSend.do");
            conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
            out.write(params.toString());
            out.flush();

            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            System.out.println(smsInfo.MsgContent+",result="+result);
            log.info(smsInfo.MsgContent+",result="+result);
            
            String strCode = result.split("\n")[0];
            long code = 0;
            code = Long.valueOf(strCode);
            if (code > 0) {//成功提交
                result="0";
            }else{
                result = "-333";
            }
        }catch(Exception e)
        {
        	e.printStackTrace();
        	throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        }
        finally
        {
        }
        
        
        return result;
	}
	
}
