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
 * <p>Description: 箱体类型修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class TBBoxTypeMod extends ActionBean {

    public String doBusiness(InParamTBBoxTypeMod in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = in.BoxNo;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.BoxNo)
            || StringUtils.isEmpty(in.BoxType))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //修改类型
        TBServerBoxDAO boxDAO = daoFactory.getTBServerBoxDAO();
        JDBCFieldArray setCols1 = new JDBCFieldArray();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();

        setCols1.add("BoxType", in.BoxType);
        whereCols1.add("BoxNo", in.BoxNo);
        whereCols1.add("TerminalNo", in.TerminalNo);

        boxDAO.update(setCols1, whereCols1);

        return result;
    }
}
