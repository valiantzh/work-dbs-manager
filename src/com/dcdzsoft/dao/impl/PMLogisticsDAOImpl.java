package com.dcdzsoft.dao.impl;

import java.sql.*;
import javax.sql.RowSet;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.dao.factory.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.constant.*;

public class PMLogisticsDAOImpl implements PMLogisticsDAO
{
	private static final Log log = LogFactory.getLog(PMLogisticsDAOImpl.class.getName());

	private DBSession dbSession = LocalSessionHolder.getCurrentDBSession();

	private Connection conn = dbSession.getConnection();

	private static final int RSET_FETCH_SIZE = 50;


	public int insert(PMLogistics obj) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "INSERT INTO PMLogistics(LogisticsID,LogisticsName,PinyinCode,Remark)VALUES(?,?,?,?)";

		SQLLog.logInsert(log, obj);

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.LogisticsID);
			PreparedStmtUtils.setString(pstmt,2,obj.LogisticsName);
			PreparedStmtUtils.setString(pstmt,3,obj.PinyinCode);
			PreparedStmtUtils.setString(pstmt,4,obj.Remark);
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_ADDPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public int update(JDBCFieldArray setCols,JDBCFieldArray whereCols) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "UPDATE PMLogistics SET ";

		StringBuffer sbSet = new StringBuffer(256);
		sbSet.append(setCols.toSetSQL());
		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null){
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbSet.toString() + " WHERE 1=1 " + sbWhere.toString();

		SQLLog.logUpdate(log,"PMLogistics",setCols,whereCols);

		try
		{
			pstmt = conn.prepareStatement(sql);
			
			int parameterIndex = 0;
			for(int i = 0 ; i < setCols.size() ; i++){
				if(setCols.get(i).name != null){
					parameterIndex++;
					PreparedStmtUtils.setObject(pstmt,parameterIndex,setCols.get(i));
				}
			}
			
			if(whereCols != null){
				for(int j = 0 ; j < whereCols.size() ; j++){
					if(whereCols.get(j).name != null){
						parameterIndex++;
						PreparedStmtUtils.setObject(pstmt,parameterIndex,whereCols.get(j));
					}
				}
			}
			
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_MODPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public int delete(PMLogistics obj) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "DELETE FROM PMLogistics WHERE LogisticsID = ? ";

		log.debug("[delete sql]:" + "DELETE FROM PMLogistics WHERE LogisticsID = " + StringUtils.addQuote(obj.LogisticsID) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.LogisticsID);
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_DELPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public int delete(JDBCFieldArray whereCols) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "DELETE FROM PMLogistics WHERE 1=1 " ;

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		SQLLog.logDelete(log,"PMLogistics",whereCols);

		try
		{
			pstmt = conn.prepareStatement(sql);
			
			int parameterIndex = 0;
			if(whereCols != null){
				for(int j = 0 ; j < whereCols.size() ; j++){
					if(whereCols.get(j).name != null){
						parameterIndex++;
						PreparedStmtUtils.setObject(pstmt,parameterIndex,whereCols.get(j));
					}
				}
			}
			
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_DELPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public boolean isExist(PMLogistics obj) throws EduException
	{
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int count = 0;

		String sql = "SELECT COUNT(*) FROM PMLogistics WHERE LogisticsID = ? ";

		log.debug("[isExist sql]:" + "SELECT COUNT(*) FROM PMLogistics WHERE LogisticsID = " + StringUtils.addQuote(obj.LogisticsID) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.LogisticsID);
			rset = pstmt.executeQuery();

			rset.next();
			count = rset.getInt(1);
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeResultSet(rset);
			dbSession.closeStatement(pstmt);
		}

		return (count > 0) ? true : false;
	}


	public int isExist(JDBCFieldArray whereCols) throws EduException
	{
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int count = 0;

		String sql = "SELECT COUNT(*) FROM PMLogistics WHERE 1=1 " ;

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		SQLLog.logIsExist(log,"PMLogistics",whereCols);

		try
		{
			pstmt = conn.prepareStatement(sql);
			
			int parameterIndex = 0;
			if(whereCols != null){
				for(int j = 0 ; j < whereCols.size() ; j++){
					if(whereCols.get(j).name != null){
						parameterIndex++;
						PreparedStmtUtils.setObject(pstmt,parameterIndex,whereCols.get(j));
					}
				}
			}
			
			rset = pstmt.executeQuery();

			rset.next();
			count = rset.getInt(1);
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeResultSet(rset);
			dbSession.closeStatement(pstmt);
		}

		return count;
	}


	public PMLogistics find(PMLogistics obj) throws EduException
	{
		PMLogistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT LogisticsID,LogisticsName,PinyinCode,Remark  FROM PMLogistics WHERE LogisticsID = ? ";

		log.debug("[find sql]:" + "SELECT LogisticsID,LogisticsName,PinyinCode,Remark  FROM PMLogistics WHERE LogisticsID = " + StringUtils.addQuote(obj.LogisticsID) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.LogisticsID);
			rset = pstmt.executeQuery();

			if(rset.next() == true)
			{
				result = new PMLogistics();
				result.LogisticsID = rset.getString(1);
				result.LogisticsName = rset.getString(2);
				result.PinyinCode = rset.getString(3);
				result.Remark = rset.getString(4);

			}
			else
			{
				throw new EduException(ErrorCode.ERR_PMLOGISTICSNODATA);
			}
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeResultSet(rset);
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public RowSet select(JDBCFieldArray whereCols) throws EduException
	{
		RowSet cacheSet = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM PMLogistics WHERE 1=1 ";
		SQLLog.logSelectRowSet(log,sql,whereCols);

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		try
		{
			pstmt = conn.prepareStatement(sql);
			
			int parameterIndex = 0;
			if(whereCols != null){
				for(int j = 0 ; j < whereCols.size() ; j++){
					if(whereCols.get(j).name != null){
						parameterIndex++;
						PreparedStmtUtils.setObject(pstmt,parameterIndex,whereCols.get(j));
					}
				}
			}
			
			rset = pstmt.executeQuery();

			rset.setFetchSize(RSET_FETCH_SIZE);

			cacheSet = dbSession.populate(rset);
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeResultSet(rset);
			dbSession.closeStatement(pstmt);
		}

		return cacheSet;
	}


	public RowSet select(JDBCFieldArray whereCols,List<String> orderCols) throws EduException
	{
		RowSet cacheSet = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM PMLogistics WHERE 1=1 ";
		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		StringBuffer sbOrder = new StringBuffer(256);
		if(orderCols != null && orderCols.size() > 0)
		{
			sbOrder.append(" ORDER BY ");
			for(int j = 0 ; j < orderCols.size() ; j++)
			{
				sbOrder.append(orderCols.get(j));
				if(j != (orderCols.size() - 1))
					sbOrder.append(",");
			}
		}

		sql = sql + sbWhere.toString() + sbOrder.toString();

		SQLLog.logSelectRowSet(log,sql,whereCols);

		try
		{
			pstmt = conn.prepareStatement(sql);
			
			int parameterIndex = 0;
			if(whereCols != null){
				for(int j = 0 ; j < whereCols.size() ; j++){
					if(whereCols.get(j).name != null){
						parameterIndex++;
						PreparedStmtUtils.setObject(pstmt,parameterIndex,whereCols.get(j));
					}
				}
			}
			
			rset = pstmt.executeQuery();

			rset.setFetchSize(RSET_FETCH_SIZE);

			cacheSet = dbSession.populate(rset);
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeResultSet(rset);
			dbSession.closeStatement(pstmt);
		}

		return cacheSet;
	}


	public RowSet select(JDBCFieldArray whereCols,int recordBegin,int recordNum) throws EduException
	{
		RowSet cacheSet = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM  PMLogistics WHERE 1=1 ";

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		java.util.List<String> list = new java.util.LinkedList<String>();
		list.add("LogisticsID");

		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbSession.getDatabaseType());
		UtilDAO utilDAO = daoFactory.getUtilDAO();
		sql = utilDAO.constructNMSql(sql,list,recordBegin,recordNum);

		log.debug("[RowSet sql]:" + sql );

		try
		{
			pstmt = conn.prepareStatement(sql);
			
			int parameterIndex = 0;
			if(whereCols != null){
				for(int j = 0 ; j < whereCols.size() ; j++){
					if(whereCols.get(j).name != null){
						parameterIndex++;
						PreparedStmtUtils.setObject(pstmt,parameterIndex,whereCols.get(j));
					}
				}
			}
			
			rset = pstmt.executeQuery();

			rset.setFetchSize(RSET_FETCH_SIZE);

			cacheSet = dbSession.populate(rset);
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeResultSet(rset);
			dbSession.closeStatement(pstmt);
		}

		return cacheSet;
	}


	public String selectFunctions(String fName,JDBCFieldArray whereCols) throws EduException
	{
		String result = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT " + fName + " FROM PMLogistics WHERE 1=1 ";
		SQLLog.logSelectFunction(log,sql,whereCols);

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		try
		{
			pstmt = conn.prepareStatement(sql);
			
			int parameterIndex = 0;
			if(whereCols != null){
				for(int j = 0 ; j < whereCols.size() ; j++){
					if(whereCols.get(j).name != null){
						parameterIndex++;
						PreparedStmtUtils.setObject(pstmt,parameterIndex,whereCols.get(j));
					}
				}
			}
			
			rset = pstmt.executeQuery();

			rset.next();
			result = rset.getString(1);

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPMLOGISTICSFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeResultSet(rset);
			dbSession.closeStatement(pstmt);
		}

		return result;
	}

}