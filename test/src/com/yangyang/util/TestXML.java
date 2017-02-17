package com.yangyang.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestXML {

	public static void main(String[] args) {
		CreateXML();
	}

	public static void ReadXML()
	{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("languages.xml"));
		
			Element root = document.getDocumentElement();
			System.out.println("cat = " + root.getAttribute("cat"));
			NodeList nodeList = root.getElementsByTagName("lan");
			for(int i = 0; i < nodeList.getLength(); i++)
			{
				Element lan = (Element) nodeList.item(i);
				System.out.println("id = " + lan.getAttribute("id"));
			
//				Element name = (Element) lan.getElementsByTagName("name").item(0);
//				System.out.println("name = " + name.getTextContent());
			
				NodeList children = lan.getChildNodes();
				for(int j = 0; j < children.getLength(); j++)
				{
					Node e = children.item(j);
					if(e instanceof Element)
						System.out.println(e.getNodeName() + " = " + e.getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void CreateXML()
	{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element root = document.createElement("Languages");
			root.setAttribute("cat", "it");
			
			Element[] lan = new Element[3];
			for(int i = 0; i< lan.length; i++)
			{
				lan[i] = document.createElement("lan");
				lan[i].setAttribute("id", i + "");
			}
			
			Element[] name = new Element[3];
			Element[] ide = new Element[3];
			for(int i = 0; i < name.length; i++)
			{
				name[i] = document.createElement("name");
				ide[i] = document.createElement("ide");
			}
			name[0].setTextContent("Java");
			ide[0].setTextContent("Eclipse");
			for(int i = 0; i < lan.length; i++)
			{
				lan[i].appendChild(name[i]);
				lan[i].appendChild(ide[i]);
				root.appendChild(lan[i]);
			}
			document.appendChild(root);
			
			//--------------------------
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			System.out.println(writer);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
