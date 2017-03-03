package com.yangyang.util.net.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	public static void main(String[] args) {
		System.out.println(getWebpage("http://www.baidu.com"));
		
	}
	
	public static String getWebpage(String utlStr)
	{
		return getWebpage(utlStr, "utf8");
	}
	
	public static String getWebpage(String urlStr, String encode)
	{
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(urlStr);
		String result = "";
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
