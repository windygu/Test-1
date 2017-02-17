package com.yangyang.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexSearch {
	public static void main(String[] args) {
		Directory directory = null;
		try {
			directory = FSDirectory.open(new File("D://index/test"));
			DirectoryReader dReader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(dReader);
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
			QueryParser parser = new QueryParser(Version.LUCENE_43, "content", analyzer);
			Query query;
			query = parser.parse("Lucene案例");
			TopDocs topDocs = searcher.search(query, 10);
			if(topDocs != null)
			{
				System.out.println("符合条件的文档总数:" + topDocs.totalHits);
				for(int i = 0; i < topDocs.scoreDocs.length; i++)
				{
					Document doc = searcher.doc(topDocs.scoreDocs[i].doc);
					System.out.println("id = " + doc.get("id"));
					System.out.println("content = " + doc.get("content"));
					System.out.println("num = " + doc.get("num"));
				}
			}
			directory.close();
			dReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
				e.printStackTrace();
			}
	}
}
