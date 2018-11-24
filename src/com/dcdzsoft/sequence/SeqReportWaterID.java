package com.dcdzsoft.sequence;

import com.dcdzsoft.EduException;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author wangzl
 * @version 1.0
 */
public class SeqReportWaterID extends Sequence{
    private static String SEQUENCE_NAME = SeqReportWaterID.class.getSimpleName();

    public SeqReportWaterID() {
    }

    protected void doGetCurrentMaxValue() throws EduException
    {
        executeSelectForUpdate(SEQUENCE_NAME);
    }

}
