package com.dcdzsoft.business.pa;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.GServer;
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
 * <p>Description: 修改控制参数信息。 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PAControlParamMod extends ActionBean
{

    public int doBusiness(InParamPAControlParamMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||in.CtrlTypeID <= 0 
			||StringUtils.isEmpty(in.KeyString)
			||StringUtils.isEmpty(in.DefaultValue))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		
		 //3.	调用PAControlParamDAO.isExist (控制类别编号, 关键词串)验证控制参数信息是否已经存在，如果不存在则返回-1215。
        PAControlParamDAO controlParamDAO = daoFactory.getPAControlParamDAO();
        PAControlParam obj = new PAControlParam();
        obj.CtrlTypeID = in.CtrlTypeID;
        obj.KeyString = in.KeyString;

        obj = controlParamDAO.find(obj);

        //4.	调用PAControlParamDAO.update ()修改控制参数信息。
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("DefaultValue", in.DefaultValue);
        setCols.add("Remark",in.Remark);

        whereCols.add("CtrlTypeID", in.CtrlTypeID);
        whereCols.add("KeyString", in.KeyString);

        controlParamDAO.update(setCols, whereCols);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = "";

		commonDAO.addOperatorLog(log);

		//重新加载到内存
		JDBCFieldArray whereColsDummy = new JDBCFieldArray();
		RowSet rset = controlParamDAO.select(whereColsDummy);
		GServer.getInstance().loadCtrlParamToMemory(rset);

        return result;
    }
}
