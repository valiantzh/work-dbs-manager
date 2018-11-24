package com.dcdzsoft.business.op;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;

import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 修改个人密码 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperModPwd extends ActionBean {

    public int doBusiness(InParamOPOperModPwd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.OperOldPassword)
            || StringUtils.isEmpty(in.OperNewPassword))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //4.	//旧密码、新密码加密
       in.OperOldPassword = SecurityUtils.md5(in.OperOldPassword);
       in.OperNewPassword = SecurityUtils.md5(in.OperNewPassword); ;

       //5.	调用OPOperatorDAO.find(操作员编号)查询操作员密码。
       OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
       OPOperator opOperator = new OPOperator();
       opOperator.OperID = in.OperID;
       opOperator = operatorDAO.find(opOperator);

       //6.	如果旧密码和查询出的密码不符，返回ERR_OPERPWDWRONG。
       if(!opOperator.OperPassword.equals(in.OperOldPassword))
                 throw new EduException(ErrorCode.ERR_OPERPWDWRONG);

       //7.	调用OPOperatorDAO.updateOPOperator(JDBCFieldArray setCols,JDBCFieldArray wherecols)修改操作员信息。
       JDBCFieldArray setCols = new JDBCFieldArray();
       JDBCFieldArray whereCols = new JDBCFieldArray();

       setCols.add("OperPassword",in.OperNewPassword);
       whereCols.add("OperID",in.OperID);

       result = operatorDAO.update(setCols,whereCols);


        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = "";

        commonDAO.addOperatorLog(log);

        return result;
    }
}
