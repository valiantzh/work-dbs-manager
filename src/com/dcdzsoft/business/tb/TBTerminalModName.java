package com.dcdzsoft.business.tb;

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
 * <p>Description: 柜体名称修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class TBTerminalModName extends ActionBean {

    public int doBusiness(InParamTBTerminalModName in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.TerminalNo)
            || StringUtils.isEmpty(in.TerminalName))
            throw new EduException(ErrorCode.ERR_PARMERR);

        if (StringUtils.isEmpty(in.RemoteFlag))
            in.RemoteFlag = SysDict.OPER_FLAG_REMOTE;

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        in.TerminalName = StringUtils.normalizeName(in.TerminalName);
        //修改状态
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("TerminalName", in.TerminalName);
        setCols.add("MBDeviceNo", in.MBDeviceNo);
        setCols.add("OfBureau", in.OfBureau);
        setCols.add("OfSegment", in.OfSegment);
        setCols.add("Location", in.Location);
        
        if(StringUtils.isNotEmpty(in.Latlon)){
            String[] Latlon = {"0","0"};
            if(StringUtils.isNotEmpty(in.Latlon)){
                Latlon = in.Latlon.split(",");
            }
            if(Latlon==null || Latlon.length != 2){
                Latlon = new String[2];
                Latlon[0] = "0";
                Latlon[1] = "0";
            }
            setCols.add("Latlon", in.Latlon);
            setCols.add("Latitude", NumberUtils.parseDouble(Latlon[0]));
            setCols.add("Longitude", NumberUtils.parseDouble(Latlon[1]));
        }

        whereCols.add("TerminalNo", in.TerminalNo);

        terminalDAO.update(setCols, whereCols);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = null;
        log.StationAddr = "";
        log.Remark = in.TerminalNo + "," + in.TerminalName;

        commonDAO.addOperatorLog(log);

        ///推送到设备端
        try
        {
        	PushBusinessProxy.doBusiness(in);
        }catch(EduException e)
        {
        	
        }

        return result;
    }
}
