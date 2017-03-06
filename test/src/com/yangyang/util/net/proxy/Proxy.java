package com.yangyang.util.net.proxy;

public class Proxy
{
	String ip;
	int port;
	boolean isTransparent;
	
	public Proxy(String ip, int port) 
	{
		this.ip = ip;
		this.port = port;
	}
	
	@Override
	public String toString() {
		
		return ip + ":" + port + " is " + (isTransparent?"transparent":"nontransparent");
	}
}
