package com.dcdzsoft.business.mb;

import javax.sql.RowSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.GServer;
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
 * <p>Description: 设备签到 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBDeviceSign0 extends ActionBean {

    public OutParamMBDeviceSign0 doBusiness(InParamMBDeviceSign0 in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamMBDeviceSign0 out = new OutParamMBDeviceSign0();

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //判断安装密码是否一致
        String SoftwareVersion = "";
        String InitPasswd = "";
        String LastInitPasswd = "";

        SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
        JDBCFieldArray whereColsDummy = new JDBCFieldArray();
        RowSet rset = systemInfoDAO.select(whereColsDummy);

        if (RowSetUtils.rowsetNext(rset)) {
            SoftwareVersion = RowSetUtils.getStringValue(rset, "SoftwareVersion");
            InitPasswd = RowSetUtils.getStringValue(rset, "InitPasswd");
            LastInitPasswd = RowSetUtils.getStringValue(rset, "LastInitPasswd");
        }

        //安装密码不一致
        if (!InitPasswd.equalsIgnoreCase(in.InitPasswd)) {
            if (StringUtils.isNotEmpty(LastInitPasswd) && !LastInitPasswd.equalsIgnoreCase(in.InitPasswd)) {
                out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;
                out.ServerTime = sysDateTime;
                out.SoftwareVersion = SoftwareVersion;

                log.error("[invalid initpasswd] " + in.TerminalNo);

                return out;
            }
        }

        JSONObject obj = JSONObject.fromObject(in.TerminalInfo);
        TBTerminal terminal = (TBTerminal) JsonUtils.jsonObjectToDTO(obj, TBTerminal.class);
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();

        if (terminalDAO.isExist(terminal)) {
            //已经存在柜体，注册失败
            if (!SysDict.TERMINAL_REGISTERFLAG_YES.equalsIgnoreCase(terminal.RegisterFlag)) {
                out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;
                out.ServerTime = sysDateTime;
                out.SoftwareVersion = SoftwareVersion;

                log.error("[exists terminalNo] " + in.TerminalNo);

                return out;
            }

            out.UpdateFlag1 = "U";
        }else
        {
            out.UpdateFlag1 = "A";
        }

        String[] boxList = in.BoxInfo.split("~");

        TBServerBoxDAO boxDAO = daoFactory.getTBServerBoxDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TerminalNo", in.TerminalNo);

        int boxCount = boxDAO.isExist(whereCols);
        if (boxCount == 0 || boxCount != boxList.length ) {
            out.UpdateFlag2 = "A";
        }else{
            out.UpdateFlag2 = "U";
        }

        out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_YES;


        return out;
    }
}
