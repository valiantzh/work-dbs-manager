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
 * <p>Description: 箱体信息查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APBoxQry extends ActionBean {

    public java.util.List<OutParamAPBoxQry> doBusiness(InParamAPBoxQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        java.util.List<OutParamAPBoxQry> outList = new java.util.LinkedList<OutParamAPBoxQry>();

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        String limitSQL = "";
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.add("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("DeskNo", in.DeskNo);
        whereSQL.checkAdd("BoxNo", in.BoxNo);
        whereSQL.checkAdd("BoxStatus", in.BoxStatus);
        whereSQL.checkAdd("LockStatus", in.LockStatus);
        whereSQL.checkAdd("FaultStatus", in.FaultStatus);
        if(StringUtils.isNotEmpty(in.TerminalName))
            whereSQL.checkAdd("TerminalName"," LIKE ", "%" + in.TerminalName + "%");

        String sql = "SELECT * FROM V_TBBox2 a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;

        java.util.LinkedList list = new java.util.LinkedList();
        list.add("TerminalNo,DeskNo,DeskBoxNo");

        RowSet rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);
        while (RowSetUtils.rowsetNext(rset)) {
            OutParamAPBoxQry outParam = new OutParamAPBoxQry();
            outParam.TerminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
            outParam.TerminalName = RowSetUtils.getStringValue(rset, "TerminalName");
            outParam.BoxNo = RowSetUtils.getStringValue(rset, "BoxNo");
            outParam.BoxType = RowSetUtils.getStringValue(rset, "BoxType");
            outParam.BoxTypeName = RowSetUtils.getStringValue(rset, "BoxTypeName");
            outParam.DeskNo = RowSetUtils.getIntValue(rset, "DeskNo");
            outParam.DeskBoxNo = RowSetUtils.getIntValue(rset, "DeskBoxNo");
            outParam.BoxStatus = RowSetUtils.getStringValue(rset, "BoxStatus");
            outParam.LockStatus = RowSetUtils.getStringValue(rset, "LockStatus");
            outParam.FaultStatus = RowSetUtils.getStringValue(rset, "FaultStatus");
            outList.add(outParam);
        }
        return outList;
    }
}
