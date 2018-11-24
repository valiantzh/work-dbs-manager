package com.dcdzsoft.business.tb;

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
 * <p>Title: 智能储物柜（码柜）运营系统</p>
 * <p>Description: 柜格口费用查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class TBTerminalHireQry extends ActionBean
{

    public RowSet doBusiness(InParamTBTerminalHireQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.验证输入参数是否有效，如果无效返回-1。


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		String limitSQL = "";
        if(StringUtils.isNotEmpty(in.OperID))
        {
            limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        }
		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		if(StringUtils.isNotBlank(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
		//whereSQL.checkAdd("TerminalName",in.TerminalName);
        if(StringUtils.isNotEmpty(in.TerminalName))
            whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
        
        if(StringUtils.isNotEmpty(in.Location))
            whereSQL.add("Location", " LIKE ", "%" + in.Location + "%");
        
        whereSQL.checkAdd("BoxType", in.BoxType);
        whereSQL.checkAdd("ChargeType", in.ChargeType);
        String sql = "SELECT * FROM V_TBTerminalCharge a WHERE 1=1 "
                + whereSQL.getPreparedWhereSQL() + limitSQL;

        java.util.LinkedList list = new java.util.LinkedList();
        list.add("TerminalNo,ChargeType");

        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);

        return rset;
    }
}
