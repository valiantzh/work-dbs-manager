package com.dcdzsoft.client.weixin.servlet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.NumberUtils;
import com.dcdzsoft.util.StringUtils;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.util.JSONUtils;

public class WxPacketUtils
{
  public static String getSuccessJSONStr(Object result)
  {
    String msg = "";
    if (result == null) {
      msg = "";
    } else {
      msg = result.toString();
    }
    msg = encodeQuotedString(msg);
    
    String outMsg = "{\"success\":true,\"result\":" + result + "}";
    
    return outMsg;
  }
  
  public static String getErrorJSONStr(String str)
  {
    if (StringUtils.isEmpty(str)) {
      str = "";
    }
    str = encodeQuotedString(str);
    
    String outMsg = "{\"success\":false,\"result\":" + str + "}";
    
    return outMsg;
  }
  
  public static String getJSONFromList(List list)
    throws EduException
  {
    StringBuffer sb = new StringBuffer(2048);
    
    sb.append("{\"success\":true,\n");
    sb.append("\"result\":" + list.size() + "\n");
    sb.append(",\"rows\":[\n");
    for (int i = 0; i < list.size(); i++)
    {
      if (i > 0) {
        sb.append(",");
      }
      sb.append(dtoToJson(list.get(i)));
    }
    sb.append("]\r\n");
    sb.append("}");
    
    return sb.toString();
  }
  
  public static Object buildBussinessDTOParam(Class c, HttpServletRequest request)
  {
    Object o = null;
    try
    {
      o = c.newInstance();
      
      Field[] fields = c.getFields();
      for (int i = 0; i < fields.length; i++)
      {
        String name = fields[i].getName();
        Class type = fields[i].getType();
        String methodName = "set" + name;
        String paramName = name;
        if (!name.equals("FunctionID"))
        {
          String typeName = type.getName();
          if (typeName.equals("java.lang.String"))
          {
            String value = "";
            value = getStringParameter(paramName, request);
            if (StringUtils.isNotEmpty(value))
            {
              Method method = c.getMethod(methodName, 
                new Class[] { String.class });
              method.invoke(o, new Object[] { value });
            }
          }
          else if (typeName.equals("int"))
          {
            if (name.equals("recordBegin")) {
              paramName = "start";
            }
            if (name.equals("recordNum")) {
              paramName = "limit";
            }
            int value = getIntParameter(paramName, request);
            if ((name.equals("recordBegin")) || (name.equals("recordNum")))
            {
              Field field = c.getField(name);
              field.setInt(o, value);
            }
            else
            {
              Class[] partypes = new Class[1];
              partypes[0] = Integer.TYPE;
              Method method = c.getMethod(methodName, partypes);
              method.invoke(o, new Object[] { new Integer(value) });
            }
          }
          else if (typeName.equals("long"))
          {
            long value = getlongParameter(paramName, request);
            
            Class[] partypes = new Class[1];
            partypes[0] = Long.TYPE;
            Method method = c.getMethod(methodName, partypes);
            method.invoke(o, new Object[] { new Long(value) });
          }
          else if (typeName.equals("double"))
          {
            Class[] partypes = new Class[1];
            partypes[0] = Double.TYPE;
            
            Double value = new Double(getDoubleParameter(paramName, 
              request));
            Method method = c.getMethod(methodName, partypes);
            method.invoke(o, new Object[] { value });
          }
          else if (typeName.equals("java.sql.Date"))
          {
            String value = "";
            value = getStringParameter(paramName, request);
            if (StringUtils.isNotEmpty(value))
            {
              Method method = c.getMethod(methodName, 
                new Class[] { Date.class });
              method.invoke(o, 
                new Object[] { DateUtils.stringToDate(value, "yyyy-MM-dd") });
            }
          }
          else if (typeName.equals("java.sql.Timestamp"))
          {
            String value = "";
            value = getStringParameter(paramName, request);
            if (StringUtils.isNotEmpty(value))
            {
              Method method = c.getMethod(methodName, 
                new Class[] { Timestamp.class });
              method.invoke(o, 
                new Object[] { DateUtils.stringToTimestamp(value, "yyyy-MM-dd HH:mm:ss") });
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
    return o;
  }
  
  public static String getStringParameter(String name, HttpServletRequest request)
  {
    String value = "";
    if (request != null)
    {
      value = StringUtils.trimToEmpty(request.getParameter(name));
      if (StringUtils.isEmpty(value)) {
        return "";
      }
    }
    return value;
  }
  
  public static int getIntParameter(String name, HttpServletRequest request)
  {
    String value = "";
    if (request != null)
    {
      value = StringUtils.trimToEmpty(request.getParameter(name));
      if (StringUtils.isEmpty(value)) {
        return 0;
      }
    }
    return NumberUtils.parseInt(value);
  }
  
  public static long getlongParameter(String name, HttpServletRequest request)
  {
    String value = "";
    if (request != null)
    {
      value = StringUtils.trimToEmpty(request.getParameter(name));
      if (StringUtils.isEmpty(value)) {
        return 0L;
      }
    }
    return NumberUtils.parseLong(value);
  }
  
  public static double getDoubleParameter(String name, HttpServletRequest request)
  {
    String value = "";
    if (request != null)
    {
      value = StringUtils.trimToEmpty(request.getParameter(name));
      if (StringUtils.isEmpty(value)) {
        return 0.0D;
      }
    }
    return NumberUtils.parseDouble(value);
  }
  
  public static String encodeQuotedString(String str)
  {
    if (StringUtils.isEmpty(str)) {
      return "\"\"";
    }
    char c = '\000';
    
    int len = str.length();
    StringBuffer sb = new StringBuffer(len + 4);
    

    sb.append('"');
    for (int i = 0; i < len; i++)
    {
      char b = c;
      c = str.charAt(i);
      switch (c)
      {
      case '"': 
      case '\\': 
        sb.append('\\');
        sb.append(c);
        break;
      case '/': 
        if (b == '<') {
          sb.append('\\');
        }
        sb.append(c);
        break;
      case '\b': 
        sb.append("\\b");
        break;
      case '\t': 
        sb.append("\\t");
        break;
      case '\n': 
        sb.append("\\n");
        break;
      case '\f': 
        sb.append("\\f");
        break;
      case '\r': 
        sb.append("\\r");
        break;
      default: 
        if (c < ' ')
        {
          String t = "000" + Integer.toHexString(c);
          sb.append("\\u").append(t.substring(t.length() - 4));
        }
        else
        {
          sb.append(c);
        }
        break;
      }
    }
    sb.append('"');
    return sb.toString();
  }
  
  public static String dtoToJson(Object obj)
  {
    Class<?> classInfo = obj.getClass();
    Field[] fields = classInfo.getFields();
    String fieldName = "";
    Class<?> fieldType = null;
    Object value = "";
    
    StringBuffer sb = new StringBuffer(512);
    sb.append("{");
    try
    {
      for (int i = 0; i < fields.length; i++)
      {
        fieldName = fields[i].getName();
        fieldType = fields[i].getType();
        value = fields[i].get(obj);
        if (i > 0) {
          sb.append(",");
        }
        sb.append("\"" + fieldName + "\":");
        if (fieldType == Date.class) {
          sb.append(dateToJsonStr((Date)value));
        } else if (fieldType == Timestamp.class) {
          sb.append(dateToJsonStr((Timestamp)value));
        } else {
          sb.append(JSONUtils.valueToString(value));
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    sb.append("}");
    
    return sb.toString();
  }
  
  public static String outParamToJson(Object obj)
    throws EduException
  {
    Class c = obj.getClass();
    if (c == LinkedList.class)
    {
      List list = (List)obj;
      
      return getJSONFromList(list);
    }
    if (c == String.class) {
      return getSuccessJSONStr(obj);
    }
    if (c == Integer.class) {
      return getSuccessJSONStr(obj);
    }
    StringBuffer sb = new StringBuffer(2048);
    
    sb.append("{\"success\":true,\n");
    sb.append("\"result\":\"\"");
    sb.append(",\"data\":" + dtoToJson(obj) + "}");
    
    return sb.toString();
  }
  
  private static String dateToJsonStr(Date date)
  {
    if (date == null) {
      return "\"\"";
    }
    return "\"" + DateUtils.dateToString(date) + "\"";
  }
  
  private static String dateToJsonStr(Timestamp date)
  {
    if (date == null) {
      return "\"\"";
    }
    return "\"" + DateUtils.timestampToString(date) + "\"";
  }
}
