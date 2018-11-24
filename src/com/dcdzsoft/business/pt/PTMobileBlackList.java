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
 * <p>Description: 验证手机是否在黑名单列表 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTMobileBlackList extends ActionBean
{

    public OutParamPTMobileBlackList doBusiness(InParamPTMobileBlackList in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTMobileBlackList out = new OutParamPTMobileBlackList();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PostmanID)
			||StringUtils.isEmpty(in.CustomerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);

		MBMobileBlackListDAO blackListDAO = daoFactory.getMBMobileBlackListDAO();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.add("CustomerMobile", in.CustomerMobile);
		
		if(blackListDAO.isExist(whereCols) > 0)
			out.InBlackList = "1"; //不在黑名单中
		else
			out.InBlackList = "0";
		
        return out;
    }
}
