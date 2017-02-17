package com.yangyang.lucene.index.operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;

import com.yangyang.lucene.index.manager.IndexManager;
import com.yangyang.lucene.index.model.SearchResultBean;

public class NRTSearch {
	private IndexManager indexManager;
	
	public NRTSearch(String indexName)
	{
		this.indexManager = IndexManager.getIndexManager(indexName);
	}
	
	public SearchResultBean search(Query query, int start, int end, Sort sort)
	{
		start = start > 0 ? start : 0;
		end = end > 0 ? end : 0;
		if(query == null || start > end || indexManager == null) return null;
		SearchResultBean bean = new SearchResultBean();
		List<Document> docs = new ArrayList<Document>();
		bean.setDocs(docs);
		IndexSearcher searcher = indexManager.getIndexSearcher();
		try {
			TopDocs topDocs = searcher.search(query, end, sort);
			bean.setCount(topDocs.totalHits);
			end = end > topDocs.totalHits ? topDocs.totalHits : end;
			for(int i = start; i < end; i++)
				docs.add(searcher.doc(topDocs.scoreDocs[i].doc));
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			indexManager.release(searcher);
		}
		return bean;
	}
	
	public SearchResultBean search(Query query, int start, int end)
	{
		start = start > 0 ? start : 0;
		end = end > 0 ? end : 0;
		if(query == null || start > end || indexManager == null) return null;
		SearchResultBean bean = new SearchResultBean();
		List<Document> docs = new ArrayList<Document>();
		bean.setDocs(docs);
		IndexSearcher searcher = indexManager.getIndexSearcher();
		try {
			TopDocs topDocs = searcher.search(query, end);
			bean.setCount(topDocs.totalHits);
			end = end > topDocs.totalHits ? topDocs.totalHits : end;
			for(int i = start; i < end; i++)
				docs.add(searcher.doc(topDocs.scoreDocs[i].doc));
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			indexManager.release(searcher);
		}
		return bean;
	}
}
