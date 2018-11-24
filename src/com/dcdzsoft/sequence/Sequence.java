package com.dcdzsoft.sequence;

import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.constant.ErrorCode;

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
public abstract class Sequence
{
    protected static final Log log = LogFactory.getLog(Sequence.class.getName());

     /**
     * The max key value
     */
    protected long maxKey = 0L;

    /**
     * The min key value
     */
    protected long minKey = 0L;

    /**
     * The current key value
     */
    protected long currentKey = 0L;

    /**
     * The cached key size
     */
    protected long cacheSize = 0L;

    /**
     * 所有的键值在数据库中的初始化值都为0
     */
    public Sequence()
    {
    }

    public void initValue()
        throws EduException
    {
        doGetCurrentMaxValue();
        minKey = maxKey - cacheSize + 1;
        currentKey = minKey;
    }

    /**
     * 取值方法，提供键的当前值(Temeplate method)
     * @return 返回当前的键值
     * @throws EduException
     */
    public synchronized long getNextKey()
        throws EduException
    {
        if (currentKey > maxKey)
        {
            doGetCurrentMaxValue();
            minKey = maxKey - cacheSize + 1;
            currentKey = minKey;
        }

        return currentKey++;
    }

    /**
     * 执行更新查询
     *  1.独立提交,防止事务失败缓存脏数据
     *  2.先更新后查询,防止多个服务器读取相同的数据
     * @param sequenceName String
     * @throws EduException
     */
    protected void executeSelectForUpdate(String sequenceName)
        throws EduException
    {
        long nMaxValue = 0L;
        long nCacheSize = 0L;

        Statement stmt = null;
        ResultSet rset = null;

        Connection conn = null;
        DBSession dbSession = new DBSession();

        //String sequenceName = sequenceClass.getSimpleName();

        String updateSQL = "UPDATE PASequence SET SeqValue = SeqValue + CacheSize  WHERE SeqName = " + "'" + sequenceName + "'";
        String qrySQL = "SELECT SeqValue,CacheSize FROM PASequence WHERE SeqName = " + "'" + sequenceName + "'";

        try
        {

        	log.debug(updateSQL);
        	log.debug(qrySQL);
        	
            conn = dbSession.getConnection();

            //执行更新
            stmt = conn.createStatement();
            stmt.executeUpdate(updateSQL);
            stmt.close();
            stmt = null;

            //执行查询
            stmt = conn.createStatement();
            rset = stmt.executeQuery(qrySQL);
            if (rset.next())
            {
                nMaxValue = rset.getLong(1);
                nCacheSize = rset.getLong(2);
            }

            //必须独立提交
            dbSession.commit();

            maxKey = nMaxValue;
            cacheSize = nCacheSize;
        }
        catch (SQLException e)
        {
            dbSession.rollback();

            log.error("[sqlcode]:" + e.getErrorCode() + " [errmsg]:" + e.getMessage());
            throw new EduException(ErrorCode.ERR_GETSEQUENCEERR);
        }
        finally
        {
            dbSession.closeResultSet(rset);
            dbSession.closeStatement(stmt);

            dbSession.closeConnection();
            conn = null;
        }
    }

    /**
     * 从数据库提取键的当前值(primitive method)
     * @throws EduException
     */
    protected abstract void doGetCurrentMaxValue()
        throws EduException;
    /*模板
     String updateSql = "UPDATE PASequence SET SeqValue = SeqValue + CacheSize + " WHERE SeqName = ''";
     String querySql = "SELECT SeqValue,CacheSize FROM PASequence WHERE SeqName = ''";
     */

}
