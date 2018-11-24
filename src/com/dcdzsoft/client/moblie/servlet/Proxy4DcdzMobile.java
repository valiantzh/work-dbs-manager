package com.dcdzsoft.client.moblie.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.packet.JsonPacket;
import com.dcdzsoft.packet.JsonResult;
import com.dcdzsoft.packet.PacketUtils;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.NumberUtils;
import com.dcdzsoft.util.StringUtils;

/**
 * 移动端业务
 * @author Administrator
 *
 */
public class Proxy4DcdzMobile {
	 private static Log log = org.apache.commons.logging.LogFactory.getLog(Proxy4DcdzMobile.class);
	 
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static String postUrl = "";
	
	//private static HttpClient client = null;
	//private static PostMethod post = null;
	
	static{
		postUrl = "http://192.168.8.116:8094/mobileservice";
		
	}
	
	/**
     * 查询用户取件
     * @param in
     * @return
	 * @throws EduException 
     */
    public static String doBusiness(InParamAPCustomerAppOpenBox in) throws EduException{
        String result = "";
        result = handleRequest(PacketUtils.createRequestPacket(in, ""));
//        JsonResult jResult = toJsonResult(result);
////        System.out.println(jResult.success+","+jResult.msg);
//        if(jResult != null){
//            if(jResult.success){
//                result = jResult.result;
//            }else{
//                result = jResult.msg;
//                throw new EduException(jResult.msg);
//            }
//        }
        //System.out.println("doBusiness:"+result);
        return result;
    }
    
   	/**
   	 * 重置取件密码
   	 * @param in
   	 * @return
   	 * @throws EduException
   	 */
    
    public static String doBusiness(InParamPTResetOpenBoxKey in) throws EduException{
        String result = "";
        result = handleRequest(PacketUtils.createRequestPacket(in, ""));
        //System.out.println("返回的result"+result);
//        JsonResult jResult = toJsonResult(result);
//        //System.out.println(jResult.success+","+jResult.msg);
//        if(jResult != null){
//            if(jResult.success){
//                result = jResult.result;
//            }else{
//                result = jResult.msg;
//                throw new EduException(jResult.msg);
//            }
//        }
        //System.out.println("doBusiness返回的值"+result);
        return result;
    }
    
	private static String handleRequest(String message)
	{
		String result = "";
		if(apCfg.isLogRawMsg())
			log.info("_dcdz_request=>" + message);
		
		result = executePost(message);
		JsonPacket packet = toJsonPacket(result);
		if(packet!=null){
		    result = packet.getBody();
		}
		
        //System.out.println("_dcdz_response:"+result);
        if(apCfg.isLogRawMsg()){
            if(result.length()>100 && packet._ServiceName.endsWith("Qry")){
                log.info("_dcdz_response<==" + result.substring(0,100));
            }else{
                log.info("_dcdz_response<==" + result);
            }
        }
        return result;
	}
	
	private static JsonResult toJsonResult(String message)
	{
	    JsonResult result = null;
	    
		try{
		    JSONObject json = JSONObject.fromObject(message);
		    
		    if(json != null){
		        result = new JsonResult(json.optBoolean("success"),json.optString("msg"));
		        result.setResult(json.optString("result"));
            }else{
                log.error("[unpack error:],msg=" + message);
            }
		}catch(Exception e){
			log.error("[handle result:],error= " + e.getMessage() + ",msg=" + message );
		}
		
		return result;
	}
	private static JsonPacket toJsonPacket(String message)
    {
        JsonPacket packet = null;
        try{
            JSONObject json = JSONObject.fromObject(message);
            
            if(json != null){
                packet = (JsonPacket)JSONObject.toBean(json,JsonPacket.class);
                
            }else{
                log.error("[unpack error:],msg=" + message);
            }
        }catch(Exception e){
            log.error("[handle request:],error= " + e.getMessage() + ",msg=" + message );
        }
        
        return packet;
    }
	private static Object toOutParamBean(String message, Class c)
    {
        Object o = null;
        JSONObject json = JSONObject.fromObject(message);
        try{
            o = c.newInstance();
            if(json != null){
                //bean = JSONObject.toBean(json,c);
                Field[] fields = c.getFields();
                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName();
                    Class type = fields[i].getType();
                    String methodName = "set" + name;
                    String paramName = name;
                    String typeName = type.getName();
                    if (typeName.equals("java.lang.String")) {
                        String value = json.optString(paramName);

                        if (StringUtils.isNotEmpty(value)) {
                            Method method = c.getMethod(methodName,
                                    new Class[] {String.class});
                            method.invoke(o, new Object[] {value});
                        }
                    }else if(typeName.equals("int")){
                        int value = json.optInt(paramName);
                        Class partypes[] = new Class[1];
                        partypes[0] = Integer.TYPE;
                        Method method = c.getMethod(methodName, partypes);
                        method.invoke(o, new Object[] {new Integer(value)});
                        
                    }else if (typeName.equals("long")) {
                        long value = json.optLong(paramName);

                        Class partypes[] = new Class[1];
                        partypes[0] = Long.TYPE;
                        Method method = c.getMethod(methodName, partypes);
                        method.invoke(o, new Object[] {new Long(value)});
                    } else if (typeName.equals("double")) {
                        Class partypes[] = new Class[1];
                        partypes[0] = Double.TYPE;

                        Double value = new Double(json.optDouble(paramName));
                        Method method = c.getMethod(methodName, partypes);
                        method.invoke(o, new Object[] {value});
                    } else if (typeName.equals("java.sql.Date")) {
                        String value = "";
                        value = json.optString(paramName);

                        if (StringUtils.isNotEmpty(value)) {
                            Method method = c.getMethod(methodName,
                                    new Class[] {java.sql.Date.class});
                            method.invoke(o, new Object[] {DateUtils.stringToDate(value)});
                        }
                    }
                    else if (typeName.equals("java.sql.Timestamp")) {
                        String value = "";
                        value = json.optString(paramName);

                        if (StringUtils.isNotEmpty(value)) {
                            Method method = c.getMethod(methodName,
                                    new Class[] {java.sql.Timestamp.class});
                            method.invoke(o, new Object[] {DateUtils.stringToTimestamp(value)});
                        }
                    } 
                }
                
            }else{
                log.error("[unpack error:],msg=" + message);
            }
        }catch(Exception e){
            log.error("[handle OutParamBean:],error= " + e.getMessage() + ",msg=" + message );
        }
        
        return o;
    }
	
	public static<T> List<T> toJsonListPacket(String message,Class t)
    {
		List<T> lst= new ArrayList<T>();
        try{
        	JSONArray jsonarr = JSONArray.fromObject(message);
            
            if(jsonarr != null){
            	for(Object obj : jsonarr){
            		JSONObject jo=JSONObject.fromObject(obj);
            		T m=(T)toOutParamBean(jo,t);
            		lst.add(m);
            	}
            }else{
                log.error("[unpack error:],msg=" + message);
            }
        }catch(Exception e){
            log.error("[handle request:],error= " + e.getMessage() + ",msg=" + message );
        }
        
        return lst;
    }
	public static Object toOutParamBean(JSONObject json, Class c)
    {
        Object o = null;
        try{
            o = c.newInstance();
            if(json != null){
                //bean = JSONObject.toBean(json,c);
                Field[] fields = c.getFields();
                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName();
                    Class type = fields[i].getType();
                    String methodName = "set" + name;
                    String paramName = name;
                    String typeName = type.getName();
                    if (typeName.equals("java.lang.String")) {
                        String value = json.optString(paramName);

                        if (StringUtils.isNotEmpty(value)) {
                            Method method = c.getMethod(methodName,
                                    new Class[] {String.class});
                            method.invoke(o, new Object[] {value});
                        }
                    }else if(typeName.equals("int")){
                        int value = json.optInt(paramName);
                        Class partypes[] = new Class[1];
                        partypes[0] = Integer.TYPE;
                        Method method = c.getMethod(methodName, partypes);
                        method.invoke(o, new Object[] {new Integer(value)});
                        
                    }else if (typeName.equals("long")) {
                        long value = json.optLong(paramName);

                        Class partypes[] = new Class[1];
                        partypes[0] = Long.TYPE;
                        Method method = c.getMethod(methodName, partypes);
                        method.invoke(o, new Object[] {new Long(value)});
                    } else if (typeName.equals("double")) {
                        Class partypes[] = new Class[1];
                        partypes[0] = Double.TYPE;

                        Double value = new Double(json.optDouble(paramName));
                        Method method = c.getMethod(methodName, partypes);
                        method.invoke(o, new Object[] {value});
                    } else if (typeName.equals("java.sql.Date")) {
                        String value = "";
                        value = json.optString(paramName);

                        if (StringUtils.isNotEmpty(value)) {
                            Method method = c.getMethod(methodName,
                                    new Class[] {java.sql.Date.class});
                            method.invoke(o, new Object[] {DateUtils.stringToDate(value)});
                        }
                    }
                    else if (typeName.equals("java.sql.Timestamp")) {
                        String value = "";
                        value = json.optString(paramName);

                        if (StringUtils.isNotEmpty(value)) {
                            Method method = c.getMethod(methodName,
                                    new Class[] {java.sql.Timestamp.class});
                            method.invoke(o, new Object[] {DateUtils.stringToTimestamp(value)});
                        }
                    } 
                }
                
            }else{
                log.error("[unpack error:],msg=" );
            }
        }catch(Exception e){
            log.error("[handle OutParamBean:],error= " + e.getMessage() + ",msg="  );
        }
        
        return o;
    }
	private static String  executePost(String msgContent){  
        String result = "";
        //System.out.println("postUrl:"+postUrl+","+msgContent);
        HttpClient client = new HttpClient();  
        HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams(); 
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(10000);
        // 设置读数据超时时间(单位毫秒)
        managerParams.setSoTimeout(10000);
        
        PostMethod post = new PostMethod(postUrl);
        post.addRequestHeader("Content-Type", "application/Json;charset=utf8");//在头文件中设置转码
        try {  
            
            RequestEntity requestEntity = new StringRequestEntity(msgContent, "application/Json",  "utf8");
            post.setRequestEntity(requestEntity );
            
            int statusCode = client.executeMethod(post);

            if(statusCode == HttpStatus.SC_OK)
            {
                result = post.getResponseBodyAsString();
                //System.out.println(result);
            }else
            {
                result = String.valueOf(statusCode);
            }
            
        } catch (HttpException e) {  
            System.err.println("Fatal protocol violation: " + e.getMessage());  
        } catch (IOException e) {  
            System.err.println("Fatal transport error: " + e.getMessage());  
        } finally {  
            post.releaseConnection();
        } 
        return result.trim();
    }  
}
