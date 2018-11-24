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
 * <p>Description: 查询会员卡信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYCustomerCardQry extends ActionBean
{

    public RowSet doBusiness(InParamPYCustomerCardQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		
		
		String limitSQL = " ";//utilDAO.getLimitSQL(operOnline.DepartmentID);
		String viewName = "";
		////////////////////
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("CardNo", in.CardNo);
        whereSQL.checkAdd("CardType", in.CardType);
        whereSQL.checkAdd("CardStatus", in.CardStatus);
        if(SysDict.CUSTOMER_TYPE_POSTMAN.equalsIgnoreCase(in.CustomerType)){
            //投递员-绿城快递柜，向投递员收费，需要给投递员发充值卡
            limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
            viewName = "V_PYPostmanCard";
            whereSQL.checkAdd("CompanyID", in.CompanyID);
            whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        }else{
            viewName = "V_PYCustomerCard";
        }
        whereSQL.checkAdd("CustomerID", in.CustomerID);
        if(StringUtils.isNotEmpty(in.CustomerName))
            whereSQL.add("CustomerName", " LIKE ", "%" + in.CustomerName + "%");
        whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
        whereSQL.checkAdd("CustomerStatus", in.CustomerStatus);

        String sql = "SELECT * FROM "+viewName+" a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + " "+ limitSQL;

        java.util.LinkedList list = new java.util.LinkedList();
        list.add("CardNo");

        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);

        return rset;
    }
}
