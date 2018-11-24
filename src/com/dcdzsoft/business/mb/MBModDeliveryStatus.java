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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 修改投递发送状态 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBModDeliveryStatus extends ActionBean
{

    public int doBusiness(InParamMBModDeliveryStatus in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PackageID)
			||in.StoredTime == null 
			||StringUtils.isEmpty(in.PackageStatus)
			||StringUtils.isEmpty(in.UploadFlag))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//修改在箱信息
		if(SysDict.PACKAGE_STATUS_NORMAL.equals(in.PackageStatus)
		    ||(SysDict.PACKAGE_STATUS_LOCKED.equals(in.PackageStatus))
		    ||(SysDict.PACKAGE_STATUS_LOCKED.equals(in.PackageStatus))
		    ||(SysDict.PACKAGE_STATUS_TIMEOUT.equals(in.PackageStatus))
            ||(SysDict.PACKAGE_STATUS_RETURNGOODS.equals(in.PackageStatus))//退货在箱-泰和
		    )
		{
			PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();

			JDBCFieldArray setCols = new JDBCFieldArray();
	        JDBCFieldArray whereCols = new JDBCFieldArray();
	        
	        setCols.add("UploadFlag", in.UploadFlag);
	        whereCols.add("PackageID", in.PackageID);
	        whereCols.add("TerminalNo", in.TerminalNo);
	        
	        if("1".equalsIgnoreCase(in.UploadFlag)) //反馈成功
	        {
	        	setCols.checkAdd("OfLogisticsID", in.OfLogisticsID);
	        	setCols.checkAdd("OfLogisticsName", in.OfLogisticsName);
	        	setCols.checkAdd("StaOrderID", in.StaOrderID);
	        }
	        
	        inboxPackDAO.update(setCols, whereCols);
		}else{ //修改历史信息
			PTDeliverHistoryDAO historyPackDAO = daoFactory.getPTDeliverHistoryDAO();

			JDBCFieldArray setCols1 = new JDBCFieldArray();
	        JDBCFieldArray whereCols1 = new JDBCFieldArray();
	        
	        setCols1.add("UploadFlag", in.UploadFlag);
	        whereCols1.add("PackageID", in.PackageID);
	        whereCols1.add("TerminalNo", in.TerminalNo);
	        whereCols1.add("StoredTime", in.StoredTime);
	        
	        historyPackDAO.update(setCols1, whereCols1);
		}

		PTFeedbackFailDAO feedbackFailDAO = daoFactory.getPTFeedbackFailDAO();
		PTFeedbackFail feedbackFail = new PTFeedbackFail();
		feedbackFail.TradeWaterNo = in.TradeWaterNo;
		
		if("1".equalsIgnoreCase(in.UploadFlag)) //反馈成功
        {
			feedbackFailDAO.delete(feedbackFail);
        }else{
        	if(feedbackFailDAO.isExist(feedbackFail)){
        		JDBCFieldArray setCols2 = new JDBCFieldArray();
    	        JDBCFieldArray whereCols2 = new JDBCFieldArray();
    	        
    	        setCols2.add("LastModifyTime", utilDAO.getCurrentDateTime());
    	        if("ManSyncDelivery".equals(in.Remark)){
    	            setCols2.addSQL(" FailureCount =  1 ");//人工同步，重新开始计数
    	        }else{
    	            setCols2.addSQL(" FailureCount = FailureCount + 1 ");
    	            setCols2.add("Remark", in.Remark);
    	        }
    	        whereCols2.add("TradeWaterNo", in.TradeWaterNo);
    	        
    	        feedbackFailDAO.update(setCols2, whereCols2);
        	}else{
        		feedbackFail.TradeWaterNo = in.TradeWaterNo;
        		feedbackFail.PackageID = in.PackageID;
        		feedbackFail.TerminalNo = in.TerminalNo;
        		feedbackFail.BoxNo = in.BoxNo;
        		feedbackFail.StoredTime = in.StoredTime;
        		feedbackFail.PostmanID = in.PostmanID;
        		feedbackFail.CompanyID = in.CompanyID;
        		feedbackFail.OfBureau = in.OfBureau;
        		feedbackFail.CustomerMobile = in.CustomerMobile;
        		feedbackFail.BlankBoxKey = in.OpenBoxKey;
        		feedbackFail.TakedTime  = in.TakedTime;
        		feedbackFail.PackageStatus = in.PackageStatus;
        		feedbackFail.OfLogisticsID = in.OfLogisticsID;
        		feedbackFail.OfLogisticsName = in.OfLogisticsName;
        		feedbackFail.StaOrderID = in.StaOrderID;
        		feedbackFail.FailureTime = utilDAO.getCurrentDateTime();
        		feedbackFail.FailureCount = 1;
        		feedbackFail.Remark = in.Remark;
        		feedbackFail.LastModifyTime = feedbackFail.FailureTime;
        		
        		feedbackFailDAO.insert(feedbackFail);
        	}
        }

        return result;
    }
}
