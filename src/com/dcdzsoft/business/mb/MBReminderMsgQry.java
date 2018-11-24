package com.dcdzsoft.business.mb;

import java.sql.Timestamp;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 待催领信息查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBReminderMsgQry extends ActionBean
{

    public RowSet doBusiness(InParamMBReminderMsgQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
    	
    	ControlParam ctrlParam = ControlParam.getInstance();
    	int reminderDays = NumberUtils.parseInt(ctrlParam.reminderDays);
    	int expiredDays = NumberUtils.parseInt(ctrlParam.recoveryTimeout);
    	if(reminderDays < 1){
    		reminderDays = 1;
    	}
    	if(expiredDays < 1){
    		expiredDays  = 1;
    	}
    	java.sql.Timestamp reminderDate = DateUtils.addTimeStamp(sysDateTime,-reminderDays);
    	java.sql.Timestamp expiredDate = DateUtils.addTimeStamp(sysDateTime,-expiredDays);
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		
		whereSQL.add("TerminalNo", in.TerminalNo);
		whereSQL.add("PackageStatus", SysDict.PACKAGE_STATUS_NORMAL);//add by zxy 在箱正常，才发送催领短信。避免退货在箱订单发送催领短信。
		if(in.reminderHours>= 24){
		    whereSQL.add("LastModifyTime", ">",new Timestamp(expiredDate.getTime()));//StoredTime
		    String sqlStr = "(LastModifyTime > " + StringUtils.addQuote(DateUtils.timestampToString(DateUtils.addTimeStampByHour(sysDateTime, -(in.reminderHours+1)))) 
            + " and LastModifyTime <= "+ StringUtils.addQuote(DateUtils.timestampToString(DateUtils.addTimeStampByHour(sysDateTime, -(in.reminderHours)))) +")";
		    whereSQL.addSQL(" AND (" + sqlStr + ")");
		    //System.out.println(DateUtils.timestampToString(sysDateTime)+",MBReminderMsgQry1  sqlStr="+sqlStr);
		}else{
		    int reminderHours = NumberUtils.parseInt(ctrlParam.getReminderDateTime());
            if(reminderHours == 0){
                whereSQL.add("LastModifyTime", ">",new Timestamp(expiredDate.getTime()));
                //whereSQL.add("LastModifyTime", "<=",new Timestamp(reminderDate.getTime()));
                String sqlStr = "";
                do{
                    int hour = reminderDays*24;
                    sqlStr += "(LastModifyTime > " + StringUtils.addQuote(DateUtils.timestampToString(DateUtils.addTimeStampByHour(sysDateTime, -(hour+1)))) 
                    + " and LastModifyTime <= "+ StringUtils.addQuote(DateUtils.timestampToString(DateUtils.addTimeStampByHour(sysDateTime, -(hour)))) +")";
                    reminderDays ++;//每天提醒？
                    if(reminderDays < expiredDays){
                        sqlStr += " OR ";
                    }else{
                        break;
                    }
                }while(reminderDays < expiredDays);
                //System.out.println(DateUtils.timestampToString(sysDateTime)+",MBReminderMsgQry2  sqlStr="+sqlStr);
                whereSQL.addSQL(" AND (" + sqlStr + ")");
            }else{
                whereSQL.add("StoredDate", ">",expiredDate);
                whereSQL.add("StoredDate", "<=",reminderDate);
            }
		}
		
		String sql = "SELECT TerminalNo,PackageID,StoredTime,PostmanID,CustomerMobile,BlankBoxKey FROM PTInBoxPackage a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();

		rset = dbSession.executeQuery(sql, whereSQL);

        return rset;
    }
}
