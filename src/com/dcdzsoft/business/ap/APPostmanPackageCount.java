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
 * <p>Description: 投递员包裹列表总记录数 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanPackageCount extends ActionBean
{

    public int doBusiness(InParamAPPostmanPackageCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PostmanID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		String viewName = "";

		if (StringUtils.isEmpty(in.PackageStatus))
			viewName = "V_AllOrder";
		else if ("0".equalsIgnoreCase(in.PackageStatus)
				|| "12".equalsIgnoreCase(in.PackageStatus))
			viewName = "PTInBoxPackage";
		else
			viewName = "PTDeliverHistory";

		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		if(in.BeginDate == null){
			in.BeginDate = DateUtils.addDate(utilDAO.getCurrentDate(), -30);
		}
		
		whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
		whereSQL.checkAdd("StoredDate", "<=", in.EndDate);
		whereSQL.add("PostmanID", in.PostmanID);
		whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
		whereSQL.checkAdd("PackageID", in.PackageID);
		whereSQL.checkAdd("TerminalNo", in.TerminalNo);

		if (StringUtils.isNotEmpty(in.PackageStatus)) {
			if ("12".equalsIgnoreCase(in.PackageStatus)) // 逾期件未取回
			{
				java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
				ControlParam ctrlParam = ControlParam.getInstance();

				int days = NumberUtils.parseInt(ctrlParam.recoveryTimeout);

				whereSQL.add("StoredTime", "<=",
						DateUtils.addTimeStamp(sysDateTime, -days));
			} else if ("11".equalsIgnoreCase(in.PackageStatus)) // 投递员取回(投递员取回,逾期回收)
			{
				whereSQL.addSQL(utilDAO.getFlagInSQL("PackageStatus", "5,6"));
			} else {
				whereSQL.add("PackageStatus", in.PackageStatus);
			}
		}

		String sql = "SELECT COUNT(PackageID) FROM " + viewName + " a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
		
		result = dbSession.executeQueryCount(sql, whereSQL);

        return result;
    }
}
