package com.yangyang.util.binary;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(27016);
		Socket socket = ss.accept();
		InputStream is = socket.getInputStream();
		byte[] b = new byte[1024];
		int len = 0;
		is.read(b);
		System.out.println();
		ss.close();
	}

}
