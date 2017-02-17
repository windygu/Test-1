package com.yangyang.lucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestAnalyzer {
	private static String str = "极客学院，Lucene 案例开发";

	public static void print(Analyzer analyzer)
	{
		StringReader stringReader = new StringReader(str);
		try
		{
			TokenStream tokenStream = analyzer.tokenStream("", stringReader);
			tokenStream.reset();
			CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
			System.out.println("分词技术: " + analyzer.getClass());
			while(tokenStream.incrementToken())
				System.out.print(term.toString() + "|");
			System.out.println();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Analyzer analyzer = null;
		
		analyzer = new StandardAnalyzer(Version.LUCENE_43);
		TestAnalyzer.print(analyzer);
		
		analyzer = new IKAnalyzer();
		TestAnalyzer.print(analyzer);

		analyzer = new WhitespaceAnalyzer(Version.LUCENE_43);
		TestAnalyzer.print(analyzer);
		
		analyzer = new SimpleAnalyzer(Version.LUCENE_43);
		TestAnalyzer.print(analyzer);
		
		analyzer = new CJKAnalyzer(Version.LUCENE_43);
		TestAnalyzer.print(analyzer);
		
		analyzer = new KeywordAnalyzer();
		TestAnalyzer.print(analyzer);
		
		analyzer = new StopAnalyzer(Version.LUCENE_43);
		TestAnalyzer.print(analyzer);
	}

}
