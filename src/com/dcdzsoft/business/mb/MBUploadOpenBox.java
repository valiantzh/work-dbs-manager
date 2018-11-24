package com.dcdzsoft.business.mb;

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
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 上传开箱消息 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class MBUploadOpenBox extends ActionBean
{

    public OutParamMBUploadOpenBox doBusiness(InParamMBUploadOpenBox in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamMBUploadOpenBox out = new OutParamMBUploadOpenBox();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.BoxNo)
			||StringUtils.isEmpty(in.TradeWaterNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//3.调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		if(StringUtils.isEmpty(in.OpenBoxType)){
			in.OpenBoxType = SysDict.OPEN_CLOSE_TYPE_OPEN2UNKNOWN;
		}
		if(in.TradeWaterNo.length()>32){
		    in.TradeWaterNo = in.TradeWaterNo.substring(0, 32);
		}
		///
		in.TradeWaterNo = "O-"+in.TradeWaterNo;//开箱流水和关箱流水号共用
		MBOpenBoxWaterDAO openBoxWaterDAO = daoFactory.getMBOpenBoxWaterDAO();
		MBOpenBoxWater openBoxWater = new MBOpenBoxWater();
		openBoxWater.TradeWaterNo = in.TradeWaterNo;
		if(openBoxWaterDAO.isExist(openBoxWater)){
		    JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();
    
            if(SysDict.BOX_DOOR_STATUS_OPEN.equals(in.DoorStatus)){
                setCols.add("OpenBoxStatus",SysDict.OPEN_CLOSE_BOX_STATUS_OPENSUCCESS);
            }else{
                setCols.add("OpenBoxStatus",SysDict.OPEN_CLOSE_BOX_STATUS_OPENFAIL);
            }
            
            setCols.add("LastModifyTime",sysDateTime);
            setCols.add("Remark",""+in.OccurTime+","+in.ArticleStatus);
            
            whereCols.add("TradeWaterNo", in.TradeWaterNo);
            
            openBoxWaterDAO.update(setCols, whereCols);
		}else{
		    openBoxWater.TerminalNo = in.TerminalNo;
		    openBoxWater.BoxNo = in.BoxNo;
		    openBoxWater.OpenBoxType = in.OpenBoxType;
		    openBoxWater.OpenBoxUser = in.OperID;
		    if(SysDict.BOX_DOOR_STATUS_OPEN.equals(in.DoorStatus)){
		        openBoxWater.OpenBoxStatus = SysDict.OPEN_CLOSE_BOX_STATUS_OPENSUCCESS;
		    }else{
		        openBoxWater.OpenBoxStatus = SysDict.OPEN_CLOSE_BOX_STATUS_OPENFAIL;
		    }
		    openBoxWater.UploadFlag = SysDict.UPLOAD_FLAG_NO;
		    openBoxWater.OpenBoxDate = sysDate;
		    openBoxWater.OpenBoxDateTime = sysDateTime;
		    openBoxWater.LastModifyTime = sysDateTime;
		    openBoxWater.Remark = ""+in.OccurTime+","+in.ArticleStatus;
		    openBoxWaterDAO.insert(openBoxWater);
		}

        return out;
    }
}
