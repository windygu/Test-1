package com.yangyang.util.db;

import cn.nusof.common.util.ClassUtils;

public class DBPool {
	private String poolPath;
	
	private DBPool(){}
	private static class DBPoolSingleton
	{
		private static DBPool dbPool = new DBPool();
	}
	
	public String  getPoolPath()
	{
		if(poolPath == null)
			poolPath = getClass().getResource("/").getFile().toString() + "proxool.xml";
		return poolPath;
	}
	
	public static DBPool getInstance()
	{
		return DBPoolSingleton.dbPool;
	}
	public static void main(String[] args) {

	}

}
