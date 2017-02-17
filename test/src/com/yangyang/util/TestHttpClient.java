package com.yangyang.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestHttpClient {

	public static void main(String[] args) {
		Post("http://vfile.fjtv.net/2016/1457/5909/5586/145759095586.ssm/manifest.m3u8");
	}

	public static void Get(String urlStr)
	{
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(urlStr);
		try {
			HttpResponse httpResponse = httpclient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity, "utf8");
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Post(String urlStr)
	{
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(urlStr);
		try {
			List<BasicNameValuePair> list = new ArrayList<>();
			list.add(new BasicNameValuePair("User-Agent", "mi_max_android"));
			list.add(new BasicNameValuePair("Cookie", "JSESSIONID=4C13A46E75ABF4F96126E46773A8953A; Path=/clt50"));
			list.add(new BasicNameValuePair("X_UP_CLIENT_CHANNEL_ID", "23000003-99000-200300220100002"));
			list.add(new BasicNameValuePair("X_UP_CLIENT_ID", "000245"));
			list.add(new BasicNameValuePair("x-up-bear-type", "WLAN"));
			list.add(new BasicNameValuePair("WDAccept-Encoding", "gzip,deflate"));
			list.add(new BasicNameValuePair("jsonStr", "{\"authid\":\"zybauthid\",\"token\":\"migushipin-token\",\"user\":{},\"device\":{\"dpid\":\"NKd9oDtYXm8fyweFC3KpJbjn6HwEcFXTpCVYYPqQ9tXj5dE2MahQW9a2XkCnBI_P\",\"ip\":\"\",\"geo\":{},\"h\":1865,\"os\":\"Android\",\"osv\":\"4.4.4\",\"connectiontype\":\"2\",\"did\":\"AA2PqZ6z7V5lEk1s1bWcZg==\",\"mac\":\"\",\"didha\":1,\"ppi\":\"300\",\"devicetype\":\"2\",\"w\":1080,\"dpidha\":1,\"macha\":1,\"carrier\":\"46003\",\"ifa\":\"kQmXKrVayla2TD59_Vm3S-Pl0TYxqFBb1rZeQKcEj88=\",\"model\":\"max\",\"orientation\":\"0\",\"make\":\"mi\",\"ua\":\"mi_max_android\",\"ifaha\":1},\"ext\":{},\"app\":{\"id\":\"zybappid\",\"bundle\":\"com.cmcc.cmvideo\",\"ext\":{\"is_url_supported\":1}},\"imp\":[{\"actiontype\":0,\"ext\":{\"inserted_ad_id\":\"\"},\"id\":\"02BB660381E0084686A7E2027F70E7F4\",\"native\":{},\"context\":{},\"video\":{\"maxduration\":45,\"minduration\":5,\"contentId\":\"619811680\",\"num\":1}}]}"));
			list.add(new BasicNameValuePair("isDisableAdRequest", "0"));
			
			httpPost.setEntity(new UrlEncodedFormEntity(list,"utf8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity, "utf8");
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
