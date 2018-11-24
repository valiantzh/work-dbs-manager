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
 * <p>Description: 物流公司增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Logistics: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMLogisticsAdd extends ActionBean
{

    public int doBusiness(InParamPMLogisticsAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.LogisticsName))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		
		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

		in.LogisticsName = StringUtils.normalizeName(in.LogisticsName);
        //////////////检查公司名称不重复//////////////////////
        PMLogisticsDAO logisticsDAO = daoFactory.getPMLogisticsDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("LogisticsName", in.LogisticsName);
        if (logisticsDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_COMPANYNAMEEXISTS);

        ///////////////生成物流公司编号//////////////////////////
        //四位序号
        String maxLogisticsID = "";
        int iMaxLogisticsID = 0;
        PMLogistics obj = new PMLogistics();

        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("LogisticsID", "<>","9999");
        maxLogisticsID = logisticsDAO.selectFunctions("MAX(LogisticsID)", whereCols1);
        if (StringUtils.isEmpty(maxLogisticsID)) {
            obj.LogisticsID = "0001";
        } else {
        	iMaxLogisticsID = NumberUtils.parseInt(maxLogisticsID) + 1;
            obj.LogisticsID = StringUtils.leftPad(String.valueOf(iMaxLogisticsID), 4, '0');
        }

        obj.LogisticsName = in.LogisticsName;
        obj.PinyinCode = in.PinyinCode;
        obj.Remark = in.Remark;
        
        logisticsDAO.insert(obj);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.LogisticsName;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
