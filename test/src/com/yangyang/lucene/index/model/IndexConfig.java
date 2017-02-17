package com.yangyang.lucene.index.model;

import java.util.HashSet;

public class IndexConfig {
	private static HashSet<ConfigBean> configBeans;
	
	private static class DefaultIndexConfig
	{
		private static final HashSet<ConfigBean> configBeansDefault = new HashSet<>();
		static
		{
			ConfigBean bean = new ConfigBean();
			configBeansDefault.add(bean);
		}
	}
	
	public static void setConfig(HashSet<ConfigBean> configBeans)
	{
		IndexConfig.configBeans = configBeans;
	}
	
	public static HashSet<ConfigBean> getConfig()
	{
		if(configBeans == null) return DefaultIndexConfig.configBeansDefault;
		return configBeans;
	}
}
