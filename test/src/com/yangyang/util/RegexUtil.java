package com.yangyang.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static ArrayList<String> getGroups(String text, String regex) {
		ArrayList<String> groups = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find())
		{
			String str = matcher.group(1);
			if(str.matches("[\\\\u.{2,4}]*"));
				str = unicode2String(str);
			groups.add(str);
		}
		return groups;
	}
	
	public static String getGroup(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if(matcher.find())
		{
			String str = matcher.group(1);
			if(str.contains("\\u"))
				str = unicode2String(str);
			return str;
		}
		return null;
	}

	/**
	 * 只有在映客的Live.getLives()才用到，映客的url需要两个参数
	 * @param text
	 * @param regex
	 * @return
	 */
	public static ArrayList<ArrayList<String>> getMultiGroups(String text, String regex) {
		ArrayList<ArrayList<String>> groups = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		int i = 1;
		while (matcher.find())
			for (int j = 1; j <= matcher.groupCount(); i++, j++) {
				groups.add(new ArrayList<String>());
				groups.get(i - 1).add(matcher.group(j));
			}
		return groups;
	}
	/**
	 * unicode 转字符串
	 */
	 public static String unicode2String(String theString) 
	 {    
	     char aChar;    
	     int len = theString.length();    
	     StringBuffer outBuffer = new StringBuffer(len);    
	     for (int x = 0; x < len;) 
	     {    
	    	 aChar = theString.charAt(x++);    
	    	 if (aChar == '\\') {    
	    		 aChar = theString.charAt(x++);    
	    		 if (aChar == 'u') 
	    		 {    
	    			 // Read the xxxx    
	    			 int value = 0;    
	    			 for (int i = 0; i < 4; i++) 
	    			 {    
	    				 aChar = theString.charAt(x++);    
	    				 switch (aChar) 
	    				 {    
		    				 case '0':    
		    				 case '1':    
						     case '2':    
						     case '3':    
						     case '4':    
						     case '5':    
						     case '6':    
						     case '7':    
						     case '8':    
						     case '9':    
						     value = (value << 4) + aChar - '0';    
						     break;    
						     case 'a':    
						     case 'b':    
						     case 'c':    
						     case 'd':    
						     case 'e':    
						     case 'f':    
						     value = (value << 4) + 10 + aChar - 'a';    
					         break;    
					         case 'A':    
					         case 'B':    
					         case 'C':    
					         case 'D':    
					         case 'E':    
					         case 'F':    
					         value = (value << 4) + 10 + aChar - 'A';    
					         	break;    
					         default:    
					        	 throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");    
	    				 }    
	    			 }    
	    			 outBuffer.append((char) value);    
		         }else {    
		        	 if (aChar == 't')    
		        		 aChar = '\t';    
		        	 else if (aChar == 'r')    
		        		 aChar = '\r';    
		        	 else if (aChar == 'n')    
		        		 aChar = '\n';    
		        	 else if (aChar == 'f')    
		        		 aChar = '\f';    
		        	 outBuffer.append(aChar);    
		         }    
	    	 } else   
	    		 outBuffer.append(aChar);    
	       	 }    
	     return outBuffer.toString();    
	 }   
}
