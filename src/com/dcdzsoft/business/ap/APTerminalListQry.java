package com.dcdzsoft.business.ap;

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
 * <p>Description: 设备列表查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APTerminalListQry extends ActionBean
{

    public java.util.List<OutParamAPTerminalListQry> doBusiness(InParamAPTerminalListQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        java.util.List<OutParamAPTerminalListQry> outList = new java.util.LinkedList<OutParamAPTerminalListQry>();
        
        
        String limitSQL = "";
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("TerminalStatus", in.TerminalStatus);
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        if(StringUtils.isNotEmpty(in.TerminalName))
            whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
        String sql = "SELECT * FROM TBTerminal a  WHERE 1=1 "
                + whereSQL.getPreparedWhereSQL() + limitSQL
                + " ORDER BY TerminalNo";
        
        RowSet rset = dbSession.executeQuery(sql, whereSQL);
        
        while (RowSetUtils.rowsetNext(rset)) {
            OutParamAPTerminalListQry outParam = new OutParamAPTerminalListQry();

            outParam.TerminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
            outParam.TerminalName = RowSetUtils.getStringValue(rset, "TerminalName");
            outParam.MBDeviceNo = RowSetUtils.getStringValue(rset, "MBDeviceNo");
            outParam.Location = RowSetUtils.getStringValue(rset, "Location");
            outParam.Latlon = RowSetUtils.getStringValue(rset, "Latlon");
            outParam.TerminalStatus = RowSetUtils.getStringValue(rset, "TerminalStatus");
            outParam.BoxNum   = RowSetUtils.getIntValue(rset, "BoxNum");
            outParam.DeskNum  = RowSetUtils.getIntValue(rset, "DeskNum");
            outParam.Remark  = RowSetUtils.getStringValue(rset, "Remark");
            outParam.CreateTime = Constant.datetimeFromat.format(RowSetUtils.getTimestampValue(rset, "CreateTime"));
            outList.add(outParam);
        }
        return outList;
    }
}
