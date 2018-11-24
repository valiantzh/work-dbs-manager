package com.dcdzsoft.business.pt;

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
 * <p>Description: 用户取件关门状态上传 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTUploadDoorStatus extends ActionBean
{

    public String doBusiness(InParamPTUploadDoorStatus in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = "";

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.TradeWaterNo))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		
		String AlertType = SysDict.ALERT_TYPE_BOXNOTCLOSED;
		String AlertLevel = SysDict.ALERT_LEVEL_COMMON;
		String remark = "No="+in.TradeWaterNo+",id="+in.PackageID+",Status="+in.ArticleStatus;
		commonDAO.insertAlert(in.TerminalNo, AlertType, AlertLevel, in.BoxNo,remark);

        return result;
    }
}
