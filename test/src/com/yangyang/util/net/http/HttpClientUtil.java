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
		System.out.println(getWebpage("http://api.mobile.youku.com/layout/android/v5/home/page?pid=95da77cfe15d2805&guid=103192adc19a787d28118b2ea7606b81&mac=6c%3A25%3Ab9%3Af7%3Ac1%3Ad0&imei=866298020582758&ver=5.7&_t_=1467250227&e=md5&_s_=97764dce3dc8d17158b9a7c06b478b77&operator=CMCC_46002&network=WIFI&site=1&bd=vivo&bt=phone&ispad=0&ouid=5f17b1b184b9cb18&os=Android&mdl=vivo+X5L&dvw=720&dvh=1280&dprm=2000&avs=5.7&osv=4.4.2&aid=5f17b1b184b9cb18&aw=a&ss=4.59"));
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
