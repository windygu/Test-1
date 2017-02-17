package com.yangyang.lucene.index.manager;

import java.util.HashSet;

import com.yangyang.lucene.index.model.ConfigBean;
import com.yangyang.lucene.index.model.IndexConfig;

public class IndexManagerTest {
	public static void main(String[] args) {
		HashSet<ConfigBean> set = new HashSet<>();
		for(int i = 0; i < 4; i++)
		{
			ConfigBean bean = new ConfigBean();
			bean.setIndexPath("d:jkxy");
			bean.setIndexName("test" + i);
			set.add(bean);
		}
		IndexConfig.setConfig(set);
		IndexManager  indexManager1 = IndexManager.getIndexManager("test0");
	}
}
