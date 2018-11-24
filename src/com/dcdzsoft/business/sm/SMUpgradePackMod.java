package com.dcdzsoft.business.sm;


import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 修改软件更新包 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SMUpgradePackMod extends ActionBean
{

    public int doBusiness(InParamSMUpgradePackMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.SoftwareID)
			||StringUtils.isEmpty(in.SoftwareName)
			//||StringUtils.isEmpty(in.SoftwareType)
			||StringUtils.isEmpty(in.SoftwareVersion)
			||StringUtils.isEmpty(in.SoftSignMd5)
			//||StringUtils.isEmpty(in.SystemType)
			||StringUtils.isEmpty(in.DownloadUrl))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		//java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		in.SoftwareName=StringUtils.normalizeName(in.SoftwareName);
		//////////////检查软件编号不重复//////////////////////
		SMUpgradePackDAO smUpgradePackDAO=daoFactory.getSMUpgradePackDAO();
			
		JDBCFieldArray whereCols0 = new JDBCFieldArray();
		whereCols0.add("SoftwareID", in.SoftwareID);
		if(smUpgradePackDAO.isExist(whereCols0)<=0){
			throw new EduException(ErrorCode.ERR_SOFTWAREIDNOTEXIST);
		}
		
		JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        //setCols.add("SoftwareType",in.SoftwareType);
        setCols.add("SoftwareName",in.SoftwareName);
        setCols.add("SoftwareVersion",in.SoftwareVersion);
        setCols.add("SoftSignMd5",in.SoftSignMd5);
        //setCols.add("SystemType",in.SystemType);
        setCols.add("DownloadUrl",in.DownloadUrl);
        setCols.add("UpdateContent",in.UpdateContent);
        setCols.add("LastModifyTime",sysDateTime);
        setCols.add("Remark",in.Remark);
        
        whereCols.add("SoftwareID", in.SoftwareID);
        smUpgradePackDAO.update(setCols, whereCols);


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
