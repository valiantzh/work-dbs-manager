package com.dcdzsoft.business.pt;

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
 * <p>Description: 添加待投递订单 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PTReadyPackageAdd extends ActionBean
{

    public OutParamPTReadyPackageAdd doBusiness(InParamPTReadyPackageAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTReadyPackageAdd out = new OutParamPTReadyPackageAdd();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PackageID))
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
		//////////////
		PTReadyPackageDAO readyPackDAO = daoFactory.getPTReadyPackageDAO();
		PTReadyPackage readyPack = new PTReadyPackage();
		readyPack.TerminalNo = in.TerminalNo;
		readyPack.PackageID = in.PackageID;
		if(readyPackDAO.isExist(readyPack)){
			throw new EduException(ErrorCode.ERR_PACKHAVEDELIVERYD);
		}
		readyPack.BoxNo      = in.BoxNo;
		readyPack.CustomerID = in.CompanyID;
		readyPack.CustomerMobile = in.CustomerMobile;
		readyPack.CustomerName   = in.CustomerName;//
		//readyPack.CustomerAddress = in.CustomerAddress;
		readyPack.OrderTime = sysDateTime;
		//readyPack.ExpiredTime = ;
		readyPack.CompanyID = in.CompanyID;
		readyPack.PostmanID = in.PostmanID;
		readyPack.PosPayFlag = in.PosPayFlag;
		readyPack.Remark     = in.Remark;//华为E站，供应商，用于回传
		readyPack.LastModifyTime = sysDateTime;
		readyPackDAO.insert(readyPack);

        return out;
    }
}
