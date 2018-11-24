package com.dcdzsoft.business.pt;

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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: PDA投递授权 </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class PTDeliveryAuth4PDA extends ActionBean {
    
    public OutParamPTDeliveryAuth4PDA doBusiness(InParamPTDeliveryAuth4PDA in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTDeliveryAuth4PDA out = new OutParamPTDeliveryAuth4PDA();
        
        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo)
            || StringUtils.isEmpty(in.PostmanID)
            || StringUtils.isEmpty(in.PDANo))
            throw new EduException(ErrorCode.ERR_PARMERR);

        String postmanid = "";
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("PostmanID", in.PostmanID);
        RowSet rset = postmanDAO.select(whereCols0);
        if (RowSetUtils.rowsetNext(rset)) {
            postmanid = RowSetUtils.getStringValue(rset, "PostmanID");
        } else {
            whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID", in.PostmanID);
            whereCols0.add("PostmanStatus", SysDict.POSTMAN_STATUS_NORMAL);
            
            rset = postmanDAO.select(whereCols0);

            if (RowSetUtils.rowsetNext(rset)) {
                postmanid = RowSetUtils.getStringValue(rset, "PostmanID");
            } else {
            	//用户名或密码错误
            	 throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
            }
        }

        PMPostman postman = new PMPostman();
        postman.PostmanID = postmanid;
        postman = postmanDAO.find(postman);
        
        if(!postman.PostmanStatus.equals(SysDict.POSTMAN_STATUS_NORMAL))
        {
        	throw new EduException(ErrorCode.ERR_POSTMANHAVECANCELED);
        }
        
        //检查投递员的运营网点编号和柜体的营业网点编号是否一致
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        TBTerminal terminal = new TBTerminal();
        terminal.TerminalNo = in.TerminalNo;
        
        /////判断一下，返回柜体未注册成功信息
        terminal = terminalDAO.find(terminal);
        
        if(StringUtils.isNotEmpty(postman.DepartmentID) 
        	&& StringUtils.isNotEmpty(terminal.DepartmentID)){
        	MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
        	MBDepartment department = new MBDepartment();
        	department.DepartmentID = terminal.DepartmentID;
        	
        	department = departmentDAO.find(department);
        	
        	//同一级别或者为父级以上级别
        	if(postman.DepartmentID.equalsIgnoreCase(department.Level1)
        	  ||postman.DepartmentID.equalsIgnoreCase(department.Level2)
        	  ||postman.DepartmentID.equalsIgnoreCase(department.Level3)
        	  ||postman.DepartmentID.equalsIgnoreCase(department.Level4)
        	  ||postman.DepartmentID.equalsIgnoreCase(department.Level5)
        	  ||postman.DepartmentID.equalsIgnoreCase(department.Level6))
        	{
        		
        	}else
        	{
        		throw new EduException(ErrorCode.ERR_FORBIDOPERATORLOCK);
        	}
        }
        
        //检查柜体权限
        commonDAO.checkManTerminalRight(postman, in.TerminalNo);

        TBPDADAO pdaDAO = daoFactory.getTBPDADAO();
        TBPDA pda = new TBPDA();
        pda.PDANo = in.PDANo;
        if(!pdaDAO.isExist(pda)){
            throw new EduException(ErrorCode.ERR_PDANOTEXISTS);
        }
        pda = pdaDAO.find(pda);
        if(SysDict.TERMINAL_STATUS_NORMAL.equals(pda.PDAStatus)
           && SysDict.TERMINAL_REGISTERFLAG_YES.equals(pda.RegisterFlag)){
            out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_YES;
        }else{
            out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_NO;
        }
        return out;
    }
    
}
