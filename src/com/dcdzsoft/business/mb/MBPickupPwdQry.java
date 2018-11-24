package com.dcdzsoft.business.mb;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * <p>
 * Description: 取件密码短消息查询
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: dcdzsoft
 * </p>
 * 
 * @author zxy
 * @version 1.0
 */

public class MBPickupPwdQry extends ActionBean {

	public RowSet doBusiness(InParamMBPickupPwdQry in) throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		RowSet rset = null;

		// 1. 验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
		        ||StringUtils.isEmpty(in.OperPwd)
		        ||StringUtils.isEmpty(in.CustomerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);

		// 2. 调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		java.sql.Date sysDate = utilDAO.getCurrentDate();
		//////////////////
		OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        OPOperator oper = new OPOperator();
        oper.OperID = in.OperID;
        try
        {
            oper = operDAO.find(oper);
        }catch(EduException e)
        {
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("UserID", in.OperID);
            RowSet rset1 = operDAO.select(whereCols);
            if(RowSetUtils.rowsetNext(rset1))
            {
                oper.OperID = RowSetUtils.getStringValue(rset1, "OperID");
                oper = operDAO.find(oper);
            }else{
                throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
            }
            
        }
        

        String pwdMd5 = SecurityUtils.md5(in.OperPwd); //MD5 加密
        
        if (oper.OperStatus.compareTo("0") != 0)
            throw new EduException(ErrorCode.ERR_OPERSTATUSABNORMAL);
        
        if (oper.OperPassword.compareTo(pwdMd5) != 0){
            log.error("operPwd=" + oper.OperPassword + ",inPwd=" + in.OperPwd);
            throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
        }
        
        
        //需要特殊权限
        OPOperSpeRightDAO operSpeRightDAO = daoFactory.getOPOperSpeRightDAO();
        OPOperSpeRight speRight = new OPOperSpeRight();
        speRight.OperID = in.OperID;
        speRight.SpePrivID = SysDict.SPEPRIV_QUREYPICKUPKEY;
        
        if(operSpeRightDAO.isExist(speRight) == false)
           throw new EduException(ErrorCode.ERR_OPERNOOPENFUNC);
        
		// /////////////////////////////////////////////////////////////
		String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);

		// ///////////////////////////////////////////////////////////
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		//whereSQL.checkAdd("DepartmentID", in.DepartmentID);
		
		whereSQL.checkAdd("PackageID", in.PackageID);
		whereSQL.add("CustomerMobile", in.CustomerMobile);
		whereSQL.add("StoredDate", ">=",DateUtils.addDate(sysDate, -7));
		String sql = "SELECT * FROM V_PwdShortMsg00 a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;

		rset = dbSession.executeQuery(sql,whereSQL);

		return rset;
	}
}
