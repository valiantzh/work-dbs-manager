package com.dcdzsoft.servlet;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.Velocity;

import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.config.BusinessConfig;
import com.dcdzsoft.email.EmailSenderManager;
import com.dcdzsoft.business.GServer;
import com.dcdzsoft.client.moblie.servlet.ElockerManager;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.DateUtils;

//@WebServlet(urlPatterns={"/InitServlet"},loadOnStartup=1)

public class InitServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//Initialize global variables
    public void init() throws ServletException
    {
    	ApplicationConfig apConfig = ApplicationConfig.getInstance();
        BusinessConfig busiCfg = BusinessConfig.getInstance();

        ServletContext context = getServletContext();
        String realPath = context.getRealPath("/");
        if(!realPath.endsWith("/"))
        	realPath = realPath + "/";
        
        String fileName = realPath + "WEB-INF/appconfig.xml";
        String busifileName = realPath + "WEB-INF/busiconfig.xml";
        String vmPath = realPath + "WEB-INF/vm/";

        //设置真实的物理路径
        apConfig.setPhysicalPath(realPath);

        //设置log属性值
        String logPath = realPath + "log";
        System.setProperty("log.home", logPath);

        String version = GServer.getSoftversion();
        try
        {
            org.apache.log4j.PropertyConfigurator.configure(logPath + "/log4j.properties");
            apConfig.load(fileName);

            busiCfg.load(busifileName);

            GServer.getInstance().initMemoryData();
            
            Properties p = new Properties();
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, vmPath);
            p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
            p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
            Velocity.init(p);

            System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + " Read dcdzcomm("+version+") config file successfully....");
            
            SMSManager.getInstance();//启动短信处理线程
            EmailSenderManager.getInstance();//启动邮件线程处理
            
            System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + " isUploadToPartner="+ApplicationConfig.getInstance().isUploadToPartner()+"");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + " Read dcdzcomm("+version+") config file failure!!!!!!" +
                               e.getMessage());
        }

        //设置上传日志的路径
        File f = new File(apConfig.getFullTerminalLogPath());
        if (!f.isDirectory())
            f.mkdirs();

        File f1 = new File(apConfig.getFullTerminalLogTmpPath());
        if (!f1.isDirectory())
            f1.mkdirs();
    }

    //Clean up resources
    public void destroy()
    {
        try{
            GServer.getInstance().destroy();
            
            EmailSenderManager.getInstance().destroy();
            
            ElockerManager.getInstance().destroy();

            SMSManager.getInstance().destroy();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
