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
 * <p>Description: 投递订单添加用户信息 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PTPackageCustomerAdd extends ActionBean
{

    public OutParamPTPackageCustomerAdd doBusiness(InParamPTPackageCustomerAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTPackageCustomerAdd out = new OutParamPTPackageCustomerAdd();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PackageID)
			||StringUtils.isEmpty(in.CustomerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		////////////////
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		TBTerminal terminal = new TBTerminal();
		terminal.TerminalNo = in.TerminalNo;
		
		if(!terminalDAO.isExist(terminal)){
			throw new EduException(ErrorCode.ERR_TERMINALNOTEXISTS);
		}
		
		TBServerBoxDAO boxDAO = daoFactory.getTBServerBoxDAO();
		TBServerBox box = new TBServerBox();
		box.TerminalNo  = in.TerminalNo;
		box.BoxNo       = in.BoxNo;
		if(!boxDAO.isExist(box)){
			throw new EduException(ErrorCode.ERR_BOXNOTEXISTS);
		}
		
		String blankBoxKey = "123456";//测试用170602
		in.OpenBoxKey      = SecurityUtils.md5(blankBoxKey);//密文
		
		//(华为E柜)
		PTInBoxPackageDAO inBoxPackDAO = daoFactory.getPTInBoxPackageDAO();
		PTInBoxPackage inBoxPack = new PTInBoxPackage();
		inBoxPack.TerminalNo = in.TerminalNo;
		inBoxPack.PackageID  = in.PackageID;
		if(inBoxPackDAO.isExist(inBoxPack)){
			//存在，更新用户信息,返回结果
			JDBCFieldArray whereCols = new JDBCFieldArray();
			JDBCFieldArray setCols = new JDBCFieldArray();
			setCols.add("CustomerID", in.CustomerID);
			setCols.add("CustomerAddress", in.CustomerAddress);
			setCols.add("CustomerMobile", in.CustomerMobile);
			setCols.add("StaOrderID", in.StaOrderID);
			setCols.add("Remark", in.Remark);
			setCols.add("StaOrderID", in.StaOrderID);
			setCols.add("OpenBoxKey", in.OpenBoxKey);
			setCols.add("BlankBoxKey", blankBoxKey);
			setCols.add("LastModifyTime", sysDateTime);
			whereCols.add("TerminalNo", in.TerminalNo);
			whereCols.add("PackageID", in.PackageID);
			
			inBoxPackDAO.update(setCols, whereCols);
			
			out.ServerTime = sysDateTime;
			return out;
		}
		
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.add("TerminalNo", in.TerminalNo);
		whereCols.add("BoxNo", in.BoxNo);
		if(inBoxPackDAO.isExist(whereCols) == 1){
			//物品已投递到格口
			JDBCFieldArray setCols = new JDBCFieldArray();
			setCols.add("CustomerID", in.CustomerID);
			setCols.add("CustomerAddress", in.CustomerAddress);
			setCols.add("CustomerMobile", in.CustomerMobile);
			setCols.add("StaOrderID", in.StaOrderID);
			setCols.add("Remark", in.Remark);
			setCols.add("StaOrderID", in.StaOrderID);
			setCols.add("OpenBoxKey", in.OpenBoxKey);
			setCols.add("BlankBoxKey", blankBoxKey);
			setCols.add("LastModifyTime", sysDateTime);
			setCols.add("PackageID", in.PackageID);//更新订单号 ?????
			
			inBoxPackDAO.update(setCols, whereCols);
			
			out.ServerTime = sysDateTime;
			return out;
		}else{
			throw new EduException(ErrorCode.ERR_BOXNOTUSABLE);
		}
    }
}
