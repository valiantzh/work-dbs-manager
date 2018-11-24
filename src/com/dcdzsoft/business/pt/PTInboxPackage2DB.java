package com.dcdzsoft.business.pt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.sql.RowSet;

import net.sf.json.JSONObject;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 在箱信息入库 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTInboxPackage2DB extends ActionBean
{

    public int doBusiness(InParamPTInboxPackage2DB in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.FileName))
			throw new EduException(ErrorCode.ERR_PARMERR);


		if(ApplicationConfig.getInstance().getSysRunModel() != Constant.SYS_RUN_MODEL_MOBILE)
			return result;
		
		//读取数据
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try
		{
			isr = new InputStreamReader(new FileInputStream(in.FileName), "UTF-8"); 
	        br = new BufferedReader(isr); 
	        
	        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();

	        //先删除，保证数据不重复
	        JDBCFieldArray whereCols1 = new JDBCFieldArray();
	        whereCols1.add("TerminalNo", in.TerminalNo);
	        inboxPackDAO.delete(whereCols1);
	        
	        String line = null; 
	        while ((line = br.readLine()) != null) {
	        	if(StringUtils.isEmpty(line.trim()))
	        		continue;
	        	
	        	JSONObject obj = JSONObject.fromObject(line);
	        	PTInBoxPackage rec = (PTInBoxPackage)JsonUtils.jsonObjectToDTO(obj, PTInBoxPackage.class);
	        	rec.TerminalNo = in.TerminalNo;
	        	
	        	inboxPackDAO.insert(rec);
	        }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(isr != null)
			{
				try
				{
					isr.close();
				}catch(Exception e){}
			}
			
			if(br != null)
			{
				try
				{
					br.close();
				}catch(Exception e){}
			}
		}

        return result;
    }
}
