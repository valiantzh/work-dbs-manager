package com.dcdzsoft.client.pda.servlet;

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

import com.dcdzsoft.businessproxy.BusinessProxy;
import com.dcdzsoft.client.pda.businessproxy.ProxyDcdzPDA;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.config.ErrorMsgConfig;
import com.dcdzsoft.constant.Constant;
import com.dcdzsoft.outerproxy.OuterProxy;
import com.dcdzsoft.packet.JsonPacket;
import com.dcdzsoft.packet.PacketUtils;
import com.dcdzsoft.util.StringUtils;
import com.dcdzsoft.util.WebUtils;

@WebServlet(name = "/PDADeviceService", urlPatterns = { "/pdadeviceservice" })
public class PDADeviceService extends HttpServlet {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	private static Log log = org.apache.commons.logging.LogFactory.getLog(PDADeviceService.class);

	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();

	private static final String DTO_PACKAGE_PREFIX = "com.dcdzsoft.dto.business.";
	private static final String PROXY_PACKAGE_PREFIX = "com.dcdzsoft.client.pda.businessproxy.";

	private static final String PROXY_CLASS_NAME = PROXY_PACKAGE_PREFIX + "ProxyDcdzPDA";
	private static final String BUSINESS_METHOD_NAME = "doBusiness";

	private static Class proxyClass = null;
	private static ProxyDcdzPDA businessProxy = null;

	private static OuterProxy outerSender = null;
	static {
		try {
			System.out.println("*********proxyClassName="+PROXY_CLASS_NAME);
			proxyClass = Class.forName(PROXY_CLASS_NAME);
			businessProxy = (ProxyDcdzPDA) proxyClass.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotEmpty(apCfg.getOutProxy())){
		    try {
		        Class outerClass = Class.forName("com.dcdzsoft.outerproxy."+apCfg.getOutProxy());
		        System.out.println("OutProxy:"+apCfg.getOutProxy());
	            outerSender = (OuterProxy)outerClass.newInstance();
	            System.out.println("OutProxy2:"+apCfg.getOutProxy());
		    } catch (Exception e) {
		        e.printStackTrace();
	        }
		}
	}

	//业务消息
	
	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// must place here
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);

		String terminalNo = WebUtils.getStringParameter("TERMINALNO", request);
		String message = WebUtils.getStringParameter("RECDATA", request);
		String action = WebUtils.getStringParameter("ACTION", request);
		String resultMsg = "";
		System.out.println("TERMINALNO="+terminalNo+",RECDATA="+ message+",ACTION="+action);
		if (apCfg.isLogRawMsg())
			log.info("request=" + terminalNo + "," + message+",action="+action);

		
		
		try{
			JSONObject json = JSONObject.fromObject(message);
			
			if(json != null){
				JsonPacket packet = (JsonPacket)JSONObject.toBean(json, JsonPacket.class);

				if(packet != null){
					if(packet._CmdType == JsonPacket.MSG_SENT_BY_CLIENT)
					{
					    if(outerSender!=null){
                            resultMsg = outerSender.packageDetail(terminalNo, message);
                        }else{
                            resultMsg = handleRequest(terminalNo, message, packet);
                        }
					}
				}

			}else{
				log.error("[unpack error:],msg=" + message);
			}
		}catch(Exception e){
			
			log.error("[handle request:],error= " + e.getMessage() + ",msg=" + message );
		}
		
		System.out.println("response=" + terminalNo + "," + resultMsg);
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
	protected String handleRequest(String terminalNo, String message, JsonPacket packet){
		String responseMsg = "";
		
		String serviceName = packet._ServiceName;

		if(StringUtils.isNotEmpty(serviceName) && serviceName.length() > 10){

			String dtoName = DTO_PACKAGE_PREFIX + packet._ServiceName;

			Class inClass = null;
			
			try
			{
			    if(outerSender!=null){//从设备端直接连接合作方服务
			        switch(packet._ServiceName){
		            case "InParamPTPackageDetail"://查询收件人手机
		                //System.out.println("_ServiceName:"+packet._ServiceName);
		                return outerSender.getInfoFormPartner(terminalNo, message);
			        }
			    }
				inClass = Class.forName(dtoName);
				Object inParam = PacketUtils.buildBussinessDTOParam(packet,inClass);

				Method method = proxyClass.getMethod(BUSINESS_METHOD_NAME, new Class[]{inClass,String.class});
                Object result = method.invoke(businessProxy, new Object[]{inParam,terminalNo});

                //构造响应包
                responseMsg =  PacketUtils.buildSuccessResult(packet,result);

                //System.out.println("response=" + responseMsg);
                if(apCfg.isLogRawMsg())
        			log.info("response=" + terminalNo + "," + responseMsg);
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
          			log.info("response=" + terminalNo + "," +  responseMsg);
            }
			catch(Exception e3)
			{
				e3.printStackTrace();
				log.error("[Service Error:],errmsg=" + e3.getMessage() + ",msg=" + message);
			}
		}
		
		return responseMsg;
	}

}
