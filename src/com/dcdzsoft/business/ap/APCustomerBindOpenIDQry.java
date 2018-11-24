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
 * <p>Description: 绑定合作方用户信息查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APCustomerBindOpenIDQry extends ActionBean
{
    public OutParamAPCustomerBindOpenIDQry doBusiness(InParamAPCustomerBindOpenIDQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPCustomerBindOpenIDQry out = new OutParamAPCustomerBindOpenIDQry();

		//1.验证输入参数是否有效，如果无效返回-1。
        CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
        CMCustomer customer = new CMCustomer();
        
        if(StringUtils.isEmpty(in.CustomerID) && StringUtils.isNotEmpty(in.BindMobile)){
            in.CustomerID = in.BindMobile;
        }
        customer.CustomerID = in.CustomerID;
        if(customerDAO.isExist(customer)){
            customer = customerDAO.find(customer);
            out.CustomerID   = customer.CustomerID;
            out.CustomerName = customer.CustomerName;
            out.CustomerType = customer.CustomerType;
            out.BindMobile   = customer.BindMobile;
            //out.UnionID      = customer.UnionID;
        }else{
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("BindMobile", in.BindMobile);
            RowSet rset = customerDAO.select(whereCols);
            if (RowSetUtils.rowsetNext(rset)) {
                out.CustomerID   = RowSetUtils.getStringValue(rset, "CustomerID");
                out.CustomerName = RowSetUtils.getStringValue(rset, "CustomerName");
                out.CustomerType = RowSetUtils.getStringValue(rset, "CustomerType");
                out.BindMobile   = RowSetUtils.getStringValue(rset, "BindMobile");
                //out.UnionID      = RowSetUtils.getStringValue(rset, "UnionID");
            }
        }
        return out;
    }
}
