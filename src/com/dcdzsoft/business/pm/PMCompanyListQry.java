package com.dcdzsoft.business.pm;

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
 * @author 王中立
 * @version 1.0
 */

public class PMCompanyListQry extends ActionBean {

    public RowSet doBusiness(InParamPMCompanyListQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        
        String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        
        String sql = "SELECT CompanyID,CompanyName,DepartmentID FROM PMCompany a WHERE 1=1 ";
        ControlParam ctrlParam = ControlParam.getInstance();
        if("1".equals(ctrlParam.companyQryModel))
        {
        	 sql = sql + limitSQL + " ORDER BY CompanyID "; 
        }else
        {
        	MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
			MBDepartment department = new MBDepartment();
			department.DepartmentID = operOnline.DepartmentID;
			
			department = departmentDAO.find(department);
			sql = sql + limitSQL + " OR DepartmentID = " + StringUtils.addQuote(department.ParentDepartID) + " ORDER BY CompanyID ";  
        }
       
        rset = dbSession.executeQuery(sql);

        return rset;
    }
}
