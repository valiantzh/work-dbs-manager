package com.dcdzsoft.business.py;

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
 * <p>Description: 增加短信服务套餐信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSServicesAdd extends ActionBean
{

    public int doBusiness(InParamPYSMSServicesAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.ServiceName))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);


		in.ServiceName = StringUtils.normalizeName(in.ServiceName);
		
        //////////////检查名称不重复//////////////////////
		PYSMSServicesDAO servicesDAO = daoFactory.getPYSMSServicesDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("ServiceName", in.ServiceName);
        if (servicesDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_SERVICESNAMEEXISTS);

        ///////////////生成编号//////////////////////////
        String maxID = "";
        int iMaxID = 0;
        PYSMSServices obj = new PYSMSServices();
        
        if(StringUtils.isEmpty(in.ServiceID))
        {
            //4位数字
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            maxID = servicesDAO.selectFunctions("MAX(ServiceID)", whereCols1);
            if (StringUtils.isEmpty(maxID)) {
                obj.ServiceID = "0001";
            } else {
                iMaxID = NumberUtils.parseInt(maxID) + 1;
                obj.ServiceID = StringUtils.leftPad(String.valueOf(iMaxID), 4, '0');
            }
        
            //obj.CompanyID = RandUtils.generateString(6).toUpperCase();
        }else
        {
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            whereCols1.add("ServiceID", in.ServiceID);
            if (servicesDAO.isExist(whereCols1) > 0)
                throw new EduException(ErrorCode.ERR_SERVICESEXISTS);
            
            obj.ServiceID = in.ServiceID;
        }
        obj.ServiceName = in.ServiceName;
        if(!"1".equalsIgnoreCase(in.Active)){
            in.Active = "0";
        }
        obj.Ways   = in.Ways;
        obj.Units  = in.Ways;
        obj.Amount = in.Amount;
        obj.SMSNum = in.SMSNum;
        obj.Active = in.Active;
        obj.LastModifyTime = sysDateTime;
        obj.CreateTime = sysDateTime;
        obj.Remark = in.Remark;

        result = servicesDAO.insert(obj);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.ServiceName;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
