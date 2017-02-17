package com.yangyang.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class TestDom4j {
	public static void main(String[] args) {
		
	}
	
	public static void Test()
	{
		String xmlString = "<root></root>";
		Document document;
		try {
			document = DocumentHelper.parseText(xmlString);
			System.out.println(document.asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
