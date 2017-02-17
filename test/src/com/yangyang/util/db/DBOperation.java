package com.yangyang.util.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBOperation {
	private String poolName;
	private Connection conn = null;
	public static void main(String[] args) {}

	public DBOperation(String poolName)
	{
		this.poolName = poolName;
	}
	
	public void close()
	{
		try {
			if(conn != null)
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void open() throws SQLException
	{
		close();
		conn = DBManager.getInstance().getConn(poolName);
	}
	
	private PreparedStatement setPres(String sql, HashMap<Integer, Object> params) throws SQLException, ClassNotFoundException
	{
		if(params == null || params.size() < 1)
			return null;
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 1; i <= params.size(); i++)
		{
			if(params.get(i) == null)
				ps.setString(i, "");
			else if(params.get(i).getClass() == Class.forName("java.lang.String"))
				ps.setString(i, (String)params.get(i));
			else if(params.get(i).getClass() == Class.forName("java.lang.Integer"))
				ps.setInt(i, (Integer)params.get(i));
			else if(params.get(i).getClass() == Class.forName("java.lang.Long"))
				ps.setLong(i, (Long)params.get(i));
			else if(params.get(i).getClass() == Class.forName("java.lang.Double"))
				ps.setDouble(i, (Double)params.get(i));
			else if(params.get(i).getClass() == Class.forName("java.lang.Float"))
				ps.setFloat(i, (Float)params.get(i));
			else if(params.get(i).getClass() == Class.forName("java.lang.Boolean"))
				ps.setBoolean(i, (Boolean)params.get(i));
			else if(params.get(i).getClass() == Class.forName("java.lang.Date"))
				ps.setDate(i, (Date)params.get(i));
			else
				return null;
		}
		return ps;
	}
	
	public int executeUpdate(String sql) throws SQLException
	{
		open();
		Statement statement = conn.createStatement();
		return statement.executeUpdate(sql);
	}
	
	public int executeUpdate(String sql, HashMap<Integer, Object> params) throws ClassNotFoundException, SQLException
	{
		open();
		PreparedStatement ps = setPres(sql, params);
		if(ps == null) return 0;
		return ps.executeUpdate();
	}
	
	public ResultSet executeQuery(String sql) throws SQLException
	{
		open();
		Statement statement = conn.createStatement();
		return statement.executeQuery(sql);
	}
	
	public ResultSet executeQuery(String sql, HashMap<Integer, Object> params) throws SQLException, ClassNotFoundException
	{
		open();
		PreparedStatement ps = setPres(sql, params);
		if(ps == null) return null;
		return ps.executeQuery();
	}
}
