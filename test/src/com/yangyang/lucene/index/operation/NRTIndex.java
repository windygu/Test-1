package com.yangyang.lucene.index.operation;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.NRTManager.TrackingIndexWriter;
import org.apache.lucene.search.Query;

import com.yangyang.lucene.index.manager.IndexManager;

public class NRTIndex {
	private TrackingIndexWriter trackingIndexWriter;
	private String indexName;
	
	public NRTIndex(String indexName)
	{
		this.indexName = indexName;
		this.trackingIndexWriter = IndexManager.getIndexManager(indexName).getTrackingIndexWriter();
	}
	
	public boolean addDocument(Document doc)
	{
		try {
			trackingIndexWriter.addDocument(doc);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteDocument(Query query)
	{
		try {
			trackingIndexWriter.deleteDocuments(query);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deletAll()
	{
		try {
			trackingIndexWriter.deleteAll();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  false;
	}
	
	public boolean updateDocument(Term term, Document doc)
	{
		try {
			trackingIndexWriter.updateDocument(term, doc);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void commit()
	{
		IndexManager.getIndexManager(indexName).commit();
	}
	public static void main(String[] args) {

	}
}
