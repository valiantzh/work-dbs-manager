package com.dcdzsoft.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.BusiHandlerEntity;
import com.dcdzsoft.config.BusinessConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.config.ErrorMsgConfig;
import com.dcdzsoft.sda.db.RowSetUtils;


/**
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author wangzl
 * @version 1.0
 */
public class WebUtils {
    protected WebUtils() {
    }
    public static String readRequestData(HttpServletRequest request) {
        String json = null;
        StringBuilder sb = new StringBuilder();
        try {
            ServletInputStream in = request.getInputStream();
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1)
                sb.append(new String(buffer, 0, len, "UTF-8"));

            json = sb.toString();
        } catch (Exception e) { /* report an error */ }
        
        
        return json;
    }
    public static void checkRequestLegality(String uuid, String OperID, HttpSession session) throws
            EduException {
        //也需要检查OperID
        Object objOperID = session.getAttribute("OperID");
        Object objUuid = session.getAttribute("uuid");

        String sessionOperID = objOperID == null ? "" : objOperID.toString();
        String sessionUuid = objUuid == null ? "" : objUuid.toString();

        if (StringUtils.isEmpty(sessionOperID)
            || StringUtils.isEmpty(sessionUuid))
            throw new EduException(ErrorMsgConfig.MSG_SESSION_TIMEOUT);

        if (!OperID.equals(sessionOperID))
            throw new EduException(ErrorMsgConfig.MSG_SESSION_TIMEOUT);

        if (!sessionUuid.equals(uuid))
            throw new EduException(ErrorMsgConfig.MSG_REPEAT_SIGN);
    }

    public static void checkRequestLegality(String OperID, HttpSession session) throws
            EduException {
        //也需要检查OperID
        Object objOperID = session.getAttribute("OperID");

        String sessionOperID = objOperID == null ? "" : objOperID.toString();

        if (StringUtils.isEmpty(sessionOperID))
            throw new EduException(ErrorMsgConfig.MSG_SESSION_TIMEOUT);

        if (!OperID.equals(sessionOperID))
            throw new EduException(ErrorMsgConfig.MSG_SESSION_TIMEOUT);
    }


    public static Object buildBussinessDTOParam(Class c,
                                                HttpServletRequest request,
                                                BusiHandlerEntity entity) {
        Object o = null;
        try {
            o = c.newInstance();

            Field[] fields = c.getFields();
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                Class type = fields[i].getType();
                String methodName = "set" + name;
                String paramName = name;

                if (name.equals("FunctionID"))
                    continue;

                if (entity.getColMap() != null) {
                    Object obj = entity.getColMap().get(name);
                    if (obj != null)
                        paramName = obj.toString();
                }

                String typeName = type.getName();
                if (typeName.equals("java.lang.String")) {
                    String value = "";
                    if (name.equals("OperID"))
                        value = getOperIDAttribute(request.getSession());
                    else if (name.equals("FuncMenuID"))
                        value = entity.getMyFuncMenuID();
                    else {
                        value = getStringParameter(paramName, request);
                    }

                    if (StringUtils.isNotEmpty(value)) {
                        Method method = c.getMethod(methodName,
                                new Class[] {String.class});
                        method.invoke(o, new Object[] {value});
                    }
                } else if (typeName.equals("int")) {
                    //System.out.println();
                    if (name.equals("recordBegin"))
                        paramName = "start";
                    if (name.equals("recordNum"))
                        paramName = "limit";

                    int value = getIntParameter(paramName, request);
                    if ("start".equals(paramName))
                        value = value + 1;

                    if (name.equals("recordBegin") || name.equals("recordNum")) {
                        Field field = c.getField(name);
                        field.setInt(o, value);
                    } else {
                        Class partypes[] = new Class[1];
                        partypes[0] = Integer.TYPE;
                        Method method = c.getMethod(methodName, partypes);
                        method.invoke(o, new Object[] {new Integer(value)});
                    }
                } else if (typeName.equals("long")) {
                    long value = getlongParameter(paramName, request);

                    Class partypes[] = new Class[1];
                    partypes[0] = Long.TYPE;
                    Method method = c.getMethod(methodName, partypes);
                    method.invoke(o, new Object[] {new Long(value)});
                } else if (typeName.equals("double")) {
                    Class partypes[] = new Class[1];
                    partypes[0] = Double.TYPE;

                    Double value = new Double(getDoubleParameter(paramName,
                            request));
                    Method method = c.getMethod(methodName, partypes);
                    method.invoke(o, new Object[] {value});
                } else if (typeName.equals("java.sql.Date")) {
                    String value = "";
                    value = getStringParameter(paramName, request);

                    if (StringUtils.isNotEmpty(value)) {
                        Method method = c.getMethod(methodName,
                                new Class[] {java.sql.Date.class});
                        method.invoke(o, new Object[] {DateUtils.stringToDate(value)});
                    }
                }
                else if (typeName.equals("java.sql.Timestamp")) {
                    String value = "";
                    value = getStringParameter(paramName, request);

                    if (StringUtils.isNotEmpty(value)) {
                        Method method = c.getMethod(methodName,
                                new Class[] {java.sql.Timestamp.class});
                        method.invoke(o, new Object[] {DateUtils.stringToTimestamp(value)});
                    }
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return o;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    public static String getUmid(){
        return getUUID().replaceAll("-", "");
    }
    public static String getOperIDAttribute(HttpSession session) {
        Object obj = session.getAttribute("OperID");
        if (obj != null)
            return obj.toString();
        else
            return "";
    }

    public static String getSessionAttribute(String name, HttpSession session) {
        Object obj = session.getAttribute(name);
        if (obj != null)
            return obj.toString();
        else
            return "";
    }

    public static String getStringParameter(String name,
                                            HttpServletRequest request) {
        String value = "";
        if (request != null) {
            value = StringUtils.trimToEmpty(request.getParameter(name));
            if (StringUtils.isEmpty(value))
                return "";
        }

        return value;
    }

    public static int getIntParameter(String name, HttpServletRequest request) {
        String value = "";
        if (request != null) {
            value = StringUtils.trimToEmpty(request.getParameter(name));
            if (StringUtils.isEmpty(value))
                return 0;
        }

        return NumberUtils.parseInt(value);
    }

    public static long getlongParameter(String name, HttpServletRequest request) {
        String value = "";
        if (request != null) {
            value = StringUtils.trimToEmpty(request.getParameter(name));
            if (StringUtils.isEmpty(value))
                return 0;
        }

        return NumberUtils.parseLong(value);
    }


    public static double getDoubleParameter(String name,
                                            HttpServletRequest request) {
        String value = "";
        if (request != null) {
            value = StringUtils.trimToEmpty(request.getParameter(name));
            if (StringUtils.isEmpty(value))
                return 0.0D;
        }

        return NumberUtils.parseDouble(value);
    }

    public static String formatDbDate(String date) {
        String str = "";

        if (StringUtils.isEmpty(date) || date.length() != 8)
            return date;

        str = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" +
              date.substring(6, 8);

        return str;
    }

    public static String formatDbTime(String time) {
        String str = "";

        if (StringUtils.isEmpty(time) || time.length() != 14)
            return time;

        str = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" +
              time.substring(6, 8)
              + " " + time.substring(8, 10) + ":" + time.substring(10, 12) +
              ":" + time.substring(12, 14);

        return str;
    }

    public static String buildDepartTreeJSONStr(RowSet menuSet)
			throws EduException {
		StringBuffer sb = new StringBuffer(256);
		int count = 0;

		sb.append("[");
		while (RowSetUtils.rowsetNext(menuSet)) {
			count++;

			String LeafFlag = RowSetUtils.getStringValue(menuSet, "LeafFlag");

			boolean leaf = false;
			String name = JsonUtils.encodeQuotedString(RowSetUtils
					.getStringValue(menuSet, "DepartmentName"));
			String id = JsonUtils.encodeQuotedString(RowSetUtils
					.getStringValue(menuSet, "DepartmentID"));

			if ("1".equals(LeafFlag))
				leaf = true;

			if (count > 1)
				sb.append(",");
			sb.append("{id:" + id + ",text:" + name + ",leaf:" + leaf + "}");
		}

		sb.append("]");

		return sb.toString();
	}

	public static String buildFuncMenuJSONStr(RowSet menuSet, boolean isOper) /*
																			 * true
																			 * OperID
																			 * false
																			 * RoleID
																			 */
	throws EduException {
		StringBuffer sb = new StringBuffer(256);
		int count = 0;

		sb.append("[");
		while (RowSetUtils.rowsetNext(menuSet)) {
			count++;

			int LeafFlag = RowSetUtils.getIntValue(menuSet, "LeafFlag");

			boolean leaf = false;
			String name = JsonUtils.encodeQuotedString(RowSetUtils
					.getStringValue(menuSet, "MenuName"));
			String id = JsonUtils.encodeQuotedString(RowSetUtils
					.getStringValue(menuSet, "MenuID"));

			boolean checked = false;
			if (isOper) {
				String OperID = RowSetUtils.getStringValue(menuSet, "OperID");
				if (StringUtils.isNotEmpty(OperID))
					checked = true;
			} else {
				int RoleID = RowSetUtils.getIntValue(menuSet, "RoleID");
				if (RoleID > 0)
					checked = true;
			}

			if (LeafFlag == 1)
				leaf = true;

			if (count > 1)
				sb.append(",");
			sb.append("{id:" + id + ",text:GMsg[" + name + "],leaf:" + leaf
					+ ",checked:" + checked + "}");
		}

		sb.append("]");

		return sb.toString();
	}

	public static String buildSpeRightJSONStr(RowSet speRightSet, boolean isOper) /*
																				 * true
																				 * OperID
																				 * false
																				 * RoleID
																				 */
	throws EduException {
		StringBuffer sb = new StringBuffer(256);
		int count = 0;

		sb.append("[");
		while (RowSetUtils.rowsetNext(speRightSet)) {
			count++;

			boolean leaf = true;
			String spePrivName = JsonUtils.encodeQuotedString(RowSetUtils
					.getStringValue(speRightSet, "SpePrivName"));
			String spePrivID = JsonUtils.encodeQuotedString(RowSetUtils
					.getStringValue(speRightSet, "SpePrivID"));

			boolean checked = false;
			if (isOper) {
				String OperID = RowSetUtils.getStringValue(speRightSet,
						"OperID");
				if (StringUtils.isNotEmpty(OperID))
					checked = true;
			} else {
				int RoleID = RowSetUtils.getIntValue(speRightSet, "RoleID");
				if (RoleID > 0)
					checked = true;
			}

			if (count > 1)
				sb.append(",");
			sb.append("{id:" + spePrivID + ",text:GMsg[" + spePrivName + "],leaf:"
					+ leaf + ",checked:" + checked + "}");
		}

		sb.append("]");

		return sb.toString();
	}

	public static String buildClientInitJSONStr(RowSet menuSet,
			RowSet operOnlineSet, String sessionid, String uuid,
			String OperPassword) throws EduException {
		StringBuffer sb = new StringBuffer(2048);
		sb.append("{uuid:" + JsonUtils.encodeQuotedString(uuid) + "");
		sb.append(",jsessionid:" + JsonUtils.encodeQuotedString(sessionid) + "");

		// 操作员在线信息
		java.sql.Timestamp LoginInTime = null;
		while (RowSetUtils.rowsetNext(operOnlineSet)) {
			String LastLoginIP = RowSetUtils.getStringValue(operOnlineSet,
					"LastLoginIP");
			java.sql.Timestamp LastLoginTime = RowSetUtils.getTimestampValue(
					operOnlineSet, "LastLoginTime");
			LoginInTime = RowSetUtils.getTimestampValue(operOnlineSet,
					"LoginInTime");

			String OperName = RowSetUtils.getStringValue(operOnlineSet,
					"OperName");
			String OperID = RowSetUtils.getStringValue(operOnlineSet, "OperID");
			String CorporationID = RowSetUtils.getStringValue(operOnlineSet,
					"DepartmentID");
			int OperType = RowSetUtils.getIntValue(operOnlineSet, "OperType");
			String PreVersion = RowSetUtils.getStringValue(operOnlineSet,
					"PreVersion");
			String CurrentVersion = RowSetUtils.getStringValue(operOnlineSet,
					"CurrentVersion");

			boolean isReloadModule = false;
			if (!CurrentVersion.equals(PreVersion))
				isReloadModule = true;

			sb.append(",\"operid\":" + JsonUtils.encodeQuotedString(OperID) + "");
			sb.append(",\"opername\":" + JsonUtils.encodeQuotedString(OperName) + "");
			sb.append(",\"departmentid\":" + JsonUtils.encodeQuotedString(CorporationID)
					+ "");
			sb.append(",\"opertype\":"+ JsonUtils.encodeQuotedString(String.valueOf(OperType)) + "");
			sb.append(",\"lastloginip\":" + JsonUtils.encodeQuotedString(LastLoginIP)
					+ "");
			sb.append(",\"lastlogintime\":" + LastLoginTime.getTime());
			sb.append(",\"isreloadmodule\":" + isReloadModule);
		}

		// 菜单信息
		int count = 0;
		int tMenuCount = 0;
		int cMenuCount = 0;
		int fMenuCount = 0;
		int actionCount = 0;

		StringBuffer sbTmenu = new StringBuffer(1024);
		StringBuffer sbCmenu = new StringBuffer(1024);
		StringBuffer sbFmenu = new StringBuffer(1024);
		StringBuffer sbAction = new StringBuffer(1024);

		sbTmenu.append("[");
		sbCmenu.append("[");
		sbFmenu.append("[");
		sbAction.append("[");

		while (RowSetUtils.rowsetNext(menuSet)) {
			count++;

			int MenuLevel = RowSetUtils.getIntValue(menuSet, "MenuLevel");
			String name = JsonUtils.encodeQuotedString(RowSetUtils.getStringValue(
					menuSet, "MenuName"));
			String id = JsonUtils.encodeQuotedString(RowSetUtils.getStringValue(menuSet,
					"MenuID"));
			String action = RowSetUtils.getStringValue(menuSet, "Action")
					.replaceAll("\"", "'");
			String icon = RowSetUtils.getStringValue(menuSet, "Icon");
			if (StringUtils.isEmpty(icon))
				icon = BusinessConfig.getInstance().getTreeleafIcon();

			icon = JsonUtils.encodeQuotedString(icon);

			if (StringUtils.isNotEmpty(action)) {
				actionCount++;
				if (actionCount > 1)
					sbAction.append(",");
				sbAction.append("{id:" + id + ",action:" + action + "}");
			}

			if (MenuLevel == 1) {
				if (count > 1)
					sbTmenu.append(",");
				if (cMenuCount > 0)
					sbCmenu.append("]},");

				sbTmenu.append("{title:GMsg[" + name + "],id:" + id + ",iconCls:"
						+ icon + "}");
				sbCmenu.append("{text:GMsg[" + name + "],id:" + id + ",children:[");

				cMenuCount = 0;
				tMenuCount++;
			} else if (MenuLevel == 2) {
				if (cMenuCount > 0)
					sbCmenu.append(",");
				sbCmenu.append("{text:GMsg[" + name + "],id:" + id
						+ ",leaf:true,icon:" + icon + "}");

				tMenuCount = 0;
				cMenuCount++;
			} else if (MenuLevel == 3) {
				fMenuCount++;
				if (fMenuCount > 1)
					sbFmenu.append(",");
				sbFmenu.append(id);
			}
		}

		if (count > 0)
			sbCmenu.append("]}]");
		else
			sbCmenu.append("]");

		sbTmenu.append("]");
		sbFmenu.append("]");
		sbAction.append("]");

		sb.append(",tmenu :" + sbTmenu.toString() + "");
		sb.append(",cmenu :" + sbCmenu.toString() + "");
		sb.append(",fmenu :" + sbFmenu.toString() + "");
		sb.append(",actions :" + sbAction.toString() + "");
		sb.append("}");

		return sb.toString();
	}

}
