package com.dcdzsoft.business.mb;

import java.sql.Date;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.businessproxy.MonitorProxy;
import com.dcdzsoft.client.web.MBWebClientAdapter;
import com.dcdzsoft.constant.Constant;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.constant.SysDict;
import com.dcdzsoft.dao.MBSignInfoDAO;
import com.dcdzsoft.dao.PTInBoxPackageDAO;
import com.dcdzsoft.dto.business.InParamMBDeviceAlertQry;
import com.dcdzsoft.dto.business.InParamMBDeviceSignQry;
import com.dcdzsoft.dto.business.InParamMBSendEmail;
import com.dcdzsoft.dto.business.InParamMBSendReminderMsg;
import com.dcdzsoft.dto.business.InParamTBTerminalListQry;
import com.dcdzsoft.dto.function.PTInBoxPackage;
import com.dcdzsoft.email.EmailInfo;
import com.dcdzsoft.email.EmailSenderManager;
import com.dcdzsoft.sda.db.JDBCFieldArray;
import com.dcdzsoft.sda.db.PreparedWhereExpression;
import com.dcdzsoft.sda.db.RowSetUtils;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.StringUtils;
/**
 *设备离线或异常发送邮件
 * @author cyf
 *
 */
public class MBSendAlertEmail extends ActionBean{
	public int doBusiness(InParamMBSendEmail in) throws EduException
	{
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		int result = 0;
		RowSet rset = null;//查询签到信息，
		RowSet rset1 = null;//直到当前离线15分钟的设备号查询报警日志获取，报警类型，状态名称
		EmailInfo emailInfo = new EmailInfo();//邮件信息
	
	    //1.	验证输入参数是否有效，如果无效返回-1。
//		if (StringUtils.isEmpty(in.TerminalNo))
//			throw new EduException(ErrorCode.ERR_PARMERR);
	
		java.sql.Timestamp sysDateTime;
		sysDateTime = utilDAO.getCurrentDateTime();
	
		//1.查询设备签到状态,找出离线设备，判断离线距当前时间是否超过限定时间，
		InParamMBDeviceSignQry inParam0 = new InParamMBDeviceSignQry();
		inParam0.OnlineStatus = "0";
		rset = offlineAlert(inParam0);		
		String beginContent = "<table><tr>"
		 		+ "<th>设备号</th>"
		 		+ "<th>设备名称</th>"
			    + "<th>地址</th>"
			    + "<th>报警种类</th>"
			    + "<th>在线状态</th>"
			    + "<th>故障箱号</th>"
			    + "<th>报告时间</th>"     	
			    + "</tr>";
		long delayInMs = (1000*60*Constant.LOCKER_OFFLINE_DELAY_MINUTES);//延迟15分钟，发送离线通知消息
		long timestamp1 = sysDateTime.getTime()-delayInMs;
		long timestamp2   = sysDateTime.getTime()-delayInMs*2;
		
		InParamMBDeviceAlertQry inParam1 = new InParamMBDeviceAlertQry();
		StringBuffer content1 = new StringBuffer();//离线异常的html语句
		while(RowSetUtils.rowsetNext(rset)){
			
			String lastModifyTime = RowSetUtils.getStringValue(rset, "LastModifyTime");
			String terminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			long time = DateUtils.stringToTimestamp(lastModifyTime).getTime();
			if(time <= timestamp1 && time > timestamp2){
				PreparedWhereExpression whereSQL = new PreparedWhereExpression();
				whereSQL.checkAdd("TerminalNo", terminalNo);
				whereSQL.checkAdd("LogTime", lastModifyTime);
				rset1 = otherAlerts(whereSQL);
				while(RowSetUtils.rowsetNext(rset1)){
					content1.append(emailContent(rset,rset1));
				}
			}
		}	
		//System.out.println("离线异常===》"+content1);
		
		//2.查询其他异常信息写入正文
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.checkAdd("AlertType","<>",SysDict.ALERT_TYPE_NETWORKCLOSED);//取其他异常
		rset = otherAlerts(whereSQL);
		StringBuffer content2 = new StringBuffer();//其他异常的html语句
		while(RowSetUtils.rowsetNext(rset)){
			Date logTime = RowSetUtils.getDateValue(rset, "LogTime");
			long time = logTime.getTime();
			if(time > timestamp1){//其他异常只在设定时间内发送一次，超过不再发送
				content2.append(emailContent(rset));
			}
		}
		//System.out.println("其他异常===》"+content2);

		//没有异常信息不发送
		if(content1.length()==0 && content2.length()==0){
			return result;
		}
		//设置邮件发送
		String emailContent = beginContent+content1+content2;
		emailInfo.setSubject("柜体异常邮件报告");
		emailInfo.setContent(emailContent);
		//发送邮件
		try {
			System.out.println("正在发送邮件---");
			EmailSenderManager.getInstance().sendHtmlMail(emailInfo);
		} catch (Exception e) {
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDEMAILFAIL);			
		}	
	   return result;
	}
	//离线异常，从RowSet集合中取出记录放入email正文
	public String emailContent(RowSet rset,RowSet rset1){
		StringBuffer htmlContent = new StringBuffer();
		try {
			 htmlContent.append("<tr align='center'><td>")
			 		.append(rset.getString("TerminalNo")).append("</td>")                      //设备号
					.append("<td>").append(rset.getString("TerminalName")).append("</td>")     //设备名称
					.append("<td>").append(rset.getString("Location")).append("</td>")         //地址
					.append("<td>").append(rset1.getString("AlertTypeName")).append("</td>")   //报警种类名称
					.append("<td>").append(rset1.getString("OnlineStatusName")).append("</td>")//在线状态
					.append("<td>").append("").append("</td>")		   						   //故障箱门
					.append("<td>").append(rset.getString("LastModifyTime")).append("</td>")   //最后修改时间
					.append("</tr>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return htmlContent.toString();
	}
	//其他异常，从RowSet集合中取出记录放入email正文
	public String emailContent(RowSet rset){
		StringBuffer htmlContent = new StringBuffer();
		try {
			 htmlContent.append("<tr align='center'><td>")
			 		.append(rset.getString("TerminalNo")).append("</td>")                     //设备号
					.append("<td>").append(rset.getString("TerminalName")).append("</td>")    //设备名称
					.append("<td>").append(rset.getString("Location")).append("</td>")        //地址
					.append("<td>").append(rset.getString("AlertTypeName")).append("</td>")   //报警种类名称
					.append("<td>").append(rset.getString("OnlineStatusName")).append("</td>")//在线状态
					.append("<td>").append(rset.getString("BoxNo")).append("</td>")           //故障箱门，如果其他异常则为空
					.append("<td>").append(rset.getString("LogTime")).append("</td>")   	  //报告时间
					.append("</tr>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return htmlContent.toString();
	}
	//查询离线异常的柜体信息列表
	public RowSet offlineAlert(InParamMBDeviceSignQry inParam0){
		RowSet rset = null;
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		String limitSQL = " ";
	    whereSQL.checkAdd("OnlineStatus", inParam0.OnlineStatus);
	    String sql = "SELECT * FROM V_MBSignInfo a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
	    try {
			rset = dbSession.executeQuery(sql,whereSQL);
			
		} catch (EduException e) {
			e.printStackTrace();
		} 
	    return rset;
	}
	//查询不是网络异常的设备
	public RowSet otherAlerts(PreparedWhereExpression whereSQL){		
		RowSet rset = null;
		String limitSQL = " ";
		String sql = "SELECT * FROM V_TerminalAlertEmail WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
	    try {
			rset = dbSession.executeQuery(sql,whereSQL);
			
		} catch (EduException e) {
			e.printStackTrace();
		} 
	    return rset;		
	}
	

}
