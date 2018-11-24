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
 * <p>Description: 查询会员卡充值流水数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYCustomerCardWaterQryCount extends ActionBean
{

    public int doBusiness(InParamPYCustomerCardWaterQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		String limitSQL  = utilDAO.getLimitSQL(operOnline.DepartmentID);
		
		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        
        if(in.EndDate !=null){
            in.EndDate = DateUtils.addDate(in.EndDate, 1);
        }
        if(in.BeginDate == null){
            in.BeginDate = DateUtils.addDate(sysDate, -7);
        }
        
        String viewName = "";
        if(SysDict.CUSTOMER_TYPE_POSTMAN.equalsIgnoreCase(in.CustomerType)){
            //投递员-绿城快递柜，向投递员收费，需要给投递员发充值卡
            viewName = "V_PYTradeWaterPostman";
        }else{
            viewName = "V_PYTradeWaterCustomer";
        }
        /////////////////////////////////////////////////////////////
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("CardNo", in.CardNo);
        whereSQL.checkAdd("CustomerID", in.CustomerID);
        if(StringUtils.isNotEmpty(in.CustomerName))
            whereSQL.add("CustomerName", " LIKE ", "%" + in.CustomerName + "%");
        whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
        whereSQL.checkAdd("BillType", in.BillType);
        whereSQL.checkAdd("UploadFlag", in.UploadFlag);
        whereSQL.checkAdd("LastModifyTime",">=", in.BeginDate);
        whereSQL.checkAdd("LastModifyTime","<=", in.EndDate);
        
        if(StringUtils.isNotBlank(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("CompanyID", in.CompanyID);
        
		String sql = "SELECT COUNT(*) FROM "+viewName+" a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
        
        result = dbSession.executeQueryCount(sql, whereSQL);
        
        return result;
    }
}
