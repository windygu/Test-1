package com.yangyang.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

public class DBManager {

	private DBManager()
	{
		try {
			JAXPConfigurator.configure(DBPool.getInstance().getPoolPath(), false);
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn(String poolName) throws SQLException
	{
		return DriverManager.getConnection(poolName);
	}
	private static class DBManagerSingleton
	{
		private static DBManager dbManager = new DBManager();
	}
	
	public static DBManager getInstance()
	{
		return DBManagerSingleton.dbManager;
	}
	
	public static void main(String[] args) {
		
	}

}
