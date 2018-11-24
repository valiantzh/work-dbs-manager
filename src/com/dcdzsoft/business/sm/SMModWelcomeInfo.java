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
import com.dcdzsoft.businessproxy.PushBusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 修改欢迎词 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SMModWelcomeInfo extends ActionBean {

    public int doBusiness(InParamSMModWelcomeInfo in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.SystemID)
            || StringUtils.isEmpty(in.WelcomeInfo))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        OPOperator operSuperObj = new OPOperator();
        operSuperObj.OperID = in.OperID;
        operSuperObj = operDAO.find(operSuperObj);

        if (operSuperObj.UserID.equalsIgnoreCase(Constant.DEFAULT_SUPEROPERID) == false)
        {
        	 OPOperSpeRightDAO operSpeRightDAO = daoFactory.getOPOperSpeRightDAO();
             OPOperSpeRight speRight = new OPOperSpeRight();
             speRight.OperID = in.OperID;
             speRight.SpePrivID = SysDict.SPEPRIV_MODWELCOMEINFO;
             
             if(operSpeRightDAO.isExist(speRight) == false)
             	throw new EduException(ErrorCode.ERR_OPERNOOPENFUNC);
        }

        SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("UpdateContent",in.WelcomeInfo);
        whereCols.add("SystemID",in.SystemID);

        systemInfoDAO.update(setCols,whereCols);

        /////////////////////////
        PushBusinessProxy.doBusiness(in,"");

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = null;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = "";

        commonDAO.addOperatorLog(log);


        return result;
    }
}
