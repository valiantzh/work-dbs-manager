package com.dcdzsoft.business.ap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * <p>Description: 修改绑定手机号 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APCustomerBindMobile extends ActionBean
{

    public OutParamAPCustomerBindMobile doBusiness(InParamAPCustomerBindMobile in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPCustomerBindMobile out = new OutParamAPCustomerBindMobile();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerID)
		    || StringUtils.isEmpty(in.CheckCode))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
		if("unbind".equalsIgnoreCase(in.CheckCode)){
		    //解除绑定
		    JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();

            setCols.add("CustomerStatus", SysDict.CUSTOMER_STATUS_UNACTIVE);
            setCols.add("BindMobile", "");
            setCols.add("LastModifyTime", sysDateTime);
            whereCols.add("CustomerID", in.CustomerID);
            customerDAO.update(setCols, whereCols);
            
            out.CustomerID   = in.CustomerID;
            out.CustomerType = "0";
            out.BindMobile   = "";
		    return out;
		}
		
		//验证码查询
        MBBindMobileWaterDAO mobileCodeDAO = daoFactory.getMBBindMobileWaterDAO();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("ExpiredTime",">", sysDateTime);//还没失效
        whereCols0.add("VerifyKey",in.CheckCode);
        whereCols0.add("BindMobile",in.BindMobile);
        if(mobileCodeDAO.isExist(whereCols0)<=0){
            throw new EduException(ErrorCode.ERR_APP_CHECKCODE_TIMEOUT);
        }else{
            //删除验证码
            MBBindMobileWater mobileCode  = new MBBindMobileWater();
            mobileCode.VerifyKey = in.CheckCode;
            mobileCodeDAO.delete(mobileCode);
        }
        
        //检查绑定的手机号
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("BindMobile",in.BindMobile);

        if(customerDAO.isExist(whereCols1) > 0)
            throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDMOBILE);
        
        //用户类型
        String customerType = "0";//0-普通用
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        JDBCFieldArray whereCols2 = new JDBCFieldArray();
        whereCols2.add("PostmanID", in.BindMobile);
        whereCols2.add("PostmanStatus", SysDict.POSTMAN_STATUS_NORMAL);
        if(postmanDAO.isExist(whereCols2)>0){
            customerType = "99";//99-投递人员
        }else{
            whereCols2 = new JDBCFieldArray();
            whereCols2.add("BindMobile", in.BindMobile);
            whereCols2.add("PostmanStatus", SysDict.POSTMAN_STATUS_NORMAL);
            if(postmanDAO.isExist(whereCols2)>0){
                customerType = "99";//99-投递人员
            }
        }
        
        CMCustomer customer = new CMCustomer();
        customer.CustomerID = in.CustomerID;
        if(customerDAO.isExist(customer)){
            JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();

            setCols.add("CustomerStatus", SysDict.CUSTOMER_STATUS_NROMAL);
            setCols.add("CustomerType", customerType);
            setCols.add("BindMobile", in.BindMobile);
            setCols.add("LastModifyTime", sysDateTime);
            whereCols.add("CustomerID", in.CustomerID);
            customerDAO.update(setCols, whereCols);
        }else{
            customer.CustomerName   = in.BindMobile;
            customer.CustomerStatus = SysDict.CUSTOMER_STATUS_NROMAL;
            customer.CustomerType   = customerType;
            customer.BindMobile     = in.BindMobile;
            customer.CreateOperName = "181818";
            customer.CreateTime     = sysDateTime;
            customer.LastModifyTime = sysDateTime;
            customerDAO.insert(customer);
        }
        out.CustomerID   = in.CustomerID;
        out.CustomerType = customerType;
        out.BindMobile   = in.BindMobile;
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.CustomerID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = "";
		log.Remark = ""+in.BindMobile+",type="+customerType;

		commonDAO.addOperatorLog(log);


        return out;
    }
    
}
