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

public class PTDeliverHistoryDAOImpl implements PTDeliverHistoryDAO
{
	private static final Log log = LogFactory.getLog(PTDeliverHistoryDAOImpl.class.getName());

	private DBSession dbSession = LocalSessionHolder.getCurrentDBSession();

	private Connection conn = dbSession.getConnection();

	private static final int RSET_FETCH_SIZE = 50;


	public int insert(PTDeliverHistory obj) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "INSERT INTO PTDeliverHistory(TerminalNo,PackageID,StoredTime,PostmanID,StoredDate,ExpiredTime,CompanyID,DynamicCode,BoxNo,CustomerID,CustomerMobile,CustomerName,CustomerAddress,TakedPersonID,OpenBoxKey,BlankBoxKey,HireAmt,HireWhoPay,PayedAmt,PosPayFlag,TakedTime,TakedWay,LeftFlag,PackageStatus,TradeWaterNo,OfLogisticsID,OfLogisticsName,StaOrderID,LastModifyTime,UploadFlag,Remark)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		SQLLog.logInsert(log, obj);

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			PreparedStmtUtils.setTimestamp(pstmt,3,obj.StoredTime);
			PreparedStmtUtils.setString(pstmt,4,obj.PostmanID);
			PreparedStmtUtils.setDate(pstmt,5,obj.StoredDate);
			PreparedStmtUtils.setTimestamp(pstmt,6,obj.ExpiredTime);
			PreparedStmtUtils.setString(pstmt,7,obj.CompanyID);
			PreparedStmtUtils.setString(pstmt,8,obj.DynamicCode);
			PreparedStmtUtils.setString(pstmt,9,obj.BoxNo);
			PreparedStmtUtils.setString(pstmt,10,obj.CustomerID);
			PreparedStmtUtils.setString(pstmt,11,obj.CustomerMobile);
			PreparedStmtUtils.setString(pstmt,12,obj.CustomerName);
			PreparedStmtUtils.setString(pstmt,13,obj.CustomerAddress);
			PreparedStmtUtils.setString(pstmt,14,obj.TakedPersonID);
			PreparedStmtUtils.setString(pstmt,15,obj.OpenBoxKey);
			PreparedStmtUtils.setString(pstmt,16,obj.BlankBoxKey);
			PreparedStmtUtils.setDouble(pstmt,17,obj.HireAmt);
			PreparedStmtUtils.setString(pstmt,18,obj.HireWhoPay);
			PreparedStmtUtils.setDouble(pstmt,19,obj.PayedAmt);
			PreparedStmtUtils.setString(pstmt,20,obj.PosPayFlag);
			PreparedStmtUtils.setTimestamp(pstmt,21,obj.TakedTime);
			PreparedStmtUtils.setString(pstmt,22,obj.TakedWay);
			PreparedStmtUtils.setString(pstmt,23,obj.LeftFlag);
			PreparedStmtUtils.setString(pstmt,24,obj.PackageStatus);
			PreparedStmtUtils.setString(pstmt,25,obj.TradeWaterNo);
			PreparedStmtUtils.setString(pstmt,26,obj.OfLogisticsID);
			PreparedStmtUtils.setString(pstmt,27,obj.OfLogisticsName);
			PreparedStmtUtils.setString(pstmt,28,obj.StaOrderID);
			PreparedStmtUtils.setTimestamp(pstmt,29,obj.LastModifyTime);
			PreparedStmtUtils.setString(pstmt,30,obj.UploadFlag);
			PreparedStmtUtils.setString(pstmt,31,obj.Remark);
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_ADDPTDELIVERHISTORYFAIL;
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

		String sql = "UPDATE PTDeliverHistory SET ";

		StringBuffer sbSet = new StringBuffer(256);
		sbSet.append(setCols.toSetSQL());
		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null){
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbSet.toString() + " WHERE 1=1 " + sbWhere.toString();

		SQLLog.logUpdate(log,"PTDeliverHistory",setCols,whereCols);

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
			String errorCode = ErrorCode.ERR_MODPTDELIVERHISTORYFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public int delete(PTDeliverHistory obj) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "DELETE FROM PTDeliverHistory WHERE TerminalNo = ?  AND PackageID = ?  AND StoredTime = ? ";

		log.debug("[delete sql]:" + "DELETE FROM PTDeliverHistory WHERE TerminalNo = " + StringUtils.addQuote(obj.TerminalNo) +
						" AND PackageID = " + StringUtils.addQuote(obj.PackageID) +
						" AND StoredTime = " + StringUtils.addQuote(obj.StoredTime) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			PreparedStmtUtils.setTimestamp(pstmt,3,obj.StoredTime);
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_DELPTDELIVERHISTORYFAIL;
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

		String sql = "DELETE FROM PTDeliverHistory WHERE 1=1 " ;

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		SQLLog.logDelete(log,"PTDeliverHistory",whereCols);

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
			String errorCode = ErrorCode.ERR_DELPTDELIVERHISTORYFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public boolean isExist(PTDeliverHistory obj) throws EduException
	{
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int count = 0;

		String sql = "SELECT COUNT(*) FROM PTDeliverHistory WHERE TerminalNo = ?  AND PackageID = ?  AND StoredTime = ? ";

		log.debug("[isExist sql]:" + "SELECT COUNT(*) FROM PTDeliverHistory WHERE TerminalNo = " + StringUtils.addQuote(obj.TerminalNo) +
						" AND PackageID = " + StringUtils.addQuote(obj.PackageID) +
						" AND StoredTime = " + StringUtils.addQuote(obj.StoredTime) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			PreparedStmtUtils.setTimestamp(pstmt,3,obj.StoredTime);
			rset = pstmt.executeQuery();

			rset.next();
			count = rset.getInt(1);
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPTDELIVERHISTORYFAIL;
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

		String sql = "SELECT COUNT(*) FROM PTDeliverHistory WHERE 1=1 " ;

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		SQLLog.logIsExist(log,"PTDeliverHistory",whereCols);

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
			String errorCode = ErrorCode.ERR_QRYPTDELIVERHISTORYFAIL;
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


	public PTDeliverHistory find(PTDeliverHistory obj) throws EduException
	{
		PTDeliverHistory result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT TerminalNo,PackageID,StoredTime,PostmanID,StoredDate,ExpiredTime,CompanyID,DynamicCode,BoxNo,CustomerID,CustomerMobile,CustomerName,CustomerAddress,TakedPersonID,OpenBoxKey,BlankBoxKey,HireAmt,HireWhoPay,PayedAmt,PosPayFlag,TakedTime,TakedWay,LeftFlag,PackageStatus,TradeWaterNo,OfLogisticsID,OfLogisticsName,StaOrderID,LastModifyTime,UploadFlag,Remark  FROM PTDeliverHistory WHERE TerminalNo = ?  AND PackageID = ?  AND StoredTime = ? ";

		log.debug("[find sql]:" + "SELECT TerminalNo,PackageID,StoredTime,PostmanID,StoredDate,ExpiredTime,CompanyID,DynamicCode,BoxNo,CustomerID,CustomerMobile,CustomerName,CustomerAddress,TakedPersonID,OpenBoxKey,BlankBoxKey,HireAmt,HireWhoPay,PayedAmt,PosPayFlag,TakedTime,TakedWay,LeftFlag,PackageStatus,TradeWaterNo,OfLogisticsID,OfLogisticsName,StaOrderID,LastModifyTime,UploadFlag,Remark  FROM PTDeliverHistory WHERE TerminalNo = " + StringUtils.addQuote(obj.TerminalNo) +
						" AND PackageID = " + StringUtils.addQuote(obj.PackageID) +
						" AND StoredTime = " + StringUtils.addQuote(obj.StoredTime) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			PreparedStmtUtils.setTimestamp(pstmt,3,obj.StoredTime);
			rset = pstmt.executeQuery();

			if(rset.next() == true)
			{
				result = new PTDeliverHistory();
				result.TerminalNo = rset.getString(1);
				result.PackageID = rset.getString(2);
				result.StoredTime = rset.getTimestamp(3);
				result.PostmanID = rset.getString(4);
				result.StoredDate = rset.getDate(5);
				result.ExpiredTime = rset.getTimestamp(6);
				result.CompanyID = rset.getString(7);
				result.DynamicCode = rset.getString(8);
				result.BoxNo = rset.getString(9);
				result.CustomerID = rset.getString(10);
				result.CustomerMobile = rset.getString(11);
				result.CustomerName = rset.getString(12);
				result.CustomerAddress = rset.getString(13);
				result.TakedPersonID = rset.getString(14);
				result.OpenBoxKey = rset.getString(15);
				result.BlankBoxKey = rset.getString(16);
				result.HireAmt = rset.getDouble(17);
				result.HireWhoPay = rset.getString(18);
				result.PayedAmt = rset.getDouble(19);
				result.PosPayFlag = rset.getString(20);
				result.TakedTime = rset.getTimestamp(21);
				result.TakedWay = rset.getString(22);
				result.LeftFlag = rset.getString(23);
				result.PackageStatus = rset.getString(24);
				result.TradeWaterNo = rset.getString(25);
				result.OfLogisticsID = rset.getString(26);
				result.OfLogisticsName = rset.getString(27);
				result.StaOrderID = rset.getString(28);
				result.LastModifyTime = rset.getTimestamp(29);
				result.UploadFlag = rset.getString(30);
				result.Remark = rset.getString(31);

			}
			else
			{
				throw new EduException(ErrorCode.ERR_PTDELIVERHISTORYNODATA);
			}
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPTDELIVERHISTORYFAIL;
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

		String sql = "SELECT * FROM PTDeliverHistory WHERE 1=1 ";
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
			String errorCode = ErrorCode.ERR_QRYPTDELIVERHISTORYFAIL;
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

		String sql = "SELECT * FROM PTDeliverHistory WHERE 1=1 ";
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
			String errorCode = ErrorCode.ERR_QRYPTDELIVERHISTORYFAIL;
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
		String sql = "SELECT * FROM  PTDeliverHistory WHERE 1=1 ";

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		java.util.List<String> list = new java.util.LinkedList<String>();
		list.add("TerminalNo");
		list.add("PackageID");
		list.add("StoredTime");

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
			String errorCode = ErrorCode.ERR_QRYPTDELIVERHISTORYFAIL;
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

		String sql = "SELECT " + fName + " FROM PTDeliverHistory WHERE 1=1 ";
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
			String errorCode = ErrorCode.ERR_QRYPTDELIVERHISTORYFAIL;
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