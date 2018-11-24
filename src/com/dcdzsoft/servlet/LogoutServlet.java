package com.dcdzsoft.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LogoutServlet
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType(CONTENT_TYPE);

        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();

        //调用登出业务
        session.invalidate();

        out.close();
    }

    //Clean up resources
    public void destroy()
    {
    }
}
