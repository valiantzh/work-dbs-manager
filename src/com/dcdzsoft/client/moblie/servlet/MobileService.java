package com.dcdzsoft.client.moblie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.logging.Log;

import net.sf.json.JSONObject;

import com.dcdzsoft.businessproxy.MobileProxy;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.config.ErrorMsgConfig;
import com.dcdzsoft.packet.JsonPacket;
import com.dcdzsoft.packet.PacketUtils;
import com.dcdzsoft.util.StringUtils;
import com.dcdzsoft.util.WebUtils;

@WebServlet(name = "/mobileservice", urlPatterns = { "/mobileservice" })
public class MobileService extends HttpServlet {
	private static final String CONTENT_TYPE = "application/Json;charset=utf8";
	//private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MobileService.class);

	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();

	private static final String DTO_PACKAGE_PREFIX = "com.dcdzsoft.dto.business.";
	private static final String PROXY_PACKAGE_PREFIX = "com.dcdzsoft.client.moblie.businessproxy.";

	private static final String PROXY_CLASS_NAME = PROXY_PACKAGE_PREFIX+ "MobileProxy";
	private static final String BUSINESS_METHOD_NAME = "doBusiness";

	private static Class proxyClass = null;
	private static MobileProxy mobileProxy = null;

	static {
		try {
			proxyClass = Class.forName(PROXY_CLASS_NAME);
			mobileProxy = (MobileProxy) proxyClass.newInstance();
		} catch (Exception e) {}
	}
	
	// Initialize global variables
	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// must place here
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);

		String message = WebUtils.readRequestData(request);
		String resultMsg = "";

		if (apCfg.isLogRawMsg()){
		    //System.out.println("request="  + message);
		    log.info("request="  + message);
		}
			
		
		

		try{
		    JSONObject json = JSONObject.fromObject(message);
		    
			if(json != null){
			    
				JsonPacket packet = (JsonPacket)JSONObject.toBean(json,JsonPacket.class);
				if(packet != null){
                    if(packet._CmdType == JsonPacket.MSG_SENT_BY_SERVER)
                    {
                        resultMsg = handleRequest(packet, message);
                    }
                }
			}else{
				log.error("[unpack error:],msg=" + message);
			}
		}catch(Exception e){
			
			log.error("[handle request:],error= " + e.getMessage() + ",msg=" + message );
		}
		
		//返回处理结果
		PrintWriter out = response.getWriter();

        out.println(resultMsg);
        out.flush();
        out.close();
	}
	
	/**
	 * 处理客户端主动发起的请求交易
	 * @param packet
	 */
	protected String handleRequest(JsonPacket packet,String message){
		String responseMsg = "";
		
		String serviceName = packet._ServiceName;
		
		if(StringUtils.isNotEmpty(serviceName) && serviceName.length() > 10){

			String dtoName = DTO_PACKAGE_PREFIX + packet._ServiceName;

			Class inClass = null;
			
			try
			{
				inClass = Class.forName(dtoName);
				Object inParam = PacketUtils.buildBussinessDTOParam(packet,inClass);
				Method method = proxyClass.getMethod(BUSINESS_METHOD_NAME, new Class[]{inClass});
                Object result = method.invoke(mobileProxy, new Object[]{inParam});

                //构造响应包
                responseMsg =  PacketUtils.buildSuccessResult(packet,result);

                //System.out.println("response=" + responseMsg+",serviceName:"+serviceName+","+inParam.getClass());
                if(apCfg.isLogRawMsg()){
                    //System.out.println("response="  + responseMsg);
                    if(responseMsg.length()>100 && packet._ServiceName.endsWith("Qry")){
                        log.info("_dcdz_response=>" + responseMsg.substring(0,100));
                    }else{
                        log.info("_dcdz_response=>" + responseMsg);
                    }
                }
        			
			}
			catch(java.lang.ClassNotFoundException e0)
			{
				log.error("[ClassNotFoundException:],errmsg=" + e0.getMessage() + ",msg=" + message);
			}
			catch (NoSuchMethodException e1){
				log.error("[NoSuchMethodException:],errmsg=" + e1.getMessage() + ",msg=" + message);
			}
			catch (java.lang.reflect.InvocationTargetException e2)
            {
				String errorMsg = ErrorMsgConfig.getLocalizedMessage(e2.getTargetException().getMessage());
                if("zh".equalsIgnoreCase(ApplicationConfig.getInstance().getLocale())){
                    responseMsg = PacketUtils.buildFailResult(packet, errorMsg);
                }else{
                    responseMsg = PacketUtils.buildFailResult(packet, e2.getTargetException().getMessage());
                }
                log.error("[Buiness Error:],errmsg=" + errorMsg + ",msg=" + message);
                
				 if(apCfg.isLogRawMsg())
          			log.info("response=" + responseMsg);
            }
			catch(Exception e3)
			{
				log.error("[Service Error:],errmsg=" + e3.getMessage() + ",msg=" + message);
			}
		}
		
		return responseMsg;
	}

}
