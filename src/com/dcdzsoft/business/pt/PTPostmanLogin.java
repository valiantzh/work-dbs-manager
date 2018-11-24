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
 * <p>Description: 投递员登陆验证 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTPostmanLogin extends ActionBean {
    public OutParamPTPostmanLogin doBusiness(InParamPTPostmanLogin in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTPostmanLogin out = new OutParamPTPostmanLogin();
        
        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.PostmanID)
            || StringUtils.isEmpty(in.Password))
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
        
        if(Constant.POSTMAN_LOGIN_TYPE_PDA.equals(in.LoginType)){
            //投递员在PDA上登录验证
            out.PostmanID       = postman.PostmanID;
            out.PostmanName     = postman.PostmanName;
            out.PostmanType     = postman.PostmanType;
            out.PostmanMobile   = postman.Mobile;
            out.InputMobileFlag = postman.InputMobileFlag;
            out.CompanyID       = postman.CompanyID;
            out.DynamicCode     = "123456";
            out.BoxList         = "0";
            
            //禁用箱门列表（可用于已预约占用的箱列表）
            out.Forbidden       = "0";

            return out;
        }else{
            if(StringUtils.isEmpty(in.TerminalNo)){
                throw new EduException(ErrorCode.ERR_PARMERR);
            }
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
        
        ControlParam ctrlParam = ControlParam.getInstance();
        //临时用(????)
        if(in.Password.length() !=32)
            in.Password = SecurityUtils.md5(in.Password);
        //身份证登陆，不验证密码
        if(!Constant.POSTMAN_LOGIN_TYPE_IDCARD.equals(in.getLoginType())){
            if (!postman.Password.equalsIgnoreCase(in.Password))
                throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
        }

        //System.out.println("getLockerChargeUser="+ControlParam.getInstance().getLockerChargeUser());
        if("1".equals(ctrlParam.getLockerChargeUser())){//对投递员收费
            //对投递员收费，需要验证密码
            if (!postman.Password.equalsIgnoreCase(in.Password))
                throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
            
            if(StringUtils.isNotEmpty(postman.BindCardID)){
                
                PYConsumedCardDAO cardDAO = daoFactory.getPYConsumedCardDAO();
                PYConsumedCard card = new PYConsumedCard();
                card.CardNo     = postman.BindCardID;
                card.CustomerID = postman.PostmanID;
                try{
                    card = cardDAO.find(card);
                }catch(EduException e){
                    throw new EduException(ErrorCode.ERR_CUSTOMERCARDNOTEXISTS);//会员卡不存在,或已注销
                }
                if(!SysDict.CARD_STATUS_NORMAL.equals(card.CardStatus) || card.Balance <= 0){
                    throw new EduException(ErrorCode.ERR_CUSTOMERCARDDISABLE);
                }
            }
        }
        
        //检查柜体权限
        commonDAO.checkManTerminalRight(postman, in.TerminalNo);

        //
        out.PostmanID       = postman.PostmanID;
        out.PostmanName     = postman.PostmanName;
        out.PostmanType     = postman.PostmanType;
        out.PostmanMobile   = postman.Mobile;
        out.InputMobileFlag = postman.InputMobileFlag;
        out.CompanyID       = postman.CompanyID;
        out.DynamicCode     = "123456";
        out.Balance         = 0;
        out.ExpiredCount    = queryExpiredCount(in.PostmanID,in.TerminalNo, postman.CompanyID);
        //out.ExpiredCount = 0;
        out.ExpiredDate     = queryExpiredDays(postman.CompanyID);
        out.OfBureau        = terminal.OfBureau;
        
        ////////////////////////////////////////////////////////////////
        if("97531468".equalsIgnoreCase(in.PostmanID)) //测试用
        	out.VerifyFlag = SysDict.POSTMAN_VERIFY_CODE;
        else
        	out.VerifyFlag = SysDict.POSTMAN_VERIFY_PWD;
        ////////////////////////////////////////////////////////
        
        //out.VerifyFlag = SysDict.POSTMAN_CHECKMODEL_STATIC;
        
        //查询格口权限
        out.BoxList = queryBoxList(in.TerminalNo, postman.CompanyID);

        
        //禁用箱门列表（可用于已预约占用的箱列表）
        out.Forbidden = "0";

        return out;
    }
    
    private String queryBoxList(String terminalNo, String companyID){
        String boxList = "0";//
        int count = 0;
        StringBuffer sb = new StringBuffer();
        ControlParam ctrlParam = ControlParam.getInstance();
        if (ctrlParam.manBoxRightCheck.equalsIgnoreCase(SysDict.POSTMAN_BOXRIGHT_JUDGE_NO)) {
            return "0";
        } else if (ctrlParam.manBoxRightCheck.equalsIgnoreCase(SysDict.POSTMAN_BOXRIGHT_JUDGE_COMPANY)) {
            PMCorpBoxRightDAO corpRightDAO = daoFactory.getPMCorpBoxRightDAO();
            JDBCFieldArray whereCols2 = new JDBCFieldArray();
            whereCols2.add("TerminalNo", terminalNo);
            whereCols2.add("CompanyID", companyID);

            RowSet rset1;
            try {
                rset1 = corpRightDAO.select(whereCols2);
                while (RowSetUtils.rowsetNext(rset1)) {
                    if (count > 0)
                        sb.append(",");
                    else
                        sb.append(RowSetUtils.getStringValue(rset1, "BoxNo"));
                    count++;
                }
            } catch (EduException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        } else if (ctrlParam.manBoxRightCheck.equalsIgnoreCase(SysDict.POSTMAN_BOXRIGHT_JUDGE_PERSON)) {
            PMPostmanBoxRightDAO manRightDAO = daoFactory.getPMPostmanBoxRightDAO();
            JDBCFieldArray whereCols2 = new JDBCFieldArray();
            whereCols2.add("TerminalNo", terminalNo);
            whereCols2.add("PostmanID", companyID);

            RowSet rset1;
            try {
                rset1 = manRightDAO.select(whereCols2);
                while (RowSetUtils.rowsetNext(rset1)) {
                    if (count > 0)
                        sb.append(",");
                    else
                        sb.append(RowSetUtils.getStringValue(rset1, "BoxNo"));

                    count++;
                }
            } catch (EduException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (count > 0)
            boxList = sb.toString();
        return boxList;
    }
    
    private int queryExpiredCount(String PostmanID,String TerminalNo, String CompanyID) throws EduException
    {
	   java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
	   java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
	   
	   PTInBoxPackageDAO inBoxPackageDAO = daoFactory.getPTInBoxPackageDAO();
	   JDBCFieldArray whereCols = new JDBCFieldArray();
	
	   if("1".equals(ControlParam.getInstance().recoveryScope)){
	       whereCols.add("PostmanID", PostmanID);
	   }else if("2".equals(ControlParam.getInstance().recoveryScope)){
	       whereCols.add("CompanyID", CompanyID);
	   }
	   whereCols.add("TerminalNo",TerminalNo);
	   //未设置逾期时间，按recoveryTimeout时间逾期；设置的逾期时间大于存物时间，按设置的逾期时间逾期
	   whereCols.addSQL(" AND ( ");
	   whereCols.addSQL(" (2=2 AND ( ExpiredTime IS NULL OR ExpiredTime <= StoredTime )");//小于存物时间，表示没有设置逾期时间
	   whereCols.add("StoredDate", "<=", queryExpiredDays(CompanyID));
	   whereCols.add("PackageStatus", "0");//正常订单，不查退货在箱订单
	   whereCols.addSQL(") "); //未设置逾期时间，按recoveryTimeout时间逾期；
	   whereCols.addSQL(" OR PackageStatus IN('1','3') "); //1.锁定 3.超时
	   whereCols.addSQL(" OR (2=2 AND ExpiredTime > StoredTime "); //设置的逾期时间大于存物时间，按设置的逾期时间逾期
	   whereCols.add("ExpiredTime", "<", sysDateTime);
	   whereCols.add("PackageStatus", "0");//正常订单，不查退货在箱
	   whereCols.addSQL(" ) )"); //
	   
	   int count = inBoxPackageDAO.isExist(whereCols);
	   
	   return count;
    }
    
    private java.sql.Date queryExpiredDays(String companyID) throws EduException
    {
    	java.sql.Date sysDate = utilDAO.getCurrentDate();
    	
    	int expiredDays = commonDAO.expiredDays(companyID);
    	
    	return DateUtils.addDate(sysDate,-expiredDays);
    }
    
}
