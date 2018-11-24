package com.dcdzsoft.business.qy;

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
 * <p>Description: 短信使用情况统计 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class QYStat4SMS extends ActionBean
{

    public RowSet doBusiness(InParamQYStat4SMS in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		
		String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		String reportID = DateUtils.timestampToString(sysDateTime);
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		//whereSQL.checkAdd("DepartmentID", in.DepartmentID);
		if(StringUtils.isNotEmpty(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("OccurMonth", in.OccurMonth);
        whereSQL.checkAdd("OccurYear", in.OccurYear);
        
        String sql = "SELECT '" + reportID + "' AS ReportID,DepartmentID,DepartmentName,OccurYear,OccurMonth,SUM(TotalNum) AS TotalNum,SUM(PwdNum) AS PwdNum,SUM(ExpireNum) AS ExpireNum,SUM(ReminderNum) AS ReminderNum,SUM(DynamicNum) AS DynamicNum,SUM(PickupNum) AS PickupNum,SUM(OtherNum) AS OtherNum FROM V_Stat4SMS a WHERE 1=1 ";
		sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL + "GROUP BY DepartmentID,DepartmentName,OccurYear,OccurMonth ";
		
        rset = dbSession.executeQuery(sql, whereSQL);

        return rset;
    }
}
