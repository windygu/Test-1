package com.yangyang.util.net.proxy;

import java.util.Map;

public class ProxyTest {
	 public static void checkProxyIp(Map<String, Integer> proxyIpMap, String reqUrl) {
		 
         for (String proxyHost : proxyIpMap.keySet()) {
               Integer proxyPort = proxyIpMap.get(proxyHost);

               int statusCode = 0;
               try 
               {
                     HttpClient httpClient = new HttpClient();
                     httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);

                     // 连接超时时间（默认10秒 10000ms） 单位毫秒（ms）
                     int connectionTimeout = 10000;
                     // 读取数据超时时间（默认30秒 30000ms） 单位毫秒（ms）
                     int soTimeout = 30000;
                     httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
                     httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

                     HttpMethod method = new GetMethod(reqUrl);

                     statusCode = httpClient.executeMethod(method);
               } catch (Exception e) {
                   System.out.println("ip " + proxyHost + " is not aviable");
               }
               if(statusCode>0){
                    System.out.format("%s:%s-->%sn", proxyHost, proxyPort,statusCode);
               }
               
         }
   }
}
