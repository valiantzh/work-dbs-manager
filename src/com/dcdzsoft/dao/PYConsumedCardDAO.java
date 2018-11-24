package com.dcdzsoft.dao;

import java.util.List;
import javax.sql.RowSet;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;

public interface PYConsumedCardDAO
{
	public int insert(PYConsumedCard obj) throws EduException;

	public int update(JDBCFieldArray setCols,JDBCFieldArray whereCols) throws EduException;

	public int delete(PYConsumedCard obj) throws EduException; 

	public int delete(JDBCFieldArray whereCols) throws EduException; 

	public boolean isExist(PYConsumedCard obj) throws EduException;

	public int isExist(JDBCFieldArray whereCols) throws EduException;

	public PYConsumedCard find(PYConsumedCard obj) throws EduException;

	public RowSet select(JDBCFieldArray whereCols) throws EduException;

	public RowSet select(JDBCFieldArray whereCols,List<String> orderCols) throws EduException;

	public RowSet select(JDBCFieldArray whereCols,int recordBegin,int recordNum) throws EduException;

	//必须对返回结果进行null判断后才能使用
	public String selectFunctions(String fName,JDBCFieldArray whereCols) throws EduException;

}