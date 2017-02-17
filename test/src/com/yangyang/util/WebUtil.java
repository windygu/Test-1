package com.yangyang.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class WebUtil {

	public static void main(String[] args){
		DownloadWeb("http://tvepg.letv.com/apk/data/item/418/424/2.shtml", "utf8");
	}

	public static void DownloadWeb(String urlStr, String encode)
	{
		File file = new File("test.html");
		FileOutputStream out = null;
		try 
		{
			URL url = new URL(urlStr);
			out = new FileOutputStream(file);
			URLConnection conn = url.openConnection();
			DataInputStream in = new DataInputStream(conn.getInputStream());
			byte[] buff = new byte[1024];
			int len;
			while((len = in.read(buff)) != -1)
				out.write(buff, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

