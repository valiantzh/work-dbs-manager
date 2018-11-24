package com.dcdzsoft.client.weixin.servlet;

import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.WebUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

@WebServlet(name="/WeixinService", urlPatterns={"/weixinservice"})
public class WeixinService
  extends HttpServlet
{
  private static final String CONTENT_TYPE = "text/html; charset=utf-8";
  
  public void init()
    throws ServletException
  {}
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doPost(request, response);
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    
    String appclientId = WebUtils.getStringParameter("appclientId", request);
    String appKey = WebUtils.getStringParameter("appKey", request);
    String action = WebUtils.getStringParameter("action", request);
    
    System.out.println(DateUtils.nowTime() + ",action:" + action + ",remote ip:" + request.getRemoteAddr());
    

    String outMsg = "";
    
    ControlParam ctrlParam = ControlParam.getInstance();
    if ((!ctrlParam.appclientId.equalsIgnoreCase(appclientId)) || 
      (!ctrlParam.appKey.equalsIgnoreCase(appKey)))
    {
      System.out.println(DateUtils.nowTime() + ",appclientId=" + appclientId + ",appKey=" + appKey);
    }
    else if (StringUtils.isEmpty(action))
    {
      outMsg = WxPacketUtils.getErrorJSONStr("invalid action");
    }
    else
    {
      String inParamClass = "com.dcdzsoft.dto.business.InParam" + action;
      try
      {
        Class inClass = Class.forName(inParamClass);
        Object inParam = WxPacketUtils.buildBussinessDTOParam(inClass, request);
        
        Class serviceClass = Class.forName("com.dcdzsoft.businessproxy.MobileProxy");
        
        String methodName = "doBusiness";
        Method method = null;
        Object result = null;
        
        method = serviceClass.getMethod(methodName, new Class[] { inClass });
        result = method.invoke(serviceClass, new Object[] { inParam });
        
        outMsg = WxPacketUtils.outParamToJson(result);
      }
      catch (ClassNotFoundException e0)
      {
        System.out.println("ClassNotFoundException");
        outMsg = WxPacketUtils.getErrorJSONStr(e0.getMessage());
      }
      catch (NoSuchMethodException e1)
      {
        System.out.println("NoSuchMethodException:" + e1.getMessage());
        outMsg = WxPacketUtils.getErrorJSONStr(e1.getMessage());
      }
      catch (InvocationTargetException e2)
      {
        Throwable ex = e2.getTargetException();
        
        System.out.println("InvocationTargetException:" + ex.getMessage());
        
        outMsg = WxPacketUtils.getErrorJSONStr(ex.getMessage());
      }
      catch (Exception e3)
      {
        outMsg = WxPacketUtils.getErrorJSONStr(e3.getMessage());
      }
    }
    PrintWriter out = response.getWriter();
    out.println(outMsg);
    out.flush();
    out.close();
  }
  
  public void destroy() {}
}
