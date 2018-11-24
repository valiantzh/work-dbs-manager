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
import com.dcdzsoft.businessproxy.PushBusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递员APP扫码登录 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanLogin extends ActionBean
{

    public OutParamAPPostmanLogin doBusiness(InParamAPPostmanLogin in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanLogin out = new OutParamAPPostmanLogin();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PostmanID)
			||StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.RandomCode))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		/*PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
		PMPostman postman = new PMPostman();
		postman.PostmanID = in.PostmanID;
		
		postman = postmanDAO.find(postman);

		if(!postman.PostmanStatus.equals(SysDict.POSTMAN_STATUS_NORMAL)){
        	throw new EduException(ErrorCode.ERR_OPERHAVECANCEL);
        }
		
		in.PostmanID = postman.PostmanID;
		in.PostmanName = postman.PostmanName;
		in.Password = postman.Password;
		in.CompanyID = postman.CompanyID;
        in.ExpiredCount = queryExpiredCount(in.PostmanID,in.TerminalNo);*/
        
        in.InputMobileFlag = SysDict.INPUTMOBILE_FLAG_LOCAL;
		in.VerifyFlag = SysDict.POSTMAN_VERIFY_PWD;
        in.BoxList = "0";
        in.ExpiredCount = 0;
        
        ControlParam ctrlParam = ControlParam.getInstance();
        
        if("1".equals(ctrlParam.appScanLogin)){
			//下行业务
			PushBusinessProxy.doBusiness(in);
			
			try{
				Thread.sleep(500);
			}catch(Exception e)
			{
				
			}
        }
		
		//////////////////////////////////////////////////////////
        out.PostmanID = in.PostmanID;
        out.PostmanName = in.PostmanName;
        out.Mobile = "";
        out.CompanyID = in.CompanyID;
         
        return out;
    }
    
    private int queryExpiredCount(String PostmanID,String TerminalNo) throws EduException
    {
    	java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
    	 ControlParam ctrlParam = ControlParam.getInstance();

         int days = NumberUtils.parseInt(ctrlParam.recoveryTimeout);
         
    	 PTInBoxPackageDAO inBoxPackageDAO = daoFactory.getPTInBoxPackageDAO();
         JDBCFieldArray whereCols = new JDBCFieldArray();

         whereCols.add("TerminalNo",TerminalNo);
         whereCols.add("PostmanID", PostmanID);
         whereCols.addSQL(" AND ((2=2 ");
         //whereCols.add("ExpiredTime", "<", sysDateTime);
         //whereCols.add("ExpiredTime", "<>", DateUtils.getMinDate());
         whereCols.add("StoredTime", "<=",DateUtils.addTimeStamp(sysDateTime, -days));
         whereCols.addSQL(") OR PackageStatus IN('1','3'))"); //1.锁定 3.超时
         
         int count = inBoxPackageDAO.isExist(whereCols);
         
         return count;
    }
}
