package com.yangyang.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
	private static String newsURL;

	public static void main(String[] args) {
		ParseWeb("test.html", "onclick=\"show(.*?)Detail.*?'(\\d*)'.*?'(\\d*)'");
	}
	
	public static void ParseWeb(String fileName, String regex) 
	{
		File file = new File(fileName);
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String buff = "";
			String str = "";
			while((buff = br.readLine()) != null)
				str += buff;
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			while(matcher.find())
			{
				int i = 1;
				newsURL = "http://ecp.sgcc.com.cn/html/{0}/{1}/{2}.html";
				ArrayList<String> nums =new ArrayList<>();
				while(i != matcher.groupCount() + 1)
				{
					nums.add(matcher.group(i));
					i++;
				}
				
				newsURL = MessageFormat.format(newsURL, nums.toArray());
				System.out.println(newsURL);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
