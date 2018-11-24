package com.dcdzsoft.business.cm;

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
 * <p>Title: 智能自提柜运营系统</p>
 * <p>Description: 会员余额查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class CMCustomerBalanceQry extends ActionBean
{

    public OutParamCMCustomerBalanceQry doBusiness(InParamCMCustomerBalanceQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamCMCustomerBalanceQry out = new OutParamCMCustomerBalanceQry();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//会员卡
		PYConsumedCardDAO cardDAO = daoFactory.getPYConsumedCardDAO();
        PYConsumedCard card = new PYConsumedCard();
        
		if(SysDict.CUSTOMER_TYPE_POSTMAN.equals(in.CustomerType)){
		    //投递员用户信息查询
		    PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
		    PMPostman obj = new PMPostman();
            
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("PostmanID", in.CustomerID);
            RowSet rset = postmanDAO.select(whereCols0);
            if (RowSetUtils.rowsetNext(rset)) {
                obj.PostmanID = RowSetUtils.getStringValue(rset, "PostmanID");
            } else {
                //绑定卡号查询
                whereCols0 = new JDBCFieldArray();
                whereCols0.add("BindCardID", in.CustomerID);
                
                rset = postmanDAO.select(whereCols0);
                if (RowSetUtils.rowsetNext(rset)) {
                    obj.PostmanID = RowSetUtils.getStringValue(rset, "PostmanID");
                } else{
                    //绑定手机号查询
                    whereCols0 = new JDBCFieldArray();
                    whereCols0.add("BindMobile", in.CustomerID);
                    
                    rset = postmanDAO.select(whereCols0);
                    if (RowSetUtils.rowsetNext(rset)) {
                        obj.PostmanID = RowSetUtils.getStringValue(rset, "PostmanID");
                    } else {
                        //会员信息不存在
                        throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
                    }
                } 
            }
            
            obj = postmanDAO.find(obj);
            out.CustomerID = obj.PostmanID;
            out.CustomerName = obj.PostmanName;
            out.CustomerMobile = obj.BindMobile;
            out.BindCardID   = obj.BindCardID;
            
            //会员卡
            card.CardNo = obj.BindCardID;
            card.CustomerID = out.CustomerID;
		}else{
		    //个人用户信息查询
		    CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
		    CMCustomer obj = new CMCustomer();
            
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("CustomerID", in.CustomerID);
            RowSet rset = customerDAO.select(whereCols0);
            if (RowSetUtils.rowsetNext(rset)) {
                obj.CustomerID = RowSetUtils.getStringValue(rset, "CustomerID");
            } else {
                //绑定卡号查询
                whereCols0 = new JDBCFieldArray();
                whereCols0.add("BindCardID", in.CustomerID);
                
                rset = customerDAO.select(whereCols0);
                if (RowSetUtils.rowsetNext(rset)) {
                    obj.CustomerID = RowSetUtils.getStringValue(rset, "CustomerID");
                } else{
                    //绑定手机号查询
                    whereCols0 = new JDBCFieldArray();
                    whereCols0.add("BindMobile", in.CustomerID);
                    
                    rset = customerDAO.select(whereCols0);
                    if (RowSetUtils.rowsetNext(rset)) {
                        obj.CustomerID = RowSetUtils.getStringValue(rset, "CustomerID");
                    } else {
                        //会员信息不存在
                        throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
                    }
                } 
            }
            
            obj = customerDAO.find(obj);
            out.CustomerID = obj.CustomerID;
            out.CustomerName = obj.CustomerName;
            out.CustomerMobile = obj.CustomerMobile;
            out.BindCardID   = obj.BindCardID;
            
            //会员卡
            card.CardNo = obj.BindCardID;
            card.CustomerID = out.CustomerID;

		}
		out.CustomerType = in.CustomerType;
		
		//查询卡内余额
		if(StringUtils.isNotEmpty(card.CardNo)
		     &&StringUtils.isNotEmpty(card.CustomerID)){
            if(cardDAO.isExist(card)){
                card = cardDAO.find(card);
            }
            out.Balance = ((double)card.Balance/100);//数据库中保存金额单位：分
        }else{
            out.Balance = 0;
        }
        return out;
    }
}
