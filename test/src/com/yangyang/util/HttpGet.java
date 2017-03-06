package com.yangyang.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class HttpGet {

	public static String ReadByGet(String urlStr) throws IOException {
		return ReadByGet(urlStr, "utf-8");
	}

	public static String ReadByGet(String urlStr, Proxy proxy) throws IOException {
		return ReadByGet(urlStr, "utf-8", proxy);
	}
	
	public static String ReadByGet(String urlStr, String encode) throws IOException {
		return ReadByGet(urlStr, encode, null, null);
	}

	public static String ReadByGet(String urlStr, String encode,Proxy proxy) throws IOException {
		return ReadByGet(urlStr, encode, null, proxy);
	}
	
	public static String ReadByGet(String urlStr, Map<String, String> headers) throws IOException {
		return ReadByGet(urlStr, "utf-8", headers);
	}
	
	public static String ReadByGet(String urlStr, String encode, Map<String, String> headers) throws IOException
	{
		return ReadByGet(urlStr, encode, headers, null);
	}
	
	public static String ReadByGet(String urlStr, String encode, Map<String, String> headers, Proxy proxy) throws IOException {
		BufferedReader br = null;
		String str = "";

			URL url;
			try {
				url = new URL(urlStr);
			
			
			HttpURLConnection conn;
			if(proxy != null)
				conn = (HttpURLConnection) url.openConnection(proxy);
			else
				conn = (HttpURLConnection) url.openConnection();
			if (headers != null && !headers.isEmpty()) 
			{
				Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, String> entry = iter.next();
					conn.addRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			if (!conn.getRequestProperties().containsKey("User-Agent"))
				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));
			String buff = "";
			while ((buff = br.readLine()) != null)
				str += buff + "\n";
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return str;
	}
}
