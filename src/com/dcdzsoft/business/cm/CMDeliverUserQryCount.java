package com.dcdzsoft.business.cm;

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
 * <p>Description: 投递服务用户务查询数量 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class CMDeliverUserQryCount extends ActionBean
{

    public int doBusiness(InParamCMDeliverUserQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);


		String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("CustomerID", in.CustomerID);
        if(StringUtils.isNotEmpty(in.FirstName))
            whereSQL.add("FirstName", " LIKE ", "%" + in.FirstName + "%");
        if(StringUtils.isNotEmpty(in.LastName))
            whereSQL.add("LastName", " LIKE ", "%" + in.LastName + "%");
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        whereSQL.checkAdd("ServiceStatus", in.ServiceStatus);
        whereSQL.checkAdd("SpecialFlag", in.SpecialFlag);
        String sql = "SELECT COUNT(CustomerID) FROM CMDeliverServiceUser a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;

        result = dbSession.executeQueryCount(sql,whereSQL);

        return result;
    }
}
