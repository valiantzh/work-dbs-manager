package com.dcdzsoft.sms.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
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
 * <p>Description: 秒嘀科技短信产品接口-百隆达快立方</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zhengxy
 * @version 1.0
 */
public class MsgProxyMiaodiKuaiLiFang implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyMiaodiKuaiLiFang.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static final String APPLICATION_JSON = "application/json";
    /**
     * url前半部分
     */
    private static final String BASE_URL = "https://api.miaodiyun.com/20150822";
    
    //private static final String BASE_URL = "http://127.0.0.1:8080/distributor/20150822";

    /**
     * 开发者注册后系统自动生成的账号，可在官网登录后查看
     */
    private static String ACCOUNT_SID = "";
    /**
     * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
     */
    private static String AUTH_TOKEN = "";
    /**
     * 响应数据类型, JSON或XML
     */
    private static final String RESP_DATA_TYPE = "json";
    static
    {
        ACCOUNT_SID = apCfg.getGatewayUser();
        AUTH_TOKEN = apCfg.getGatewayPwd();
    }
    /**
     * post请求
     * 
     * @param url
     *            功能和操作
     * @param body
     *            要post的数据
     * @return
     * @throws IOException
     */
    private static String post(String url, String body)
    {
        System.out.println("url:" + System.lineSeparator() + url);
        System.out.println("body:" + System.lineSeparator() + body);

        String result = "";
        try
        {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);

            // 提交数据
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 读取返回数据
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null)
            {
                if (firstLine)
                {
                    firstLine = false;
                } else
                {
                    result += System.lineSeparator();
                }
                result += line;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
		String result = "";
		//System.out.println(smsInfo.MsgContent);
		
        try
        {
            result =  _sendIndustrySMS(smsInfo.MsgContent,smsInfo.CustomerMobile);
            
            JSONObject respJson = JSONObject.fromObject(result);
            if(respJson!=null){
                String code = respJson.optString("respCode");
                if(!"00000".equals(code)){
                    result = "-333";
                }
            }else{
                result = "-333";
            }
            System.out.println("result:"+result);
        }catch(Exception e)
        {
        	e.printStackTrace();
        	throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        }
        
        return result;
	}
	
	/**
	 * 验证码通知短信接口
	 * @param smsContent
	 * @param to
	 * @return
	 */
	private static String _sendIndustrySMS(String smsContent, String to){
	    String operation = "/industrySMS/sendSMS";
	    String accountSid = ACCOUNT_SID;
	    String url = BASE_URL + operation;
        String body = "accountSid=" + accountSid +"&smsContent="+smsContent+"&to=" + to 
                + createCommonParam();
        // 提交请求
        String result = post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);
        return result;
	}
	/**
     * 短信邮验证码通知短信接口
     * @param smsContent
     * @param to
     * @return
     */
    private static String _sendIndustryEmailSMS(String smsContent, String to){
        String operation = "/industryEmailSMS/sendEmailSMS";
        String accountSid = ACCOUNT_SID;
        String url = BASE_URL + operation;
        String body = "accountSid=" + accountSid +
                "&smsContent="+smsContent+"&to=" + to 
                + createCommonParam();
        // 提交请求
        String result = post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);
        return result;
    }
	/**
     * @param args
     */
    public static void main(String[] args) {
        // 获取开发者账号信息
        //getAccountInfo();
        // 语音验证码
        // VoiceCode.execute();
        //新接口行业纯短信
        //_sendIndustrySMS("【快立方】您的包裹:E123456789已成功投递，领取密码:123456.","13656637085,18306039396");
        
        //新接口行业邮件短信  您的包裹:E123456789,领取密码:123456 
        //_sendIndustryEmailSMS("【秒嘀科技】您的包裹:E123456789,验证码是345678，30分钟输入有效。","13656637085");
        
        //新接口营销纯短信
        //affMarkSMS.execute();
        
        //营销邮件短信
        //affMarkEmailSMS.execute();
        
    }
    /**
     * 获取开发者账号信息
     * @return
     */
    private static String getAccountInfo(){
        
     // url中20150822之后的部分
        String operation = "/query/accountInfo";
        // 参数详述请参考http://www.qingmayun.com/document.html
        String accountSid = ACCOUNT_SID;
        
        String url = BASE_URL + operation;
        String body = "accountSid=" + accountSid + createCommonParam();
        // 提交请求
        String result = post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);
        return result;
    }
	/**
     * 构造通用参数timestamp、sig和respDataType
     * 
     * @return
     */
    private static String createCommonParam()
    {
        // 时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());

        // 签名
        String sig = DigestUtils.md5Hex(ACCOUNT_SID + AUTH_TOKEN + timestamp);

        return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;
    }

    
    /**
     * 回调测试工具方法
     * 
     * @param url
     * @param reqStr
     * @return
     */
    private static String postHuiDiao(String url, String body)
    {
        String result = "";
        try
        {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);

            // 提交数据
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 读取返回数据
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null)
            {
                if (firstLine)
                {
                    firstLine = false;
                } else
                {
                    result += System.lineSeparator();
                }
                result += line;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
