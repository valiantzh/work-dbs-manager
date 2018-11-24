package com.dcdzsoft.business.mb;

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
 * <p>Description: 投递信息反馈失败信息查询 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBFeedbackFailQry extends ActionBean
{

    public RowSet doBusiness(InParamMBFeedbackFailQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		//if (StringUtils.isEmpty(in.OperID))
		//	throw new EduException(ErrorCode.ERR_PARMERR);

		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.checkAdd("TerminalNo", in.TerminalNo);
		String sql = "SELECT * FROM PTFeedbackFail WHERE FailureCount <=10 "+ whereSQL.getPreparedWhereSQL() +" ORDER BY FailureTime";

		rset = dbSession.executeQuery(sql,whereSQL);
		
        return rset;
    }
}
