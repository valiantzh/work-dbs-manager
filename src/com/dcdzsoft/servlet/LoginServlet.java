package com.dcdzsoft.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.RowSet;

import com.dcdzsoft.util.WebUtils;
import com.dcdzsoft.util.JsonUtils;

import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sda.db.RowSetUtils;
import com.dcdzsoft.dto.business.InParamOPOperLogin;
import com.dcdzsoft.client.web.OPWebClientAdapter;
import com.dcdzsoft.client.web.servlet.UserLoginInfo;
import com.dcdzsoft.client.web.servlet.UserLoginInfoList;
import com.dcdzsoft.client.web.servlet.UserTraceListener;
import com.dcdzsoft.dto.business.InParamOPOperOnlineQry;


public class LoginServlet
    extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";

    //Initialize global variables
    public void init()
        throws ServletException
    {
    }

    //Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        //must place here
        request.setCharacterEncoding("utf-8");
        response.setContentType(CONTENT_TYPE);

        String outmsg = "";
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        if (!session.isNew())
        {
            session.invalidate(); //销毁Session
            session = request.getSession(true); //创建新的Session
        }

        String jsessionid = session.getId();
        String uuid = WebUtils.getUUID();

        String OperID = WebUtils.getStringParameter("OperID",request);
        String OperPassword = WebUtils.getStringParameter("OperPassword",request).trim();
        int OperType = WebUtils.getIntParameter("OperType",request);

        String Passwd_md5 = SecurityUtils.md5(OperPassword);

        try
        {
            //操作员登录
            RowSet rset = null;
            InParamOPOperLogin in = new InParamOPOperLogin();
            in.OperID = OperID.trim();
            in.OperPassword = Passwd_md5;
            in.LoginIPAddr = request.getRemoteAddr();

            rset = OPWebClientAdapter.doBusiness(in);
            //操作员信息
            RowSet rset1 = null;
            InParamOPOperOnlineQry operQry = new InParamOPOperOnlineQry();
            operQry.OperID = OperID;
            operQry.UserID = OperID;
            
            rset1 = OPWebClientAdapter.doBusiness(operQry);
            outmsg = JsonUtils.getSuccessJSONStr(WebUtils.buildClientInitJSONStr(rset,rset1,jsessionid,uuid,OperPassword),"0");
            //取得登陆信息,并在session保存登陆信息
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            RowSetUtils.rowsetBeforeFirst(rset1);
            if(RowSetUtils.rowsetNext(rset1))
            {
                userLoginInfo.setOperID(RowSetUtils.getStringValue(rset1, "OperID"));
                userLoginInfo.setOperName(RowSetUtils.getStringValue(rset1, "OperName"));
                userLoginInfo.setOperType(RowSetUtils.getIntValue(rset1, "OperType"));
                userLoginInfo.setDepartmentID(RowSetUtils.getStringValue(rset1, "DepartmentID"));
                userLoginInfo.setLoginInTime(RowSetUtils.getStringValue(rset1, "LoginInTime"));
                userLoginInfo.setLoginOutTime(RowSetUtils.getStringValue(rset1, "LoginOutTime"));
                userLoginInfo.setLoginIPAddr(RowSetUtils.getStringValue(rset1, "LoginIPAddr"));
                session.setAttribute("OperID", RowSetUtils.getStringValue(rset1, "OperID"));
            }
            session.setAttribute("uuid", uuid);
            session.setAttribute("userLoginInfo", userLoginInfo);

            UserLoginInfoList userList = UserLoginInfoList.getInstance();
            UserTraceListener userTrace = new UserTraceListener();
            userTrace.setUser(userLoginInfo);
            session.setAttribute("userTrace", userTrace);
            userList.addUser(userTrace.getUser());
            out.println(outmsg);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            outmsg = JsonUtils.getErrorJSONStr("【登录失败】" , e);
            out.println(outmsg);
        }
        finally
        {
            out.close();
        }
    }

    //Clean up resources
    public void destroy()
    {
    }
}
