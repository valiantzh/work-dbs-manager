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
 * <p>Title: 智能自提柜运营系统</p>
 * <p>Description: 会员信息查询数量 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class CMCustomerQryCount extends ActionBean
{

    public int doBusiness(InParamCMCustomerQryCount in) throws EduException
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


		String limitSQL = " ";//utilDAO.getLimitSQL(operOnline.DepartmentID);

        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("CustomerID", in.CustomerID);
        if(StringUtils.isNotEmpty(in.CustomerName))
            whereSQL.add("CustomerName", " LIKE ", "%" + in.CustomerName + "%");
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
        whereSQL.checkAdd("CustomerStatus", in.CustomerStatus);
        whereSQL.checkAdd("CustomerType", in.CustomerType);

        String sql = "SELECT COUNT(CustomerID) FROM MBCustomer a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;

        result = dbSession.executeQueryCount(sql,whereSQL);

        return result;
    }
}
