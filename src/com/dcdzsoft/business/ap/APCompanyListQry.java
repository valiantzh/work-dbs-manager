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

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递公司列表查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APCompanyListQry extends ActionBean
{

    public java.util.List<OutParamAPCompanyListQry> doBusiness(InParamAPCompanyListQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();

		//1.验证输入参数是否有效，如果无效返回-1。


		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		java.util.List<OutParamAPCompanyListQry> outList = new java.util.LinkedList<OutParamAPCompanyListQry>();
        
        String sql = "SELECT CompanyID,CompanyName FROM PMCompany ORDER BY CompanyName";
        RowSet rset = dbSession.executeQuery(sql);
        
        while (RowSetUtils.rowsetNext(rset)) {
            OutParamAPCompanyListQry outParam = new OutParamAPCompanyListQry();

            outParam.CompanyID = RowSetUtils.getStringValue(rset, "CompanyID");
            outParam.CompanyName = RowSetUtils.getStringValue(rset, "CompanyName");
            
            outList.add(outParam);
        }
        return outList;
    }
}
