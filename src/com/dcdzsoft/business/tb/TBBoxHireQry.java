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
 * <p>Title: 智能储物柜系统</p>
 * <p>Description: 箱租用金额查询 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class TBBoxHireQry extends ActionBean
{

    public RowSet doBusiness(InParamTBBoxHireQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.验证输入参数是否有效，如果无效返回-1。

		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		
		
		// ///////////////////////////////////////////////////////////
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("BoxType", in.BoxType);
        //whereSQL.checkAdd("BoxTypeName", in.BoxTypeName);
        if(StringUtils.isNotEmpty(in.BoxTypeName))
            whereSQL.add("BoxTypeName", " LIKE ", "%" + in.BoxTypeName + "%");
        String sql = "";
        
        //
        TBServerBoxTypeDAO serverBoxTypeDAO = daoFactory.getTBServerBoxTypeDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TerminalNo", in.TerminalNo);
        if(StringUtils.isEmpty(in.TerminalNo) || serverBoxTypeDAO.isExist(whereCols) < 1){
            //统一格口收费查询
            sql = "SELECT ''AS TerminalNo,''AS TerminalName,BoxType,BoxTypeName,MBBoxType,ChargeAmt,Remark FROM TBBoxType a WHERE 1=1 "
                    + whereSQL.getPreparedWhereSQL() +  " ORDER BY BoxType";
        }else{
            //柜体格口收费查询
            whereSQL.add("TerminalNo", in.TerminalNo);
            sql = "SELECT * FROM V_TBBoxTypeCharge a WHERE 1=1 "
                    + whereSQL.getPreparedWhereSQL() +  " ORDER BY BoxType";
        }
        
        rset = dbSession.executeQuery(sql, whereSQL);

        return rset;
    }
}
