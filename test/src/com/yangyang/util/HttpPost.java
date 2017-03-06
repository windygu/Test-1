package com.yangyang.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpPost {

	public static String ReadByPost(String urlStr, String query) {
		return ReadByPost(urlStr, query, "utf8");
	}

	public static String ReadByPost(String urlStr, String query, String encode) {
		return ReadByPost(urlStr, query, encode, null);
	}

	public static String ReadByPost(String urlStr, String query, String encode, Map<String, String> headers) {
		String str = "";
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.addRequestProperty("encoding", "utf-8");
			if (headers != null && !headers.isEmpty()) {
				Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, String> entry = iter.next();
					conn.addRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			if (!conn.getRequestProperties().containsKey("User-Agent"))
				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36");

			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), encode));
			bw.write(query);
			bw.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));

			String buff;
			while ((buff = br.readLine()) != null)
				str += buff + "\n";
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}

