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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 箱体信息列表查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class TBBoxListQry extends ActionBean
{

    public RowSet doBusiness(InParamTBBoxListQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("DeskNo", in.DeskNo);
        whereSQL.checkAdd("BoxNo", in.BoxNo);
        whereSQL.checkAdd("BoxType", in.BoxType);
        
        String viewName = "";
        if(StringUtils.isNotEmpty(in.BoxStatus)){
        	if(!"0".equals(in.BoxStatus)){
        		whereSQL.checkAdd("BoxStatus", in.BoxStatus);
        		 viewName = "V_TBBox";
        	}else{
        		//空箱
        		viewName = "V_TBBoxFree";
        	}
        }
        
        String sql = "SELECT TerminalNo,BoxNo,BoxName FROM "+viewName+" a WHERE 1=1 " 
        		+ whereSQL.getPreparedWhereSQL() + " ORDER BY TerminalNo,DeskNo,DeskBoxNo";;

        rset = dbSession.executeQuery(sql, whereSQL);

        return rset;

    }
}
