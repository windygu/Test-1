package com.yangyang.lucene.index.model;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class ConfigBean {
	private String indexName = "index";
	private String indexPath = "/index/";
	private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
	private double indexReopenMaxStaleSec = 10;
	private double indexReopenMinStaleSec = 0.025;
	private int indexCommitSeconds = 60;
	public static void main(String[] args) {
		
	}
	public String getIndexName() {
		return indexName;
	}
	public String getIndexPath() {
		return indexPath;
	}
	public Analyzer getAnalyzer() {
		return analyzer;
	}
	public double getIndexReopenMaxStaleSec() {
		return indexReopenMaxStaleSec;
	}
	public double getIndexReopenMinStaleSec() {
		return indexReopenMinStaleSec;
	}
	public int getIndexCommitSeconds() {
		return indexCommitSeconds;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}
	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	public void setIndexReopenMaxStaleSec(double indexReopenMaxStaleSec) {
		this.indexReopenMaxStaleSec = indexReopenMaxStaleSec;
	}
	public void setIndexReopenMinStaleSec(double indexReopenMinStaleSec) {
		this.indexReopenMinStaleSec = indexReopenMinStaleSec;
	}
	public void setIndexCommitSeconds(int indexCommitSeconds) {
		this.indexCommitSeconds = indexCommitSeconds;
	}
}
