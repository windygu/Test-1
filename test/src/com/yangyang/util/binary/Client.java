package com.yangyang.util.binary;

import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		byte[] b = {0x01,0x02,0x00,0x00};
		Socket socket = new Socket("192.168.1.109", 27016);
		OutputStream os = socket.getOutputStream();
		os.write(b);
		socket.close();
	}

}
