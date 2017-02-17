package com.yangyang.util.net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TooManyListenersException;

public class TcpClient {
	private Socket socket  ;
	InputStream in;
	OutputStream out;
	BufferedReader br;
	
	class GetDanmu implements Runnable
	{

		@Override
		public void run() {			try {
				InetAddress add = InetAddress.getByName("openbarrage.douyutv.com");
				socket = new Socket(add,8601);
				in = socket.getInputStream();
				out = socket.getOutputStream();
				br = new BufferedReader(new InputStreamReader(in));
				push("type@=loginreq/roomid@=" + 216769 + "/");
				pull();
				push("type@=joingroup/rid@=" + 216769 +"/gid@=-9999/");	
				while(true)
				{
					pull();
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	class KeepAlive implements Runnable
	{
		public void run() {
			try {
				while(true)
				{
					push("type@=keeplive/tick@=" + System.currentTimeMillis()/1000);
					Thread.sleep(30*1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void push(String data) throws IOException
	{
		byte[] b = {(byte) 0xb1,0x02,0x00,0x00};
		byte[] c = {0x00};
		byte[] d = concat(b,data.getBytes(),c);
		System.out.println(new String(d,"gbk"));
		out.write(d);
	}
	
	public void pull()
	{
		
		String buff = "";
		try {
			while((buff = br.readLine()) != null)
				System.out.println(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] concat(byte[] first, byte[]... rest)
	{
		int length = first.length;
		for (byte[] ts : rest) 
			length += ts.length;
		byte[] result = Arrays.copyOf(first, length);
		int offset = first.length;
		for (byte[] t : rest) {
			System.arraycopy(t, 0, result, offset, t.length);
			offset += t.length;
		}
		return result;
	}
	
	public void getDanmu()
	{
		Thread thread1 = new Thread(new GetDanmu());
		Thread thread2 = new Thread(new KeepAlive());
		thread2.setDaemon(true);
		thread1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
	}
	
	public static void main(String[] args) {
		TcpClient client = new TcpClient();
		client.getDanmu();
	}
}
