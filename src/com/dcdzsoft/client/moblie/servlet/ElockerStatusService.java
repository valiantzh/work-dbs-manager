package com.dcdzsoft.client.moblie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.logging.Log;


import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.util.WebUtils;


/**
 * 查询设备状态信息
 * @author zxy
 *
 */
@WebServlet(name = "/dbs/api/elockerstatus", urlPatterns = { "/dbs/api/elockerstatus" })
public class ElockerStatusService extends HttpServlet {
	private static final long serialVersionUID = 3317342951003278196L;
	private static final String CONTENT_TYPE = "text/json; charset=utf-8";//text/json text/html
	private static Log log = org.apache.commons.logging.LogFactory.getLog(ElockerStatusService.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();

	private static ElockerManager elockerManager = ElockerManager.getInstance();
	
	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// must place here
		
		String subinv = WebUtils.getStringParameter("subinv", request);
		

		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);
		
		String respMsg = elockerManager.getElockerStatusList();
        
		//返回处理结果
		PrintWriter out = response.getWriter();

        out.println(respMsg);
        out.flush();
        out.close();
	}
}
