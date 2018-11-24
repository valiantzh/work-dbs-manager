package com.dcdzsoft.business.sc;

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
 * <p>Description: 投递记录查询 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SCPushDataQueueQryCount extends ActionBean {

    public int doBusiness(InParamSCPushDataQueueQryCount in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。

        String limitSQL = "";
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("ServiceName", in.ServiceName);
        whereSQL.checkAdd("MsgUid", in.MsgUid);
        String sql = "SELECT * FROM SCPushDataQueue a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
        result = dbSession.executeQueryCount(sql,whereSQL);
 
        return result;
    }
}
