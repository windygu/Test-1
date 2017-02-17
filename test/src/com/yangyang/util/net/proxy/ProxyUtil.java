package com.yangyang.util.net.proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yangyang.util.net.http.HttpClientUtil;

public class ProxyUtil {

	public static void main(String[] args) {
	}

	public static String getByProxy(String urlStr)
	{
		Set<Entry<String, Integer>> set = getProxyMap().entrySet();
		Entry<String, Integer>[] entries = null;
		set.toArray(entries);
		int i = (int)Math.random()*entries.length;
		return getByProxy(urlStr, "utf8",entries[i].getKey(), entries[i].getValue());
	}
	
	public static String getByProxy(String urlStr, String encode, String proxyIp, int  proxyPort)
	{
		URL url;
		String buff = "";
		try {
			url = new URL(urlStr);
			InetSocketAddress add = new InetSocketAddress(proxyIp, proxyPort);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, add);
			URLConnection conn = url.openConnection(proxy);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));
			while((buff += br.readLine()) != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buff;
	}
	
	public static Map<String, Integer> getProxyMap()
	{
		String regex = "(\\d+.\\d+.\\d+.\\d+):(\\d{1,4})";
		String webpage = HttpClientUtil.getWebpage(ProxyProviders._66ip);
		Map<String, Integer> map = new HashMap<>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(webpage);
		while(matcher.find())
			map.put(matcher.group(1), Integer.parseInt(matcher.group(2)));
		return map;
	}
}
