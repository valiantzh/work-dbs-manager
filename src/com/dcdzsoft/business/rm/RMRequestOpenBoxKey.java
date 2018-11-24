package com.dcdzsoft.business.rm;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.config.ApplicationConfig;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 申请开箱密码 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class RMRequestOpenBoxKey extends ActionBean
{

    public OutParamRMRequestOpenBoxKey doBusiness(InParamRMRequestOpenBoxKey in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamRMRequestOpenBoxKey out = new OutParamRMRequestOpenBoxKey();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.AppealUser)
			||StringUtils.isEmpty(in.AppealType)
			||StringUtils.isEmpty(in.BoxNo)
			||StringUtils.isEmpty(in.PackageID)
			||StringUtils.isEmpty(in.CustomerMobile)
			||in.StoredTime == null )
			throw new EduException(ErrorCode.ERR_PARMERR);


		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		RMAppealLogDAO appealLogDAO = daoFactory.getRMAppealLogDAO();
        RMAppealLog appealLog = new RMAppealLog();
        appealLog.AppealNo = in.AppealNo;
        appealLog.AppealUser = in.AppealUser;
        appealLog.AppealType = in.AppealType;
        appealLog.BoxNo = in.BoxNo;
        appealLog.PackageID = in.PackageID;
        appealLog.CustomerMobile = in.CustomerMobile;
        appealLog.AppealTime = sysDateTime;
        appealLog.LastModifyTime = sysDateTime;
        appealLog.AppealStatus = SysDict.APPEAL_STATUS_READYOPEN;
        appealLog.TerminalNo = in.TerminalNo;
        
        if(999 == ApplicationConfig.getInstance().getSysRunModel()){
            appealLog.OpenBoxKey = "111111";
        }else{
            appealLog.OpenBoxKey = RandUtils.generateNumber(6);
        }
        
        appealLog.AppealContent = "PackageID     : " + in.PackageID + "\n";
        appealLog.AppealContent+= "CustomerMobile: " + in.CustomerMobile + "\n";
        appealLog.AppealContent+= "PostmanID     : " + in.PostmanID + "\n";
        appealLog.AppealContent+= "StoredTime    : " + DateUtils.timestampToString(in.StoredTime) + "\n";
        if(in.TakedTime != null)
        	appealLog.AppealContent+= "TakedTime     : " + DateUtils.timestampToString(in.TakedTime);
        else
        	appealLog.AppealContent+= "TakedTime     : ";

        appealLogDAO.insert(appealLog);
        
        //////////////////////////////////////////////////////////////////////////
        ControlParam ctrlParam = ControlParam.getInstance();
        
        out.AppealNo = in.AppealNo;
        out.OpenBoxKey = SecurityUtils.md5(appealLog.OpenBoxKey);
        out.ContactTel = ctrlParam.contactTel;

        return out;
    }
}
