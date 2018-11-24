package com.dcdzsoft.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import javax.sql.*;

import java.lang.reflect.*;
import java.net.URLEncoder;
import java.net.URL;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dcdzsoft.print.PrintUtils;
import com.dcdzsoft.util.*;
import com.dcdzsoft.business.op.*;
import com.dcdzsoft.business.pa.*;
import com.dcdzsoft.business.pm.*;
import com.dcdzsoft.business.pt.*;
import com.dcdzsoft.client.web.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.EduException;
import com.dcdzsoft.config.BusiHandlerEntity;
import com.dcdzsoft.config.BusinessConfig;

public class MyService extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    
    private String reportPath = "";
    
    //Initialize global variables
    public void init()
        throws ServletException
    {
        ServletContext context = getServletContext();
        
        String realPath = context.getRealPath("/");
        if(!realPath.endsWith("/"))
        	realPath = realPath + "/";
        
        reportPath = realPath + "report/";
    }
    
    //Process the HTTP Post request
     public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException
     {
         request.setCharacterEncoding("utf-8");
         response.setContentType(CONTENT_TYPE);

         HttpSession session = request.getSession(true);
         
         //合法性检查 前台必须通过url?的形式把以下两个参数传递过来
         //String jsessionid = request.getRequestedSessionId();
         String uuid = WebUtils.getStringParameter("uuid", request);

         String sUuid = WebUtils.getSessionAttribute("uuid", session);
         String sOperID = WebUtils.getOperIDAttribute(session);

         if (StringUtils.isEmpty(sOperID))
         {
             PrintWriter out = response.getWriter();

             out.println(JsonUtils.getErrorJSONStr("系统超时，请重新登陆", null));
             out.close();

             return;
         }
         if (StringUtils.isEmpty(uuid) || !uuid.equals(sUuid))
         {
             PrintWriter out = response.getWriter();

             out.println(JsonUtils.getErrorJSONStr("非法的请求", null));
             out.close();

             return;
         }


         boolean isMultipart = ServletFileUpload.isMultipartContent(request);
         if(isMultipart) //处理文件上传的请求
         {

         }
         else
         {
             String FuncMenuID = WebUtils.getStringParameter("FuncMenuID", request);
             String cmd = WebUtils.getStringParameter("cmd", request);

             if (StringUtils.isEmpty(cmd)) cmd = "default";

             if ("print".equals(cmd) || "export".equals(cmd))
             {
            	 try
                 {
                     String key = FuncMenuID + cmd;
                     BusinessConfig cfg = BusinessConfig.getInstance();
                     BusiHandlerEntity handler = cfg.getHandler(key);

                     if (handler == null)
                     {
                         String errMsg = JsonUtils.getErrorJSONStr(cfg.getNoFuncMsg(), null);

                         PrintWriter out = response.getWriter();
                         out.println(errMsg);
                         out.flush();
                         out.close();

                         return;
                     }

                     byte[] bytes = handPrintExpBusiness(FuncMenuID, cmd, request);
                     if ("print".equals(cmd))
                     {
                         ////inline --- 在线打开 IE下为何打开两个pdf???
                         if (cfg.getPrintWay().equals("pdf"))
                             response.setContentType("application/pdf");
                         else
                             response.setContentType("text/html; charset=utf-8");

                         response.setHeader("content-disposition", "inline;filename=\"" + URLEncoder.encode(handler.getAttachFile(), "UTF-8") + ".pdf\"");
                     }
                     else if ("export".equals(cmd))
                     {
                         ////attachment --- 作为附件下载
                         response.setContentType("application/vnd.ms-excel");
                         response.setHeader("content-disposition", "attachment;filename=\"" + URLEncoder.encode(handler.getAttachFile(), "UTF-8") + ".xls\"");
                     }

                     response.setContentLength(bytes.length);
                     ServletOutputStream ouputStream = response.getOutputStream();
                     ouputStream.write(bytes, 0, bytes.length);

                     ouputStream.flush();
                     ouputStream.close();
                 }
                 catch (EduException e)
                 {
                     String errMsg = e.getMessage();

                     PrintWriter out = response.getWriter();
                     out.println(errMsg);
                     out.flush();
                     out.close();
                 }
             }
             else
             {
                 PrintWriter out = response.getWriter();

                 String outMsg = handCommonBusiness(FuncMenuID, cmd, request);

                 out.println(outMsg);
                 out.flush();
                 out.close();
             }
         }
     }

     //一般的业务处理
    private String handCommonBusiness(String FuncMenuID, String cmd, HttpServletRequest request)
    {
        String outMsg = "";

        BusinessConfig cfg = BusinessConfig.getInstance();
        String key = FuncMenuID + cmd;

        BusiHandlerEntity handler = cfg.getHandler(key);
        if (handler == null)
        {
            outMsg = JsonUtils.getErrorJSONStr(cfg.getNoFuncMsg(), null);
        }
        else
        {
            try
            {
                Class inClass = Class.forName(handler.getParamClass());
                Class adapterClass = Class.forName(handler.getAdapterClass());

                Object inParam = WebUtils.buildBussinessDTOParam(inClass, request, handler);
                String methodName = "doBusiness";

                Method method = adapterClass.getMethod(methodName, new Class[]
                    {inClass});
                Object result = method.invoke(adapterClass, new Object[]
                                              {inParam});

                String returntype = handler.getReturntype();
                if ("S".equals(returntype)) //更新操作
                {
                    outMsg = JsonUtils.getSuccessJSONStr(handler.getSucessMsg(), result);
                }
                else if ("N".equals(returntype)) //查询一次返回
                {
                    RowSet rset = (RowSet) result;

                    //功能权限菜单
                    if ("querymenu".equalsIgnoreCase(cmd) && "13010000".equalsIgnoreCase(FuncMenuID))
                        outMsg = WebUtils.buildFuncMenuJSONStr(rset, false);
                    else if ("querymenu".equalsIgnoreCase(cmd) && "13020000".equalsIgnoreCase(FuncMenuID))
                        outMsg = WebUtils.buildFuncMenuJSONStr(rset, true);
                    //特殊权限菜单
                    else if ("queryspe".equalsIgnoreCase(cmd) && "13010000".equalsIgnoreCase(FuncMenuID))
                        outMsg = WebUtils.buildSpeRightJSONStr(rset, false);
                    else if ("queryspe".equalsIgnoreCase(cmd) && "13020000".equalsIgnoreCase(FuncMenuID))
                        outMsg = WebUtils.buildSpeRightJSONStr(rset, true);
                    //运营网点查询树
                    else if ("querytree".equalsIgnoreCase(cmd) && "12150000".equalsIgnoreCase(FuncMenuID))
                        outMsg = WebUtils.buildDepartTreeJSONStr(rset);
                    else
                        outMsg = JsonUtils.getJSONFromRowSet(rset, 0);
                }
                else if ("P".equals(returntype)) //查询多次返回
                {
                    Class inCountClass = Class.forName(handler.getParamCountClass());
                    Object inCountParam = WebUtils.buildBussinessDTOParam(inCountClass, request, handler);

                    Method methodCount = adapterClass.getMethod(methodName, new Class[]
                        {inCountClass});
                    Object objCount = methodCount.invoke(adapterClass, new Object[]
                        {inCountParam});

                    int count = NumberUtils.parseInt(objCount.toString());

                    RowSet rset = (RowSet) result;

                    outMsg = JsonUtils.getJSONFromRowSet(rset, count);
                }

              
            }
            catch (NoSuchMethodException e)
            {
                outMsg = JsonUtils.getErrorJSONStr(cfg.getSysErrMsg(), null);

                System.err.println("[My Service:NoSuchMethodException]" + e.getMessage());

            }
            catch (java.lang.reflect.InvocationTargetException e)
            {
                outMsg = JsonUtils.getErrorJSONStr(handler.getErrorMsg(), e.getTargetException());
            }
            catch (Exception e)
            {
                outMsg = JsonUtils.getErrorJSONStr(cfg.getSysErrMsg(), null);
                System.err.println("[My Service]" + e.getMessage());
            }
        }

        return outMsg;
    }
    
  //打印和导出业务的处理
    private byte[] handPrintExpBusiness(String FuncMenuID, String cmd, HttpServletRequest request)
        throws EduException
    {
        String errMsg = "";
        byte[] bytes = new byte[0];

        BusinessConfig cfg = BusinessConfig.getInstance();
        String key = FuncMenuID + cmd;

        BusiHandlerEntity handler = cfg.getHandler(key);
        if (handler == null)
        {
            errMsg = JsonUtils.getErrorJSONStr(cfg.getNoFuncMsg(), null);
            throw new EduException(errMsg);
        }

        try
        {
            String methodName = "doBusiness";
            Object resultRep = null;
            Object result = null;
            Map parameters = null;

            //报表的参数
            if(StringUtils.isNotEmpty(handler.getParamRepClass()))
            {
                Class inRepClass = Class.forName(handler.getParamRepClass());
                Class adapterRepClass = Class.forName(handler.getAdapterRepClass());

                Object inRepParam = WebUtils.buildBussinessDTOParam(inRepClass, request, handler);

                Method methodRep = adapterRepClass.getMethod(methodName, new Class[]
                                                             {inRepClass});
                resultRep = methodRep.invoke(adapterRepClass, new Object[]
                                                    {inRepParam});
            }

            //报表的数据
            if(StringUtils.isNotEmpty(handler.getParamClass()))
            {
                Class inClass = Class.forName(handler.getParamClass());
                Class adapterClass = Class.forName(handler.getAdapterClass());

                Object inParam = WebUtils.buildBussinessDTOParam(inClass, request, handler);

                Method method = adapterClass.getMethod(methodName, new Class[]
                                                       {inClass});
                result = method.invoke(adapterClass, new Object[]
                                              {inParam});
            }

            String fileName = reportPath + handler.getReportFile();

            //生成实际的报表数据流
            if ("print".equals(cmd))
            {
                if(resultRep == null)
                    parameters = PrintUtils.initReportParameter(request, "print");
                else
                    parameters = PrintUtils.initReportParameter((RowSet) resultRep, "print");

                if("pdf".equalsIgnoreCase(cfg.getPrintWay()))
                    bytes = PrintUtils.exportToPdf(result == null ? null : (RowSet) result, parameters, fileName);
                else
                    bytes = PrintUtils.exportToHtml(result == null ? null : (RowSet) result, parameters, fileName);
            }
            else if ("export".equals(cmd))
            {
                if(resultRep == null)
                   parameters = PrintUtils.initReportParameter(request, "export");
               else
                   parameters = PrintUtils.initReportParameter((RowSet) resultRep, "export");

               bytes = PrintUtils.exportToXls(result == null ? null : (RowSet) result, parameters, fileName);
            }
        }
        catch (NoSuchMethodException e)
        {
            errMsg = JsonUtils.getErrorJSONStr(cfg.getSysErrMsg(), null);

            System.err.println("[My Service:NoSuchMethodException]" + e.getMessage());

            throw new EduException(errMsg);

        }
        catch (java.lang.reflect.InvocationTargetException e)
        {
            errMsg = JsonUtils.getErrorJSONStr(handler.getErrorMsg(), e.getTargetException());

            throw new EduException(errMsg);
        }
        catch (Exception e)
        {
            errMsg = JsonUtils.getErrorJSONStr(cfg.getSysErrMsg(), null);
            System.err.println("[My Service]" + e.getMessage());
            e.printStackTrace();

            throw new EduException(errMsg);
        }

        return bytes;
    }

    //Clean up resources
    public void destroy()
    {
    }
}
