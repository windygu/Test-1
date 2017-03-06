package com.yangyang.util.net.proxy;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yangyang.util.HttpGet;
import com.yangyang.util.RegexUtil;

import cn.nusof.common.net.util.InetAddressUtils;


public class ProxyProvider
{
	private static int pages = 6;
	private static ArrayList<Proxy> proxyList = new ArrayList<>();
	
	public static ArrayList<Proxy> getProxyList()
	{
		Class<?> clazz = ProxyProvider.class;
		ArrayList<String> websites = new ArrayList<>();
		websites.add("_66ip");
		websites.add("_kuaidaili");
		websites.add("_goubanjia");
		Iterator<String> iter = websites.iterator();
		while(iter.hasNext())
		{
			try 
			{
			Method method = clazz.getDeclaredMethod("get" + iter.next());
			method.invoke(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Iterator<Proxy> iter1 = proxyList.iterator();
		while(iter1.hasNext())
		{
			Proxy proxy = null;
			try
			{
				proxy = iter1.next();
				check(proxy);
			}catch(IOException e)		//The proxy is invalid if there's an exception
			{
				iter1.remove();
				System.out.println("代理无效");
			}
			System.out.println(proxy);
		}
		return proxyList;
	}
	
	public static void get_66ip() throws IOException
	{
		String url = "http://www.66ip.cn/mo.php?sxb=&tqsl=800&port=&export=&ktip=&sxa=&submit=%CC%E1++%C8%A1&textarea=";
		String regex = "(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d{1,5})";
	
		String html = HttpGet.ReadByGet(url);
	
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		while(matcher.find())
		{
			String ip = matcher.group(1);
			int port = Integer.parseInt(matcher.group(2));
			proxyList.add(new Proxy(ip, port));
		}
	}
	
	public static void get_kuaidaili() throws IOException
	{
		String url = "http://www.kuaidaili.com/proxylist/{0}/";
		String regex = "<td data-title=\"IP\">(.*?)</td>\\s*?<td data-title=\"PORT\">(\\d*)</td>";
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Cookie", "_ydclearance=d855c4bf06fcb4b0f7cd4598-e9be-46a2-af91-ceaa2bb88d1c-1488633793; channelid=0; sid=1488626339253581; _ga=GA1.2.810021308.1488509733; _gat=1; Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1488509733,1488626593; Hm_lpvt_7ed65b1cc4b810e9fd37959c9bb51b31=1488627210");
		//headers.put("Referer", "http://www.kuaidaili.com/proxylist/");
		headers.put("Cache-Control", "max-age=0");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate, sdch");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8");
		for(int i = 0; i != pages; i++)
		{
			String html = HttpGet.ReadByGet( MessageFormat.format(url, i), headers);
			
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(html);
			while(matcher.find())
			{
				String ip = matcher.group(1);
				int port = Integer.parseInt(matcher.group(2));
				ip = ip.replaceAll("<p style='.*?none;'>.*?</p>", "").replaceAll("<.*?>", "");
				proxyList.add(new Proxy(ip, port));
			}
		}
	}
	
	public static void get_goubanjia() throws IOException
	{
		String url = "http://www.goubanjia.com/free/gngn/index{0}.shtml";
		String regex = "<td class=\"ip\">((?:(?:<.*?>)*[\\d\\.]*)*).*?(\\d+)";
		
		for(int i = 0; i != pages; i++)
		{
			String html = "";
			if(i == 0)
				html = HttpGet.ReadByGet(url.replace("{0}", ""));
			else
				html = HttpGet.ReadByGet(MessageFormat.format(url, i));
			
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(html);
			while(matcher.find())
			{
				String ip = matcher.group(1);
				int port = Integer.parseInt(matcher.group(2));
				proxyList.add(new Proxy(ip, port));
			}
		}
	}

	public static void check(Proxy proxy) throws IOException
	{
		System.out.println("正在测试的代理为 " + proxy.ip + ":" + proxy.port);
		String html = HttpGet.ReadByGet("http://www.1356789.com/", new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress(proxy.ip, proxy.port)));
		String newIp = RegexUtil.getGroup(html, "您的IP是：(\\S*)");
		if(newIp.equals(proxy.ip))
			proxy.isTransparent = false;
	}

	public static void main(String[] args) {
		System.out.println(getProxyList());
	}
}

