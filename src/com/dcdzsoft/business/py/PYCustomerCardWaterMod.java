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
 * <p>Description: 充值流水状态修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYCustomerCardWaterMod extends ActionBean
{

    public int doBusiness(InParamPYCustomerCardWaterMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CardNo)
			||StringUtils.isEmpty(in.CustomerID)
			||StringUtils.isEmpty(in.TradeWaterNo)
			||StringUtils.isEmpty(in.UploadFlag))
			throw new EduException(ErrorCode.ERR_PARMERR);


        //3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        
        if(!SysDict.UPLOAD_FLAG_YES.equalsIgnoreCase(in.UploadFlag)){
            in.UploadFlag = SysDict.UPLOAD_FLAG_FAILURE;
        }
        //
        PYTransactionWaterDAO tradeWaterDAO = daoFactory.getPYTransactionWaterDAO();
        JDBCFieldArray setCols0 = new JDBCFieldArray();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        setCols0.add("UploadFlag", in.UploadFlag);
        
        whereCols0.add("TradeWaterNo", in.TradeWaterNo);
        tradeWaterDAO.update(setCols0, whereCols0);
        
        return result;
    }
}
