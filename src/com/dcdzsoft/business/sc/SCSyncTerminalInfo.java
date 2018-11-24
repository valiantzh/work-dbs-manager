package com.dcdzsoft.business.sc;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.GServer;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.guotong.GuotongManager;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.businessproxy.BusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 人工同步柜体信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncTerminalInfo extends ActionBean
{
    public int doBusiness(InParamSCSyncTerminalInfo in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		String limitSQL = "";
		OPOperOnline operOnline = null;
        
        if(StringUtils.isNotEmpty(in.OperID))
        {
            operOnline = commonDAO.isOnline(in.OperID);
            limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        }

		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		if(operOnline!=null){
		    OPOperatorLog log = new OPOperatorLog();
	        log.OperID = in.OperID;
	        log.FunctionID = in.getFunctionID();
	        log.OccurTime = sysDateTime;
	        log.StationAddr = operOnline.LoginIPAddr;
	        log.Remark = "";

	        commonDAO.addOperatorLog(log);
		}
		
		
		if(ApplicationConfig.getInstance().isRegisterToPartner())
		{
			TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
	    	TBTerminal terminal = new TBTerminal();
	    	terminal.TerminalNo = in.TerminalNo;
	    	
	    	terminal = terminalDAO.find(terminal);
		    
	    	if(ApplicationConfig.getInstance().isNewGuotongApi()){
	    	    if(StringUtils.isEmpty(terminal.MBDeviceNo)){
	    	        //#start 注册并获取运营方设备ID
	    	        String resMsg = GuotongManager.getInstance().sentRegisterTerminal(terminal,"A");
	    	        //F0#|T201601:310009314;
	    	        if(resMsg.startsWith("F0#|")){
	    	            String temp = resMsg.substring(4);//T201601:310009314;
	    	            String[] temp2 = temp.split(";");
	    	            String[] lockerid = temp2[0].split(":");
	    	            if(lockerid.length == 2 && in.TerminalNo.equals(lockerid[0])){
	    	                terminal.MBDeviceNo = lockerid[1];
	    	                //记录运营方柜体编号
	    	                JDBCFieldArray setCols1 = new JDBCFieldArray();
	    	                JDBCFieldArray whereCols1 = new JDBCFieldArray();
	    	                setCols1.add("RegisterFlag", "1");
	    	                setCols1.add("MBDeviceNo", lockerid[1]);
	    	                whereCols1.add("TerminalNo", in.TerminalNo);

	    	                terminalDAO.update(setCols1, whereCols1);
	    	            }else{
	    	                throw new EduException(resMsg);
	    	            }
	    	        }else{
	    	            throw new EduException(resMsg);
	    	        }
	    	        //#end
	            }else{
	                //同步柜体信息
	                String resMsg = GuotongManager.getInstance().sentRegisterTerminal(terminal,"U");
	                if(!resMsg.startsWith("F0")){
	                    throw new EduException(resMsg);
	                }
	            }
	    	    
	    	    //#start同步格口信息
	    	    PreparedWhereExpression whereSQL = new PreparedWhereExpression();
	            whereSQL.add("TerminalNo", terminal.TerminalNo);
	            String sql = "SELECT * FROM TBServerBox WHERE 1=1" 
	                    + whereSQL.getPreparedWhereSQL() + " ORDER BY  DeskNo,DeskBoxNo";
	            int boxNum = 0;
	            StringBuffer boxInfoList = new StringBuffer(100);
	            RowSet rset = dbSession.executeQuery(sql,whereSQL);
	            while(RowSetUtils.rowsetNext(rset)){
	                //BoxNo,BoxName,DeskNo,DeskBoxNo,BoxType,BoxStatus~...
	                StringBuffer boxinfo = new StringBuffer(64);
	                boxinfo.append(RowSetUtils.getStringValue(rset, "BoxNo")+",");
	                boxinfo.append(RowSetUtils.getStringValue(rset, "BoxName")+",");
	                boxinfo.append(RowSetUtils.getIntValue(rset, "DeskNo")+",");
	                boxinfo.append(RowSetUtils.getIntValue(rset, "DeskBoxNo")+",");
	                boxinfo.append(RowSetUtils.getStringValue(rset, "BoxType")+",");
	                boxinfo.append(RowSetUtils.getStringValue(rset, "BoxStatus"));
	                if(boxInfoList.length()>0){
	                    boxInfoList.append("~"+boxinfo); 
	                }else{
	                    boxInfoList.append(boxinfo);
	                }
	                boxNum++;
	            }
	            if(boxNum > 0){
	                String resMsg = GuotongManager.getInstance().sentBoxInfo(terminal,boxInfoList.toString(),"A");
	                if(!resMsg.startsWith("F0")){
	                    throw new EduException(resMsg);
	                }
	            }
	            //#end
	    	}else{
	    	    String resMsg = GuotongManager.getInstance().sentRegisterTerminal(terminal,"A");
	            if(!resMsg.equals("0")){
	                throw new EduException(resMsg);
	            }
	    	}
	    	
		}
		else
		{
			//String PROXY_PACKAGE_PREFIX = "com.dcdzsoft.businessproxy.";
			//String PROXY_CLASS_NAME = PROXY_PACKAGE_PREFIX + ApplicationConfig.getInstance().getInterfacePackage();
			try
			{
				//Class proxyClass = Class.forName(PROXY_CLASS_NAME);
			    Class proxyClass = GServer.getLockerBusinussProxyClass();
				BusinessProxy businessProxy = (BusinessProxy)proxyClass.newInstance();
				
				businessProxy.doBusiness(in, in.TerminalNo);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}

        return result;
    }
}
