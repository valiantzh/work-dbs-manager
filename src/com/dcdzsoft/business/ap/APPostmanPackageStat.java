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
 * <p>Description: 投递员包裹统计 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanPackageStat extends ActionBean
{

    public java.util.List<OutParamAPPostmanPackageStat> doBusiness(InParamAPPostmanPackageStat in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PostmanID)
			||in.BeginDate == null 
			||in.EndDate == null )
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.util.List<OutParamAPPostmanPackageStat> outList = new java.util.LinkedList<OutParamAPPostmanPackageStat>();


		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		if(in.BeginDate == null){
			in.BeginDate = DateUtils.addDate(utilDAO.getCurrentDate(), -30);
		}
		
		whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
		whereSQL.checkAdd("StoredDate", "<=", in.EndDate);
		whereSQL.add("PostmanID", in.PostmanID);
		whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);

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
		
		String sql = "SELECT COUNT(*) as PackageNum, PackageStatus FROM  V_OrderStat3 a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + " GROUP BY PackageStatus";
		rset = dbSession.executeQuery(sql, whereSQL);

		while (RowSetUtils.rowsetNext(rset)) {
			OutParamAPPostmanPackageStat outParam = new OutParamAPPostmanPackageStat();

			outParam.PackageStatus = RowSetUtils.getStringValue(rset, "PackageStatus");
			outParam.PackageNum = RowSetUtils.getIntValue(rset, "PackageNum");
			
			outList.add(outParam);
		}

        return outList;
    }
}
