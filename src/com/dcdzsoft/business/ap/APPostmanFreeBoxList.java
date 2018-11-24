package com.dcdzsoft.business.ap;

import javax.sql.RowSet;

import org.apache.commons.lang.StringUtils;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;

import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.client.pda.PDAManager;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 获取可用箱列表 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APPostmanFreeBoxList extends ActionBean
{

    public OutParamAPPostmanFreeBoxList doBusiness(InParamAPPostmanFreeBoxList in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanFreeBoxList out = new OutParamAPPostmanFreeBoxList();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

        PDAManager.getInstance().updatePdaLastSignTime(in.TerminalNo);
        
        String freeBoxList = PDAManager.getInstance().getFreeBoxList(in.TerminalNo, in.TradeWaterNo);
        if(StringUtils.isNotEmpty(freeBoxList)){
            //System.out.println("freeBoxList:"+freeBoxList);
            String[] temp = freeBoxList.split("=");
            if(temp.length ==2){
                if(!"NULL".equals(temp[0])){
                    out.TerminalNo = temp[0];//柜号
                }
                
                if(!"NOTFREE".equals(temp[1])){
                    out.FreeBoxList = temp[1];//可用箱列表BoxNo,BoxType~BoxNo,BoxType  1,0~2,1~3,2~.....
                }
            }
            
        }

        return out;
    }
}
