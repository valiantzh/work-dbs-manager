package com.dcdzsoft.guotong;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.Constant;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.constant.SysDict;
import com.dcdzsoft.dto.function.TBTerminal;
import com.dcdzsoft.outerproxy.OuterProxy;
import com.dcdzsoft.outerproxy.SendInfo;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.JsonUtils;

import net.sf.json.JSONObject;

public class HttpClient4Guotong {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(HttpClient4Guotong.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	private static String postUrl = "";
	public static SimpleDateFormat sdf = null;
	public static SimpleDateFormat sdf1 = null;
	
	private static OuterProxy outerSender = null;
	static
	{
		postUrl = "http://" + apCfg.getDbsAipsIp() + ":" + apCfg.getDbsAipsPort() + "/guotongservice";
		
		
		System.out.println("HttpClient4Guotong发送到运营商地址"+postUrl);
		sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getDefault());
		
		sdf1 = new SimpleDateFormat("yyyy.MM.dd");
		sdf1.setTimeZone(TimeZone.getDefault());
		
		if(StringUtils.isNotEmpty(apCfg.getOutProxy())){
            try {
                System.out.println("OutProxy:"+apCfg.getOutProxy());
                Class outerClass = Class.forName("com.dcdzsoft.outerproxy."+apCfg.getOutProxy());
                outerSender = (OuterProxy)outerClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 发送投递反馈信息
	 * @param smsInfo
	 * @return
	 */
	public static int doSentDeliveryInfo(SMSInfo smsInfo)
	{
		String isSendPartner = ControlParam.getInstance().getSendToPartner();
		//System.out.println("isSendPartner="+isSendPartner+","+apCfg.getOutProxy()+","+outerSender);

		if(Constant.PARTNER_NAME_HAIER.equals(isSendPartner)){
    		 return _doSendDeliveryInfo4Haier(smsInfo);
    	}else{
            if(outerSender != null){
                return outerSender.sendDeliveryInfo(smsInfo);
            }else{
                return _doSendDeliveryInfo4Aips(smsInfo);
            }
            
    	}
	}
	
	/**
	 * 发送投递反馈信息
	 * @param smsInfo
	 * @return
	 */
	private static int _doSendDeliveryInfo4Aips(SMSInfo smsInfo)
	{
		int result = 0;
		
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
        PostMethod post = new PostMethod(postUrl);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        
        if(SysDict.PACKAGE_STATUS_NORMAL.equals(smsInfo.PackageStatus)){
            smsInfo.SendBZ = "P1"; //包裹已投箱
        }
        else if(SysDict.PACKAGE_STATUS_LOCKED.equals(smsInfo.PackageStatus)||
                SysDict.PACKAGE_STATUS_TIMEOUT.equals(smsInfo.PackageStatus)){
            smsInfo.SendBZ = "P4"; //包裹逾期或锁定
        }else if(SysDict.PACKAGE_STATUS_OUT4MANAGER.equals(smsInfo.PackageStatus) ||
        		SysDict.PACKAGE_STATUS_OUTNORMAL.equals(smsInfo.PackageStatus))
        {
            smsInfo.SendBZ = "P2"; //用户已领取
        }
        else
        {
            smsInfo.SendBZ = "P3"; //包裹逾期未领,投递员收回
        }
        
		try
        {
		    NameValuePair[] data = SendInfo.toNameValuePair(Constant.ACTION_SENT_DELIVERY, smsInfo);
		    
            log.debug(postUrl+","+smsInfo.PackageID+","+smsInfo.CustomerMobile+",bz="+smsInfo.SendBZ);
            

        	 post.setRequestBody(data);
        	 int statusCode = client.executeMethod(post);

        	 if(statusCode == HttpStatus.SC_OK)
        	 {
        		 String resultMsg = post.getResponseBodyAsString();
        		 
        		 resultMsg = StringUtils.trim(resultMsg);
        		 if(StringUtils.isNotEmpty(resultMsg) && resultMsg.startsWith("{") && resultMsg.endsWith("}"))
        		 {
        			 JSONObject jsonObject = JSONObject.fromObject(resultMsg);
        			 
        			 smsInfo.OfLogisticsID = JsonUtils.jsonStringValue(jsonObject, "WLID");
        			 smsInfo.OfLogisticsName = JsonUtils.jsonStringValue(jsonObject, "WLMC");
        			 smsInfo.StaOrderID = JsonUtils.jsonStringValue(jsonObject, "ORDERID");
        			 
        			 //包裹逾期或锁定状态反馈，获取新的逾期时间
        			 if("P4".equalsIgnoreCase(smsInfo.SendBZ)){
        			     smsInfo.result  = JsonUtils.jsonStringValue(jsonObject, "result");
                         smsInfo.NewStatus  = JsonUtils.jsonStringValue(jsonObject, "NewStatus");
                         String newExpiredTime = JsonUtils.jsonStringValue(jsonObject, "ExpiredTime");
                         if(StringUtils.isNotEmpty(newExpiredTime)){
                             smsInfo.ExpiredTime  = DateUtils.stringToTimestamp(newExpiredTime);
                         }
        			 }
        		 }else{
	        		 if(!resultMsg.equals("0")&& !resultMsg.startsWith("F0"))
	        		 {
	        			 log.error("[sentDeliveryInfo error0] = " + resultMsg);	
	        			 
	        			 if("-11".equals(resultMsg) || "-12".equals(resultMsg))
	        				 result = -1;
	        			 else
	        				 result = 1;
	        		 }
        		 }
        	 }else
        	 {
        		 log.error("[sentDeliveryInfo statusCode] = " + statusCode);
        		 
        		 result = -1;
        	 }
        }catch(Exception e)
        {
        	log.error("[sentDeliveryInfo error1] = " + e.getMessage());
        	
        	result = -1;
        }
        finally
        {
        	post.releaseConnection();
        }
        
		return result;
	}
	
	
	/**
	 * 发送投递反馈信息(海尔)
	 * @param smsInfo
	 * @return
	 */
	private static int _doSendDeliveryInfo4Haier(SMSInfo smsInfo)
	{
		int result = 0;
		
		HashMap<String,String> paramMap = new HashMap<String,String>();
		
        if(SysDict.PACKAGE_STATUS_NORMAL.equals(smsInfo.PackageStatus))
        {
        	paramMap.put("customerPhone", smsInfo.CustomerMobile);
        	paramMap.put("couriesPhone", smsInfo.PostmanID);
        	paramMap.put("openKey", smsInfo.OpenBoxKey);
        	paramMap.put("orderNo", smsInfo.PackageID);
        	paramMap.put("guiziNo", smsInfo.TerminalNo);
        	paramMap.put("comNo", smsInfo.CompanyID);
        	paramMap.put("gladEyeKey", encodeUrl("rO0ABXQAD1YawoQ8w5h2QsK8ekYrOA=="));
        	
        	result = sendHaierRequest("storePackage.json",paramMap);
        }else if(SysDict.PACKAGE_STATUS_OUT4POSTMAN.equals(smsInfo.PackageStatus)
        		||SysDict.PACKAGE_STATUS_OUTEXCEPTION.equals(smsInfo.PackageStatus)
        		||SysDict.PACKAGE_STATUS_OUTEXPIRED.equals(smsInfo.PackageStatus))
        {
        	paramMap.put("customerPhone", smsInfo.CustomerMobile);
        	paramMap.put("couriesPhone", smsInfo.PostmanID);
        	paramMap.put("orderNo", smsInfo.PackageID);
        	paramMap.put("guiziNo", smsInfo.TerminalNo);
        	paramMap.put("comNo", smsInfo.CompanyID);
        	paramMap.put("gladEyeKey", encodeUrl("rO0ABXQAD1YawoQ8w5h2QsK8ekYrOA=="));
        	
        	result = sendHaierRequest("postManTakeUp.json",paramMap);
        }
        
      
		return result;
	}
	
	
	/**
	 * 发送柜体注册信息
	 * @param terminal
	 * @param GXBZ
	 * @return
	 */
	public static String doSentTerminalInfo(TBTerminal terminal,String GXBZ)
	{
		String isSendPartner = ControlParam.getInstance().getSendToPartner();
    	if(Constant.PARTNER_NAME_HAIER.equals(isSendPartner)){
    		 return "0";
    	}else{
    	    if(outerSender != null){
    	        return "0";
    	    }else{
    	        return _doSentTerminalInfo4Apis(terminal,GXBZ);
    	    }
    		
    	}
	}
	
	/**
	 * 发送柜体注册信息4国通
	 * @param terminal
	 * @param GXBZ
	 * @return
	 */
	private static String _doSentTerminalInfo4Apis(TBTerminal terminal,String GXBZ)
	{
		String result = "";
		
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
        PostMethod post = new PostMethod(postUrl);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        
        NameValuePair[] data ={ new NameValuePair("ACTION", Constant.ACTION_SENT_TERMINAL),//"600073"
        		                new NameValuePair("TERMINALNO", terminal.TerminalNo),
        		                new NameValuePair("MBDEVICENO", terminal.MBDeviceNo),
        		                new NameValuePair("DEPARTID", terminal.DepartmentID),
        		                new NameValuePair("LOCATION",terminal.Location),
        		                new NameValuePair("TNAME",terminal.TerminalName),
        		                new NameValuePair("BOXNUM",String.valueOf(terminal.BoxNum)),
        		                new NameValuePair("TDJH",terminal.OfBureau),
        		                new NameValuePair("CDATE",sdf1.format(terminal.CreateTime)),
        		                new NameValuePair("LOCATIONTYPE",terminal.Remark),//LocationType
        		                new NameValuePair("TSTATUS",terminal.TerminalStatus),//TerminalStatus
        		                new NameValuePair("DESKNUM",String.valueOf(terminal.DeskNum)),//DeskNum
        		                new NameValuePair("LAT",String.valueOf(terminal.Latitude)),//Latitude
        		                new NameValuePair("LON",String.valueOf(terminal.Longitude)),//Longitude
        		                new NameValuePair("MODEL",String.valueOf(terminal.Model)),//Model
        		                new NameValuePair("BRAND",String.valueOf(terminal.Brand)),//Brand 品牌，制造商
        		                new NameValuePair("SNAME","小东东"),//客服人员名称
        		                new NameValuePair("SPHONE",ControlParam.getInstance().getServiceTel()),//客服电话
        		                new NameValuePair("GXBZ",GXBZ)};
		
        try
        {
        	 //System.out.println(msgContent);

        	 post.setRequestBody(data);
        	 int statusCode = client.executeMethod(post);

        	 if(statusCode == HttpStatus.SC_OK)
        	 {
        		 result = post.getResponseBodyAsString();
        		 result = StringUtils.trim(result);
        		 
        		 if(!result.equals("0")&& !result.startsWith("F0"))
        		 {
        			 log.error("[sentTerminalInfo error0] = " + result);
        		 }
        	 }else
        	 {
        		 log.error("[sentTerminalInfo statusCode] = " + statusCode);
        		 
        		 result = String.valueOf(statusCode);
        	 }
        }catch(Exception e)
        {
        	log.error("[sentTerminalInfo error1] = " + e.getMessage());
        	
        	result = e.getMessage();
        }
        finally
        {
        	post.releaseConnection();
        }
        
		return result;
	}
	/**
     * 发送在线柜体列表信息
     * @param terminalNoList  在线设备编号列表
     * @return
     */
    public static String doSentOnlineTerminals(String terminalNoList)
    {
        String isSendPartner = ControlParam.getInstance().getSendToPartner();
        if(Constant.PARTNER_NAME_HAIER.equals(isSendPartner)){
             return "0";
        }else{
            return _doSentTerminalOnline4Apis(terminalNoList);
        }
    }
	/**
     * 发送柜体在线信息4国通
     * @param terminalNoList
     * @return
     */
    private static String _doSentTerminalOnline4Apis(String terminalNoList)
    {
        String result = "";
        
        HttpClient client = new HttpClient();
        HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(10000);
        // 设置读数据超时时间(单位毫秒)
        managerParams.setSoTimeout(10000);
        
        PostMethod post = new PostMethod(postUrl);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        
        NameValuePair[] data ={ new NameValuePair("ACTION", Constant.ACTION_SENT_CHECKLINK),//"600073"
                                new NameValuePair("MBDEVICENO", terminalNoList)};
        
        try
        {
             //System.out.println(msgContent);

             post.setRequestBody(data);
             int statusCode = client.executeMethod(post);

             if(statusCode == HttpStatus.SC_OK)
             {
                 result = post.getResponseBodyAsString();
                 result = StringUtils.trim(result);
                 
                 if(!result.equals("0")&& !result.startsWith("F0"))
                 {
                     log.error("[sentTerminalOnline error0] = " + result);
                 }
             }else
             {
                 log.error("[sentTerminalOnline statusCode] = " + statusCode);
                 
                 result = String.valueOf(statusCode);
             }
        }catch(Exception e)
        {
            log.error("[sentTerminalOnline error1] = " + e.getMessage());
            
            result = e.getMessage();
        }
        finally
        {
            post.releaseConnection();
        }
        
        return result;
    }
	/**
     * 发送格口信息
     * @param terminal
     * @param boxinfo
     * @param GXBZ
     * @return
     */
    public static String doSentBoxInfo(TBTerminal terminal,String boxinfo, String GXBZ){
        String isSendPartner = ControlParam.getInstance().getSendToPartner();
        if(Constant.PARTNER_NAME_HAIER.equals(isSendPartner)){
             return "0";
        }else{
            if(outerSender != null){
                return "0";
            }else{
                return _doSentBoxInfo4Apis(terminal,boxinfo, GXBZ);
            }
            
        }
    }
    /**
     * 发送柜体注册信息4国通
     * @param terminal
     * @param GXBZ
     * @return
     */
    private static String _doSentBoxInfo4Apis(TBTerminal terminal,String boxinfo,String GXBZ)
    {
        String result = "";
        
        HttpClient client = new HttpClient();
        HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(10000);
        // 设置读数据超时时间(单位毫秒)
        managerParams.setSoTimeout(10000);
        
        PostMethod post = new PostMethod(postUrl);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        
        NameValuePair[] data ={ new NameValuePair("ACTION", Constant.ACTION_SENT_BOXINFO),
                                new NameValuePair("TERMINALNO", terminal.MBDeviceNo),
                                new NameValuePair("BOXLIST", boxinfo),
                                new NameValuePair("GXBZ",GXBZ)};
        
        try
        {
             //System.out.println(msgContent);

             post.setRequestBody(data);
             int statusCode = client.executeMethod(post);

             if(statusCode == HttpStatus.SC_OK)
             {
                 result = post.getResponseBodyAsString();
                 result = StringUtils.trim(result);
                 
                 if(!result.equals("0")&& !result.startsWith("F0"))
                 {
                     log.error("[sentTerminalInfo error0] = " + result);
                 }
             }else
             {
                 log.error("[sentTerminalInfo statusCode] = " + statusCode);
                 
                 result = String.valueOf(statusCode);
             }
        }catch(Exception e)
        {
            log.error("[sentTerminalInfo error1] = " + e.getMessage());
            
            result = e.getMessage();
        }
        finally
        {
            post.releaseConnection();
        }
        
        return result;
    }
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int sendHaierRequest(String uri,HashMap<String,String> paraform)
	{
		int result = 0;
		
		String url = "http://203.130.41.104:8050/guizi-app-jiqimao/haier/userPush/" + uri;

		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(10000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		
		int i = 0;
		StringBuffer sb = new StringBuffer();
		
		Set<String> key_arr = paraform.keySet();
		for (String key : key_arr) {   
			String value = paraform.get(key);
			
			if(i > 0){
				sb.append(String.format("&%s=%s", key,value));
			}else{
				sb.append(String.format("%s=%s", key,value));
			}
			
			i++;
		}  
		
		url = url + "?" + sb.toString();
		
        GetMethod getMethod = new GetMethod(url);
        getMethod.addRequestHeader("Content-Type","application/json");//在头文件中设置转码
     
        try
        {
        	 // Execute the method.
             int statusCode = client.executeMethod(getMethod);

             if(statusCode == HttpStatus.SC_OK)
        	 {
        		 String resultMsg = getMethod.getResponseBodyAsString();
        		 
        		 resultMsg = StringUtils.trim(resultMsg);
        		 if(resultMsg.indexOf("200") == -1)
        		 {
        			 log.error("[sentDeliveryInfo error0] = " + resultMsg);	
        			 
        			 result = 1;
        		 }
        	 }else
        	 {
        		 log.error("[sentDeliveryInfo statusCode] = " + statusCode);
        		 
        		 result = -1;
        	 }
        }catch(Exception e)
        {
        	log.error("[sentDeliveryInfo error1] = " + e.getMessage());
        	
        	result = -1;
        }
        finally
        {
        	getMethod.releaseConnection();
        }
        
		return result;
	}
	
	/**
     * 发送会员信息
     * @param sendInfo
     * @return
     */
    public static int doSentCustomerInfo(SendInfo sendInfo)
    {
        int result = 0;
        
        HttpClient client = new HttpClient();
        HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(10000);
        // 设置读数据超时时间(单位毫秒)
        managerParams.setSoTimeout(10000);
        
        PostMethod post = new PostMethod(postUrl);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        
        NameValuePair[] data = SendInfo.toNameValuePair(Constant.ACTION_SENT_CUSTOMER_TOPUP, sendInfo);
        try
        {
            //log.debug(postUrl+","+smsInfo.PackageID+","+smsInfo.CustomerMobile+",bz="+smsInfo.SendBZ);

             post.setRequestBody(data);
             int statusCode = client.executeMethod(post);

             if(statusCode == HttpStatus.SC_OK)
             {
                 String resultMsg = post.getResponseBodyAsString();
                 
                 resultMsg = StringUtils.trim(resultMsg);
                 if(StringUtils.isNotEmpty(resultMsg) && resultMsg.startsWith("{") && resultMsg.endsWith("}"))
                 {
                     JSONObject jsonObject = JSONObject.fromObject(resultMsg);
                 }
             }else
             {
                 log.error("[SentCustomerInfo statusCode] = " + statusCode);
                 
                 result = -1;
             }
        }catch(Exception e)
        {
            log.error("[SentCustomerInfo error1] = " + e.getMessage());
            
            result = -1;
        }
        finally
        {
            post.releaseConnection();
        }
        
        return result;
    }
    
	private static String encodeUrl(String url){
		String result = url;
		
		try{
			result = URLEncoder.encode(url, "utf-8");
		}catch(Exception e)
		{
			
		}
		
		return result;
	}
}
