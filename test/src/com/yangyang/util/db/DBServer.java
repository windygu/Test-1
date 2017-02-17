package com.yangyang.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBServer {
	private DBOperation dbOperation;
	
	public DBServer(String poolName) {
		dbOperation = new DBOperation(poolName);
	}
	
	public void close()
	{
		dbOperation.close();
	}
	
	public int insert(String sql) throws SQLException
	{
		return dbOperation.executeUpdate(sql);
	}
	
	public int insert(String tableName, String columns, HashMap<Integer, Object> params) throws ClassNotFoundException, SQLException
	{
		String sql = insertSql(tableName, columns);
		return dbOperation.executeUpdate(sql, params);
	}
	
	public int delete(String sql) throws SQLException
	{
		return dbOperation.executeUpdate(sql);
	}
	
	public int delete(String tableName, String condition) throws SQLException
	{
		if(tableName == null) return 0;
		String sql = "delete from " + tableName + " " + condition;
		return dbOperation.executeUpdate(sql);
	}
	
	public int update(String sql) throws SQLException
	{
		return dbOperation.executeUpdate(sql);
	}
	
	public int update(String tableName, String columns, String condition, HashMap<Integer,Object> params) throws ClassNotFoundException, SQLException
	{
		String sql = updateSql(tableName, columns, condition);
		return dbOperation.executeUpdate(sql, params);
	}
	
	public ResultSet select (String sql) throws SQLException
	{
		return dbOperation.executeQuery(sql);
	}
	
	public ResultSet select(String tableName,  String columns, String condition) throws SQLException
	{
		String sql = "select " + columns + "from" + tableName + " " + condition;
		return dbOperation.executeQuery(sql);
	}
	
	private String updateSql(String tableName, String columns, String condition)
	{
		if(tableName == null || columns == null) return "";
		String[] column = columns.split(",");
		StringBuilder sb = new StringBuilder("");
		sb.append("update " + tableName + " set " + column[0] + ") values (?");
		for(int i = 1; i < column.length; i++)
			sb.append(", " + column[i] + "=?");
		sb.append(" " + condition);
		return sb.toString();
	}
	
	private String insertSql(String tableName, String columns)
	{
		if(tableName == null || columns == null) return null;
		int n = columns.split(",").length;
		StringBuilder sb = new StringBuilder("");
		sb.append("insert into " + tableName + " (" + columns + ") values (?");
		for(int i = 1; i < n; i++)
			sb.append(",?");
		return sb.toString();
	}
	public static void main(String[] args) {
		
	}

}
