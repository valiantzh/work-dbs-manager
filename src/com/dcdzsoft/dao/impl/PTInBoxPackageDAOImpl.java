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

public class PTInBoxPackageDAOImpl implements PTInBoxPackageDAO
{
	private static final Log log = LogFactory.getLog(PTInBoxPackageDAOImpl.class.getName());

	private DBSession dbSession = LocalSessionHolder.getCurrentDBSession();

	private Connection conn = dbSession.getConnection();

	private static final int RSET_FETCH_SIZE = 50;


	public int insert(PTInBoxPackage obj) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "INSERT INTO PTInBoxPackage(TerminalNo,PackageID,PostmanID,BoxNo,StoredTime,StoredDate,ExpiredTime,CompanyID,DynamicCode,CustomerID,CustomerMobile,CustomerName,CustomerAddress,OpenBoxKey,BlankBoxKey,HireAmt,HireWhoPay,PayedAmt,PosPayFlag,LeftFlag,PackageStatus,TradeWaterNo,OfLogisticsID,OfLogisticsName,StaOrderID,LastModifyTime,UploadFlag,Remark)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		SQLLog.logInsert(log, obj);

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			PreparedStmtUtils.setString(pstmt,3,obj.PostmanID);
			PreparedStmtUtils.setString(pstmt,4,obj.BoxNo);
			PreparedStmtUtils.setTimestamp(pstmt,5,obj.StoredTime);
			PreparedStmtUtils.setDate(pstmt,6,obj.StoredDate);
			PreparedStmtUtils.setTimestamp(pstmt,7,obj.ExpiredTime);
			PreparedStmtUtils.setString(pstmt,8,obj.CompanyID);
			PreparedStmtUtils.setString(pstmt,9,obj.DynamicCode);
			PreparedStmtUtils.setString(pstmt,10,obj.CustomerID);
			PreparedStmtUtils.setString(pstmt,11,obj.CustomerMobile);
			PreparedStmtUtils.setString(pstmt,12,obj.CustomerName);
			PreparedStmtUtils.setString(pstmt,13,obj.CustomerAddress);
			PreparedStmtUtils.setString(pstmt,14,obj.OpenBoxKey);
			PreparedStmtUtils.setString(pstmt,15,obj.BlankBoxKey);
			PreparedStmtUtils.setDouble(pstmt,16,obj.HireAmt);
			PreparedStmtUtils.setString(pstmt,17,obj.HireWhoPay);
			PreparedStmtUtils.setDouble(pstmt,18,obj.PayedAmt);
			PreparedStmtUtils.setString(pstmt,19,obj.PosPayFlag);
			PreparedStmtUtils.setString(pstmt,20,obj.LeftFlag);
			PreparedStmtUtils.setString(pstmt,21,obj.PackageStatus);
			PreparedStmtUtils.setString(pstmt,22,obj.TradeWaterNo);
			PreparedStmtUtils.setString(pstmt,23,obj.OfLogisticsID);
			PreparedStmtUtils.setString(pstmt,24,obj.OfLogisticsName);
			PreparedStmtUtils.setString(pstmt,25,obj.StaOrderID);
			PreparedStmtUtils.setTimestamp(pstmt,26,obj.LastModifyTime);
			PreparedStmtUtils.setString(pstmt,27,obj.UploadFlag);
			PreparedStmtUtils.setString(pstmt,28,obj.Remark);
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_ADDPTINBOXPACKAGEFAIL;
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

		String sql = "UPDATE PTInBoxPackage SET ";

		StringBuffer sbSet = new StringBuffer(256);
		sbSet.append(setCols.toSetSQL());
		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null){
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbSet.toString() + " WHERE 1=1 " + sbWhere.toString();

		SQLLog.logUpdate(log,"PTInBoxPackage",setCols,whereCols);

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
			String errorCode = ErrorCode.ERR_MODPTINBOXPACKAGEFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public int delete(PTInBoxPackage obj) throws EduException
	{
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "DELETE FROM PTInBoxPackage WHERE TerminalNo = ?  AND PackageID = ? ";

		log.debug("[delete sql]:" + "DELETE FROM PTInBoxPackage WHERE TerminalNo = " + StringUtils.addQuote(obj.TerminalNo) +
						" AND PackageID = " + StringUtils.addQuote(obj.PackageID) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			result = pstmt.executeUpdate();

		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_DELPTINBOXPACKAGEFAIL;
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

		String sql = "DELETE FROM PTInBoxPackage WHERE 1=1 " ;

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		SQLLog.logDelete(log,"PTInBoxPackage",whereCols);

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
			String errorCode = ErrorCode.ERR_DELPTINBOXPACKAGEFAIL;
			log.error("[DBERROR-("+ errorCode + ")]==>[sqlcode]:"+e.getErrorCode() + " [errmsg]:"+e.getMessage() + " [sql]:"+sql);
			throw new EduException(errorCode);
		}
		finally
		{
			dbSession.closeStatement(pstmt);
		}

		return result;
	}


	public boolean isExist(PTInBoxPackage obj) throws EduException
	{
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int count = 0;

		String sql = "SELECT COUNT(*) FROM PTInBoxPackage WHERE TerminalNo = ?  AND PackageID = ? ";

		log.debug("[isExist sql]:" + "SELECT COUNT(*) FROM PTInBoxPackage WHERE TerminalNo = " + StringUtils.addQuote(obj.TerminalNo) +
						" AND PackageID = " + StringUtils.addQuote(obj.PackageID) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			rset = pstmt.executeQuery();

			rset.next();
			count = rset.getInt(1);
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPTINBOXPACKAGEFAIL;
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

		String sql = "SELECT COUNT(*) FROM PTInBoxPackage WHERE 1=1 " ;

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		SQLLog.logIsExist(log,"PTInBoxPackage",whereCols);

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
			String errorCode = ErrorCode.ERR_QRYPTINBOXPACKAGEFAIL;
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


	public PTInBoxPackage find(PTInBoxPackage obj) throws EduException
	{
		PTInBoxPackage result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT TerminalNo,PackageID,PostmanID,BoxNo,StoredTime,StoredDate,ExpiredTime,CompanyID,DynamicCode,CustomerID,CustomerMobile,CustomerName,CustomerAddress,OpenBoxKey,BlankBoxKey,HireAmt,HireWhoPay,PayedAmt,PosPayFlag,LeftFlag,PackageStatus,TradeWaterNo,OfLogisticsID,OfLogisticsName,StaOrderID,LastModifyTime,UploadFlag,Remark  FROM PTInBoxPackage WHERE TerminalNo = ?  AND PackageID = ? ";

		log.debug("[find sql]:" + "SELECT TerminalNo,PackageID,PostmanID,BoxNo,StoredTime,StoredDate,ExpiredTime,CompanyID,DynamicCode,CustomerID,CustomerMobile,CustomerName,CustomerAddress,OpenBoxKey,BlankBoxKey,HireAmt,HireWhoPay,PayedAmt,PosPayFlag,LeftFlag,PackageStatus,TradeWaterNo,OfLogisticsID,OfLogisticsName,StaOrderID,LastModifyTime,UploadFlag,Remark  FROM PTInBoxPackage WHERE TerminalNo = " + StringUtils.addQuote(obj.TerminalNo) +
						" AND PackageID = " + StringUtils.addQuote(obj.PackageID) );

		try
		{
			pstmt = conn.prepareStatement(sql);
			PreparedStmtUtils.setString(pstmt,1,obj.TerminalNo);
			PreparedStmtUtils.setString(pstmt,2,obj.PackageID);
			rset = pstmt.executeQuery();

			if(rset.next() == true)
			{
				result = new PTInBoxPackage();
				result.TerminalNo = rset.getString(1);
				result.PackageID = rset.getString(2);
				result.PostmanID = rset.getString(3);
				result.BoxNo = rset.getString(4);
				result.StoredTime = rset.getTimestamp(5);
				result.StoredDate = rset.getDate(6);
				result.ExpiredTime = rset.getTimestamp(7);
				result.CompanyID = rset.getString(8);
				result.DynamicCode = rset.getString(9);
				result.CustomerID = rset.getString(10);
				result.CustomerMobile = rset.getString(11);
				result.CustomerName = rset.getString(12);
				result.CustomerAddress = rset.getString(13);
				result.OpenBoxKey = rset.getString(14);
				result.BlankBoxKey = rset.getString(15);
				result.HireAmt = rset.getDouble(16);
				result.HireWhoPay = rset.getString(17);
				result.PayedAmt = rset.getDouble(18);
				result.PosPayFlag = rset.getString(19);
				result.LeftFlag = rset.getString(20);
				result.PackageStatus = rset.getString(21);
				result.TradeWaterNo = rset.getString(22);
				result.OfLogisticsID = rset.getString(23);
				result.OfLogisticsName = rset.getString(24);
				result.StaOrderID = rset.getString(25);
				result.LastModifyTime = rset.getTimestamp(26);
				result.UploadFlag = rset.getString(27);
				result.Remark = rset.getString(28);

			}
			else
			{
				throw new EduException(ErrorCode.ERR_PTINBOXPACKAGENODATA);
			}
		}
		catch(SQLException e)
		{
			String errorCode = ErrorCode.ERR_QRYPTINBOXPACKAGEFAIL;
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

		String sql = "SELECT * FROM PTInBoxPackage WHERE 1=1 ";
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
			String errorCode = ErrorCode.ERR_QRYPTINBOXPACKAGEFAIL;
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

		String sql = "SELECT * FROM PTInBoxPackage WHERE 1=1 ";
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
			String errorCode = ErrorCode.ERR_QRYPTINBOXPACKAGEFAIL;
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
		String sql = "SELECT * FROM  PTInBoxPackage WHERE 1=1 ";

		StringBuffer sbWhere = new StringBuffer(256);
		if(whereCols != null)
		{
			sbWhere.append(whereCols.toWhereSQL());
		}

		sql = sql + sbWhere.toString();

		java.util.List<String> list = new java.util.LinkedList<String>();
		list.add("TerminalNo");
		list.add("PackageID");

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
			String errorCode = ErrorCode.ERR_QRYPTINBOXPACKAGEFAIL;
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

		String sql = "SELECT " + fName + " FROM PTInBoxPackage WHERE 1=1 ";
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
			String errorCode = ErrorCode.ERR_QRYPTINBOXPACKAGEFAIL;
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