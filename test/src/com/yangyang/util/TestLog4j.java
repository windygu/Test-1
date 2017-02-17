package com.yangyang.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog4j {
	//基于名称访问，不建议使用
	//private static Logger logger = Logger.getLogger("test");
	private static Logger logger =Logger.getLogger(TestLog4j.class);
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		logger.debug("this is a debug");
		logger.error("error");
//		String url = TestLog4j.class.getClassLoader().getResource("").getPath();
//		url = url.replace("bin/", "");
//		System.out.println(url);
//		System.setProperty("LOG URL", url);
//		logger.debug("debug");
//		logger.info("info");
//		logger.error("error");
//		System.out.println(System.getProperty("LOG URL"));
	}
	
}
