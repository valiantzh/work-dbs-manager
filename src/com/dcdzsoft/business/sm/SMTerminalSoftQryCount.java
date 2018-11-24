package com.dcdzsoft.business.sm;

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
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 查询终端软件版本信息数量 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SMTerminalSoftQryCount extends ActionBean
{

    public int doBusiness(InParamSMTerminalSoftQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		String limitSQL = "";
        if(StringUtils.isNotEmpty(in.OperID)){
        	OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        	limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        } 


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
	    whereSQL.checkAdd("DepartmentID", in.DepartmentID);
	    whereSQL.checkAdd("TerminalNo", in.TerminalNo);
	    whereSQL.checkAdd("TerminalName", in.TerminalName);
	    whereSQL.checkAdd("SoftwareID", in.SoftwareID);
	    whereSQL.checkAdd("SoftwareName", in.SoftwareName);
	    whereSQL.checkAdd("SoftwareType", in.SoftwareType);
	    whereSQL.checkAdd("SystemID", in.SystemID);
	    whereSQL.checkAdd("UpdateStatus", in.UpdateStatus);
	    
	    if(StringUtils.isNotBlank(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
	    
	    String sql = "SELECT COUNT(SystemID) FROM V_SMTerminalSoft a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;

	    result = dbSession.executeQueryCount(sql, whereSQL);


        return result;
    }
}
