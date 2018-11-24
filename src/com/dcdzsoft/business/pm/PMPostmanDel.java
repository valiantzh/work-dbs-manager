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
 * <p>Description: 投递员删除 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMPostmanDel extends ActionBean {

    public int doBusiness(InParamPMPostmanDel in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;
    	OPOperOnline operOnline = null ;
        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.PostmanID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        if(StringUtils.isNotEmpty(in.OperID)){
        	operOnline = commonDAO.isOnline(in.OperID);
        }
        
        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        //判断投递员是否存在
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("PostmanID", in.PostmanID);
        if(postmanDAO.isExist(whereCols) < 1){
        	throw new EduException(ErrorCode.ERR_PMPOSTMANNODATA);
        }

        //判断是否已经有投递记录
        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("PostmanID",in.PostmanID);

        if(inboxPackDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_FORBIDDELPOSTMAN);

        PTDeliverHistoryDAO historyDAO = daoFactory.getPTDeliverHistoryDAO();
        if(historyDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_FORBIDDELPOSTMAN);

        //删除柜子权限
        PMManTerminalRightDAO manTerminalRightDAO = daoFactory.getPMManTerminalRightDAO();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("PostmanID", in.PostmanID);

        manTerminalRightDAO.delete(whereCols1);

        //删除格口权限
        PMPostmanBoxRightDAO boxRigthDAO = daoFactory.getPMPostmanBoxRightDAO();
        boxRigthDAO.delete(whereCols1);

        //4.	调用PMCompanyDAO.delete()删除企业信息。
//        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        postmanDAO.delete(whereCols1);



        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        if(operOnline!=null){
        	log.StationAddr = operOnline.LoginIPAddr;
        }
        log.Remark = "";

        commonDAO.addOperatorLog(log);

        return result;
    }
}
