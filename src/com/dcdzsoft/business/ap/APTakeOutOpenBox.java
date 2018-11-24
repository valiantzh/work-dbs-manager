package com.dcdzsoft.business.ap;

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
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 取件开箱（新接口） </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class APTakeOutOpenBox extends ActionBean
{

    public OutParamAPTakeOutOpenBox doBusiness(InParamAPTakeOutOpenBox in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPTakeOutOpenBox out = new OutParamAPTakeOutOpenBox();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TradeWaterNo)
			||StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.BoxNo))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		///推送到设备端
        PushBusinessProxy.doBusiness(in);


        return out;
    }
}
