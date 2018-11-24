package com.dcdzsoft.business.pm;

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
 * <p>Description: 投递公司删除 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMCompanyDel extends ActionBean {

    public int doBusiness(InParamPMCompanyDel in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.CompanyID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("CompanyID", in.CompanyID);

        if (postmanDAO.isExist(whereCols) > 0)
            throw new EduException(ErrorCode.ERR_FORBIDDELCOMPANY);

        //删除柜子权限
        PMCorpTerminalRightDAO terminalRightDAO = daoFactory.getPMCorpTerminalRightDAO();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("CompanyID", in.CompanyID);

        terminalRightDAO.delete(whereCols1);

        //删除格口权限
        PMCorpBoxRightDAO boxRigthDAO = daoFactory.getPMCorpBoxRightDAO();
        boxRigthDAO.delete(whereCols1);

        //4.	调用PMCompanyDAO.delete()删除企业信息。
        PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
        companyDAO.delete(whereCols);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.CompanyID;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
