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
 * <p>Description: 运营网点列表查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APDepartListQry extends ActionBean
{

    public java.util.List<OutParamAPDepartListQry> doBusiness(InParamAPDepartListQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
		//1.	验证输入参数是否有效，如果无效返回-1。
        java.util.List<OutParamAPDepartListQry> outList = new java.util.LinkedList<OutParamAPDepartListQry>();
        
        String sql = "SELECT DepartmentID,DepartmentName FROM MBDepartment ORDER BY DepartmentName";
        RowSet rset = dbSession.executeQuery(sql);
        
        while (RowSetUtils.rowsetNext(rset)) {
        	OutParamAPDepartListQry outParam = new OutParamAPDepartListQry();

			outParam.DepartmentID = RowSetUtils.getStringValue(rset, "DepartmentID");
			outParam.DepartmentName = RowSetUtils.getStringValue(rset, "DepartmentName");
			
			outList.add(outParam);
		}
        
        return outList;
    }
}
