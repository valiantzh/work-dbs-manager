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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 系统配置查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SMSysInfoQry extends ActionBean {

    public RowSet doBusiness(InParamSMSysInfoQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。


        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
        JDBCFieldArray whereColsDummy = new JDBCFieldArray();
        

        rset = systemInfoDAO.select(whereColsDummy);

        return rset;
    }
}
